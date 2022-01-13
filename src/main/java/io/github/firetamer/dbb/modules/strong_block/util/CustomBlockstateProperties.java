package io.github.firetamer.dbb.modules.strong_block.util;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;

public class CustomBlockstateProperties
{
    public static final EnumProperty<WarenaiBlockCondition> BLOCK_CONDITION = EnumProperty.create("block_condition", WarenaiBlockCondition.class);

    public static final EnumProperty<BlockPatternEnum> BLOCK_PATTERN = EnumProperty.create("block_pattern", BlockPatternEnum.class);

    public static final BooleanProperty ACTIVE_TILE_ENTITY = BooleanProperty.create("active_tile_entity");

    public static final EnumProperty<ColorsEnum> BLOCK_COLOR = EnumProperty.create("block_color", ColorsEnum.class, ColorsEnum.values());

    public static final EnumProperty<PaintMixerAnimationEnum> PAINT_MIXER_ANIMATION_STATE =
            EnumProperty.create("paint_mixer_animation_state", PaintMixerAnimationEnum.class, PaintMixerAnimationEnum.values());
}
