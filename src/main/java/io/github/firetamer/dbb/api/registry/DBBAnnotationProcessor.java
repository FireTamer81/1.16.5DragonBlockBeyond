package io.github.firetamer.dbb.api.registry;

import static java.util.Optional.of;
import com.matyrobbrt.lib.registry.annotation.AnnotationProcessor;
import io.github.firetamer.dbb.api.player_data.PlayerSkill;
import io.github.firetamer.dbb.api.player_data.PlayerStatType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBBAnnotationProcessor extends AnnotationProcessor {

    public static final Map<String, List<PlayerSkill>> PLAYER_SKILLS = new HashMap<>();
    public static final Map<String, List<PlayerStatType<?>>> PLAYER_STAT_TYPES = new HashMap<>();

    public DBBAnnotationProcessor(String modid) {
        super(modid);
    }

    @Override
    public void register(IEventBus modBus) {
        super.register(modBus);
        addListenerIfNotIgnored(PlayerSkill.class, this::registerPlayerSkills);
        addListenerIfNotIgnored(PlayerStatType.class, this::registerPlayerStatTypes);
    }

    private void registerPlayerSkills(final RegistryEvent.Register<PlayerSkill> event) {
        registerFieldsWithAnnotation(event, RegisterPlayerSkill.class, RegisterPlayerSkill::value, of(PLAYER_SKILLS));
    }

    private void registerPlayerStatTypes(final RegistryEvent.Register<PlayerStatType<?>> event) {
        registerFieldsWithAnnotation(event, RegisterPlayerStatType.class, RegisterPlayerStatType::value, of(PLAYER_STAT_TYPES));
    }

}
