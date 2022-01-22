package io.github.firetamer.dbb.modules.strong_block.blocks.paint_mixer.shape_filler;

import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.stream.Stream;

public class PaintMixerShapeFiller extends HorizontalBlock {
    public static final VoxelShape BOTTOM_FRONT_CENTER = Block.box(0, 0, 13, 16, 16, 16);
    public static final VoxelShape BOTTOM_FRONT_CENTER_EAST = Block.box(0, 0, 0, 3, 16, 16);
    public static final VoxelShape BOTTOM_FRONT_CENTER_SOUTH = Block.box(0, 0, 0, 16, 16, 3);
    public static final VoxelShape BOTTOM_FRONT_CENTER_WEST = Block.box(13, 0, 0, 16, 16, 16);
    public static final VoxelShape BOTTOM_FRONT_RIGHT = Block.box(11.75, 0, 13, 16, 16, 16);
    public static final VoxelShape BOTTOM_FRONT_RIGHT_EAST = Block.box(0, 0, 11.75, 3, 16, 16);
    public static final VoxelShape BOTTOM_FRONT_RIGHT_SOUTH = Block.box(0, 0, 0, 4.25, 16, 3);
    public static final VoxelShape BOTTOM_FRONT_RIGHT_WEST = Block.box(13, 0, 0, 16, 16, 4.25);
    public static final VoxelShape BOTTOM_RIGHT = Block.box(11, 0, 0, 16, 16, 16);
    public static final VoxelShape BOTTOM_RIGHT_EAST = Block.box(0, 0, 11, 16, 16, 16);
    public static final VoxelShape BOTTOM_RIGHT_SOUTH = Block.box(0, 0, 0, 5, 16, 16);
    public static final VoxelShape BOTTOM_RIGHT_WEST = Block.box(0, 0, 0, 16, 16, 5);
    public static final VoxelShape BOTTOM_LEFT = Stream.of(Block.box(0, 0, 0, 5, 16, 16), Block.box(5, 0, 0, 10, 16, 16), Block.box(10, 11, 0, 12, 16, 2), Block.box(10, 0, 0, 14, 12, 15), Block.box(14, 0, 0, 16, 12, 12)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_LEFT_EAST = Stream.of(Block.box(0, 0, 0, 16, 16, 5), Block.box(0, 0, 5, 16, 16, 10), Block.box(14, 11, 10, 16, 16, 12), Block.box(1, 0, 10, 16, 12, 14), Block.box(4, 0, 14, 16, 12, 16)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_LEFT_SOUTH = Stream.of(Block.box(11, 0, 0, 16, 16, 16),Block.box(6, 0, 0, 11, 16, 16),Block.box(4, 11, 14, 6, 16, 16),Block.box(2, 0, 1, 6, 12, 16),Block.box(0, 0, 4, 2, 12, 16)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_LEFT_WEST = Stream.of(Block.box(0, 0, 11, 16, 16, 16),Block.box(0, 0, 6, 16, 16, 11),Block.box(0, 11, 4, 2, 16, 6),Block.box(0, 0, 2, 15, 12, 6),Block.box(0, 0, 0, 12, 12, 2)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_LEFT_2 = Stream.of(Block.box(0, 15, 7, 2.25, 16, 9), Block.box(0, 11.5, 7, 3, 15, 9), Block.box(0, 0, 10.5, 5, 11.5, 12.5), Block.box(0, 0, 3.5, 5, 11.5, 5.5), Block.box(0, 0, 5.5, 6.5, 11.5, 10.5)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_LEFT_2_EAST = Stream.of(Block.box(5.5, 0, 0, 10.5, 11.5, 6.5),Block.box(10.5, 0, 0, 12.5, 11.5, 5),Block.box(3.5, 0, 0, 5.5, 11.5, 5),Block.box(7, 11.5, 0, 9, 15, 3),Block.box(7, 15, 0, 9, 16, 2.25)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_LEFT_2_SOUTH = Stream.of(Block.box(9.5, 0, 5.5, 16, 11.5, 10.5),Block.box(11, 0, 10.5, 16, 11.5, 12.5),Block.box(11, 0, 3.5, 16, 11.5, 5.5),Block.box(13, 11.5, 7, 16, 15, 9),Block.box(13.75, 15, 7, 16, 16, 9)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_LEFT_2_WEST = Stream.of(Block.box(5.5, 0, 9.5, 10.5, 11.5, 16),Block.box(3.5, 0, 11, 5.5, 11.5, 16),Block.box(10.5, 0, 11, 12.5, 11.5, 16),Block.box(7, 11.5, 13, 9, 15, 16),Block.box(7, 15, 13.75, 9, 16, 16)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_FRONT_LEFT = Stream.of(Block.box(0, 0, 13, 4.25, 16, 16), Block.box(6.5, 0, 14, 15.5, 11.5, 16), Block.box(7.5, 0, 12.5, 14.5, 11.5, 14)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_FRONT_LEFT_EAST = Stream.of(Block.box(0, 0, 0, 3, 16, 4.25),Block.box(0, 0, 6.5, 2, 11.5, 15.5),Block.box(2, 0, 7.5, 3.5, 11.5, 14.5)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_FRONT_LEFT_SOUTH = Stream.of(Block.box(11.75, 0, 0, 16, 16, 3),Block.box(0.5, 0, 0, 9.5, 11.5, 2),Block.box(1.5, 0, 2, 8.5, 11.5, 3.5)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_FRONT_LEFT_WEST = Stream.of(Block.box(13, 0, 11.75, 16, 16, 16),Block.box(14, 0, 0.5, 16, 11.5, 9.5),Block.box(12.5, 0, 1.5, 14, 11.5, 8.5)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape BOTTOM_BACK = Block.box(0, 0, 0, 16, 16, 1);
    public static final VoxelShape BOTTOM_BACK_EAST = Block.box(15, 0, 0, 16, 16, 16);
    public static final VoxelShape BOTTOM_BACK_SOUTH = Block.box(0, 0, 15, 16, 16, 16);
    public static final VoxelShape BOTTOM_BACK_WEST = Block.box(0, 0, 0, 1, 16, 16);
    public static final VoxelShape BOTTOM_BACK_RIGHT = VoxelShapes.join(Block.box(8.5, 0, 0, 16, 8, 1), Block.box(9.25, 8, 0, 16, 16, 1), IBooleanFunction.OR);
    public static final VoxelShape BOTTOM_BACK_RIGHT_EAST = VoxelShapes.join(Block.box(15, 0, 8.5, 16, 8, 16), Block.box(15, 8, 9.25, 16, 16, 16), IBooleanFunction.OR);
    public static final VoxelShape BOTTOM_BACK_RIGHT_SOUTH = VoxelShapes.join(Block.box(0, 0, 15, 7.5, 8, 16), Block.box(0, 8, 15, 6.75, 16, 16), IBooleanFunction.OR);
    public static final VoxelShape BOTTOM_BACK_RIGHT_WEST = VoxelShapes.join(Block.box(0, 0, 0, 1, 8, 7.5), Block.box(0, 8, 0, 1, 16, 6.75), IBooleanFunction.OR);
    public static final VoxelShape BOTTOM_BACK_LEFT = VoxelShapes.join(Block.box(0, 0, 0, 7.5, 8, 1), Block.box(0, 8, 0, 6.75, 16, 1), IBooleanFunction.OR);
    public static final VoxelShape BOTTOM_BACK_LEFT_EAST = VoxelShapes.join(Block.box(15, 0, 0, 16, 8, 7.5), Block.box(15, 8, 0, 16, 16, 6.75), IBooleanFunction.OR);
    public static final VoxelShape BOTTOM_BACK_LEFT_SOUTH = VoxelShapes.join(Block.box(8.5, 0, 15, 16, 8, 16), Block.box(9.25, 8, 15, 16, 16, 16), IBooleanFunction.OR);
    public static final VoxelShape BOTTOM_BACK_LEFT_WEST = VoxelShapes.join(Block.box(0, 0, 8.5, 1, 8, 16), Block.box(0, 8, 9.25, 1, 16, 16), IBooleanFunction.OR);
    public static final VoxelShape TOP_LEFT = Stream.of(Block.box(10, 0.5, 7, 14, 3.5, 9), Block.box(0, 4, 0, 4, 9, 16), Block.box(0, 0, 0, 10, 4, 16), Block.box(14, 0, 7, 16, 2.5, 9)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape TOP_LEFT_EAST = Stream.of(Block.box(7, 0.5, 10, 9, 3.5, 14),Block.box(0, 4, 0, 16, 9, 4),Block.box(0, 0, 0, 16, 4, 10),Block.box(7, 0, 14, 9, 2.5, 16)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape TOP_LEFT_SOUTH = Stream.of(Block.box(2, 0.5, 7, 6, 3.5, 9),Block.box(12, 4, 0, 16, 9, 16),Block.box(6, 0, 0, 16, 4, 16),Block.box(0, 0, 7, 2, 2.5, 9)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape TOP_LEFT_WEST = Stream.of(Block.box(7, 0.5, 2, 9, 3.5, 6),Block.box(0, 4, 12, 16, 9, 16),Block.box(0, 0, 6, 16, 4, 16),Block.box(7, 0, 0, 9, 2.5, 2)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape TOP_RIGHT = Stream.of(Block.box(11, 0, 0, 16, 9, 16), Block.box(11, 9, 2, 13, 10, 4), Block.box(2, 2, 2, 11, 9, 3.5), Block.box(0, 2, 1, 11, 11, 2)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape TOP_RIGHT_EAST = Stream.of(Block.box(0, 0, 11, 16, 9, 16),Block.box(12, 9, 11, 14, 10, 13),Block.box(12.5, 2, 2, 14, 9, 11),Block.box(14, 2, 0, 15, 11, 11)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape TOP_RIGHT_SOUTH = Stream.of(Block.box(0, 0, 0, 5, 9, 16),Block.box(3, 9, 12, 5, 10, 14),Block.box(5, 2, 12.5, 14, 9, 14),Block.box(5, 2, 14, 16, 11, 15)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape TOP_RIGHT_WEST = Stream.of(Block.box(0, 0, 0, 16, 9, 5),Block.box(2, 9, 3, 4, 10, 5),Block.box(2, 2, 5, 3.5, 9, 14),Block.box(1, 2, 5, 2, 11, 16)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape TOP_BACK_RIGHT = VoxelShapes.join(Block.box(10, 0, 0, 16, 8, 1), Block.box(11, 8, 0, 16, 12.75, 1), IBooleanFunction.OR);
    public static final VoxelShape TOP_BACK_RIGHT_EAST = VoxelShapes.join(Block.box(15, 0, 10, 16, 8, 16), Block.box(15, 8, 11, 16, 12.75, 16), IBooleanFunction.OR);
    public static final VoxelShape TOP_BACK_RIGHT_SOUTH = VoxelShapes.join(Block.box(0, 0, 15, 6, 8, 16), Block.box(0, 8, 15, 5, 12.75, 16), IBooleanFunction.OR);
    public static final VoxelShape TOP_BACK_RIGHT_WEST = VoxelShapes.join(Block.box(0, 0, 0, 1, 8, 6), Block.box(0, 8, 0, 1, 12.75, 5), IBooleanFunction.OR);
    public static final VoxelShape TOP_BACK_LEFT = VoxelShapes.join(Block.box(0, 0, 0, 6, 8, 1), Block.box(0, 8, 0, 5, 12.75, 1), IBooleanFunction.OR);
    public static final VoxelShape TOP_BACK_LEFT_EAST = VoxelShapes.join(Block.box(15, 0, 0, 16, 8, 6), Block.box(15, 8, 0, 16, 12.75, 5), IBooleanFunction.OR);
    public static final VoxelShape TOP_BACK_LEFT_SOUTH = VoxelShapes.join(Block.box(10, 0, 15, 16, 8, 16), Block.box(11, 8, 15, 16, 12.75, 16), IBooleanFunction.OR);
    public static final VoxelShape TOP_BACK_LEFT_WEST = VoxelShapes.join(Block.box(0, 0, 10, 1, 8, 16), Block.box(0, 8, 11, 1, 12.75, 16), IBooleanFunction.OR);
    public static final VoxelShape TOP_BACK_CENTER = Block.box(0, 0, 0, 16, 14.5, 1);
    public static final VoxelShape TOP_BACK_CENTER_EAST = Block.box(15, 0, 0, 16, 14.5, 16);
    public static final VoxelShape TOP_BACK_CENTER_SOUTH = Block.box(0, 0, 15, 16, 14.5, 16);
    public static final VoxelShape TOP_BACK_CENTER_WEST = Block.box(0, 0, 0, 1, 14.5, 16);
    public static final VoxelShape TOP_FRONT = Block.box(0, 0, 13, 16, 10, 16);
    public static final VoxelShape TOP_FRONT_EAST = Block.box(0, 0, 0, 3, 10, 16);
    public static final VoxelShape TOP_FRONT_SOUTH = Block.box(0, 0, 0, 16, 10, 3);
    public static final VoxelShape TOP_FRONT_WEST = Block.box(13, 0, 0, 16, 10, 16);
    public static final VoxelShape TOP_CENTER = Block.box(0, 0, 0, 16, 10.25, 16); //Symmetrical, didn't need Horizontal Rotations
    public static final VoxelShape TOP_FRONT_RIGHT = Block.box(11.75, 0, 13, 16, 9, 16);
    public static final VoxelShape TOP_FRONT_RIGHT_EAST = Block.box(0, 0, 11.75, 3, 9, 16);
    public static final VoxelShape TOP_FRONT_RIGHT_SOUTH = Block.box(0, 0, 0, 4.25, 9, 3);
    public static final VoxelShape TOP_FRONT_RIGHT_WEST = Block.box(13, 0, 0, 16, 9, 4.25);
    public static final VoxelShape TOP_FRONT_LEFT = Block.box(0, 0, 13, 4.25, 9, 16);
    public static final VoxelShape TOP_FRONT_LEFT_EAST = Block.box(0, 0, 0, 3, 9, 4.25);
    public static final VoxelShape TOP_FRONT_LEFT_SOUTH = Block.box(11.75, 0, 0, 16, 9, 3);
    public static final VoxelShape TOP_FRONT_LEFT_WEST = Block.box(13, 0, 11.75, 16, 9, 16);

    public static final IntegerProperty SHAPE_SELECTION = IntegerProperty.create("shape_selection", 1, 18);







    public PaintMixerShapeFiller() {
        super(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK).noOcclusion());
        stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SHAPE_SELECTION, 1);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, SHAPE_SELECTION);
    }

    @Override
    public ActionResultType use(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
        if (pState.getValue(SHAPE_SELECTION).equals(11)) {
            switch (pState.getValue(FACING)) {
                default:
                    return ActionResultType.FAIL;
                case NORTH:
                    Minecraft.getInstance().player.displayClientMessage(new StringTextComponent("Hello"), true);
                    return ActionResultType.SUCCESS;
            }
        } else {
            return ActionResultType.PASS;
        }
    }

    @Override
    public void onRemove(BlockState pState, World pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        //Currently, this positions are entirely correct, but having all of this in the break method causes problems.
        //You break one, it finds the center block, then the center block breaks all of the others, which makes a much larger area disappear.
        //Don't know of a way to fix it yet, btu for right now it's there for later use.
        /**
        switch(pState.getValue(SHAPE_SELECTION)) {
            case 1:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.south(), true);
                    case EAST: pLevel.destroyBlock(pPos.west(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.north(), true);
                    case WEST: pLevel.destroyBlock(pPos.east(), true);
                }
            case 2:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.south().east(), true);
                    case EAST: pLevel.destroyBlock(pPos.west().south(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.north().west(), true);
                    case WEST: pLevel.destroyBlock(pPos.east().north(), true);
                }
            case 3:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.east(), true);
                    case EAST: pLevel.destroyBlock(pPos.south(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.west(), true);
                    case WEST: pLevel.destroyBlock(pPos.north(), true);
                }
            case 4:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.west(), true);
                    case EAST: pLevel.destroyBlock(pPos.north(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.east(), true);
                    case WEST: pLevel.destroyBlock(pPos.south(), true);
                }
            case 5:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.west(2), true);
                    case EAST: pLevel.destroyBlock(pPos.north(2), true);
                    case SOUTH: pLevel.destroyBlock(pPos.east(2), true);
                    case WEST: pLevel.destroyBlock(pPos.south(2), true);
                }
            case 6:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.west().south(), true);
                    case EAST: pLevel.destroyBlock(pPos.north().west(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.east().north(), true);
                    case WEST: pLevel.destroyBlock(pPos.south().east(), true);
                }
            case 7:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.north(), true);
                    case EAST: pLevel.destroyBlock(pPos.east(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.south(), true);
                    case WEST: pLevel.destroyBlock(pPos.west(), true);
                }
            case 8:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.north().east(), true);
                    case EAST: pLevel.destroyBlock(pPos.east().south(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.south().west(), true);
                    case WEST: pLevel.destroyBlock(pPos.west().north(), true);
                }
            case 9:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.north().west(), true);
                    case EAST: pLevel.destroyBlock(pPos.east().north(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.south().east(), true);
                    case WEST: pLevel.destroyBlock(pPos.west().south(), true);
                }
            case 10:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.below().west(), true);
                    case EAST: pLevel.destroyBlock(pPos.below().north(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.below().east(), true);
                    case WEST: pLevel.destroyBlock(pPos.below().south(), true);
                }
            case 11:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.below().east(), true);
                    case EAST: pLevel.destroyBlock(pPos.below().south(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.below().west(), true);
                    case WEST: pLevel.destroyBlock(pPos.below().north(), true);
                }
            case 12:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.below().north().east(), true);
                    case EAST: pLevel.destroyBlock(pPos.below().east().south(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.below().south().west(), true);
                    case WEST: pLevel.destroyBlock(pPos.below().west().north(), true);
                }
            case 13:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.below().north().west(), true);
                    case EAST: pLevel.destroyBlock(pPos.below().east().north(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.below().south().east(), true);
                    case WEST: pLevel.destroyBlock(pPos.below().west().south(), true);
                }
            case 14:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.below().north(), true);
                    case EAST: pLevel.destroyBlock(pPos.below().east(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.below().south(), true);
                    case WEST: pLevel.destroyBlock(pPos.below().west(), true);
                }
            case 15:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.below().south(), true);
                    case EAST: pLevel.destroyBlock(pPos.below().west(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.below().north(), true);
                    case WEST: pLevel.destroyBlock(pPos.below().east(), true);
                }
            case 16:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.below(), true);
                    case EAST: pLevel.destroyBlock(pPos.below(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.below(), true);
                    case WEST: pLevel.destroyBlock(pPos.below(), true);
                }
            case 17:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.below().south().east(), true);
                    case EAST: pLevel.destroyBlock(pPos.below().west().south(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.below().north().west(), true);
                    case WEST: pLevel.destroyBlock(pPos.below().east().north(), true);
                }
            case 18:
                switch (pState.getValue(FACING)) {
                    default: return;
                    case NORTH: pLevel.destroyBlock(pPos.below().south().west(), true);
                    case EAST: pLevel.destroyBlock(pPos.below().west().north(), true);
                    case SOUTH: pLevel.destroyBlock(pPos.below().north().east(), true);
                    case WEST: pLevel.destroyBlock(pPos.below().east().south(), true);
                }
        }
        **/
    }

    @Override
    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        switch(pState.getValue(SHAPE_SELECTION)) {
            default:
                return VoxelShapes.block();
            case 1:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return BOTTOM_FRONT_CENTER;
                    case EAST: return BOTTOM_FRONT_CENTER_EAST;
                    case SOUTH: return BOTTOM_FRONT_CENTER_SOUTH;
                    case WEST: return BOTTOM_FRONT_CENTER_WEST;
                }
            case 2:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return BOTTOM_FRONT_RIGHT;
                    case EAST: return BOTTOM_FRONT_RIGHT_EAST;
                    case SOUTH: return BOTTOM_FRONT_RIGHT_SOUTH;
                    case WEST: return BOTTOM_FRONT_RIGHT_WEST;
                }
            case 3:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return BOTTOM_RIGHT;
                    case EAST: return BOTTOM_RIGHT_EAST;
                    case SOUTH: return BOTTOM_RIGHT_SOUTH;
                    case WEST: return BOTTOM_RIGHT_WEST;
                }
            case 4:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return BOTTOM_LEFT;
                    case EAST: return BOTTOM_LEFT_EAST;
                    case SOUTH: return BOTTOM_LEFT_SOUTH;
                    case WEST: return BOTTOM_LEFT_WEST;
                }
            case 5:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return BOTTOM_LEFT_2;
                    case EAST: return BOTTOM_LEFT_2_EAST;
                    case SOUTH: return BOTTOM_LEFT_2_SOUTH;
                    case WEST: return BOTTOM_LEFT_2_WEST;
                }
            case 6:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return BOTTOM_FRONT_LEFT;
                    case EAST: return BOTTOM_FRONT_LEFT_EAST;
                    case SOUTH: return BOTTOM_FRONT_LEFT_SOUTH;
                    case WEST: return BOTTOM_FRONT_LEFT_WEST;
                }
            case 7:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return BOTTOM_BACK;
                    case EAST: return BOTTOM_BACK_EAST;
                    case SOUTH: return BOTTOM_BACK_SOUTH;
                    case WEST: return BOTTOM_BACK_WEST;
                }
            case 8:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return BOTTOM_BACK_RIGHT;
                    case EAST: return BOTTOM_BACK_RIGHT_EAST;
                    case SOUTH: return BOTTOM_BACK_RIGHT_SOUTH;
                    case WEST: return BOTTOM_BACK_RIGHT_WEST;
                }
            case 9:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return BOTTOM_BACK_LEFT;
                    case EAST: return BOTTOM_BACK_LEFT_EAST;
                    case SOUTH: return BOTTOM_BACK_LEFT_SOUTH;
                    case WEST: return BOTTOM_BACK_LEFT_WEST;
                }
            case 10:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return TOP_LEFT;
                    case EAST: return TOP_LEFT_EAST;
                    case SOUTH: return TOP_LEFT_SOUTH;
                    case WEST: return TOP_LEFT_WEST;
                }
            case 11:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return TOP_RIGHT;
                    case EAST: return TOP_RIGHT_EAST;
                    case SOUTH: return TOP_RIGHT_SOUTH;
                    case WEST: return TOP_RIGHT_WEST;
                }
            case 12:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return TOP_BACK_RIGHT;
                    case EAST: return TOP_BACK_RIGHT_EAST;
                    case SOUTH: return TOP_BACK_RIGHT_SOUTH;
                    case WEST: return TOP_BACK_RIGHT_WEST;
                }
            case 13:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return TOP_BACK_LEFT;
                    case EAST: return TOP_BACK_LEFT_EAST;
                    case SOUTH: return TOP_BACK_LEFT_SOUTH;
                    case WEST: return TOP_BACK_LEFT_WEST;
                }
            case 14:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return TOP_BACK_CENTER;
                    case EAST: return TOP_BACK_CENTER_EAST;
                    case SOUTH: return TOP_BACK_CENTER_SOUTH;
                    case WEST: return TOP_BACK_CENTER_WEST;
                }
            case 15:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return TOP_FRONT;
                    case EAST: return TOP_FRONT_EAST;
                    case SOUTH: return TOP_FRONT_SOUTH;
                    case WEST: return TOP_FRONT_WEST;
                }
            case 16:
                return TOP_CENTER;
            case 17:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return TOP_FRONT_RIGHT;
                    case EAST: return TOP_FRONT_RIGHT_EAST;
                    case SOUTH: return TOP_FRONT_RIGHT_SOUTH;
                    case WEST: return TOP_FRONT_RIGHT_WEST;
                }
            case 18:
                switch(pState.getValue(FACING)) {
                    default: return VoxelShapes.block();
                    case NORTH: return TOP_FRONT_LEFT;
                    case EAST: return TOP_FRONT_LEFT_EAST;
                    case SOUTH: return TOP_FRONT_LEFT_SOUTH;
                    case WEST: return TOP_FRONT_LEFT_WEST;
                }
        }
    }
}
