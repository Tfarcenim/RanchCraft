package tfar.ranchcraft.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.INameable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemStackHandler;
import tfar.ranchcraft.init.ModBlockEntities;
import tfar.ranchcraft.item.FlyRodItem;
import tfar.ranchcraft.menu.AnglerMenu;
import tfar.ranchcraft.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class AnglerBlockEntity extends AbstractMachineBlockEntity implements INamedContainerProvider, INameable {

	public final ItemStackHandler handler = new ItemStackHandler(27) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			markDirty();
		}
	};

	public final ItemStackHandler rodHandler = new ItemStackHandler() {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			markDirty();
			needsRefresh = true;
		}

		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			return stack.getItem() instanceof FishingRodItem && super.isItemValid(slot, stack);
		}
	};

	public AnglerBlockEntity() {
		super(ModBlockEntities.ANGLER);
	}

	@Override
	public void refresh() {
		if (!rodHandler.getStackInSlot(0).isEmpty()) {
			ItemStack fishingRod = rodHandler.getStackInSlot(0);
			active = true;
			this.progress = MathHelper.nextInt(world.rand, 100, fishingRod.getItem() instanceof FlyRodItem ? 500 : 600);
			this.progress -= EnchantmentHelper.getEnchantmentLevel(Enchantments.LURE, fishingRod) * 100;
			needsRefresh = false;
		} else {
			active = false;
		}
	}

	@Override
	public void process() {
		ItemStack fishingRod = rodHandler.getStackInSlot(0);
		LootContext.Builder builder = (new LootContext.Builder((ServerWorld)this.world))
						.withParameter(LootParameters.TOOL, fishingRod).withRandom(world.rand)
						.withParameter(LootParameters.field_237457_g_, new Vector3d(pos.getX(),pos.getY(),pos.getZ()))
						.withLuck((float)EnchantmentHelper.getFishingLuckBonus(fishingRod) + /*this.owner.getLuck()*/ 0);
		LootTable lootTable = this.world.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
		List<ItemStack> list = lootTable.generate(builder.build(LootParameterSets.FISHING));
		for (int i = 0; i < handler.getSlots(); i++) {
			for (int i1 = 0; i1 < list.size(); i1++) {
				ItemStack stack = list.get(i1);
				if (stack.isEmpty())continue;
				ItemStack leftovers = handler.insertItem(i, stack, false);
				list.set(i1,leftovers);
			}
		}
		fishingRod.setDamage(fishingRod.getDamage()+1);
		needsRefresh = true;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		handler.deserializeNBT(nbt.getCompound(Constants.INV));
		rodHandler.deserializeNBT(nbt.getCompound("fishing_rod"));
		super.read(state, nbt);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put(Constants.INV,handler.serializeNBT());
		compound.put("fishing_rod",rodHandler.serializeNBT());
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
		return new AnglerMenu(p_createMenu_1_,p_createMenu_2_,handler,rodHandler);
	}
}
