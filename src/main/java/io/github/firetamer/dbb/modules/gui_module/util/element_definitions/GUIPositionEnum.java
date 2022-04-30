package io.github.firetamer.dbb.modules.gui_module.util.element_definitions;

import net.minecraft.util.IStringSerializable;

public enum GUIPositionEnum implements IStringSerializable {
    TOP_LEFT("top_left"),
    MIDDLE_LEFT("middle_left"),
    BOTTOM_LEFT("bottom_left"),
    TOP_MIDDLE("top_middle"),
    CENTER("center"),
    BOTTOM_MIDDLE("bottom_middle"),
    TOP_RIGHT("top_right"),
    MIDDLE_RIGHT("middle_right"),
    BOTTOM_RIGHT("bottom_right");

    private final String name;

    GUIPositionEnum(String pName) {
        name = pName;
    }

    @Override
    public String toString() {
        return this.getSerializedName();
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
