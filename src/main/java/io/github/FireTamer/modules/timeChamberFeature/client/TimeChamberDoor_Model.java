package io.github.FireTamer.modules.timeChamberFeature.client;

import io.github.FireTamer.DBB_Main;
import io.github.FireTamer.modules.timeChamberFeature.tiles.TimeChamberDoor_Tile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TimeChamberDoor_Model extends AnimatedGeoModel<TimeChamberDoor_Tile> {
    @Override
    public ResourceLocation getModelLocation(TimeChamberDoor_Tile object) {
        return new ResourceLocation(DBB_Main.MOD_ID, "geo/time_chamber_door.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TimeChamberDoor_Tile object) {
        return new ResourceLocation(DBB_Main.MOD_ID, "textures/gecko/time_chamber_door.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TimeChamberDoor_Tile animatable) {
        return new ResourceLocation(DBB_Main.MOD_ID, "animations/time_chamber_door.animation.json");
    }
}
