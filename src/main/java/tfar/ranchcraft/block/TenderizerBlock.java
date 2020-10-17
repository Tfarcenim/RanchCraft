package tfar.ranchcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TenderizerBlock extends ContainerBlock {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public TenderizerBlock(Properties properties, Supplier<TileEntity> blockEntitySupplier) {
		super(properties,blockEntitySupplier);
		setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

}
