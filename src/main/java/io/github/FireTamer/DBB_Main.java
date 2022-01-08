package io.github.FireTamer;

import com.matyrobbrt.lib.ClientSetup;
import com.matyrobbrt.lib.ModSetup;
import com.matyrobbrt.lib.registry.annotation.AnnotationProcessor;
import io.github.FireTamer.common.DBB_AnnotationProcessor;
import io.github.FireTamer.common.worldgen.OreGeneration;
import io.github.FireTamer.common.itemGroups.DBB_Blocks_ItemGroup;
import io.github.FireTamer.common.itemGroups.DBB_ItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.Optional;
import java.util.function.Supplier;

@Mod("dragonblockbeyond")
public class DBB_Main extends ModSetup
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "dragonblockbeyond";
    public static final ItemGroup MAIN_GROUP = new DBB_ItemGroup("dbb_main_itemgroup");
    public static final ItemGroup BLOCKS_GROUP = new DBB_Blocks_ItemGroup("dbb_blocks_itemgroup");
    public static final DBB_AnnotationProcessor ANNOTATION_PROCESSOR = new DBB_AnnotationProcessor(MOD_ID);
    //public static final File CONFIF_DIR = new File(CONFIG_DIR_PATH);




    public DBB_Main() {
        super(MOD_ID);

        GeckoLib.initialize();

        modBus.addListener(this::setup);
        //modBus.addListener(this::gatherData);
        forgeBus.addListener(EventPriority.HIGH, this::onServerStart);
        //forgeBus.addListener(EventPriority.HIGH, this::onRegisterCommands);
        forgeBus.addListener(EventPriority.HIGH, OreGeneration::generateOres);


        /**
        if (!CONFIF_DIR.exists()) {
            LOGGER.info("Created DBB config folder!");
            CONFIF_DIR.mkdirs();
        }

        ANNOTATION_PROCESSOR.afterInit(() -> {
            CommonConfig.register();
            ClientConfig.register();
            ServerConfig.register();
        });
         **/

        ANNOTATION_PROCESSOR.setAutoBlockItemTab(block -> BLOCKS_GROUP);


        MinecraftForge.EVENT_BUS.register(this);
    }




    @Override
    public AnnotationProcessor annotationProcessor() {
        return ANNOTATION_PROCESSOR;
    }

    @Override
    public Optional<Supplier<ClientSetup>> clientSetup() {
        return Optional.of(() -> new ClientSetup(modBus));
    }

    private void setup(final FMLCommonSetupEvent event) {}


    /**
    public void onRegisterCommands(final RegisterCommandsEvent event) {
        CommandInit.registerCommands(event);
    }
     **/

    // Load all saved data
    public void onServerStart(final FMLServerStartingEvent event) {
        MinecraftServer server = event.getServer();
    }


    /**
     * Currently does not work, cannot figure out why in the slightest
     * Might be something to do with how MatyLib registers the Blocks and Items, but Matylib makes things easier,
     * so no Datagen might be managable.
     * Especially since I might be able to get all 240 variations of the strong blocks into just 5 baked models
     *
    public void gatherData(GatherDataEvent event)
    {
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
    **/
}
