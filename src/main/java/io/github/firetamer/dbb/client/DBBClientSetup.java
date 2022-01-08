package io.github.firetamer.dbb.client;

import com.matyrobbrt.lib.ClientSetup;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.client.armour.DBBArmourLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import java.util.Map;

public class DBBClientSetup extends ClientSetup {

    private static final Minecraft minecraft = Minecraft.getInstance();

    public DBBClientSetup(IEventBus modBus) {
        super(modBus);
        modBus.addListener(this::loadComplete);
    }

    public void loadComplete(final FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            EntityRendererManager entityRenderManager = Minecraft.getInstance().getEntityRenderDispatcher();
            // Add Player layers
            for (Map.Entry<String, PlayerRenderer> entry : entityRenderManager.getSkinMap().entrySet()) {
                addCustomLayers(EntityType.PLAYER, entry.getValue());
            }

            // Add mob layers
            for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : entityRenderManager.renderers.entrySet()) {
                EntityRenderer<?> renderer = entry.getValue();
                if (renderer instanceof LivingRenderer) {
                    addCustomLayers(entry.getKey(), (LivingRenderer<LivingEntity, BipedModel<LivingEntity>>) renderer);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private static <T extends LivingEntity, M extends BipedModel<T>> void addCustomLayers(EntityType<?> type,
                                                                                          LivingRenderer<T, M> renderer) {
        BipedArmorLayer<T, M, ?> bipedArmorLayer = null;
        for (LayerRenderer<T, M> layerRenderer : renderer.layers) {
            if (layerRenderer != null) {
                // Only on exact class match so that other mods don't mess stuff up
                Class<?> layerClass = layerRenderer.getClass();
                if (layerClass == BipedArmorLayer.class) {
                    bipedArmorLayer = (BipedArmorLayer<T, M, ?>) layerRenderer;
                }
            }
        }
        if (bipedArmorLayer != null) {
            renderer.addLayer(new DBBArmourLayer<>(renderer, bipedArmorLayer.innerModel, bipedArmorLayer.outerModel));
            DragonBlockBeyond.LOGGER.debug("Added DBB Armour Layer to entity of type: {}", type.getRegistryName());
        }
    }

}
