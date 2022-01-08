package io.github.firetamer.dbb.api.player_data;

import io.github.firetamer.dbb.api.extensions.ApiExtendable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.UUID;

public interface PlayerData extends INBTSerializable<CompoundNBT> {

    UUID getPlayerUUID();

    @FunctionalInterface
    interface Deserializer extends ApiExtendable {

        PlayerData deserialize(CompoundNBT nbt);

    }

}