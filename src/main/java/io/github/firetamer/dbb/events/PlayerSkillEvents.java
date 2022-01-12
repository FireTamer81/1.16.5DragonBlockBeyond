package io.github.firetamer.dbb.events;

import io.github.firetamer.dbb.api.client.ClientDataHolder;
import io.github.firetamer.dbb.api.extensions.dbb.PlayerDataManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class PlayerSkillEvents {

	public void register(final IEventBus forgeBus, final IEventBus modBus) {
		forgeBus.addListener(this::onPlayerTick);
	}

	private void onPlayerTick(final TickEvent.PlayerTickEvent event) {
		if (event.side.isServer()) {
			final ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) event.player;
			if (serverPlayerEntity.getServer() == null) {
				return;
			}
			PlayerDataManager.getManagerForServer(serverPlayerEntity.getServer())
					.getDataForPlayer(serverPlayerEntity).getSkills().forEach(skill -> skill.onPlayerServerTick(serverPlayerEntity, event.phase));
		}
	}

}
