package io.github.firetamer.dbb.modules.timeChamberFeature.client;

import net.minecraft.util.ResourceLocation;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.modules.timeChamberFeature.tiles.TimeChamberDoor_Tile;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TimeChamberDoor_Model extends AnimatedGeoModel<TimeChamberDoor_Tile> {
    @Override
    public ResourceLocation getModelLocation(TimeChamberDoor_Tile object) {
        return new ResourceLocation(DragonBlockBeyond.MOD_ID, "geo/time_chamber_door.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TimeChamberDoor_Tile object) {
        return new ResourceLocation(DragonBlockBeyond.MOD_ID, "textures/gecko/time_chamber_door.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TimeChamberDoor_Tile animatable) {
        return new ResourceLocation(DragonBlockBeyond.MOD_ID, "animations/time_chamber_door.animation.json");
    }
}
