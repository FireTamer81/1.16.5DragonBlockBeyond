package io.github.FireTamer.modules.strongBlockFeature.blocks.fullBlock;

import io.github.FireTamer.modules.strongBlockFeature.StrongBlockModule;
import io.github.FireTamer.modules.strongBlockFeature.util.BlockPatternEnum;
import io.github.FireTamer.modules.strongBlockFeature.util.ColorsEnum;
import io.github.FireTamer.modules.strongBlockFeature.util.CustomBlockstateProperties;
import io.github.FireTamer.modules.strongBlockFeature.util.WarenaiBlockCondition;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class WarenaiBlock extends Block
{
    public static final EnumProperty<WarenaiBlockCondition> BLOCK_CONDITION = CustomBlockstateProperties.BLOCK_CONDITION;
    public static final EnumProperty<BlockPatternEnum> BLOCK_PATTERN = CustomBlockstateProperties.BLOCK_PATTERN;
    public static final BooleanProperty ACTIVE_TILE_ENTITY = CustomBlockstateProperties.ACTIVE_TILE_ENTITY;
    public static final EnumProperty<ColorsEnum> BLOCK_COLOR = CustomBlockstateProperties.BLOCK_COLOR;


    public WarenaiBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(BLOCK_CONDITION, WarenaiBlockCondition.NORMAL)
                .setValue(BLOCK_PATTERN, BlockPatternEnum.SMOOTH)
                .setValue(ACTIVE_TILE_ENTITY, false)
                .setValue(BLOCK_COLOR, ColorsEnum.WHITE));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BLOCK_CONDITION, BLOCK_PATTERN, ACTIVE_TILE_ENTITY, BLOCK_COLOR);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        if (!state.getValue(ACTIVE_TILE_ENTITY)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return StrongBlockModule.STRONG_BLOCK_TILE.create();
    }

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






    /******************************************************************************************************************/
    //Testing
    /******************************************************************************************************************/

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) {

        //world.setBlockAndUpdate(pos, state.cycle(BLOCK_CONDITION));
        //world.setBlockAndUpdate(pos, state.cycle(BLOCK_PATTERN));
        //world.setBlockAndUpdate(pos, state.cycle(ACTIVE_TILE_ENTITY));
        //world.setBlockAndUpdate(pos, state.cycle(BLOCK_COLOR));

        return ActionResultType.SUCCESS;
    }
}
