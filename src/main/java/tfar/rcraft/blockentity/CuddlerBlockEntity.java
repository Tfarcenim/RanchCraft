package tfar.rcraft.blockentity;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.INameable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.items.ItemStackHandler;
import tfar.rcraft.block.TenderizerBlock;
import tfar.rcraft.init.ModBlockEntities;
import tfar.rcraft.init.ModItems;
import tfar.rcraft.menus.AnglerMenu;
import tfar.rcraft.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CuddlerBlockEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider, INameable {

	protected ITextComponent customName;

	private static GameProfile PROFILE = new GameProfile(UUID.fromString("a42ac406-c797-4e0e-b147-f01ac5551be6"), "[MobGrinder]");


	protected boolean needsRefresh = true;


	public static final Method droploot;

	public static final Method dropSpecialItems;

	//todo use mixin instead
	static {
		//entity.dropLoot(source,true);
		droploot = ObfuscationReflectionHelper.findMethod(LivingEntity.class, "func_213354_a",
						DamageSource.class, boolean.class);
		//entity.dropSpecialItems(source, i, true);
		dropSpecialItems = ObfuscationReflectionHelper.findMethod(LivingEntity.class, "func_213333_a",
						DamageSource.class, int.class, boolean.class);
	}

	protected int processTime = 0;
	public boolean processing = false;

	public final ItemStackHandler handler = new ItemStackHandler(27) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			markDirty();
		}
	};

	public final ItemStackHandler malletHandler = new ItemStackHandler() {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			markDirty();
			needsRefresh = true;
		}

		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			return stack.getItem() == ModItems.MALLET && super.isItemValid(slot, stack);
		}
	};

	public CuddlerBlockEntity() {
		super(ModBlockEntities.TENDERIZER);
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (needsRefresh) {
				if (!malletHandler.getStackInSlot(0).isEmpty()) {
					processing = true;
					this.processTime = 200;
					needsRefresh = false;
				} else {
					processing = false;
				}
			}

			if (processing) {
				if (processTime > 0) {
					processTime--;
				} else {
					Direction dir = getBlockState().get(TenderizerBlock.FACING);

					AxisAlignedBB axisAlignedBB = new AxisAlignedBB(pos.offset(dir)).grow(1,1,1);

					List<AnimalEntity> animals = world.getEntitiesWithinAABB(AnimalEntity.class,axisAlignedBB,animalEntity -> !animalEntity.isChild());
					ItemStack mallet = malletHandler.getStackInSlot(0);

					for (AnimalEntity animal : animals) {
						FakePlayer fakePlayer = FakePlayerFactory.get((ServerWorld) world, PROFILE);
						fakePlayer.setHeldItem(Hand.MAIN_HAND, handler.getStackInSlot(1));

						DamageSource source = DamageSource.causePlayerDamage(fakePlayer);

						ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, animal, fakePlayer, "field_70717_bb");

						animal.captureDrops(new ArrayList<>());
						int lootingLevel = ForgeHooks.getLootingLevel(animal, fakePlayer, source);

						try {
							//entity.dropLoot(source,true);
							droploot.invoke(animal, source, true);
							//entity.dropSpecialItems(source, lootingLevel, true);
							dropSpecialItems.invoke(animal, source, lootingLevel, true);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}

						Collection<ItemEntity> mobDrops = animal.captureDrops(null);
						LivingDropsEvent event = new LivingDropsEvent(animal, source, mobDrops, lootingLevel, true);
						if (!MinecraftForge.EVENT_BUS.post(event)) {
							List<ItemStack> stacks = event.getDrops().stream()
											.map(ItemEntity::getItem)
											.collect(Collectors.toList());

							for (int i = 0; i < handler.getSlots(); i++) {
								for (int i1 = 0; i1 < stacks.size(); i1++) {
									ItemStack stack = stacks.get(i1);
									if (stack.isEmpty())continue;
									ItemStack leftovers = handler.insertItem(i, stack, false);
									stacks.set(i1,leftovers);
								}
							}
						}
						animal.setChild(true);
					}
					if (!animals.isEmpty()) {
						mallet.setDamage(mallet.getDamage()+1);
					}
					needsRefresh = true;
				}
			}
		}
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		handler.deserializeNBT(nbt.getCompound(Constants.INV));
		malletHandler.deserializeNBT(nbt.getCompound("fishing_rod"));
		super.read(state, nbt);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put(Constants.INV,handler.serializeNBT());
		compound.put("fishing_rod", malletHandler.serializeNBT());
		return super.write(compound);
	}

	public void setCustomName(ITextComponent text) {
		this.customName = text;
	}

	@Override
	public ITextComponent getName() {
		return customName != null ? customName : getDefaultName();
	}

	ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.rcraft.angler");
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	@Nullable
	@Override
	public ITextComponent getCustomName() {
		return customName;
	}

	@Nullable
	@Override
	public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
		return new AnglerMenu(p_createMenu_1_,p_createMenu_2_,handler, malletHandler);
	}
}
