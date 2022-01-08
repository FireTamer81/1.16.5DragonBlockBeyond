package io.github.firetamer.dbb.events;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.api.player_data.PlayerDataManager;
import io.github.firetamer.dbb.api.player_data.skill.PlayerSkillType;
import io.github.firetamer.dbb.util.DBBResourceLocation;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ForgeEvents {

    @SubscribeEvent
    public static void onItemDropped(final ItemTossEvent event) {
        PlayerDataManager dataManager = PlayerDataManager.getManagerForServer(event.getPlayer().getServer());
        dataManager.getDataForPlayer(event.getPlayer()).getSkills().add(DragonBlockBeyond.WEIRD_SKILL_TYPE);
        dataManager.setChanged();
    }

}
