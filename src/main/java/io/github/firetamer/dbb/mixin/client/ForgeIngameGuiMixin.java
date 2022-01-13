package io.github.firetamer.dbb.mixin.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.api.client.overlay.IIngameOverlay;
import io.github.firetamer.dbb.api.client.overlay.OverlayRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ForgeIngameGui.class, remap = false, priority = 1050)
public abstract class ForgeIngameGuiMixin extends IngameGui {

	public ForgeIngameGuiMixin(Minecraft p_i46325_1_) {
		super(p_i46325_1_);
	}

	@Shadow
	private RenderGameOverlayEvent eventParent;

	@Shadow
	protected abstract boolean pre(RenderGameOverlayEvent.ElementType type, MatrixStack mStack);

	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;pre(Lnet/minecraftforge/client/event/RenderGameOverlayEvent$ElementType;Lcom/mojang/blaze3d/matrix/MatrixStack;)Z", ordinal = 0), method = "render(Lcom/mojang/blaze3d/matrix/MatrixStack;F)V")
	private boolean dbb$renderOverlays(ForgeIngameGui gui, RenderGameOverlayEvent.ElementType elementType, MatrixStack stack, MatrixStack stack2, float partialTicks) {
		boolean value = pre(elementType, stack);
		if (!value) {
			OverlayRegistry.orderedEntries().forEach(entry -> {
				try {
					if (!entry.isEnabled()) return;
					IIngameOverlay overlay = entry.getOverlay();
					if (pre(overlay, stack2)) return;
					overlay.render(gui, stack2, partialTicks, screenWidth, screenHeight);
					post(overlay, stack2);
				} catch (Exception e) {
					DragonBlockBeyond.LOGGER.error("Error rendering overlay '{}'", entry.getDisplayName(), e);
				}
			});
		}
		return value;
	}

	private boolean pre(IIngameOverlay overlay, MatrixStack mStack) {
		return MinecraftForge.EVENT_BUS.post(new IIngameOverlay.RenderPreLayer(mStack, eventParent, overlay));
	}

	private void post(IIngameOverlay overlay, MatrixStack mStack) {
		MinecraftForge.EVENT_BUS.post(new IIngameOverlay.RenderPostLayer(mStack, eventParent, overlay));
	}


}
