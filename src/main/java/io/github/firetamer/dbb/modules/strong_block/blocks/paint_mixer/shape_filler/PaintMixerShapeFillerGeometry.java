package io.github.firetamer.dbb.modules.strong_block.blocks.paint_mixer.shape_filler;

import com.mojang.datafixers.util.Pair;
import io.github.firetamer.dbb.modules.strong_block.blocks.full_block.WarenaiBlockGeometry;
import io.github.firetamer.dbb.modules.strong_block.blocks.full_block.WarenaiBlockModel;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

public class PaintMixerShapeFillerGeometry implements IModelGeometry<PaintMixerShapeFillerGeometry> {
    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
        return new PaintMixerShapeFillerModel();
    }

    @Override
    public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        return Collections.singletonList(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, WarenaiBlockModel.UNDERLAY_TEXTURE));
    }
}
