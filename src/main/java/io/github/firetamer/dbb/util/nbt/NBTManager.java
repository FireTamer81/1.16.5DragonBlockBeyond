package io.github.firetamer.dbb.util.nbt;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NBTManager implements INBTSerializable<CompoundNBT> {

	private final Map<String, INBTSerializable<?>> data = new HashMap<>();

	public NBTManager track(@Nonnull String key, @Nonnull INBTSerializable<?> toTrack) {
		data.put(key, toTrack);
		return this;
	}

	@Override
	public CompoundNBT serializeNBT() {
		NBTBuilder builder = new NBTBuilder();
		data.forEach((key, serializable) -> builder.put(key, serializable.serializeNBT()));
		return builder.build();
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		NBTReader reader = NBTReader.of(nbt);
		data.forEach((key, serializable) -> {
			if (reader.contains(key)) {
				reader.load(key, serializable);
			}
		});
	}
}
