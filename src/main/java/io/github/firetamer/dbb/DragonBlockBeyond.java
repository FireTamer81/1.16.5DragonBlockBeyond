package io.github.firetamer.dbb;

import java.util.Optional;
import java.util.function.Supplier;

import io.github.firetamer.dbb.api.extensions.ApiExtensions;
import io.github.firetamer.dbb.api.extensions.dbb.PlayerDataManager;
import io.github.firetamer.dbb.api.player_data.PlayerSkill;
import io.github.firetamer.dbb.client.DBBClientSetup;
import io.github.firetamer.dbb.events.PlayerSkillEvents;
import io.github.firetamer.dbb.network.DBBNetwork;
import io.github.firetamer.dbb.util.DBBResourceLocation;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.matyrobbrt.lib.ClientSetup;
import com.matyrobbrt.lib.ModSetup;
import com.matyrobbrt.lib.registry.annotation.AnnotationProcessor;

import net.minecraft.item.ItemGroup;

import io.github.firetamer.dbb.common.DBBAnnotationProcessor;
import io.github.firetamer.dbb.common.item_groups.DBBBlocksItemGroup;
import io.github.firetamer.dbb.common.item_groups.DBBItemGroup;
import io.github.firetamer.dbb.common.worldgen.OreGeneration;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.GeckoLib;

@Mod(DragonBlockBeyond.MOD_ID)
public class DragonBlockBeyond extends ModSetup {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "dragonblockbeyond";
    public static final ItemGroup MAIN_GROUP = new DBBItemGroup("dbb_main_itemgroup");
    public static final ItemGroup BLOCKS_GROUP = new DBBBlocksItemGroup("dbb_blocks_itemgroup");
    public static final DBBAnnotationProcessor ANNOTATION_PROCESSOR = new DBBAnnotationProcessor(MOD_ID);


    public DragonBlockBeyond() {
        super(MOD_ID);

        GeckoLib.initialize();

        modBus.addGenericListener(PlayerSkill.class, DragonBlockBeyond::registerSkillTypes);

        forgeBus.addListener(EventPriority.HIGH, OreGeneration::generateOres);

        ANNOTATION_PROCESSOR.setAutoBlockItemTab(block -> BLOCKS_GROUP);

        ApiExtensions.registerAnnotationExtensions();

        new PlayerSkillEvents().register(forgeBus, modBus);

    }

    @Override
    public void onCommonSetup(FMLCommonSetupEvent event) {
        DBBNetwork.INSTANCE.register();
    }

    @Override
    public AnnotationProcessor annotationProcessor() {
        return ANNOTATION_PROCESSOR;
    }

    @Override
    public Optional<Supplier<ClientSetup>> clientSetup() {
        return Optional.of(() -> new DBBClientSetup(modBus));
    }

    public static final PlayerSkill WEIRD_SKILL_TYPE = new PlayerSkill().setRegistryName(new DBBResourceLocation("weird"));

    public static void registerSkillTypes(final RegistryEvent.Register<PlayerSkill> event) {
        event.getRegistry().register(WEIRD_SKILL_TYPE);
    }

    @SubscribeEvent
    public void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent e) {
        if (e.getEntity().level.isClientSide) {
            return;
        }
		PlayerDataManager.getManagerForServer(e.getEntity().getServer()).syncWithPlayer((ServerPlayerEntity) e.getPlayer());
    }
}
