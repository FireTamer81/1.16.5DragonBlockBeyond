package io.github.firetamer.dbb.common.itemGroups;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import io.github.firetamer.dbb.commonInit.BlockInit;
import io.github.firetamer.dbb.modules.namekFeature.NamekModule;
import io.github.firetamer.dbb.modules.strong_block.StrongBlockModule;
import io.github.firetamer.dbb.modules.timeChamberFeature.TimeChamberModule;

public class DBB_Blocks_ItemGroup extends ItemGroup {

    public DBB_Blocks_ItemGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() { return BlockInit.DIRTY_STONE.asItem().getDefaultInstance(); }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        items.add(BlockInit.DIRTY_STONE.asItem().getDefaultInstance());
        items.add(BlockInit.CLAY_DIRT.asItem().getDefaultInstance());
        items.add(BlockInit.WARENAI_ORE.asItem().getDefaultInstance());

        items.add(TimeChamberModule.TIME_CHAMBER_DOOR_BLOCK.asItem().getDefaultInstance());
        items.add(TimeChamberModule.TIME_CHAMBER_FLOOR_BLOCK.asItem().getDefaultInstance());
        items.add(TimeChamberModule.TIME_CHAMBER_PORTAL_BLOCK.asItem().getDefaultInstance());

        items.add(NamekModule.NAMEK_KELP_TOP.asItem().getDefaultInstance());
        items.add(NamekModule.NAMEK_KELP_STEM.asItem().getDefaultInstance());
        items.add(NamekModule.NAMEK_TREE_SAPLING.asItem().getDefaultInstance());
        items.add(NamekModule.NAMEK_SEAGRASS.asItem().getDefaultInstance());
        items.add(NamekModule.NAMEK_TALL_SEAGRASS.asItem().getDefaultInstance());
        items.add(NamekModule.NAMEK_LOG.asItem().getDefaultInstance());
        items.add(NamekModule.NAMEK_LEAVES.asItem().getDefaultInstance());
        items.add(NamekModule.SHORT_NAMEK_GRASS.asItem().getDefaultInstance());
        items.add(NamekModule.TALL_NAMEK_GRASS.asItem().getDefaultInstance());
        items.add(NamekModule.AJISA_BUSH.asItem().getDefaultInstance());
        items.add(NamekModule.TILLED_NAMEK_DIRT.asItem().getDefaultInstance());

        items.add(StrongBlockModule.WARENAI_STRONG_BLOCK.asItem().getDefaultInstance());
    }
}
