package tfar.rcraft.menu.slot;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class FoodSlot extends SlotItemHandler {
	public FoodSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(@Nonnull ItemStack stack) {
		return super.isItemValid(stack) && (stack.getItem().isFood() || stack.getItem().isIn(Tags.Items.SEEDS));
	}
}
