package io.github.firetamer.dbb.modules.strong_block.util;

import net.minecraft.util.IStringSerializable;

public enum PaintMixerAnimationEnum implements IStringSerializable {
    OFF("off_animation"),
    OFF_TO_IDLE("off_to_idle_animation"),
    IDLE_TO_OFF("idle_to_off_animation"),
    LOAD_CAN("load_can_animation"),
    UNLOAD_CAN("unload_can_animation"),
    FILL_AND_MIX("fill_and_mix_animation");

    private final String name;

    PaintMixerAnimationEnum(String pName) { name = pName; }

    @Override
    public String getSerializedName() { return name; }
}
