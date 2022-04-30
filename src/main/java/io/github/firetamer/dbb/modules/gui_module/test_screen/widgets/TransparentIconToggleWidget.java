package io.github.firetamer.dbb.modules.gui_module.test_screen.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.firetamer.dbb.modules.gui_module.util.GUIHelper;
import io.github.firetamer.dbb.modules.gui_module.util.element_definitions.GUIColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TransparentIconToggleWidget extends Widget {
    protected ResourceLocation iconTextureResource;
    protected boolean isStateTriggered;
    protected int texTopLeftX;
    protected int texTopLeftY;
    protected int texWidth;
    protected int texHeight;
    protected GUIColor backgroundColor;
    protected GUIColor hoverColor;
    protected int buttonPosTopLeftX;
    protected int buttonPosTopLeftY;

    public TransparentIconToggleWidget(int pX, int pY, int pWidth, int pHeight, boolean pInitialState, GUIColor backgroundColorIn, GUIColor hoverColorIn) {
        super(pX, pY, pWidth, pHeight, StringTextComponent.EMPTY);
        this.isStateTriggered = pInitialState;
        this.backgroundColor = backgroundColorIn;
        this.hoverColor = hoverColorIn;

        this.buttonPosTopLeftX = pX;
        this.buttonPosTopLeftY = pY;
    }

    public void initIconTextureValues(int pXTexStart, int pYTexStart, int pXDiffTex, int pYDiffTex, ResourceLocation pResourceLocation) {
        this.texTopLeftX = pXTexStart;
        this.texTopLeftY = pYTexStart;
        this.texWidth = pXDiffTex;
        this.texHeight = pYDiffTex;
        this.iconTextureResource = pResourceLocation;
    }

    public void setStateTriggered(boolean pTriggered) {
        this.isStateTriggered = pTriggered;
    }

    public boolean isStateTriggered() {
        return this.isStateTriggered;
    }

    public void setPosition(int pX, int pY) {
        this.x = pX;
        this.y = pY;
    }

    public void renderButton(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bind(this.iconTextureResource);
        RenderSystem.disableDepthTest();

        GUIHelper.drawFillColor(pMatrixStack.last().pose(), this.buttonPosTopLeftX, this.buttonPosTopLeftY, this.buttonPosTopLeftX + this.texWidth, this.buttonPosTopLeftY + this.texHeight, this.backgroundColor);

        int i = this.texTopLeftX;
        int j = this.texTopLeftY;

        if (this.isStateTriggered) {
            i += this.texWidth;
        }

        if (this.isHovered()) {
            //GUIColor hoverColor = new GUIColor(255, 0, 0, 150);
            GUIHelper.drawFillColor(pMatrixStack.last().pose(), this.buttonPosTopLeftX, this.buttonPosTopLeftY, this.buttonPosTopLeftX + this.texWidth, this.buttonPosTopLeftY + this.texHeight, this.hoverColor);
        }

        this.blit(pMatrixStack, this.x, this.y, i, j, this.width, this.height);
        RenderSystem.enableDepthTest();
    }
}
