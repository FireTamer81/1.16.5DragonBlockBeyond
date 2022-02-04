package io.github.firetamer.dbb.modules.machines.paint_mixer.client;

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

    ITextComponent[] terminalMessages = new ITextComponent[10];

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

        this.initTerminalMessages();
        this.initButtons();
    }

    public void initTerminalMessages() {
        terminalMessages[0] = new StringTextComponent("");
        terminalMessages[1] = new StringTextComponent("");
        terminalMessages[2] = new StringTextComponent("");
        terminalMessages[3] = new StringTextComponent("");
        terminalMessages[4] = new StringTextComponent("");
        terminalMessages[5] = new StringTextComponent("");
        terminalMessages[6] = new StringTextComponent("");
        terminalMessages[7] = new StringTextComponent("");
        terminalMessages[8] = new StringTextComponent("");
        terminalMessages[9] = new StringTextComponent("Paint Mixer - Active");
    }

    public void initButtons() {
        this.mixActionButton = new ToggleWidget(topLeftX + 15, topLeftY + 103, 60, 18, isMixActionButtonActive);
        this.mixActionButton.initTextureValues(0, 220, 60, 18, TEXTURE);
        this.pickColorButton = new ToggleWidget(topLeftX + 15, topLeftY + 127, 60, 18, isPickColorButtonActive);
        this.pickColorButton.initTextureValues(0, 220, 60, 18, TEXTURE);
        this.capsulizeButton = new ToggleWidget(topLeftX + 15, topLeftY + 151, 60, 18, isCapsulizeButtonActive);
        this.capsulizeButton.initTextureValues(0, 220, 60, 18, TEXTURE);
    }

    public void addNewTerminalMessage(ITextComponent newMessage) {
        terminalMessages[0] = terminalMessages[1];
        terminalMessages[1] = terminalMessages[2];
        terminalMessages[2] = terminalMessages[3];
        terminalMessages[3] = terminalMessages[4];
        terminalMessages[4] = terminalMessages[5];
        terminalMessages[5] = terminalMessages[6];
        terminalMessages[6] = terminalMessages[7];
        terminalMessages[7] = terminalMessages[8];
        terminalMessages[8] = terminalMessages[9];
        terminalMessages[9] = newMessage;
    }

    @Override
    public boolean mouseClicked(double xDouble, double yDouble, int button) {
        super.mouseClicked(xDouble, yDouble, button);

        if (this.mixActionButton.mouseClicked(xDouble, yDouble, button)) {
            if (this.mixActionButton.isStateTriggered()) {
                this.mixActionButton.setStateTriggered(false);
                isMixActionButtonActive = false;
                this.addNewTerminalMessage(new StringTextComponent("no more mixing"));
            } else {
                this.mixActionButton.setStateTriggered(true);
                isMixActionButtonActive = true;
                this.addNewTerminalMessage(new StringTextComponent("MIXING"));
            }

            return true;
        }
        else if (this.pickColorButton.mouseClicked(xDouble, yDouble, button)) {
            if (this.pickColorButton.isStateTriggered()) {
                this.pickColorButton.setStateTriggered(false);
                isPickColorButtonActive = false;
                this.addNewTerminalMessage(new StringTextComponent("no more color"));
            } else {
                this.pickColorButton.setStateTriggered(true);
                isPickColorButtonActive = true;
                this.addNewTerminalMessage(new StringTextComponent("MORE COLOR!!"));
            }

            return true;
        }
        else if (this.capsulizeButton.mouseClicked(xDouble, yDouble, button)) {
            if (this.capsulizeButton.isStateTriggered()) {
                this.capsulizeButton.setStateTriggered(false);
                isCapsulizeButtonActive = false;
                this.addNewTerminalMessage(new StringTextComponent("no more capsulizing?"));
            } else {
                this.capsulizeButton.setStateTriggered(true);
                isCapsulizeButtonActive = true;
                this.addNewTerminalMessage(new StringTextComponent("CAPSULIZE"));
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
        //ITextComponent line1Text = new StringTextComponent("Paint Can Loaded");
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

        drawString(stack, fontRenderer, terminalMessages[0], lineTopLeftPosX, lineTopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, terminalMessages[1], lineTopLeftPosX, line2TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, terminalMessages[2], lineTopLeftPosX, line3TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, terminalMessages[3], lineTopLeftPosX, line4TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, terminalMessages[4], lineTopLeftPosX, line5TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, terminalMessages[5], lineTopLeftPosX, line6TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, terminalMessages[6], lineTopLeftPosX, line7TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, terminalMessages[7], lineTopLeftPosX, line8TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, terminalMessages[8], lineTopLeftPosX, line9TopLeftPosY, 0xFFFFFF);
        drawString(stack, fontRenderer, terminalMessages[9], lineTopLeftPosX, line10TopLeftPosY, 0xFFFFFF);
    }
}
