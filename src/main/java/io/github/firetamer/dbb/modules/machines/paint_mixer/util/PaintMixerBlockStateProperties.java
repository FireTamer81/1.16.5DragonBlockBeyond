package io.github.firetamer.dbb.modules.machines.paint_mixer.util;

import net.minecraft.state.EnumProperty;

public class PaintMixerBlockStateProperties {
    public static final EnumProperty<PaintMixerAnimationEnum> PAINT_MIXER_ANIMATION_STATE =
            EnumProperty.create("paint_mixer_animation_state", PaintMixerAnimationEnum.class, PaintMixerAnimationEnum.values());
}
