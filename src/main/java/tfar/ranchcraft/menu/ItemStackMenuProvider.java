package tfar.ranchcraft.menu;

import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public abstract class ItemStackMenuProvider implements INamedContainerProvider {

	protected final ItemStack stack;

	public ItemStackMenuProvider(ItemStack stack) {
		this.stack = stack;
	}

	@Override
	public ITextComponent getDisplayName() {
		return stack.getDisplayName();
	}

}
