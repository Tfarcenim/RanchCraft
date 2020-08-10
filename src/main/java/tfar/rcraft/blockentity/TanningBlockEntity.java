package tfar.rcraft.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.ItemStackHandler;
import tfar.rcraft.init.ModItems;
import tfar.rcraft.item.IncubatingEggItem;

import javax.annotation.Nonnull;

import static tfar.rcraft.blockentity.IncubatorBlockEntity.INPUT;

public class TanningBlockEntity extends AbstractMachineBlockEntity {

	public TanningBlockEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public final ItemStackHandler handler = new ItemStackHandler(2) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			markDirty();
			needsRefresh = true;
		}

		@Override
		public int getSlotLimit(int slot) {
			return 1;
		}

		//todo expand this into a recipe system instead of hardcoding?
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			return stack.getItem() == ModItems.SOAKED_LEATHER && slot == INPUT && super.isItemValid(slot, stack);
		}
	};

	public void refresh() {
		if (!handler.getStackInSlot(INPUT).isEmpty()) {
			active = true;
			progress = 200;
		} else {
			active = false;
		}
	}

	public void process() {

	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {

		super.read(state, nbt);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {

		return super.write(compound);
	}
}
