package tfar.rcraft.menus;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class TannedSatchelMenuProvider extends ItemStackMenuProvider {

	public TannedSatchelMenuProvider(ItemStack stack) {
		super(stack);
	}

	@Nullable
	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity p_createMenu_3_) {
		return new TannedSatchelMenu(id,inv);
	}
}
