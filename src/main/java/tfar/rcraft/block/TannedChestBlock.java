package tfar.rcraft.block;

import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tfar.rcraft.blockentity.TannedChestBlockEntity;
import tfar.rcraft.init.ModBlockEntities;

import javax.annotation.Nullable;

public class TannedChestBlock extends AbstractChestBlock<TannedChestBlockEntity> {

	public TannedChestBlock(Properties builder) {
		super(builder, () -> ModBlockEntities.TANNED_CHEST);
	}

	@Override
	public TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity> combine(BlockState state, World world, BlockPos pos, boolean p_225536_4_) {
		return TileEntityMerger.ICallback::func_225537_b_;
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new TannedChestBlockEntity();
	}
}
