package io.github.firetamer.dbb.datagen;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.datagen.client.DBBBlockStatesProvider;
import io.github.firetamer.dbb.datagen.client.DBBItemModelsProvider;
import io.github.firetamer.dbb.datagen.client.lang.EnUs;
import io.github.firetamer.dbb.datagen.common.DBBBlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = DragonBlockBeyond.MOD_ID)
public class DBBDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new DBBBlockStatesProvider(gen, existingFileHelper));
        gen.addProvider(new DBBItemModelsProvider(gen, existingFileHelper));
        gen.addProvider(new DBBBlockTagsProvider(gen, existingFileHelper));

        gen.addProvider(new EnUs(gen));
    }

}
