package io.github.firetamer.dbb.modules.strong_block.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.firetamer.dbb.DragonBlockBeyond;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class PaintMixerFunctionalGUI extends Screen {
    private final ResourceLocation TEXTURE = new ResourceLocation(DragonBlockBeyond.MOD_ID, "textures/gui/paint_mixer_screen.png");

    private int width;
    private int height;
    private int topLeftX;
    private int topLeftY;
    private ToggleWidget mixActionButton;
    private boolean isMixActionButtonActive = false;
    private ToggleWidget pickColorButton;
    private boolean isPickColorButtonActive = false;
    private ToggleWidget capsulizeButton;
    private boolean isCapsulizeButtonActive = false;

    protected Minecraft mc;

    protected PaintMixerFunctionalGUI() {
        super(new StringTextComponent(""));
    }

    @Override
    public void init(Minecraft pMinecraft, int pWidth, int pHeight) {
        this.mc = pMinecraft;
        this.width = pWidth;
        this.height = pHeight;
        this.topLeftX = (this.width / 2) - 128;
        this.topLeftY = (this.height / 2) - 93;

        this.initButtons();
    }

    public void initButtons() {
        this.mixActionButton = new ToggleWidget(topLeftX + 15, topLeftY + 103, 60, 18, isMixActionButtonActive);
        this.mixActionButton.initTextureValues(0, 220, 60, 18, TEXTURE);
        this.pickColorButton = new ToggleWidget(topLeftX + 15, topLeftY + 127, 60, 18, isPickColorButtonActive);
        this.pickColorButton.initTextureValues(0, 220, 60, 18, TEXTURE);
        this.capsulizeButton = new ToggleWidget(topLeftX + 15, topLeftY + 151, 60, 18, isCapsulizeButtonActive);
        this.capsulizeButton.initTextureValues(0, 220, 60, 18, TEXTURE);
    }

    @Override
    public boolean mouseClicked(double xDouble, double yDouble, int button) {
        super.mouseClicked(xDouble, yDouble, button);

        if (this.mixActionButton.mouseClicked(xDouble, yDouble, button)) {
            if (this.mixActionButton.isStateTriggered()) {
                this.mixActionButton.setStateTriggered(false);
                isMixActionButtonActive = false;
            } else {
                this.mixActionButton.setStateTriggered(true);
                isMixActionButtonActive = true;
            }

            return true;
        }
        else if (this.pickColorButton.mouseClicked(xDouble, yDouble, button)) {
            if (this.pickColorButton.isStateTriggered()) {
                this.pickColorButton.setStateTriggered(false);
                isPickColorButtonActive = false;
            } else {
                this.pickColorButton.setStateTriggered(true);
                isPickColorButtonActive = true;
            }

            return true;
        }
        else if (this.capsulizeButton.mouseClicked(xDouble, yDouble, button)) {
            if (this.capsulizeButton.isStateTriggered()) {
                this.capsulizeButton.setStateTriggered(false);
                isCapsulizeButtonActive = false;
            } else {
                this.capsulizeButton.setStateTriggered(true);
                isCapsulizeButtonActive = true;
            }

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        this.mixActionButton.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        this.pickColorButton.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        this.capsulizeButton.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);

        renderTerminalStrings(pMatrixStack);
    }

    public void renderTerminalStrings(MatrixStack stack) {
        ITextComponent line1Text = new StringTextComponent("Paint Can Loaded");
        int lineTopLeftPosX = topLeftX + 12;
        int lineTopLeftPosY = topLeftY + 13;
        int line2TopLeftPosY = topLeftY + 21;
        int line3TopLeftPosY = topLeftY + 29;
        int line4TopLeftPosY = topLeftY + 37;
        int line5TopLeftPosY = topLeftY + 45;
        int line6TopLeftPosY = topLeftY + 53;
        int line7TopLeftPosY = topLeftY + 61;
        int line8TopLeftPosY = topLeftY + 69;
        int line9TopLeftPosY = topLeftY + 77;
        int line10TopLeftPosY = topLeftY + 85;
        FontRenderer fontRenderer = this.mc.font;

        drawString(stack, fontRenderer, line1Text, lineTopLeftPosX, lineTopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, line1Text, lineTopLeftPosX, line2TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, line1Text, lineTopLeftPosX, line3TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, line1Text, lineTopLeftPosX, line4TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, line1Text, lineTopLeftPosX, line5TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, line1Text, lineTopLeftPosX, line6TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, line1Text, lineTopLeftPosX, line7TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, line1Text, lineTopLeftPosX, line8TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, line1Text, lineTopLeftPosX, line9TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, line1Text, lineTopLeftPosX, line10TopLeftPosY, 0xFFFFFF);
    }
}
