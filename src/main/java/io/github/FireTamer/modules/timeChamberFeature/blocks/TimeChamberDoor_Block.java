package io.github.FireTamer.modules.timeChamberFeature.blocks;

import io.github.FireTamer.modules.timeChamberFeature.TimeChamberModule;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TimeChamberDoor_Block extends HorizontalBlock {
    public static final BooleanProperty IS_OPEN = BooleanProperty.create("is_open");

    public TimeChamberDoor_Block() {
        super(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK).noOcclusion());
        stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(IS_OPEN, false);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) {
        world.setBlockAndUpdate(pos, state.cycle(IS_OPEN));

        return ActionResultType.SUCCESS;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, IS_OPEN);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        Direction direction = context.getHorizontalDirection().getOpposite();
        BlockPos blockpos = context.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction);

        return context.getLevel().getBlockState(blockpos1).canBeReplaced(context) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TimeChamberModule.TIME_CHAMBER_DOOR_TILE.create();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
