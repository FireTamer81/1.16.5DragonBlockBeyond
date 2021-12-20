package io.github.FireTamer.modules.strongBlockFeature.util;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;

public class CustomBlockstateProperties
{
    public static final EnumProperty<WarenaiBlockCondition> BLOCK_CONDITION = EnumProperty.create("block_condition", WarenaiBlockCondition.class);

    public static final EnumProperty<BlockPatternEnum> BLOCK_PATTERN = EnumProperty.create("block_pattern", BlockPatternEnum.class);

    public static final BooleanProperty ACTIVE_TILE_ENTITY = BooleanProperty.create("active_tile_entity");

    public static final EnumProperty<ColorsEnum> BLOCK_COLOR = EnumProperty.create("block_color", ColorsEnum.class, ColorsEnum.values());
}
