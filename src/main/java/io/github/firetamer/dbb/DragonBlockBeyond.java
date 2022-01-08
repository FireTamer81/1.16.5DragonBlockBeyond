package io.github.firetamer.dbb;

import java.util.Optional;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.matyrobbrt.lib.ClientSetup;
import com.matyrobbrt.lib.ModSetup;
import com.matyrobbrt.lib.registry.annotation.AnnotationProcessor;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;

import io.github.firetamer.dbb._testing.datagen.DBB_BlockStatesProvider;
import io.github.firetamer.dbb._testing.datagen.DBB_BlockTagsProvider;
import io.github.firetamer.dbb._testing.datagen.DBB_ItemModelsProvider;
import io.github.firetamer.dbb.common.DBBAnnotationProcessor;
import io.github.firetamer.dbb.common.itemGroups.DBB_Blocks_ItemGroup;
import io.github.firetamer.dbb.common.itemGroups.DBB_ItemGroup;
import io.github.firetamer.dbb.common.worldgen.OreGeneration;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import software.bernie.geckolib3.GeckoLib;

@Mod(DragonBlockBeyond.MOD_ID)
public class DragonBlockBeyond extends ModSetup {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "dragonblockbeyond";
    public static final ItemGroup MAIN_GROUP = new DBB_ItemGroup("dbb_main_itemgroup");
    public static final ItemGroup BLOCKS_GROUP = new DBB_Blocks_ItemGroup("dbb_blocks_itemgroup");
    public static final DBBAnnotationProcessor ANNOTATION_PROCESSOR = new DBBAnnotationProcessor(MOD_ID);


    public DragonBlockBeyond() {
        super(MOD_ID);

        GeckoLib.initialize();

        modBus.addListener(this::setup);
        modBus.addListener(this::gatherData);
        forgeBus.addListener(EventPriority.HIGH, OreGeneration::generateOres);

        ANNOTATION_PROCESSOR.setAutoBlockItemTab(block -> BLOCKS_GROUP);
    }


    @Override
    public AnnotationProcessor annotationProcessor() {
        return ANNOTATION_PROCESSOR;
    }

    @Override
    public Optional<Supplier<ClientSetup>> clientSetup() {
        return Optional.empty();
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    /**
     * Currently does not work, cannot figure out why in the slightest
     * Might be something to do with how MatyLib registers the Blocks and Items, but Matylib makes things easier,
     * so no Datagen might be managable.
     * Especially since I might be able to get all 240 variations of the strong blocks into just 5 baked models
     */
    public void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new DBB_BlockStatesProvider(gen, existingFileHelper));
        gen.addProvider(new DBB_ItemModelsProvider(gen, existingFileHelper));
        gen.addProvider(new DBB_BlockTagsProvider(gen, existingFileHelper));

        //The BlockTagsProvider is in a variable because the ItemTagsProvider requires it, so the variable makes sure it loads first or something.
        //DBEBlockTagsProvider blockTags = new DBEBlockTagsProvider(gen, existingFileHelper);
        //gen.addProvider(blockTags);
        //gen.addProvider(new DBEItemTagsProvider(gen, blockTags, existingFileHelper));
    }

}
