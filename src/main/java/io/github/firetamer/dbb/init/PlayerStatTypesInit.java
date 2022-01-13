package io.github.firetamer.dbb.init;

import com.matyrobbrt.lib.registry.annotation.RegistryHolder;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.api.player_data.PlayerStatType;
import io.github.firetamer.dbb.api.registry.RegisterPlayerStatType;
import io.github.firetamer.dbb.util.nbt.NBTBuilder;
import io.github.firetamer.dbb.util.nbt.NBTReader;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.function.Function;

@RegistryHolder(modid = DragonBlockBeyond.MOD_ID)
public class PlayerStatTypesInit {

	private static final Function<INBT, Integer> INT_DESERIALIZER = nbt -> {
		if (nbt instanceof IntNBT) {
			return ((IntNBT) nbt).getAsInt();
		}
		return 0;
	};

	@RegisterPlayerStatType("test")
	public static final PlayerStatType<Integer> TEST_TYPE = new PlayerStatType<>(IntNBT::valueOf, INT_DESERIALIZER);

	@RegisterPlayerStatType("test_2")
	public static final PlayerStatType<CustomTestData> TEST_TYPE_2 = new PlayerStatType<CustomTestData>(CustomTestData::serializeNBT,
			nbt -> CustomTestData.fromNBT((CompoundNBT) nbt));

	public static class CustomTestData implements INBTSerializable<CompoundNBT> {

		public static CustomTestData fromNBT(CompoundNBT nbt) {
			CustomTestData data = new CustomTestData();
			data.deserializeNBT(nbt);
			return data;
		}

		public int randomInt;
		public ItemStack randomStack = ItemStack.EMPTY;
		public String randomString = "";

		@Override
		public CompoundNBT serializeNBT() {
			return new NBTBuilder()
					.putInt("randomInt", randomInt)
					.put("randomStack", randomStack.serializeNBT())
					.putString("randomString", randomString)
					.build();
		}

		@Override
		public void deserializeNBT(CompoundNBT nbt) {
			NBTReader.of(nbt)
					.loadInt("randomInt", i -> randomInt = i)
					.loadString("randomString", s -> randomString = s)
					.loadStack("randomStack", s -> randomStack = s);
		}
	}
}
