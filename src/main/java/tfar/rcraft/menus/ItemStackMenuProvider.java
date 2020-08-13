package tfar.rcraft.menus;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

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
