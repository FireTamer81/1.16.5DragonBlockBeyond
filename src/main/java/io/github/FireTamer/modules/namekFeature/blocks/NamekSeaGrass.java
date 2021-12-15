package io.github.FireTamer.modules.namekFeature.blocks;

import io.github.FireTamer.modules.namekFeature.NamekModule;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class NamekSeaGrass extends BushBlock implements IGrowable, ILiquidContainer, net.minecraftforge.common.IForgeShearable
{
    public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public NamekSeaGrass(Properties properties)
    {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    protected boolean mayPlaceOn (BlockState state, IBlockReader reader, BlockPos pos)
    {
        return state.isFaceSturdy(reader, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return fluidstate.getType() == NamekModule.NAMEK_FLUID_FLOWING.get() || fluidstate.getType() == NamekModule.NAMEK_FLUID_SOURCE.get() &&
                fluidstate.getAmount() == 8 ? super.getStateForPlacement(context) : null;
    }

    public BlockState updateShape(BlockState state1, Direction direction, BlockState state2, IWorld world, BlockPos pos1, BlockPos pos2)
    {
        BlockState blockstate = super.updateShape(state1, direction, state2, world, pos1, pos2);

        if (!blockstate.isAir())
        {
            //This might be a point of concern if the block doesn't work as intended
            world.getLiquidTicks().scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return blockstate;
    }

    public FluidState getFluidState(BlockState state) { return NamekModule.NAMEK_FLUID_SOURCE.get().getSource(false); }

    public boolean canPlaceLiquid(IBlockReader reader, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    public boolean placeLiquid(IWorld world, BlockPos pos, BlockState state, FluidState fluid) {
        return false;
    }

    public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pos, BlockState state, boolean bool) { return true; }

    public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state)
    {
        return true;
    }

    public void performBonemeal(ServerWorld server, Random rand, BlockPos pos, BlockState state)
    {
        BlockState blockstate = NamekModule.NAMEK_TALL_SEAGRASS.defaultBlockState();
        BlockState blockstate1 = blockstate.setValue(TallSeaGrassBlock.HALF, DoubleBlockHalf.UPPER);
        BlockPos blockpos = pos.above();
        if (server.getBlockState(blockpos).is(NamekModule.NAMEK_FLUID_BLOCK)) {
            server.setBlock(pos, blockstate, 2);
            server.setBlock(blockpos, blockstate1, 2);
        }
    }
}
