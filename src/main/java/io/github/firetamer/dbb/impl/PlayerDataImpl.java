package io.github.firetamer.dbb.impl;

import io.github.firetamer.dbb.api.extensions.ApiExtension;
import io.github.firetamer.dbb.api.player_data.PlayerData;
import io.github.firetamer.dbb.util.NBTBuilder;
import net.minecraft.nbt.CompoundNBT;

import java.util.UUID;

class PlayerDataImpl implements PlayerData {

    @ApiExtension(Deserializer.class)
    private static final PlayerData.Deserializer DESERIALIZER = nbt -> {
        PlayerDataImpl data = new PlayerDataImpl(UUID.fromString(nbt.getString("playerUUID")));
        data.deserializeNBT(nbt);
        return data;
    };

    private final UUID playerUUID;

    public PlayerDataImpl(final UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    @Override
    public UUID getPlayerUUID() {
        return playerUUID;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return new NBTBuilder()
                .putStringUUID("playerUUID", playerUUID)
                .build();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }
}
