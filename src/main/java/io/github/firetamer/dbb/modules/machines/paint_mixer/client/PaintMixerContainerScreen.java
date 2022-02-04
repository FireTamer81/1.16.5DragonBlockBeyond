package io.github.firetamer.dbb.modules.machines.paint_mixer.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.modules.machines.paint_mixer.containers.PaintMixerContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class PaintMixerContainerScreen extends ContainerScreen<PaintMixerContainer> {
    private final ResourceLocation TEXTURE = new ResourceLocation(DragonBlockBeyond.MOD_ID, "textures/gui/paint_mixer_screen.png");

    private final PaintMixerFunctionalGUI functionalGUI = new PaintMixerFunctionalGUI();

    /**
     The Mix animation lasts 12 seconds.
     That would be 240 ticks.

     I think I can solve the slot thing by actually saving the "isMixing" boolean in nbt since I think it never really changes without being saved... or something like that.
     **/

    public PaintMixerContainerScreen(PaintMixerContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
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
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == 256) { this.minecraft.player.closeContainer(); }

        //This shows that the parent screen can work with the child screen perfectly fine
        if (pKeyCode == 65) { functionalGUI.addNewTerminalMessage(new StringTextComponent("Hello")); }

        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        this.renderBg(pMatrixStack, pPartialTicks, pMouseX, pMouseY);
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        functionalGUI.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);

        //if (this.menu.) {
        //
        //}

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
