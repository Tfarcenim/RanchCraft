package tfar.rcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

import java.util.function.Supplier;

public class CastIronStoveBlock extends ContainerBlock {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public CastIronStoveBlock(Properties properties, Supplier<TileEntity> blockEntitySupplier) {
		super(properties, blockEntitySupplier);
		setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(LIT,false));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING,LIT);
	}
}
