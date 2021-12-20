package io.github.FireTamer.modules.strongBlockFeature;

import com.matyrobbrt.lib.annotation.RL;
import com.matyrobbrt.lib.module.IModule;
import com.matyrobbrt.lib.module.Module;
import com.matyrobbrt.lib.module.ModuleHelper;
import com.matyrobbrt.lib.registry.annotation.AutoBlockItem;
import com.matyrobbrt.lib.registry.annotation.RegisterBlock;
import com.matyrobbrt.lib.registry.annotation.RegisterItem;
import com.matyrobbrt.lib.registry.annotation.RegisterTileEntityType;
import io.github.FireTamer.DBB_Main;
import io.github.FireTamer.modules.strongBlockFeature.blocks.fullBlock.WarenaiBlock;
import io.github.FireTamer.modules.strongBlockFeature.blocks.fullBlock.WarenaiBlock_Loader;
import io.github.FireTamer.modules.strongBlockFeature.blocks.fullBlock.WarenaiBlock_Model;
import io.github.FireTamer.modules.strongBlockFeature.items.DamageItem;
import io.github.FireTamer.modules.strongBlockFeature.items.PolisherItem;
import io.github.FireTamer.modules.strongBlockFeature.items.RepairItem;
import io.github.FireTamer.modules.strongBlockFeature.items.WedgeItem;
import io.github.FireTamer.modules.strongBlockFeature.blocks.fullBlock.StrongBlockTile;
import io.github.FireTamer.modules.strongBlockFeature.util.BlockColors;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DBB_Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@Module(id = @RL(modid = DBB_Main.MOD_ID, path = "strong_block"))
public class StrongBlockModule extends ModuleHelper implements IModule {

    @AutoBlockItem
    @RegisterBlock("warenai_strong_block")
    public static final WarenaiBlock WARENAI_STRONG_BLOCK = new WarenaiBlock(AbstractBlock.Properties.of(Material.STONE)
            .strength(-1.0F, 50F)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(3));

    @RegisterTileEntityType("strong_block_tile")
    public static final TileEntityType<StrongBlockTile> STRONG_BLOCK_TILE = TileEntityType.Builder
            .of(StrongBlockTile::new, WARENAI_STRONG_BLOCK).build(null);

    @RegisterItem("repair_item")
    public static final Item REPAIR_ITEM = new RepairItem(new Item.Properties());

    @RegisterItem("damage_item")
    public static final Item DAMAGE_ITEM = new DamageItem(new Item.Properties());

    @RegisterItem("polisher_item")
    public static final Item POLISHER_ITEM = new PolisherItem(new Item.Properties().durability(500));

    @RegisterItem("wedge_item")
    public static final Item WEDGE_ITEM = new WedgeItem(new Item.Properties().durability(2000));


    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        BlockColors.registerBlockColors();
        RenderTypeLookup.setRenderLayer(WARENAI_STRONG_BLOCK, RenderType.translucent());
    }

    @SubscribeEvent
    public static void onModelRegistryEvent(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(DBB_Main.MOD_ID, "warenai_full_block_loader"), new WarenaiBlock_Loader());
    }

    @SubscribeEvent
    public static void onTextureStitchEvent(TextureStitchEvent.Pre event) { //getSmoothWarenaiBlockResourceLocations()
        if (event.getMap().location() == AtlasTexture.LOCATION_BLOCKS) {
            event.addSprite(WarenaiBlock_Model.UNDERLAY_TEXTURE);
            event.addSprite(WarenaiBlock_Model.POLISHED_UNDERLAY_TEXTURE);
            event.addSprite(WarenaiBlock_Model.LARGE_BRICK_UNDERLAY_TEXTURE);
            event.addSprite(WarenaiBlock_Model.POLISHED_LARGE_BRICK_UNDERLAY_TEXTURE);
            event.addSprite(WarenaiBlock_Model.SMALL_BRICK_UNDERLAY_TEXTURE);
            event.addSprite(WarenaiBlock_Model.POLISHED_SMALL_BRICK_UNDERLAY_TEXTURE);

            event.addSprite(WarenaiBlock_Model.SCUFFED_OVERLAY_TEXTURE);
            event.addSprite(WarenaiBlock_Model.CRACKED1_OVERLAY_TEXTURE);
            event.addSprite(WarenaiBlock_Model.CRACKED2_OVERLAY_TEXTURE);
            event.addSprite(WarenaiBlock_Model.CRACKED3_OVERLAY_TEXTURE);
            event.addSprite(WarenaiBlock_Model.CRACKED4_OVERLAY_TEXTURE);
        }
    }
}
