package io.github.FireTamer.itemGroups;

import io.github.FireTamer.init.BlockInit;
import io.github.FireTamer.init.ItemInit;
import io.github.FireTamer.modules.namekFeature.NamekModule;
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
        items.add(ItemInit.KATCHIN_SHARD.getDefaultInstance());

        items.add(NamekModule.AJISA_FLOWERS.getDefaultInstance());
        items.add(NamekModule.NAMEK_KELP_BUDS.getDefaultInstance());
        items.add(NamekModule.NAMEK_WATER_BUCKET.getDefaultInstance());
    }
}
