package io.github.firetamer.dbb.common.itemGroups;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import io.github.firetamer.dbb.commonInit.ItemInit;
import io.github.firetamer.dbb.modules.namekFeature.NamekModule;
import io.github.firetamer.dbb.modules.strong_block.StrongBlockModule;

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

        items.add(StrongBlockModule.REPAIR_ITEM.getDefaultInstance());
        items.add(StrongBlockModule.DAMAGE_ITEM.getDefaultInstance());
        items.add(StrongBlockModule.POLISHER_ITEM.getDefaultInstance());
        items.add(StrongBlockModule.WEDGE_ITEM.getDefaultInstance());
    }
}
