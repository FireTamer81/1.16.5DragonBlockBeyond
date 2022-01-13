package io.github.firetamer.dbb.modules.strong_block.blocks.full_block;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.resources.IResourceManager;

import net.minecraftforge.client.model.IModelLoader;

public class WarenaiBlockLoader implements IModelLoader<WarenaiBlockGeometry> {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {}

    @Override
    public WarenaiBlockGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new WarenaiBlockGeometry();
    }
}
