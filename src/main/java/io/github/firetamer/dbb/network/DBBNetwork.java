package io.github.firetamer.dbb.network;

import com.matyrobbrt.lib.network.BaseNetwork;
import com.matyrobbrt.lib.network.INetworkMessage;
import io.github.firetamer.dbb.api.client.ClientDataHolder;
import io.github.firetamer.dbb.util.DBBResourceLocation;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;
import java.util.function.Function;

public class DBBNetwork {

	public static final String NETWORK_VERSION = "0.1.0";

	public static final DBBNetwork INSTANCE = new DBBNetwork();

	public final SimpleChannel mainChannel = newSimpleChannel("main");

	protected int ID = 0;

	protected int nextId() {
		return ID++;
	}

	public void register() {
		registerServerToClient(ClientDataHolder.PlayerDataSyncMessage.class, ClientDataHolder.PlayerDataSyncMessage::decode);
	}

	private <M extends INetworkMessage> void registerClientToServer(Class<M> type,
																	Function<PacketBuffer, M> decoder) {
		registerMessage(mainChannel, type, decoder, NetworkDirection.PLAY_TO_SERVER);
	}

	private <M extends INetworkMessage> void registerServerToClient(Class<M> type,
																	Function<PacketBuffer, M> decoder) {
		registerMessage(mainChannel, type, decoder, NetworkDirection.PLAY_TO_CLIENT);
	}

	private <M extends INetworkMessage> void registerMessage(SimpleChannel channel, Class<M> msgClass,
															 Function<PacketBuffer, M> decoder, NetworkDirection direction) {
		channel.registerMessage(nextId(), msgClass, INetworkMessage::encode, decoder, INetworkMessage::handle,
				Optional.of(direction));
	}


	private static SimpleChannel newSimpleChannel(String name) {
		return NetworkRegistry.newSimpleChannel(new DBBResourceLocation(name), () -> NETWORK_VERSION,
				version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	}

}
