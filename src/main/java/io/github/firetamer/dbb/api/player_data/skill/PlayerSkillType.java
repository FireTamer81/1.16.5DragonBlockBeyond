package io.github.firetamer.dbb.api.player_data.skill;

import com.matyrobbrt.lib.registry.annotation.RegisterCustomRegistry;
import com.matyrobbrt.lib.registry.annotation.RegistryHolder;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.util.helper.CustomRegistryHelper;
import io.github.firetamer.dbb.util.objects.TargetField;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Optional;

@RegistryHolder(modid = DragonBlockBeyond.MOD_ID)
public class PlayerSkillType extends ForgeRegistryEntry<PlayerSkillType> {

    public static final IForgeRegistry<PlayerSkillType> REGISTRY = null;

    @RegisterCustomRegistry
    public static void createRegistries(RegistryEvent.NewRegistry event) {
        CustomRegistryHelper.<PlayerSkillType>registerRegistry(
                new TargetField(PlayerSkillType.class, "REGISTRY"),
                PlayerSkillType.class, new ResourceLocation(DragonBlockBeyond.MOD_ID, "player_skill_type"),
                Optional.empty(), Optional.empty());
    }

}
