package io.github.firetamer.dbb.modules.gui_testing.test_screen.base_classes;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import javafx.scene.input.MouseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public abstract class DBB_AbstractGUI {
    Minecraft mc;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected float zLevel;

    private int blitOffset;

    protected DBB_AbstractGUI parent;
    protected List<DBB_AbstractGUI> children = new ArrayList<>();

    protected List<String> tooltip = new ArrayList<>();

    protected boolean visible = true;
    protected boolean initialized;

    public DBB_AbstractGUI(int inputX, int inputY, int inputWidth, int inputHeight, int inputZLevel) {
        this.mc = Minecraft.getInstance();
        this.x = inputX;
        this.y = inputY;
        this.width = inputWidth;
        this.height = inputHeight;

        this.zLevel = inputZLevel;

        initialized = true;
    }

    public boolean hasChildren() {
        return true;
    }

    public <T extends DBB_AbstractGUI> T addChild(T child) {
        child.parent = this;
        children.add(child);
        return child;
    }

    public void removeChild(DBB_AbstractGUI child) {
        children.remove(child);
    }

    public DBB_AbstractGUI getRoot() {
        if (parent != null) {
            return parent.getRoot();
        }
        return this;
    }

    public boolean isWithinBounds(int inputX, int inputY) {
        boolean isWithinXRange = inputX >= this.x && inputX <= this.x + width;
        boolean isWithinYRange = inputY >= this.y && inputY <= this.x + height;

        return isWithinXRange && isWithinYRange;
    }

    public void onMove(int mouseX, int mouseY, MouseButton mouseButton) {}

    protected void bindTexture(ResourceLocation texture) {
        mc.getTextureManager().bind(texture);
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



    //In older versions this was "drawTexturedRect"
    protected void blit(int topLeftX, int topLeftY, int textureWidth, int textureHeight) {
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

    protected void fillGradient(MatrixStack pPoseStack, int pX1, int pY1, int pX2, int pY2, int pColorFrom, int pColorTo) {
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.disableAlphaTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        fillGradient(pPoseStack.last().pose(), bufferbuilder, pX1, pY1, pX2, pY2, this.blitOffset, pColorFrom, pColorTo);
        tessellator.end();
        RenderSystem.shadeModel(7424);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableTexture();
    }

    protected static void fillGradient(Matrix4f pMatrix, BufferBuilder pBuilder, int pX1, int pY1, int pX2, int pY2, int pBlitOffset, int pColorA, int pColorB) {
        float f = (float)(pColorA >> 24 & 255) / 255.0F;
        float f1 = (float)(pColorA >> 16 & 255) / 255.0F;
        float f2 = (float)(pColorA >> 8 & 255) / 255.0F;
        float f3 = (float)(pColorA & 255) / 255.0F;
        float f4 = (float)(pColorB >> 24 & 255) / 255.0F;
        float f5 = (float)(pColorB >> 16 & 255) / 255.0F;
        float f6 = (float)(pColorB >> 8 & 255) / 255.0F;
        float f7 = (float)(pColorB & 255) / 255.0F;
        pBuilder.vertex(pMatrix, (float)pX2, (float)pY1, (float)pBlitOffset).color(f1, f2, f3, f).endVertex();
        pBuilder.vertex(pMatrix, (float)pX1, (float)pY1, (float)pBlitOffset).color(f1, f2, f3, f).endVertex();
        pBuilder.vertex(pMatrix, (float)pX1, (float)pY2, (float)pBlitOffset).color(f5, f6, f7, f4).endVertex();
        pBuilder.vertex(pMatrix, (float)pX2, (float)pY2, (float)pBlitOffset).color(f5, f6, f7, f4).endVertex();
    }

    public int getBlitOffset() {
        return this.blitOffset;
    }

    public void setBlitOffset(int pValue) {
        this.blitOffset = pValue;
    }
}
