package io.github.firetamer.dbb.modules.strong_block.blocks.full_block;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.lib.compat.top.ITOPDriver;
import com.matyrobbrt.lib.compat.top.ITOPInfoProvider;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import io.github.firetamer.dbb.modules.strong_block.StrongBlockModule;
import io.github.firetamer.dbb.modules.strong_block.WarenaiTOPDriver;
import io.github.firetamer.dbb.modules.strong_block.util.BlockPatternEnum;
import io.github.firetamer.dbb.modules.strong_block.util.ColorsEnum;
import io.github.firetamer.dbb.modules.strong_block.util.CustomBlockstateProperties;
import io.github.firetamer.dbb.modules.strong_block.util.WarenaiBlockCondition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WarenaiBlock extends Block implements ITOPInfoProvider {
    public static final EnumProperty<WarenaiBlockCondition> BLOCK_CONDITION = CustomBlockstateProperties.BLOCK_CONDITION;
    public static final EnumProperty<BlockPatternEnum> BLOCK_PATTERN = CustomBlockstateProperties.BLOCK_PATTERN;
    public static final BooleanProperty ACTIVE_TILE_ENTITY = CustomBlockstateProperties.ACTIVE_TILE_ENTITY;
    public static final EnumProperty<ColorsEnum> BLOCK_COLOR = CustomBlockstateProperties.BLOCK_COLOR;

    public WarenaiBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(BLOCK_CONDITION, WarenaiBlockCondition.NORMAL)
                .setValue(BLOCK_PATTERN, BlockPatternEnum.SMOOTH)
                .setValue(ACTIVE_TILE_ENTITY, true)
                .setValue(BLOCK_COLOR, ColorsEnum.WHITE));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BLOCK_CONDITION, BLOCK_PATTERN, ACTIVE_TILE_ENTITY, BLOCK_COLOR);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return state.getValue(ACTIVE_TILE_ENTITY);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) { return StrongBlockModule.STRONG_BLOCK_TILE.create(); }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader blockReader, List<ITextComponent> textComponent, ITooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, blockReader, textComponent, tooltipFlag);

        CompoundNBT stackNBT = itemStack.getTagElement("BlockEntityTag");
        if (stackNBT != null) {
            if (stackNBT.contains("BlockHealth")) {
                int blockHealthValue = stackNBT.getInt("BlockHealth");
                StringTextComponent mainTooltip = new StringTextComponent("Block Health is: " + blockHealthValue);
                textComponent.add(mainTooltip);
            }
        }
    }

    @Override
    public ITOPDriver getTheOneProbeDriver() {
        return WarenaiTOPDriver.DRIVER;
    }



    /******************************************************************************************************************/
    //Testing
    /******************************************************************************************************************/

    /**
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) {

        //world.setBlockAndUpdate(pos, state.cycle(BLOCK_CONDITION));
        //world.setBlockAndUpdate(pos, state.cycle(BLOCK_PATTERN));

        if (player.isCrouching()) {
            world.setBlockAndUpdate(pos, state.cycle(ACTIVE_TILE_ENTITY));
        }

        //world.setBlockAndUpdate(pos, state.cycle(BLOCK_COLOR));

        return ActionResultType.SUCCESS;
    }
    **/


}
