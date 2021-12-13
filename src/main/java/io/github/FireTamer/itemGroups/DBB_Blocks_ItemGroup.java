package io.github.FireTamer.itemGroups;

import io.github.FireTamer.init.BlockInit;
import io.github.FireTamer.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class DBB_Blocks_ItemGroup extends ItemGroup {

    public DBB_Blocks_ItemGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() { return BlockInit.DIRTY_STONE.asItem().getDefaultInstance(); }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        items.add(BlockInit.DIRTY_STONE.asItem().getDefaultInstance());
    }
}
