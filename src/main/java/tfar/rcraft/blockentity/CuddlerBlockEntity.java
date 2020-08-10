package tfar.rcraft.blockentity;

import net.minecraft.block.BlockState;
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
import net.minecraft.util.Hand;
import net.minecraft.util.INameable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.items.ItemStackHandler;
import tfar.rcraft.init.ModBlockEntities;
import tfar.rcraft.init.ModItems;
import tfar.rcraft.menus.AnglerMenu;
import tfar.rcraft.menus.CuddlerMenu;
import tfar.rcraft.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static tfar.rcraft.blockentity.TenderizerBlockEntity.PROFILE;

public class CuddlerBlockEntity extends AbstractMachineBlockEntity implements INamedContainerProvider, INameable {

	protected ITextComponent customName;

	public final ItemStackHandler feedHandler = new ItemStackHandler() {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			markDirty();
			needsRefresh = true;
		}
	};

	public CuddlerBlockEntity() {
		super(ModBlockEntities.CUDDLER);
	}

	@Override
	public void refresh() {
		if (feedHandler.getStackInSlot(0).getCount() > 1) {
			active = true;
			this.progress = 200;
		} else {
			active = false;
		}
	}

	@Override
	public void process() {
				AxisAlignedBB axisAlignedBB = new AxisAlignedBB(pos).grow(2,2,2);

				List<AnimalEntity> animals = world.getEntitiesWithinAABB(AnimalEntity.class,axisAlignedBB,animalEntity -> !animalEntity.isChild() && animalEntity.canBreed());
				if (animals.size() > 78)return;

				ItemStack feed = feedHandler.getStackInSlot(0);

				int index = -1;
				int index2 = -1;
				for (int i = 0; i < animals.size(); i++) {
					AnimalEntity animal = animals.get(i);
					for (int j = 0; j < animals.size();j++) {
						if (i == j)continue;
						AnimalEntity other = animals.get(j);
						if (other.getType() == animal.getType()) {
							index = i;
							index2 = j;
							break;
						}
					}
				}
				if (index != -1 && index2 != -1) {

					FakePlayer fakePlayer = FakePlayerFactory.get((ServerWorld) world, PROFILE);
					fakePlayer.setHeldItem(Hand.MAIN_HAND, feed);

					animals.get(index).func_230254_b_(fakePlayer, Hand.MAIN_HAND);
					animals.get(index2).func_230254_b_(fakePlayer, Hand.MAIN_HAND);
				}
				needsRefresh = true;
			}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		feedHandler.deserializeNBT(nbt.getCompound(Constants.INV));
		super.read(state, nbt);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put(Constants.INV, feedHandler.serializeNBT());
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
		return new CuddlerMenu(p_createMenu_1_,p_createMenu_2_, feedHandler);
	}
}
