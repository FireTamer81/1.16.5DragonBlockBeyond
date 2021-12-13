package io.github.FireTamer.itemGroups;

import io.github.FireTamer.init.BlockInit;
import io.github.FireTamer.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class DBB_ItemGroup extends ItemGroup {

    public DBB_ItemGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() { return ItemInit.WARENAI_CRYSTAL.getDefaultInstance(); }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        items.add(ItemInit.WARENAI_CRYSTAL.getDefaultInstance());
    }
}
