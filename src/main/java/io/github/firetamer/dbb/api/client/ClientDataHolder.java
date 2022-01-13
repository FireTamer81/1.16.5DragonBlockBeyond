package io.github.firetamer.dbb.api.client;

import com.matyrobbrt.lib.network.INetworkMessage;
import io.github.firetamer.dbb.api.extensions.ApiExtensions;
import io.github.firetamer.dbb.api.extensions.dbb.PlayerDataManager;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ClientDataHolder {

	private static PlayerDataManager playerDataManager;

	static void setPlayerDataManager(@Nonnull PlayerDataManager dataManager) {
		playerDataManager = dataManager;
	}

	@Nullable
	public static PlayerDataManager getPlayerDataManager() {
		return playerDataManager;
	}

	public static class PlayerDataSyncMessage implements INetworkMessage {

		private final PlayerDataManager dataManager;

		public PlayerDataSyncMessage(PlayerDataManager dataManager) {
			this.dataManager = dataManager;
		}

		@Override
		public void handle(NetworkEvent.Context context) {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> executeClient(context, this));
		}

		private static void executeClient(NetworkEvent.Context context, PlayerDataSyncMessage msg) {
			setPlayerDataManager(msg.dataManager);
		}

		@Override
		public void encode(PacketBuffer buffer) {
			buffer.writeNbt(dataManager.serializeNBT());
		}

		public static PlayerDataSyncMessage decode(final PacketBuffer buffer) {
			return new PlayerDataSyncMessage(ApiExtensions.withExtension(PlayerDataManager.Deserializer.class,
					deserializer -> deserializer.deserialize(buffer.readNbt())));
		}
	}

}
