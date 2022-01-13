package io.github.firetamer.dbb.modules.strong_block.blocks;

import io.github.firetamer.dbb.modules.strong_block.StrongBlockModule;
import io.github.firetamer.dbb.modules.strong_block.util.ColorsEnum;
import io.github.firetamer.dbb.modules.strong_block.util.CustomBlockstateProperties;
import io.github.firetamer.dbb.modules.strong_block.util.PaintMixerAnimationEnum;
import io.github.firetamer.dbb.modules.time_chamber.TimeChamberModule;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class PaintMixer extends HorizontalBlock {
    public static final EnumProperty<PaintMixerAnimationEnum> ANIMATION_ENUM = CustomBlockstateProperties.PAINT_MIXER_ANIMATION_STATE;

    public PaintMixer() {
        super(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK).noOcclusion());
        stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ANIMATION_ENUM, PaintMixerAnimationEnum.OFF);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, ANIMATION_ENUM);
    }

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
        return StrongBlockModule.PAINT_MIXER_TILE.create();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
