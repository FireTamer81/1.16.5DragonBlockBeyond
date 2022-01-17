package io.github.firetamer.dbb.init;

import com.matyrobbrt.lib.registry.annotation.RegisterItem;
import com.matyrobbrt.lib.registry.annotation.RegistryHolder;

import io.github.firetamer.dbb.init.objects.item.SunglassesItem;
import io.github.firetamer.dbb.init.objects.item.api.SpecialArmourMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

import io.github.firetamer.dbb.DragonBlockBeyond;

@RegistryHolder(modid = DragonBlockBeyond.MOD_ID)
public class ItemInit {

    @RegisterItem("warenai_crystal")
    public static final Item WARENAI_CRYSTAL = new Item(new Item.Properties());

    @RegisterItem("katchin_shard")
    public static final Item KATCHIN_SHARD = new Item(new Item.Properties());

    @RegisterItem("sunglasses")
    public static final SunglassesItem SUNGLASSES = new SunglassesItem(new SpecialArmourMaterial() {

        @Override
        public String getName() { return DragonBlockBeyond.MOD_ID + ":sunglasses"; }
    }, EquipmentSlotType.HEAD, new Item.Properties().tab(DragonBlockBeyond.MAIN_GROUP));
}
