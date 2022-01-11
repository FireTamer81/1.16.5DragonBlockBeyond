import com.mojang.blaze3d.systems.RenderSystem;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.api.client.overlay.OverlayRegistry;
import io.github.firetamer.dbb.modules.gui_testing.player_screen.PlayerScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DragonBlockBeyond.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TestOverlay {

	@SubscribeEvent
	public static void registerOverlay(final FMLClientSetupEvent event) {
		OverlayRegistry.registerOverlayBottom("hi", (gui, matrixStack, partialTicks, width, height) -> {
			Minecraft.getInstance().getTextureManager().bind(PlayerScreen.GEAR_LOCATION);
			gui.blit(matrixStack, 0, 0, 0, 0, 97, 95);
		});
	}

}
