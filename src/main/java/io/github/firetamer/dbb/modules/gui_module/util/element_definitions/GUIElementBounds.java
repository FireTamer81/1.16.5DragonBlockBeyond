package io.github.firetamer.dbb.modules.gui_module.util.element_definitions;

public class GUIElementBounds {
    float[] bounds = new float[4];

    public GUIElementBounds(float topLeftX, float topLeftY, float bottomRightX, float bottomRightY) {
        bounds[0] = topLeftX;
        bounds[1] = topLeftY;
        bounds[2] = bottomRightX;
        bounds[3] = bottomRightY;
    }

    public float[] getBounds() {
        return bounds;
    }

}
