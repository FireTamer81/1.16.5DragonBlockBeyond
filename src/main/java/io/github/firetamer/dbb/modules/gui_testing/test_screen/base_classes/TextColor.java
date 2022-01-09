package io.github.firetamer.dbb.modules.gui_testing.test_screen.base_classes;

import com.mojang.blaze3d.platform.GlStateManager;

import java.awt.*;

public class TextColor {
    public static final Color BLACK = new Color(0xFF000000);
    public static final Color WHITE = new Color(0xFFFFFFFF);



    private float alpha = 1;
    private float red;
    private float green;
    private float blue;

    public TextColor(int color) {
        alpha = (float)(color >> 24 & 255) / 255f;
        red = (float)(color >> 16 & 255) / 255f;
        green = (float)(color >> 8 & 255) / 255f;
        blue = (float)(color & 255) / 255f;
    }

    public int toARGB() {
        return TextColor.toARGB((int)(alpha * 255), (int)(red * 255), (int)(green * 255), (int)(blue * 255));
    }

    @Override
    public String toString() {
        return String.format("Color(alpha=%f, red=%f, green=%f, blue=%f, ARGB=%x", alpha, red, green, blue, toARGB());
    }

    public static int toARGB(int a, int r, int g, int b) {
        return ((a & 255) << 24) | ((r & 255) << 16) | ((g & 255) << 8) | (b & 255);
    }

    public void apply() {
        GlStateManager._color4f(red, green, blue, alpha);
    }
}
