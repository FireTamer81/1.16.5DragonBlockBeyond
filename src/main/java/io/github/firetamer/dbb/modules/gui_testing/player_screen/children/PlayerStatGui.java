package io.github.firetamer.dbb.modules.gui_testing.player_screen.children;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import io.github.firetamer.dbb.DragonBlockBeyond;

public class PlayerStatGui extends Screen {
    public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(DragonBlockBeyond.MOD_ID, "textures/gui/player_stat_menu_2.png");
    public static final ResourceLocation BUTTONS_TEXTURE = new ResourceLocation(DragonBlockBeyond.MOD_ID, "textures/gui/player_stat_menu_buttons.png");

    private int width;
    private int height;
    private double topLeftX;
    private double topLeftY;
    private boolean isDragging;
    private double lastMouseX;
    private double lastMouseY;

    private ToggleWidget strengthButton;
    private boolean strengthButtonActivationState = false;


    protected Minecraft mc;

    public PlayerStatGui() {
        super(new StringTextComponent(""));
    }

    public void init(Minecraft minecraft, int widthIn, int heightIn) {
        this.mc = minecraft;
        this.width = widthIn;
        this.height = heightIn;
        this.topLeftX = 200;
        this.topLeftY = 150;
        this.isDragging = false;

        this.initButtons();
    }

    public void initButtons() {
        this.strengthButton = new ToggleWidget((int)(topLeftX + 10), (int)(topLeftY + 20), 77, 23, strengthButtonActivationState);
        this.strengthButton.initTextureValues(0, 163, 77, 23, BUTTONS_TEXTURE);
    }

    @Override
    public boolean mouseClicked(double xDouble, double yDouble, int button) {
        super.mouseClicked(xDouble, yDouble, button);

        if (this.strengthButton.mouseClicked(xDouble, yDouble, button)) {
            if (this.strengthButton.isStateTriggered()) {
                this.strengthButton.setStateTriggered(false);
                strengthButtonActivationState = false;
            } else {
                this.strengthButton.setStateTriggered(true);
                strengthButtonActivationState = true;
            }

            return true;
        } else {
            final double minX = this.topLeftX;
            final double minY = this.topLeftY;
            final double maxX = minX + 236;
            final double maxY = minY + 187;

            if (xDouble >= minX && xDouble <= maxX && yDouble >= minY && yDouble <= maxY) {
                this.isDragging = true;
                this.lastMouseX = xDouble;
                this.lastMouseY = yDouble;

                //This Works!!!!!!!!
                //System.out.println("Heyo, it only activates when within the GUI boundary");

                return true;
            } else {
                return false;
            }
        }
    }



    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int action) {
        this.isDragging = false;
        return super.mouseReleased(mouseX, mouseY, action);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int lastButtonClicked, double p_231045_6_, double p_231045_8_) {
        if (this.isDragging) {
            double tempXChange;
            double tempYChange;

            if (mouseX > lastMouseX) {
                tempXChange = mouseX - lastMouseX;
                this.topLeftX = this.topLeftX + tempXChange;
            } else {
                tempXChange = lastMouseX - mouseX;
                this.topLeftX = this.topLeftX - tempXChange;
            }

            if (mouseY > lastMouseY) {
                tempYChange = mouseY - lastMouseY;
                this.topLeftY = this.topLeftY + tempYChange;
            } else {
                tempYChange = lastMouseY - mouseY;
                this.topLeftY = this.topLeftY - tempYChange;
            }

            this.lastMouseX = mouseX;
            this.lastMouseY = mouseY;

            this.initButtons();

            //System.out.println(mouseX + ", " + mouseY);
        }

        return super.mouseDragged(mouseX, mouseY, lastButtonClicked, p_231045_6_, p_231045_8_);
    }

    @Override
    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBG(stack);

        this.strengthButton.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);

        ITextComponent strengthButtonText = new StringTextComponent("Hello, this is the Strength Button");
        int strengthButtonTopLeftPosX = (int)(topLeftX + 10);
        int strengthButtonTopLeftPosY = (int)(topLeftY + 20);
        FontRenderer fontRenderer = this.mc.font;
        drawCenteredString(stack, fontRenderer, strengthButtonText, strengthButtonTopLeftPosX, strengthButtonTopLeftPosY, 0xFFFFFF);
        //drawString(stack, fontRenderer, strengthButtonText, strengthButtonTopLeftPosX, strengthButtonTopLeftPosY, 0xFFFFFF);
        //this.mc.font.draw(stack, "Hello All", strengthButtonTopLeftPosX, strengthButtonTopLeftPosY, 0xFFFFFF);

        super.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
    }


    private void renderBG(MatrixStack stack) {
        RenderSystem.pushMatrix();
        RenderSystem.translatef(0.0f, 0.0f, 100.0f);
        this.mc.getTextureManager().bind(BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.blit(stack, (int)(topLeftX), (int)(topLeftY), 0, 0, 236, 187);      //Main Background
        RenderSystem.popMatrix();
    }




}