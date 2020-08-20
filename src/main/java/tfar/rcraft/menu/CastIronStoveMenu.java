package tfar.rcraft.menu;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import tfar.rcraft.init.ModMenus;
import tfar.rcraft.menu.slot.FuelSlot;

public class CastIronStoveMenu extends Container {

	protected final ItemStackHandler handler;
	protected final IIntArray furnaceData;

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}

	public CastIronStoveMenu(int id, PlayerInventory playerInventoryIn) {
		this(id,playerInventoryIn,new ItemStackHandler(3),new IntArray(4));
	}

	public CastIronStoveMenu(int id, PlayerInventory playerInventoryIn, ItemStackHandler stackHandler,IIntArray iIntArray) {
		super(ModMenus.CAST_IRON_STOVE, id);
		handler = stackHandler;
		this.furnaceData = iIntArray;
		int i = -19;

		this.addSlot(new SlotItemHandler(handler, 0, 56, 17));
		this.addSlot(new FuelSlot(handler, 1, 56, 53));
		this.addSlot(new SlotItemHandler(handler, 2, 116, 35));

		for(int l = 0; l < 3; ++l) {
			for(int j1 = 0; j1 < 9; ++j1) {
				this.addSlot(new Slot(playerInventoryIn, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
			}
		}

		for(int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new Slot(playerInventoryIn, i1, 8 + i1 * 18, 161 + i));
		}
		this.trackIntArray(iIntArray);
	}

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	 * inventory and the other inventory(s).
	 */
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < 3 * 9) {
				if (!this.mergeItemStack(itemstack1, 3 * 9, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 3 * 9, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}

	public boolean isBurning() {
		return this.furnaceData.get(0) > 0;
	}

	public int getCookProgressionScaled() {
		int i = this.furnaceData.get(2);
		int j = this.furnaceData.get(3);
		return j != 0 && i != 0 ? (24 - (i * 24) / j) : 0;
	}

	public int getBurnLeftScaled() {
		int i = this.furnaceData.get(1);
		if (i == 0) {
			i = 200;
		}
		return this.furnaceData.get(0) * 13 / i;
	}

}
