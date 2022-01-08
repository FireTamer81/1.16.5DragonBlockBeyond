package io.github.firetamer.dbb.modules.time_chamber.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TimeChamberPortalBlock extends HorizontalBlock {

    //Voxel Shapes
    protected static final VoxelShape N_COLLISION_SHAPE = Block.box(0, 0, 3, 16, 16, 16);
    protected static final VoxelShape E_COLLISION_SHAPE = Block.box(0, 0, 0, 13, 16, 16);
    protected static final VoxelShape S_COLLISION_SHAPE = Block.box(0, 0, 0, 16, 16, 13);
    protected static final VoxelShape W_COLLISION_SHAPE = Block.box(3, 0, 0, 16, 16, 16);


    public TimeChamberPortalBlock() {
        super(AbstractBlock.Properties.copy(Blocks.NETHERITE_BLOCK));
        stateDefinition.any().setValue(FACING, Direction.NORTH);
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext selectionContext) {
        switch ((Direction)state.getValue(FACING))
        {
            case SOUTH:
                return S_COLLISION_SHAPE;
            default:
                return N_COLLISION_SHAPE;
            case WEST:
                return W_COLLISION_SHAPE;
            case EAST:
                return E_COLLISION_SHAPE;
        }
    }

    public VoxelShape getBlockSupportShape(BlockState p_230335_1_, IBlockReader p_230335_2_, BlockPos p_230335_3_) {
        return VoxelShapes.block();
    }

    //Right now this just damages the player inside the "BlockSupportShape" and colliding with the "CollisionShape"
    //But, once I figure out how to manipulate transporting the player from dimension to dimension, I'll put that here.
    public void entityInside(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
        p_196262_4_.hurt(DamageSource.CACTUS, 1.0F);
    }

    /**
     * @Override
     *        public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
     *    {
     * 		//return SHAPES.get(this).get(state.getValue(HORIZONTAL_FACING));
     *
     * 		switch((Direction)state.getValue(FACING))
     *        {
     * 		case SOUTH:
     * 			return SOUTH_SHAPE;
     * 		default:
     * 			return NORTH_SHAPE;
     * 		case WEST:
     * 			return WEST_SHAPE;
     * 		case EAST:
     * 			return EAST_SHAPE;
     *        }
     *    }
     */

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        Direction direction = context.getHorizontalDirection().getOpposite();
        BlockPos blockpos = context.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction);

        return context.getLevel().getBlockState(blockpos1).canBeReplaced(context) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateContainer)
    {
        stateContainer.add(FACING);
    }
}
