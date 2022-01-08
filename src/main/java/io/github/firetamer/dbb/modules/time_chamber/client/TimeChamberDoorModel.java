package io.github.firetamer.dbb.modules.time_chamber.client;

import net.minecraft.util.ResourceLocation;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.modules.time_chamber.tiles.TimeChamberDoorTile;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TimeChamberDoorModel extends AnimatedGeoModel<TimeChamberDoorTile> {
    @Override
    public ResourceLocation getModelLocation(TimeChamberDoorTile object) {
        return new ResourceLocation(DragonBlockBeyond.MOD_ID, "geo/time_chamber_door.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TimeChamberDoorTile object) {
        return new ResourceLocation(DragonBlockBeyond.MOD_ID, "textures/gecko/time_chamber_door.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TimeChamberDoorTile animatable) {
        return new ResourceLocation(DragonBlockBeyond.MOD_ID, "animations/time_chamber_door.animation.json");
    }
}
