package io.github.firetamer.dbb.modules.machines.paint_mixer.blocks.shape_filler;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaintMixerShapeFillerModel implements IDynamicBakedModel {
    public static final ResourceLocation PARTICLE_TEXTURE = new ResourceLocation("iron_block");

    public PaintMixerShapeFillerModel() {}

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        List<BakedQuad> allQuads = new ArrayList<>();

        return allQuads;
    }

    private TextureAtlasSprite getTextureSprite(ResourceLocation textureResource) {
        return Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(textureResource);
    }


    @Override
    public boolean useAmbientOcclusion() { return true; }

    @Override
    public boolean isGui3d() { return false; }

    @Override
    public boolean usesBlockLight() { return false; }

    @Override
    public boolean isCustomRenderer() { return false; }

    @Override
    public TextureAtlasSprite getParticleIcon() { return getTextureSprite(PARTICLE_TEXTURE); }

    @Override
    public ItemOverrideList getOverrides() { return ItemOverrideList.EMPTY; }
}
