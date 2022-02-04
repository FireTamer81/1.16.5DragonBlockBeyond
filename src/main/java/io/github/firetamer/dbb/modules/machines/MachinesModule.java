package io.github.firetamer.dbb.modules.machines;

import com.matyrobbrt.lib.annotation.RL;
import com.matyrobbrt.lib.module.IModule;
import com.matyrobbrt.lib.module.Module;
import com.matyrobbrt.lib.module.ModuleHelper;
import com.matyrobbrt.lib.registry.annotation.AutoBlockItem;
import com.matyrobbrt.lib.registry.annotation.RegisterBlock;
import com.matyrobbrt.lib.registry.annotation.RegisterContainerType;
import com.matyrobbrt.lib.registry.annotation.RegisterTileEntityType;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.modules.machines.paint_mixer.blocks.PaintMixer;
import io.github.firetamer.dbb.modules.machines.paint_mixer.blocks.shape_filler.PaintMixerShapeFiller;
import io.github.firetamer.dbb.modules.machines.paint_mixer.blocks.shape_filler.PaintMixerShapeFillerLoader;
import io.github.firetamer.dbb.modules.machines.paint_mixer.client.PaintMixerContainerScreen;
import io.github.firetamer.dbb.modules.machines.paint_mixer.client.PaintMixerRenderer;
import io.github.firetamer.dbb.modules.machines.paint_mixer.containers.PaintMixerContainer;
import io.github.firetamer.dbb.modules.machines.paint_mixer.tiles.PaintMixerTile;
import io.github.firetamer.dbb.modules.strong_block.util.BlockColors;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Module(id = @RL(modid = DragonBlockBeyond.MOD_ID, path = "machines_module"))
public class MachinesModule extends ModuleHelper implements IModule {

    /******************************************************************************************************************/
    //Paint Mixer Machine Stuff
    /******************************************************************************************************************/

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

    //Voxel Shape Filler Block
    @AutoBlockItem
    @RegisterBlock("paint_mixer_shape_filler_block")
    public static final PaintMixerShapeFiller PAINT_MIXER_SHAPE_FILLER = new PaintMixerShapeFiller();




    /******************************************************************************************************************/
    //Events
    /******************************************************************************************************************/

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(PAINT_MIXER_TILE, PaintMixerRenderer::new);

        //RenderTypeLookup.setRenderLayer(PAINT_MIXER_SHAPE_FILLER, RenderType.translucent());

        event.enqueueWork(() -> {
            ScreenManager.register(PAINT_MIXER_CONTAINER, PaintMixerContainerScreen::new);
        });
    }

    @SubscribeEvent
    public static void onModelRegistryEvent(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(DragonBlockBeyond.MOD_ID, "paint_mixer_shape_filler_loader"), new PaintMixerShapeFillerLoader());
    }
}
