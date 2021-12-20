package io.github.FireTamer.modules.strongBlockFeature.blocks.fullBlock;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class WarenaiBlock_Loader implements IModelLoader<WarenaiBlock_Geometry> {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {}

    @Override
    public WarenaiBlock_Geometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new WarenaiBlock_Geometry();
    }
}
