package io.github.firetamer.dbb.api.client.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * This interface represents an overlay that will be rendered on top of the GUI.
 * Registering is done through methods in {@link OverlayRegistry}.
 *
 * @see OverlayRegistry
 */
@FunctionalInterface
public interface IIngameOverlay {

	/**
	 * Render the overlay
	 * @param gui the gui on which the overlay is rendered
	 * @param matrixStack the {@link MatrixStack}
	 * @param partialTicks partialTicks
	 * @param width the gui width
	 * @param height the gui height
	 */
	void render(ForgeIngameGui gui, MatrixStack matrixStack, float partialTicks, int width, int height);

	@Cancelable
	class RenderPreLayer extends RenderGameOverlayEvent.Pre {
		private final IIngameOverlay overlay;

		public RenderPreLayer(MatrixStack mStack, RenderGameOverlayEvent parent, IIngameOverlay overlay) {
			// TODO fix element type
			super(mStack, parent, ElementType.VIGNETTE);
			this.overlay = overlay;
		}

		@Override
		public boolean isCancelable() {
			return true;
		}

		public IIngameOverlay getOverlay() {
			return overlay;
		}
	}

	class RenderPostLayer extends RenderGameOverlayEvent.Post {
		private final IIngameOverlay overlay;

		public RenderPostLayer(MatrixStack mStack, RenderGameOverlayEvent parent, IIngameOverlay overlay) {
			// TODO fix element type
			super(mStack, parent, ElementType.VIGNETTE);
			this.overlay = overlay;
		}

		public IIngameOverlay getOverlay() {
			return overlay;
		}
	}

}
