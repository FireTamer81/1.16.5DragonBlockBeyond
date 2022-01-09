package io.github.firetamer.dbb;

import io.github.firetamer.dbb.client.DBBBlockStatesProvider;
import io.github.firetamer.dbb.client.DBBItemModelsProvider;
import io.github.firetamer.dbb.client.lang.EnUs;
import io.github.firetamer.dbb.common.DBBBlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = DragonBlockBeyond.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DBBDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            gen.addProvider(new DBBBlockStatesProvider(gen, existingFileHelper));
            gen.addProvider(new DBBItemModelsProvider(gen, existingFileHelper));

            gen.addProvider(new EnUs(gen));
        }

        if (event.includeServer()) {
            gen.addProvider(new DBBBlockTagsProvider(gen, existingFileHelper));
        }
    }

}
