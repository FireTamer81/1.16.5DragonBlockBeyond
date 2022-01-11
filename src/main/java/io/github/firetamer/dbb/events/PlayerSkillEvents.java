package io.github.firetamer.dbb.events;

import io.github.firetamer.dbb.api.client.ClientDataHolder;
import io.github.firetamer.dbb.api.extensions.dbb.PlayerDataManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class PlayerSkillEvents {

	public void register(final IEventBus forgeBus, final IEventBus modBus) {

	}

	private void onPlayerTick(final TickEvent.PlayerTickEvent event) {
		if (event.side.isServer()) {
			final ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) event.player;
			if (serverPlayerEntity.getServer() == null) {
				return;
			}
			PlayerDataManager.getManagerForServer(serverPlayerEntity.getServer())
					.getDataForPlayer(serverPlayerEntity).getSkills().forEach(skill -> skill.onPlayerServerTick(serverPlayerEntity, event.phase));
		} else {
			// There is no import due to this class being loaded on both client and server, but the client
			// player entity class is Client-side only
			ClientDataHolder.getPlayerDataManager().getDataForPlayer(event.player)
					.getSkills().forEach(skill -> skill.onPlayerClientTick((net.minecraft.client.entity.player.ClientPlayerEntity) event.player, event.phase));
		}
	}

}
