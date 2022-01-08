package io.github.FireTamer.commonInit;

import com.matyrobbrt.lib.registry.annotation.AutoBlockItem;
import com.matyrobbrt.lib.registry.annotation.RegisterBlock;
import com.matyrobbrt.lib.registry.annotation.RegistryHolder;
import io.github.FireTamer.DBB_Main;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

@RegistryHolder(modid = DBB_Main.MOD_ID)
public class BlockInit {

    @AutoBlockItem
    @RegisterBlock("dirty_stone")
    public static final Block DIRTY_STONE = new Block(AbstractBlock.Properties.copy(Blocks.STONE));

    @AutoBlockItem
    @RegisterBlock("clay_dirt")
    public static final Block CLAY_DIRT = new Block(AbstractBlock.Properties.of(Material.DIRT)
            .strength(0.5f, 0.5F)
            .harvestTool(ToolType.SHOVEL)
            .sound(SoundType.GRAVEL)
            .harvestLevel(0));

    @AutoBlockItem
    @RegisterBlock("warenai_ore")
    public static final Block WARENAI_ORE = new Block(AbstractBlock.Properties.copy(Blocks.IRON_ORE));
}
