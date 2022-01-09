package io.github.firetamer.dbb.impl;

import com.matyrobbrt.lib.nbt.BaseNBTList;
import io.github.firetamer.dbb.api.extensions.ApiExtension;
import io.github.firetamer.dbb.api.extensions.playerInterfaces.PlayerData;
import io.github.firetamer.dbb.api.player_data.skill.PlayerSkillType;
import io.github.firetamer.dbb.util.NBTBuilder;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.UUID;

class PlayerDataImpl implements PlayerData {

    @ApiExtension(Deserializer.class)
    private static final PlayerData.Deserializer DESERIALIZER = nbt -> {
        PlayerDataImpl data = new PlayerDataImpl(UUID.fromString(nbt.getString("PlayerUUID")));
        data.deserializeNBT(nbt);
        return data;
    };

    private final UUID playerUUID;

    private final BaseNBTList<PlayerSkillType, StringNBT> skills = new BaseNBTList<>(type -> StringNBT.valueOf(type.getRegistryName().toString()),
            nbt -> PlayerSkillType.REGISTRY.getValue(new ResourceLocation(nbt.getAsString())));

    public PlayerDataImpl(final UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    @Override
    public UUID getPlayerUUID() {
        return playerUUID;
    }

    @Override
    public List<PlayerSkillType> getSkills() {
        return skills;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return new NBTBuilder()
                .putStringUUID("PlayerUUID", playerUUID)
                .put("Skills", skills.serializeNBT())
                .build();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        skills.deserializeNBT(nbt.getCompound("Skills"));
    }
}
