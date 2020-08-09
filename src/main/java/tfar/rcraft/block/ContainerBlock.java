package tfar.rcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ContainerBlock extends Block {

	protected final Supplier<TileEntity> blockEntitySupplier;

	public ContainerBlock(Properties properties, Supplier<TileEntity> blockEntitySupplier) {
		super(properties);
		this.blockEntitySupplier = blockEntitySupplier;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			INamedContainerProvider inamedcontainerprovider = this.getContainer(state, worldIn, pos);
			if (inamedcontainerprovider != null) {
				player.openContainer(inamedcontainerprovider);
				//player.addStat(this.getOpenStat());
			}
			return ActionResultType.CONSUME;
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public final TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return blockEntitySupplier.get();
	}

	@Nullable
	@Override
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
		return (INamedContainerProvider) worldIn.getTileEntity(pos);
	}
}
