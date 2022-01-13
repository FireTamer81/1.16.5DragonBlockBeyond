package io.github.firetamer.dbb.modules.strong_block.client;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.modules.strong_block.tiles.PaintMixerTile;
import io.github.firetamer.dbb.modules.time_chamber.tiles.TimeChamberDoorTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PaintMixerModel extends AnimatedGeoModel<PaintMixerTile> {
    @Override
    public ResourceLocation getModelLocation(PaintMixerTile object) {
        return new ResourceLocation(DragonBlockBeyond.MOD_ID, "geo/paint_mixer.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PaintMixerTile object) {
        return new ResourceLocation(DragonBlockBeyond.MOD_ID, "textures/gecko/paint_mixer_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PaintMixerTile animatable) {
        return new ResourceLocation(DragonBlockBeyond.MOD_ID, "animations/paint_mixer.animation.json");
    }
}
