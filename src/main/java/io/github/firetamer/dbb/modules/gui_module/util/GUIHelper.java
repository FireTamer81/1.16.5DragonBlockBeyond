package io.github.firetamer.dbb.modules.gui_module.util;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.firetamer.dbb.modules.gui_module.util.element_definitions.GUIColor;
import io.github.firetamer.dbb.modules.gui_module.util.element_definitions.GUIElementBounds;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Matrix4f;

public final class GUIHelper {
    private GUIHelper() {}




    //TODO Add a method that creates a bordered box of any given size that takes the interior fill color and border color (Might require another input for color than the individual values of a rgba color set)
    //Might also have variations for different types of borders, and also variations for gradients in the fill colors
    //and MAYBE rounded edge borders [would be a pain in the ass for the amount of "drawFillColor" methods]



    //The content box is what the variable "contentBoxBounds" defines. The border will extends outside the bounds defined based on the "borderWidth" float
    //Basically, you will have to account for the border when you are positioning GUI elements
    public static void drawContentBoxWithSimpleBorder(Matrix4f stack, GUIElementBounds contentBoxBounds, float borderWidth, GUIColor borderColor, GUIColor contentBoxColor) {
        float[] contentBoxBoundsArray = contentBoxBounds.getBounds();
        drawFillColor(stack, contentBoxBoundsArray[0], contentBoxBoundsArray[1], contentBoxBoundsArray[2], contentBoxBoundsArray[3], contentBoxColor);

        //Draw Left Border
        drawFillColor(stack, contentBoxBoundsArray[0] - borderWidth, contentBoxBoundsArray[1], contentBoxBoundsArray[0], contentBoxBoundsArray[3], borderColor);
        //Draw Right Border
        drawFillColor(stack, contentBoxBoundsArray[2], contentBoxBoundsArray[1], contentBoxBoundsArray[2] + borderWidth, contentBoxBoundsArray[3], borderColor);
        //Draw Top Border
        drawFillColor(stack, contentBoxBoundsArray[0] - borderWidth, contentBoxBoundsArray[1] - borderWidth, contentBoxBoundsArray[2] + borderWidth, contentBoxBoundsArray[1], borderColor);
        //Draw Bottom Border
        drawFillColor(stack, contentBoxBoundsArray[0] - borderWidth, contentBoxBoundsArray[3], contentBoxBoundsArray[2] + borderWidth, contentBoxBoundsArray[3] + borderWidth, borderColor);
    }


    //If given 0 for x1 & y1 + screen width & height for x2 & y2, it will color the background whatever rgba value is given.
    //Other uses for this is transparent elements like button and such. (Would need a border either drawn or a given texture)
    public static void drawFillColor(Matrix4f stack, float x1, float y1, float x2, float y2, GUIColor guiColor) {

        int[] guiColorArray = guiColor.getRgba();

        float j;
        if (x1 < x2) {
            j = x1;
            x1 = x2;
            x2 = j;
        }
        if (y1 < y2) {
            j = y1;
            y1 = y2;
            y2 = j;
        }

        BufferBuilder builder = Tessellator.getInstance().getBuilder();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();

        builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        builder.vertex(stack, x1, y2, 0.0F).color(guiColorArray[0], guiColorArray[1], guiColorArray[2], guiColorArray[3]).endVertex();
        builder.vertex(stack, x2, y2, 0.0F).color(guiColorArray[0], guiColorArray[1], guiColorArray[2], guiColorArray[3]).endVertex();
        builder.vertex(stack, x2, y1, 0.0F).color(guiColorArray[0], guiColorArray[1], guiColorArray[2], guiColorArray[3]).endVertex();
        builder.vertex(stack, x1, y1, 0.0F).color(guiColorArray[0], guiColorArray[1], guiColorArray[2], guiColorArray[3]).endVertex();
        builder.end();

        WorldVertexBufferUploader.end(builder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }
}
