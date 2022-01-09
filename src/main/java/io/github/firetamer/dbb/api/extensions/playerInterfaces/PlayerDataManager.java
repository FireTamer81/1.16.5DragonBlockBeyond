package io.github.firetamer.dbb.api.extensions.playerInterfaces;

import io.github.firetamer.dbb.api.extensions.ApiExtendable;
import io.github.firetamer.dbb.api.extensions.ApiExtensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.WillNotClose;
import java.util.Map;
import java.util.UUID;

public interface PlayerDataManager extends INBTSerializable<CompoundNBT> {

    static PlayerDataManager getManagerForServer(@Nonnull @WillNotClose final MinecraftServer server) {
        return ApiExtensions.withExtension(Getter.class, ext -> ext.getManagerForServer(server));
    }

    /**
     * Gets the data for the player with the specified {@code playerUUID}. <br>
     * This will create the data if it doesn't exist
     *
     * @param playerUUID the UUID to get the data for
     * @return the data of the player with the uuid
     */
    @Nonnull
    PlayerData getDataForPlayer(UUID playerUUID);

    /**
     * Gets the data for the {@code player}. <br>
     * This will create the data if it doesn't exist
     *
     * @param player the player to get the data for
     * @return the data of the player
     */
    @Nonnull
    default PlayerData getDataForPlayer(PlayerEntity player) {
        return getDataForPlayer(player.getUUID());
    }

    /**
     * Getter for all the player data currently stored.
     * <b>DO NOT MODIFY THE RETURNED DATA</b>
     *
     * @return all the currently stored {@link PlayerData}
     */
    Map<UUID, PlayerData> getAllPlayerData();

    void setChanged();

    @FunctionalInterface
    interface Getter extends ApiExtendable {

        PlayerDataManager getManagerForServer(@WillNotClose @Nonnull final MinecraftServer server);

    }

    @FunctionalInterface
    interface Deserializer extends ApiExtendable {

        PlayerDataManager deserialize(CompoundNBT nbt);

    }

}
