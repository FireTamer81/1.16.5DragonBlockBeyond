package io.github.firetamer.dbb.api.extensions.dbb;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;

import io.github.firetamer.dbb.api.extensions.ApiExtendable;
import io.github.firetamer.dbb.api.player_data.PlayerSkill;
import io.github.firetamer.dbb.api.player_data.PlayerStat;
import io.github.firetamer.dbb.api.player_data.PlayerStatType;
import net.minecraftforge.common.util.INBTSerializable;

public interface PlayerData extends INBTSerializable<CompoundNBT> {

	UUID getPlayerUUID();

	List<PlayerSkill> getSkills();

	List<PlayerStat<?>> getStats();

	default <T> T getStatValueOrElse(PlayerStatType<T> statType, T orElse) {
		return Optional.ofNullable(getStatForType(statType)).map(PlayerStat::getValue)
				.orElse(orElse);
	}

	default <T> void setStatValue(PlayerStatType<T> statType, T newValue) {
		PlayerStat<T> stat = getStatForType(statType);
		if (stat == null) {
			getStats().add(new PlayerStat<>(statType, newValue));
		} else {
			stat.setValue(newValue);
		}
	}

	@SuppressWarnings("unchecked")
	@Nullable
	default <T> PlayerStat<T> getStatForType(PlayerStatType<T> type) {
		return getStats().stream().filter(stat -> stat.getStatType() == type)
				.map(stat -> (PlayerStat<T>) stat).findFirst().orElse(null);
	}

	default <T> void modifyStat(PlayerStatType<T> statType, Supplier<T> createIfNotExists, Consumer<T> modifer) {
		PlayerStat<T> stat = getStatForType(statType);
		if (stat == null) {
			stat = new PlayerStat<>(statType, createIfNotExists.get());
			modifer.accept(stat.getValue());
			getStats().add(stat);
		} else {
			modifer.accept(stat.getValue());
		}
	}

	@FunctionalInterface
	interface Deserializer extends ApiExtendable {

		PlayerData deserialize(CompoundNBT nbt);

	}

}
