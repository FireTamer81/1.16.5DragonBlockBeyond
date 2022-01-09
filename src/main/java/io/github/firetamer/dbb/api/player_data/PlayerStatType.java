package io.github.firetamer.dbb.api.player_data;

import com.matyrobbrt.lib.registry.annotation.RegisterCustomRegistry;
import com.matyrobbrt.lib.registry.annotation.RegistryHolder;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.util.helper.CustomRegistryHelper;
import io.github.firetamer.dbb.util.objects.TargetField;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Optional;
import java.util.function.Function;

@RegistryHolder(modid = DragonBlockBeyond.MOD_ID)
public class PlayerStatType<T> extends ForgeRegistryEntry<PlayerStatType<?>> {

    public final Function<T, INBT> valueSerializer;
    public final Function<INBT, T> valueDeserializer;

    public PlayerStatType(Function<T, INBT> valueSerializer, Function<INBT, T> valueDeserializer) {
        this.valueSerializer = valueSerializer;
        this.valueDeserializer = valueDeserializer;
    }

    public static final IForgeRegistry<PlayerStatType<?>> REGISTRY = null;

    @RegisterCustomRegistry
    public static void createRegistries(RegistryEvent.NewRegistry event) {
        CustomRegistryHelper.<PlayerStatType<?>>registerRegistry(
                new TargetField(PlayerStatType.class, "REGISTRY"),
                withWildcard(PlayerStatType.class), new ResourceLocation(DragonBlockBeyond.MOD_ID, "player_stat_type"),
                Optional.empty(), Optional.empty());
    }

    @SuppressWarnings("unchecked")
    private static <Z> Class<Z> withWildcard(Class<?> cls) {
        return (Class<Z>) cls;
    }

}
