package io.github.firetamer.dbb.modules.strong_block.blocks.paint_mixer;

import io.github.firetamer.dbb.modules.strong_block.StrongBlockModule;
import io.github.firetamer.dbb.modules.strong_block.blocks.paint_mixer.shape_filler.PaintMixerShapeFiller;
import io.github.firetamer.dbb.modules.strong_block.util.CustomBlockstateProperties;
import io.github.firetamer.dbb.modules.strong_block.util.PaintMixerAnimationEnum;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

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

    @Override
    public void onPlace(BlockState pState, World pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (pState.getValue(FACING).equals(Direction.NORTH)) {
            Direction d = Direction.NORTH;

            addFillerBlocks(pLevel, pPos.north(), 1, d);
            addFillerBlocks(pLevel, pPos.north().west(), 2, d);
            addFillerBlocks(pLevel, pPos.west(), 3, d);
            addFillerBlocks(pLevel, pPos.east(), 4, d);
            addFillerBlocks(pLevel, pPos.east(2), 5, d);
            addFillerBlocks(pLevel, pPos.east().north(), 6, d);
            addFillerBlocks(pLevel, pPos.south(), 7, d);
            addFillerBlocks(pLevel, pPos.south().west(), 8, d);
            addFillerBlocks(pLevel, pPos.south().east(), 9, d);
            addFillerBlocks(pLevel, pPos.above().east(), 10, d);
            addFillerBlocks(pLevel, pPos.above().west(), 11, d);
            addFillerBlocks(pLevel, pPos.above().south().west(), 12, d);
            addFillerBlocks(pLevel, pPos.above().south().east(), 13, d);
            addFillerBlocks(pLevel, pPos.above().south(), 14, d);
            addFillerBlocks(pLevel, pPos.above().north(), 15, d);
            addFillerBlocks(pLevel, pPos.above(), 16, d);
            addFillerBlocks(pLevel, pPos.above().north().west(), 17, d);
            addFillerBlocks(pLevel, pPos.above().north().east(), 18, d);

        } else if (pState.getValue(FACING).equals(Direction.EAST)) {
            Direction d = Direction.EAST;

            addFillerBlocks(pLevel, pPos.east(), 1, d);
            addFillerBlocks(pLevel, pPos.east().north(), 2, d);
            addFillerBlocks(pLevel, pPos.north(), 3, d);
            addFillerBlocks(pLevel, pPos.south(), 4, d);
            addFillerBlocks(pLevel, pPos.south(2), 5, d);
            addFillerBlocks(pLevel, pPos.south().east(), 6, d);
            addFillerBlocks(pLevel, pPos.west(), 7, d);
            addFillerBlocks(pLevel, pPos.west().north(), 8, d);
            addFillerBlocks(pLevel, pPos.west().south(), 9, d);
            addFillerBlocks(pLevel, pPos.above().south(), 10, d);
            addFillerBlocks(pLevel, pPos.above().north(), 11, d);
            addFillerBlocks(pLevel, pPos.above().west().north(), 12, d);
            addFillerBlocks(pLevel, pPos.above().west().south(), 13, d);
            addFillerBlocks(pLevel, pPos.above().west(), 14, d);
            addFillerBlocks(pLevel, pPos.above().east(), 15, d);
            addFillerBlocks(pLevel, pPos.above(), 16, d);
            addFillerBlocks(pLevel, pPos.above().east().north(), 17, d);
            addFillerBlocks(pLevel, pPos.above().east().south(), 18, d);

        } else if (pState.getValue(FACING).equals(Direction.SOUTH)) {
            Direction d = Direction.SOUTH;

            addFillerBlocks(pLevel, pPos.south(), 1, d);
            addFillerBlocks(pLevel, pPos.south().east(), 2, d);
            addFillerBlocks(pLevel, pPos.east(), 3, d);
            addFillerBlocks(pLevel, pPos.west(), 4, d);
            addFillerBlocks(pLevel, pPos.west(2), 5, d);
            addFillerBlocks(pLevel, pPos.west().south(), 6, d);
            addFillerBlocks(pLevel, pPos.north(), 7, d);
            addFillerBlocks(pLevel, pPos.north().east(), 8, d);
            addFillerBlocks(pLevel, pPos.north().west(), 9, d);
            addFillerBlocks(pLevel, pPos.above().west(), 10, d);
            addFillerBlocks(pLevel, pPos.above().east(), 11, d);
            addFillerBlocks(pLevel, pPos.above().north().east(), 12, d);
            addFillerBlocks(pLevel, pPos.above().north().west(), 13, d);
            addFillerBlocks(pLevel, pPos.above().north(), 14, d);
            addFillerBlocks(pLevel, pPos.above().south(), 15, d);
            addFillerBlocks(pLevel, pPos.above(), 16, d);
            addFillerBlocks(pLevel, pPos.above().south().east(), 17, d);
            addFillerBlocks(pLevel, pPos.above().south().west(), 18, d);

        } else if (pState.getValue(FACING).equals(Direction.WEST)) {
            Direction d = Direction.WEST;

            addFillerBlocks(pLevel, pPos.west(), 1, d);
            addFillerBlocks(pLevel, pPos.west().south(), 2, d);
            addFillerBlocks(pLevel, pPos.south(), 3, d);
            addFillerBlocks(pLevel, pPos.north(), 4, d);
            addFillerBlocks(pLevel, pPos.north(2), 5, d);
            addFillerBlocks(pLevel, pPos.north().west(), 6, d);
            addFillerBlocks(pLevel, pPos.east(), 7, d);
            addFillerBlocks(pLevel, pPos.east().south(), 8, d);
            addFillerBlocks(pLevel, pPos.east().north(), 9, d);
            addFillerBlocks(pLevel, pPos.above().north(), 10, d);
            addFillerBlocks(pLevel, pPos.above().south(), 11, d);
            addFillerBlocks(pLevel, pPos.above().east().south(), 12, d);
            addFillerBlocks(pLevel, pPos.above().east().north(), 13, d);
            addFillerBlocks(pLevel, pPos.above().east(), 14, d);
            addFillerBlocks(pLevel, pPos.above().west(), 15, d);
            addFillerBlocks(pLevel, pPos.above(), 16, d);
            addFillerBlocks(pLevel, pPos.above().west().south(), 17, d);
            addFillerBlocks(pLevel, pPos.above().west().north(), 18, d);

        }
    }

    public static void addFillerBlocks(World pLevel, BlockPos pos, int modelShapeIndex, Direction rotation) {
        pLevel.setBlockAndUpdate(pos, StrongBlockModule.PAINT_MIXER_SHAPE_FILLER.defaultBlockState()
                .setValue(PaintMixerShapeFiller.SHAPE_SELECTION, modelShapeIndex).setValue(FACING, rotation));
    }

    @Override
    public void onRemove(BlockState pState, World pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        removeFillerBlocks(pState, pLevel, pPos);
    }

    public static void removeFillerBlocks(BlockState pState, World pLevel, BlockPos pos) {
        pLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.north(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.north().east(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.east(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.east().south(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.south(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.south().west(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.west(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.west().north(), Blocks.AIR.defaultBlockState());

        pLevel.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.above().north(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.above().north().east(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.above().east(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.above().east().south(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.above().south(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.above().south().west(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.above().west(), Blocks.AIR.defaultBlockState());
        pLevel.setBlockAndUpdate(pos.above().west().north(), Blocks.AIR.defaultBlockState());

        if (pState.getValue(FACING).equals(Direction.NORTH)) {
            //Minecraft.getInstance().player.displayClientMessage(new StringTextComponent("Hello"), true);
            pLevel.setBlockAndUpdate(pos.east(2), Blocks.AIR.defaultBlockState());
        } else if (pState.getValue(FACING).equals(Direction.EAST)) {
            //Minecraft.getInstance().player.displayClientMessage(new StringTextComponent("Hello"), true);
            pLevel.setBlockAndUpdate(pos.south(2), Blocks.AIR.defaultBlockState());
        } else if (pState.getValue(FACING).equals(Direction.SOUTH)) {
            //Minecraft.getInstance().player.displayClientMessage(new StringTextComponent("Hello"), true);
            pLevel.setBlockAndUpdate(pos.west(2), Blocks.AIR.defaultBlockState());
        } else if (pState.getValue(FACING).equals(Direction.WEST)) {
            //Minecraft.getInstance().player.displayClientMessage(new StringTextComponent("Hello"), true);
            pLevel.setBlockAndUpdate(pos.north(2), Blocks.AIR.defaultBlockState());
        }
    }
}
