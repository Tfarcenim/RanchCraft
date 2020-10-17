package tfar.ranchcraft.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;

public class TannedSatchelItemStackHandler extends ItemStackItemStackHandler {
	public TannedSatchelItemStackHandler(int slots, ItemStack stack) {
		super(slots, stack);
	}

	@Override
	public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
		return super.isItemValid(slot, stack) && (stack.getItem().isFood() || stack.getItem().isIn(Tags.Items.SEEDS));
	}
}
