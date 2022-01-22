package io.github.firetamer.dbb.modules.strong_block;

import com.matyrobbrt.lib.annotation.RL;
import com.matyrobbrt.lib.module.IModule;
import com.matyrobbrt.lib.module.Module;
import com.matyrobbrt.lib.module.ModuleHelper;
import com.matyrobbrt.lib.registry.annotation.*;

import io.github.firetamer.dbb.modules.strong_block.blocks.paint_mixer.PaintMixer;
import io.github.firetamer.dbb.modules.strong_block.blocks.paint_mixer.shape_filler.PaintMixerShapeFiller;
import io.github.firetamer.dbb.modules.strong_block.blocks.paint_mixer.shape_filler.PaintMixerShapeFillerLoader;
import io.github.firetamer.dbb.modules.strong_block.client.PaintMixerRenderer;
import io.github.firetamer.dbb.modules.strong_block.client.PaintMixerScreen;
import io.github.firetamer.dbb.modules.strong_block.containers.PaintMixerContainer;
import io.github.firetamer.dbb.modules.strong_block.tiles.PaintMixerTile;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.modules.strong_block.tiles.StrongBlockTile;
import io.github.firetamer.dbb.modules.strong_block.blocks.full_block.WarenaiBlock;
import io.github.firetamer.dbb.modules.strong_block.blocks.full_block.WarenaiBlockLoader;
import io.github.firetamer.dbb.modules.strong_block.blocks.full_block.WarenaiBlockModel;
import io.github.firetamer.dbb.modules.strong_block.items.DamageItem;
import io.github.firetamer.dbb.modules.strong_block.items.PolisherItem;
import io.github.firetamer.dbb.modules.strong_block.items.RepairItem;
import io.github.firetamer.dbb.modules.strong_block.items.WedgeItem;
import io.github.firetamer.dbb.modules.strong_block.util.BlockColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DragonBlockBeyond.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@Module(id = @RL(modid = DragonBlockBeyond.MOD_ID, path = "strong_block"))
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






    /******************************************************************************************************************/
    //Paint Mixer Machine Stuff
    /******************************************************************************************************************/
    //PaintMixer
    @AutoBlockItem
    @RegisterBlock("paint_mixer_block")
    public static final PaintMixer PAINT_MIXER_BLOCK = new PaintMixer();

    @RegisterTileEntityType("paint_mixer_tile")
    public static final TileEntityType<PaintMixerTile> PAINT_MIXER_TILE = TileEntityType.Builder
            .of(PaintMixerTile::new, PAINT_MIXER_BLOCK).build(null);

    @RegisterContainerType("paint_mixer_container")
    public static final ContainerType<PaintMixerContainer> PAINT_MIXER_CONTAINER = IForgeContainerType.create(((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.level;
        return new PaintMixerContainer(windowId, world, pos, inv, inv.player);
    }));

    @AutoBlockItem
    @RegisterBlock("paint_mixer_shape_filler_block")
    public static final PaintMixerShapeFiller PAINT_MIXER_SHAPE_FILLER = new PaintMixerShapeFiller();




    /******************************************************************************************************************/
    //Events
    /******************************************************************************************************************/

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(PAINT_MIXER_TILE, PaintMixerRenderer::new);

        BlockColors.registerBlockColors();
        RenderTypeLookup.setRenderLayer(WARENAI_STRONG_BLOCK, RenderType.translucent());
        RenderTypeLookup.setRenderLayer(PAINT_MIXER_SHAPE_FILLER, RenderType.translucent());

        event.enqueueWork(() -> {
            //ScreenManager.register(PAINT_MIXER_CONTAINER, PaintMixerScreen::new);
            ScreenManager.register(PAINT_MIXER_CONTAINER, PaintMixerScreen::new);
        });
    }

    @SubscribeEvent
    public static void onModelRegistryEvent(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(DragonBlockBeyond.MOD_ID, "warenai_full_block_loader"), new WarenaiBlockLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(DragonBlockBeyond.MOD_ID, "paint_mixer_shape_filler_loader"), new PaintMixerShapeFillerLoader());
    }

	@SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void onTextureStitchEvent(TextureStitchEvent.Pre event) { //getSmoothWarenaiBlockResourceLocations()
        if (event.getMap().location() == AtlasTexture.LOCATION_BLOCKS) {
            event.addSprite(WarenaiBlockModel.UNDERLAY_TEXTURE);
            event.addSprite(WarenaiBlockModel.POLISHED_UNDERLAY_TEXTURE);
            event.addSprite(WarenaiBlockModel.LARGE_BRICK_UNDERLAY_TEXTURE);
            event.addSprite(WarenaiBlockModel.POLISHED_LARGE_BRICK_UNDERLAY_TEXTURE);
            event.addSprite(WarenaiBlockModel.SMALL_BRICK_UNDERLAY_TEXTURE);
            event.addSprite(WarenaiBlockModel.POLISHED_SMALL_BRICK_UNDERLAY_TEXTURE);

            event.addSprite(WarenaiBlockModel.SCUFFED_OVERLAY_TEXTURE);
            event.addSprite(WarenaiBlockModel.CRACKED1_OVERLAY_TEXTURE);
            event.addSprite(WarenaiBlockModel.CRACKED2_OVERLAY_TEXTURE);
            event.addSprite(WarenaiBlockModel.CRACKED3_OVERLAY_TEXTURE);
            event.addSprite(WarenaiBlockModel.CRACKED4_OVERLAY_TEXTURE);
        }
    }
}
