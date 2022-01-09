package io.github.firetamer.dbb.api.extensions.dbb;

import io.github.firetamer.dbb.api.extensions.ApiExtendable;
import io.github.firetamer.dbb.api.player_data.PlayerSkill;
import io.github.firetamer.dbb.api.player_data.PlayerStat;
import io.github.firetamer.dbb.api.player_data.PlayerStatType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;
import java.util.UUID;

public interface PlayerData extends INBTSerializable<CompoundNBT> {

	UUID getPlayerUUID();

	List<PlayerSkill> getSkills();

	List<PlayerStat<?>> getStats();

	@SuppressWarnings("unchecked")
	default <T> PlayerStat<T> getStatForType(PlayerStatType<T> type) {
		return getStats().stream().filter(stat -> stat.getStatType() == type)
				.map(stat -> (PlayerStat<T>) stat).findFirst().orElse(null);
	}

	@FunctionalInterface
	interface Deserializer extends ApiExtendable {

		PlayerData deserialize(CompoundNBT nbt);

	}

}
