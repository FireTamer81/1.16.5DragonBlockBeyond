package io.github.firetamer.dbb.events;

import io.github.firetamer.dbb.api.extensions.dbb.PlayerDataManager;
import io.github.firetamer.dbb.init.PlayerStatTypesInit;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class ForgeEvents {

    public static final Random RAND = new Random();

    @SubscribeEvent
    public static void onItemDropped(final ItemTossEvent event) {
        PlayerDataManager dataManager = PlayerDataManager.getManagerForServer(event.getPlayer().getServer());

        dataManager.getDataForPlayer(event.getPlayer())
                        .modifyStat(PlayerStatTypesInit.TEST_TYPE_2, PlayerStatTypesInit.CustomTestData::new, data ->{
                            data.randomInt = RAND.nextInt(1346);
                        });

        dataManager.setChanged();
    }

}
