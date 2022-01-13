package io.github.firetamer.dbb.util.nbt;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

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

	public NBTReader loadInt(@Nonnull String key, @Nonnull Consumer<Integer> setter) {
		setter.accept(nbt.getInt(key));
		return this;
	}

	public NBTReader loadString(@Nonnull String key, @Nonnull Consumer<String> setter) {
		setter.accept(nbt.getString(key));
		return this;
	}

	public NBTReader loadStack(@Nonnull String key, @Nonnull Consumer<ItemStack> setter) {
		setter.accept(ItemStack.of(nbt.getCompound(key)));
		return this;
	}

	public boolean contains(String key) {
		return nbt.contains(key);
	}
}
