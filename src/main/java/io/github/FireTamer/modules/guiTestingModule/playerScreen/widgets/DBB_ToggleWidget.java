package io.github.FireTamer.modules.guiTestingModule.playerScreen.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

public class DBB_ToggleWidget extends Widget {
    protected ResourceLocation resourceLocation;
    protected boolean isStateTriggered;
    protected int xTexStart;
    protected int yTexStart;
    protected int xDiffTex;
    protected int yDiffTex;

    public DBB_ToggleWidget(int screenPosX, int screenPosY, int buttonWidth, int buttonHeight, boolean isActivated, ITextComponent buttonTextInput) {
        super(screenPosX, screenPosY, buttonWidth, buttonHeight, buttonTextInput);
        this.isStateTriggered = isActivated;
    }

    public void initTextureValues(int p_191751_1_, int p_191751_2_, int p_191751_3_, int p_191751_4_, ResourceLocation p_191751_5_) {
        this.xTexStart = p_191751_1_;
        this.yTexStart = p_191751_2_;
        this.xDiffTex = p_191751_3_;
        this.yDiffTex = p_191751_4_;
        this.resourceLocation = p_191751_5_;
    }

    public void setStateTriggered(boolean p_191753_1_) {
        this.isStateTriggered = p_191753_1_;
    }

    public boolean isStateTriggered() {
        return this.isStateTriggered;
    }

    public void setPosition(int p_191752_1_, int p_191752_2_) {
        this.x = p_191752_1_;
        this.y = p_191752_2_;
    }

    public void renderButton(MatrixStack stack, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer fontrenderer = minecraft.font;
        int color = getFGColor();
        drawCenteredString(stack, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, color | MathHelper.ceil(this.alpha * 255.0F) << 24);

        minecraft.getTextureManager().bind(this.resourceLocation);
        RenderSystem.disableDepthTest();
        int i = this.xTexStart;
        int j = this.yTexStart;
        if (this.isStateTriggered) {
            i += this.xDiffTex;
        }

        if (this.isHovered()) {
            j += this.yDiffTex;
        }

        this.blit(stack, this.x, this.y, i, j, this.width, this.height);
        RenderSystem.enableDepthTest();
    }
}