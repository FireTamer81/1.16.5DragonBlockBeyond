package io.github.firetamer.dbb.modules.strong_block.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.modules.player_gui_module.player_screen.children.MenuSelectorGui;
import io.github.firetamer.dbb.modules.strong_block.containers.PaintMixerContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PaintMixerScreen extends ContainerScreen<PaintMixerContainer> {
    private final ResourceLocation TEXTURE = new ResourceLocation(DragonBlockBeyond.MOD_ID, "textures/gui/paint_mixer_screen.png");

    private final PaintMixerFunctionalGUI functionalGUI = new PaintMixerFunctionalGUI();

    public PaintMixerScreen(PaintMixerContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 256;
        this.imageHeight = 186;

        this.inventoryLabelX = (this.width / 2) - 128;
        this.inventoryLabelY = (this.height / 2) - 93;
    }

    @Override
    protected void init() {
        super.init();

        this.functionalGUI.init(minecraft, this.width, this.height);
        this.children.add(this.functionalGUI);
        this.setInitialFocus(this.functionalGUI);
    }

    @Override
    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        this.renderBg(pMatrixStack, pPartialTicks, pMouseX, pMouseY);
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        functionalGUI.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        this.renderTooltip(pMatrixStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(MatrixStack pMatrixStack, float pPartialTicks, int pX, int pY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(TEXTURE);
        int x = this.getGuiLeft();
        int y = this.getGuiTop();
        this.blit(pMatrixStack, x, y, 0, 0, this.getXSize(), this.getYSize());
    }
}
