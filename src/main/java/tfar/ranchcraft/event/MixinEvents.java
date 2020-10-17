package tfar.ranchcraft.event;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tfar.ranchcraft.init.ModItems;

import java.util.List;

public class MixinEvents {

	public static void wetLeather(ItemEntity itemEntity) {
		World world = itemEntity.world;
		itemEntity.remove();
		world.addEntity(new ItemEntity(world,itemEntity.getPosX(),itemEntity.getPosY(),itemEntity.getPosZ(),new ItemStack(ModItems.SOAKED_LEATHER,itemEntity.getItem().getCount())));
	}

	public static void onBlockDrops(BlockState state, ServerWorld worldIn, BlockPos pos, TileEntity tileEntityIn, Entity entityIn, ItemStack stack, List<ItemStack> returnValue) {
		if (state.getBlock() == Blocks.GRASS && worldIn.rand.nextDouble() < .1) {
			returnValue.add(new ItemStack(ModItems.CORN_SEEDS));
		}
		if (state.getBlock() == Blocks.GRASS && worldIn.rand.nextDouble() < .1) {
			returnValue.add(new ItemStack(ModItems.CORN_SEEDS));
		}
	}
}
