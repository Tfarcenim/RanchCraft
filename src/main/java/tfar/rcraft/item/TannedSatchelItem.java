package tfar.rcraft.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import tfar.rcraft.menus.ItemStackMenuProvider;

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
			INamedContainerProvider inamedcontainerprovider = new ItemStackMenuProvider(stack);
			playerIn.openContainer(inamedcontainerprovider);
			return ActionResult.resultConsume(stack);
		}
	}
}
