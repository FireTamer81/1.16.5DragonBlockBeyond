package io.github.firetamer.dbb.modules.gui_testing.test_screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.firetamer.dbb.modules.gui_testing.test_screen.base_classes.TextColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.opengl.GL11;

public class BaseScreen extends Screen {
    private Minecraft mc;
    private int zLevel;
    protected int x;
    protected int y;

    protected boolean visible = true;
    protected boolean initialized;

    protected BaseScreen(ITextComponent pTitle) {
        super(pTitle);
    }

    public void init(Minecraft pMinecraft, int inputX, int inputY, int inputWidth, int inputHeight, int inputZLevel) {
        super.init(pMinecraft, inputWidth, inputHeight);
        this.zLevel = inputZLevel;
        this.x = inputX;
        this.y = inputY;
        this.mc = pMinecraft;

        initialized = true;
    }

    protected void bindTexture(ResourceLocation texture) {
        mc.getTextureManager().bind(texture);
    }

    public boolean isWithinBounds(int inputX, int inputY) {
        boolean isWithinXRange = inputX >= this.x && inputX <= this.x + width;
        boolean isWithinYRange = inputY >= this.y && inputY <= this.x + height;

        return isWithinXRange && isWithinYRange;
    }

    protected void drawText(MatrixStack matrix, ITextComponent text, int x, int y, TextColor textColor, boolean shadow) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, zLevel + .5f);

        if (shadow) {
            mc.font.drawShadow(matrix, text, x, y, textColor.toARGB());
        } else {
            mc.font.draw(matrix, text, x, y, textColor.toARGB());
        }

        //Don't understand this bit or it's purpose
        //TextColor.WHITE.apply();
        GL11.glPopMatrix();
    }

    protected void drawCenteredText(MatrixStack matrix, ITextComponent text, int x, int y, int maxX, int maxY, TextColor textColor, boolean shadow) {
        int centerX = x + ((maxX - x) / 2) - (mc.font.width(text) / 2);
        int centerY = y + ((maxY - y) / 2) - (mc.font.lineHeight / 2);
        drawText(matrix, text, centerX, centerY, textColor, shadow);
    }

    @Override
    public void blit(MatrixStack pMatrixStack, int topLeftX, int topLefty, int textureWidth, int textureHeight, int pUWidth, int pVHeight) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

        bufferbuilder
                .vertex(x, y + height, this.zLevel)
                .uv(((float)textureWidth * f), ((float)(textureHeight + height) * f1))
                .endVertex();
        bufferbuilder
                .vertex(x + width, (y + height), this.zLevel)
                .uv(((float)(textureWidth + width) * f), ((float)(textureHeight + height) * f1))
                .endVertex();
        bufferbuilder
                .vertex(x + width, y, this.zLevel)
                .uv(((float)(textureWidth + width) * f), ((float)textureHeight * f1))
                .endVertex();
        bufferbuilder
                .vertex(x, y, this.zLevel)
                .uv(((float)textureWidth * f), ((float)textureHeight * f1))
                .endVertex();

        bufferbuilder.end();
        RenderSystem.enableAlphaTest();
        WorldVertexBufferUploader.end(bufferbuilder);
    }
}
