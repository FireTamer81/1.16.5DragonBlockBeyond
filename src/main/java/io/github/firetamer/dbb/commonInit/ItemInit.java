package io.github.firetamer.dbb.commonInit;

import com.matyrobbrt.lib.registry.annotation.RegisterItem;
import com.matyrobbrt.lib.registry.annotation.RegistryHolder;

import net.minecraft.item.Item;

import io.github.firetamer.dbb.DragonBlockBeyond;

@RegistryHolder(modid = DragonBlockBeyond.MOD_ID)
public class ItemInit {

    @RegisterItem("warenai_crystal")
    public static final Item WARENAI_CRYSTAL = new Item(new Item.Properties());

    @RegisterItem("katchin_shard")
    public static final Item KATCHIN_SHARD = new Item(new Item.Properties());
}
