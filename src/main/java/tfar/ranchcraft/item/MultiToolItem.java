package tfar.ranchcraft.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tfar.ranchcraft.block.ContainerBlock;

public class MultiToolItem extends Item {
	public MultiToolItem(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity playerentity = context.getPlayer();
		World world = context.getWorld();
		if (!world.isRemote && playerentity != null) {
			BlockPos blockpos = context.getPos();
			this.handleClick(playerentity, world.getBlockState(blockpos), world, blockpos, true, context.getItem());
		}


		return ActionResultType.func_233537_a_(world.isRemote);
	}

	private void handleClick(PlayerEntity player, BlockState blockState, World world, BlockPos blockpos, boolean b, ItemStack item) {
		if (blockState.getBlock() instanceof ContainerBlock) {
			breakMachine(blockpos,player,world);
		}
	}

	public void breakMachine(BlockPos pos,PlayerEntity player,World world) {
		world.destroyBlock(pos,true,player);
	}
}
