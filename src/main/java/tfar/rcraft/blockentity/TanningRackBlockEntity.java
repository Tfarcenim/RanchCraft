package tfar.rcraft.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.ItemStackHandler;
import tfar.rcraft.init.ModBlockEntities;
import tfar.rcraft.init.ModItems;
import tfar.rcraft.item.IncubatingEggItem;
import tfar.rcraft.util.Constants;

import javax.annotation.Nonnull;

import static tfar.rcraft.blockentity.IncubatorBlockEntity.INPUT;
import static tfar.rcraft.blockentity.IncubatorBlockEntity.OUTPUT;

public class TanningRackBlockEntity extends AbstractMachineBlockEntity {

	public TanningRackBlockEntity() {
		super(ModBlockEntities.TANNING_RACK);
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

	//todo recipe system?
	public void process() {
		handler.setStackInSlot(INPUT,ItemStack.EMPTY);
		handler.setStackInSlot(OUTPUT,new ItemStack(ModItems.TANNED_LEATHER));
	}

	public void addItem(ItemStack stack) {
		if (handler.getStackInSlot(OUTPUT).isEmpty()) {
			handler.setStackInSlot(INPUT,stack.copy());
			stack.shrink(1);
		}
	}


	public void takeItem(PlayerEntity player) {
		if (!handler.getStackInSlot(OUTPUT).isEmpty()) {
			ItemStack stack = handler.getStackInSlot(OUTPUT).copy();
			if (!player.addItemStackToInventory(stack)) {
				world.addEntity(new ItemEntity(world,pos.getX(),pos.getY(),pos.getZ(),stack));
			}
			handler.setStackInSlot(OUTPUT,ItemStack.EMPTY);
		}
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		handler.deserializeNBT(nbt.getCompound(Constants.INV));
		super.read(state, nbt);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put(Constants.INV, handler.serializeNBT());
		return super.write(compound);
	}
}
