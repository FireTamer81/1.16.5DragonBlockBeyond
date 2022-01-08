package io.github.firetamer.dbb.util;

import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.FloatNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.nbt.ShortNBT;
import net.minecraft.nbt.StringNBT;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NBTBuilder {

    private final Map<String, INBT> tags;

    public NBTBuilder() {
        tags = new HashMap<>();
    }

    private NBTBuilder(CompoundNBT tag) {
        tags = tag.tags;
    }

    public static NBTBuilder of(CompoundNBT otherTag) {
        return new NBTBuilder(otherTag);
    }

    /**
     * Stores a new NBTTagString with the given string value into the map with the given string key.
     */
    public NBTBuilder putString(String key, String value) {
        return put(key, StringNBT.valueOf(value));
    }

    public NBTBuilder putByte(String pKey, byte pValue) {
        return put(pKey, ByteNBT.valueOf(pValue));
    }

    /**
     * Stores a new NBTTagShort with the given short value into the map with the given string key.
     */
    public NBTBuilder putShort(String pKey, short pValue) {
        return put(pKey, ShortNBT.valueOf(pValue));
    }

    /**
     * Stores a new NBTTagInt with the given integer value into the map with the given string key.
     */
    public NBTBuilder putInt(String pKey, int pValue) {
        return put(pKey, IntNBT.valueOf(pValue));
    }

    /**
     * Stores a new NBTTagLong with the given long value into the map with the given string key.
     */
    public NBTBuilder putLong(String pKey, long pValue) {
        return put(pKey, LongNBT.valueOf(pValue));
    }

    /**
     * Stores a new NBTTagFloat with the given float value into the map with the given string key.
     */
    public NBTBuilder putFloat(String pKey, float pValue) {
        return put(pKey, FloatNBT.valueOf(pValue));
    }

    /**
     * Stores a new NBTTagDouble with the given double value into the map with the given string key.
     */
    public NBTBuilder putDouble(String pKey, double pValue) {
        return put(pKey, DoubleNBT.valueOf(pValue));
    }

    public NBTBuilder putStringUUID(String key, UUID value) {
        return putString(key, value.toString());
    }

    public NBTBuilder putUUID(String key, UUID value) {
        return put(key, NBTUtil.createUUID(value));
    }

    public NBTBuilder put(String key, INBT value) {
        tags.put(key, value);
        return this;
    }

    public CompoundNBT build() {
        return new CompoundNBT(tags) {
            // The constructor is protected
        };
    }

}
