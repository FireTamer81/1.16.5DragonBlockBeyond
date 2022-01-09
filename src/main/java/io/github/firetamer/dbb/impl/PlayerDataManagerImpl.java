package io.github.firetamer.dbb.impl;

import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.annotation.Nonnull;

import com.matyrobbrt.lib.nbt.BaseNBTMap;

import io.github.firetamer.dbb.util.nbt.NBTBuilder;
import io.github.firetamer.dbb.util.nbt.NBTReader;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.api.extensions.ApiExtension;
import io.github.firetamer.dbb.api.extensions.ApiExtensions;
import io.github.firetamer.dbb.api.extensions.dbb.PlayerData;
import io.github.firetamer.dbb.api.extensions.dbb.PlayerDataManager;

class PlayerDataManagerImpl extends WorldSavedData implements PlayerDataManager {

	private static final String ID = DragonBlockBeyond.MOD_ID + "_player_data";

	@ApiExtension(Getter.class)
	public static final PlayerDataManager.Getter GETTER = server -> server.getLevel(World.OVERWORLD).getDataStorage()
			.computeIfAbsent(() -> new PlayerDataManagerImpl(ID), ID);

	@ApiExtension(Deserializer.class)
	public static final PlayerDataManager.Deserializer DESERIALIZER = nbt -> {
		PlayerDataManagerImpl manager = new PlayerDataManagerImpl(ID);
		manager.deserializeNBT(nbt);
		return manager;
	};

	private final PlayerDataMap playerData = new PlayerDataMap();

	private PlayerDataManagerImpl(String name) {
		super(name);
	}

	@Nonnull
	@Override
	public PlayerData getDataForPlayer(UUID playerUUID) {
		return playerData.computeIfAbsent(playerUUID, PlayerDataImpl::new);
	}

	@Override
	public Map<UUID, PlayerData> getAllPlayerData() {
		return playerData.locked();
	}

	@Override
	public void setChanged() {
		setDirty(true);
	}

	@Override
	public void load(CompoundNBT nbt) {
		NBTReader.of(nbt)
				.load("PlayerData", playerData);
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		return NBTBuilder.of(nbt)
				.put("PlayerData", playerData.serializeNBT())
				.build();
	}

	static class PlayerDataMap extends BaseNBTMap<UUID, PlayerData, StringNBT, CompoundNBT> {

		private boolean isLocked = false;

		public PlayerDataMap(Map<? extends UUID, ? extends PlayerData> otherMap) {
			this();
			putAll(otherMap);
		}

		public PlayerDataMap() {
			super(uuid -> StringNBT.valueOf(uuid.toString()), PlayerData::serializeNBT,
					nbt -> UUID.fromString(nbt.getAsString()),
					nbt -> ApiExtensions.withExtension(PlayerData.Deserializer.class, ext -> ext.deserialize(nbt)));
		}

		@Override
		public PlayerData get(Object key) {
			if (key instanceof UUID) {
				if (isLocked) {
					return super.get(key);
				} else {
					return computeIfAbsent((UUID) key, PlayerDataImpl::new);
				}
			}
			return super.get(key);
		}

		public PlayerDataMap locked() {
			PlayerDataMap newMap = new PlayerDataMap(this);
			newMap.isLocked = true;
			return newMap;
		}

		@Override
		public void clear() {
			if (isLocked) {
				return;
			}
			super.clear();
		}

		@Override
		public PlayerData put(UUID key, PlayerData value) {
			if (isLocked) {
				return get(key);
			}
			return super.put(key, value);
		}

		@Override
		public void putAll(Map<? extends UUID, ? extends PlayerData> m) {
			if (isLocked) {
				return;
			}
			super.putAll(m);
		}

		@Override
		public PlayerData putIfAbsent(UUID key, PlayerData value) {
			if (isLocked) {
				return get(key);
			}
			return super.putIfAbsent(key, value);
		}

		@Override
		public PlayerData computeIfAbsent(UUID key, Function<? super UUID, ? extends PlayerData> mappingFunction) {
			if (isLocked) {
				return get(key);
			}
			return super.computeIfAbsent(key, mappingFunction);
		}

		@Override
		public PlayerData compute(UUID key, BiFunction<? super UUID, ? super PlayerData, ? extends PlayerData> remappingFunction) {
			if (isLocked) {
				return get(key);
			}
			return super.compute(key, remappingFunction);
		}

		@Override
		public PlayerData computeIfPresent(UUID key, BiFunction<? super UUID, ? super PlayerData, ? extends PlayerData> remappingFunction) {
			if (isLocked) {
				return get(key);
			}
			return super.computeIfPresent(key, remappingFunction);
		}

		@Override
		public PlayerData remove(Object key) {
			if (isLocked) {
				return get(key);
			}
			return super.remove(key);
		}

		@Override
		public boolean remove(Object key, Object value) {
			if (isLocked) {
				return false;
			}
			return super.remove(key, value);
		}

		@Override
		protected boolean removeEldestEntry(Map.Entry<UUID, PlayerData> eldest) {
			if (isLocked) {
				return false;
			}
			return super.removeEldestEntry(eldest);
		}
	}
}
