package io.github.firetamer.dbb;

import java.util.Optional;
import java.util.function.Supplier;

import io.github.firetamer.dbb.client.DBBClientSetup;
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

        forgeBus.addListener(EventPriority.HIGH, OreGeneration::generateOres);

        ANNOTATION_PROCESSOR.setAutoBlockItemTab(block -> BLOCKS_GROUP);
    }


    @Override
    public AnnotationProcessor annotationProcessor() {
        return ANNOTATION_PROCESSOR;
    }

    @Override
    public Optional<Supplier<ClientSetup>> clientSetup() {
        return Optional.of(() -> new DBBClientSetup(modBus));
    }

}
