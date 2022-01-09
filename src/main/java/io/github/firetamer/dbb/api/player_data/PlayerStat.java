package io.github.firetamer.dbb.api.player_data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

public class PlayerStat<T> implements INBTSerializable<CompoundNBT> {

    private PlayerStatType<T> statType;
    private T value;

    public PlayerStat(PlayerStatType<T> statType, T value) {
        this.statType = statType;
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    public void set(Object v) {
        setValue((T) v);
    }

    public T getValue() { return this.value; }

    public PlayerStatType<T> getStatType() {
        return statType;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.put("value", statType.valueSerializer.apply(this.value));
        tag.putString("type", statType.getRegistryName().toString());
        return tag;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.statType = (PlayerStatType<T>) PlayerStatType.REGISTRY.getValue(new ResourceLocation(nbt.getString("type")));
        this.value = statType.valueDeserializer.apply(nbt.get("value"));
    }

    @SuppressWarnings({
            "rawtypes", "unchecked"
    })
    public static PlayerStat<?> fromNBT(CompoundNBT nbt) {
        PlayerStatType<?> type = PlayerStatType.REGISTRY.getValue(new ResourceLocation(nbt.getString("type")));
        if (type != null) {
            return new PlayerStat(type, type.valueDeserializer.apply(nbt.get("value")));
        }
        return null;
    }

}
