package io.github.firetamer.dbb.modules.machines.paint_mixer.blocks.shape_filler;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class PaintMixerShapeFillerLoader implements IModelLoader<PaintMixerShapeFillerGeometry> {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {}

    @Override
    public PaintMixerShapeFillerGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new PaintMixerShapeFillerGeometry();
    }
}
