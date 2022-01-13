package io.github.firetamer.dbb.events;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.api.client.overlay.IIngameOverlay;
import io.github.firetamer.dbb.api.client.overlay.OverlayRegistry;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonBlockBeyond.MOD_ID)
public class OverlayEventHandler {

	/*public static void renderOverlays(final RenderGameOverlayEvent.Pre event) {
		OverlayRegistry.orderedEntries().forEach(entry -> {
			try
			{
				if (!entry.isEnabled()) return;
				IIngameOverlay overlay = entry.getOverlay();
				overlay.render(this, pStack, partialTicks, screenWidth, screenHeight);
				post(overlay, pStack);
			}
			catch(Exception e)
			{
				LOGGER.error("Error rendering overlay '{}'", entry.getDisplayName(), e);
			}
		});
	}*/

}
