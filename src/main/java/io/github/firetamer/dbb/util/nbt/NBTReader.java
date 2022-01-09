package io.github.firetamer.dbb.util.nbt;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;

public class NBTReader {

	private final CompoundNBT nbt;

	private NBTReader(CompoundNBT nbt) {
		this.nbt = nbt;
	}

	public static NBTReader of(CompoundNBT nbt) {
		return new NBTReader(nbt);
	}

	@SuppressWarnings("unchecked")
	public <NBT extends INBT> NBTReader load(@Nonnull String key, @Nonnull INBTSerializable<NBT> object) {
		object.deserializeNBT((NBT) nbt.tags.get(key));
		return this;
	}

	public boolean contains(String key) {
		return nbt.contains(key);
	}
}
