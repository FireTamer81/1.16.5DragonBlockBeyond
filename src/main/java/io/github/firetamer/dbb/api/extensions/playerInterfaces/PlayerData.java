package io.github.firetamer.dbb.api.extensions.playerInterfaces;

import io.github.firetamer.dbb.api.extensions.ApiExtendable;
import io.github.firetamer.dbb.api.player_data.skill.PlayerSkillType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;
import java.util.UUID;

public interface PlayerData extends INBTSerializable<CompoundNBT> {

    UUID getPlayerUUID();

    List<PlayerSkillType> getSkills();

    @FunctionalInterface
    interface Deserializer extends ApiExtendable {

        PlayerData deserialize(CompoundNBT nbt);

    }

}
