package tfar.ranchcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tfar.ranchcraft.blockentity.TanningRackBlockEntity;
import tfar.ranchcraft.init.ModItems;

import javax.annotation.Nullable;

public class TanningRackBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public TanningRackBlock(Properties properties) {
		super(properties);
		setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		TanningRackBlockEntity tanningRackBlockEntity = (TanningRackBlockEntity)worldIn.getTileEntity(pos);
		ItemStack stack = player.getHeldItem(hand);
		if (player.getHeldItem(hand).isEmpty()) {
			if (!worldIn.isRemote) {
				//remove leather
				tanningRackBlockEntity.takeItem(player);
			}

			return ActionResultType.func_233537_a_(worldIn.isRemote);
		}

		//todo recipe system?
		if (player.getHeldItem(hand).getItem() == ModItems.SOAKED_LEATHER) {
			if (!worldIn.isRemote) {
				//add leather
				tanningRackBlockEntity.addItem(stack);
			}
			return ActionResultType.func_233537_a_(worldIn.isRemote);
		}

		else {
			ItemStack itemstack = player.getHeldItem(hand);
			return !itemstack.isEmpty() && !itemstack.getItem().isIn(ItemTags.LECTERN_BOOKS) ? ActionResultType.CONSUME : ActionResultType.PASS;
		}	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TanningRackBlockEntity();
	}
}
