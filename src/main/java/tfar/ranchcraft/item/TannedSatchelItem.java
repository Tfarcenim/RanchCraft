package tfar.ranchcraft.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import tfar.ranchcraft.menu.TannedSatchelMenuProvider;

public class TannedSatchelItem extends Item {
	public TannedSatchelItem(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (worldIn.isRemote) {
			return ActionResult.resultSuccess(stack);
		} else {
			INamedContainerProvider inamedcontainerprovider = new TannedSatchelMenuProvider(stack);
			playerIn.openContainer(inamedcontainerprovider);
			return ActionResult.resultConsume(stack);
		}
	}
}
