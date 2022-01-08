package io.github.firetamer.dbb.modules.strong_block.util;

import net.minecraft.util.IStringSerializable;

public enum BlockPatternEnum implements IStringSerializable {
    SMOOTH("smooth"),
    LARGE_BRICK("large_brick"),
    SMALL_BRICK("small_brick");

    private final String name;

    BlockPatternEnum(String pName) {
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
