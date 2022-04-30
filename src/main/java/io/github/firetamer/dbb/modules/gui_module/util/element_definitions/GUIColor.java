package io.github.firetamer.dbb.modules.gui_module.util.element_definitions;

public class GUIColor {
    int[] rgba = new int[4];

    public GUIColor(int r_In, int g_In, int b_In, int a_In) {
        testColorValueRange(r_In, g_In, b_In, a_In);

        rgba[0] = r_In;
        rgba[1] = g_In;
        rgba[2] = b_In;
        rgba[3] = a_In;
    }

    public int[] getRgba() {
        return rgba;
    }

    private static void testColorValueRange(int r, int g, int b, int a) {
        boolean rangeError = false;
        String badComponentString = "";


        if (r < 0 || r > 255) {
            rangeError = true;
            badComponentString = " Red";
        }
        if (g < 0 || g > 255) {
            rangeError = true;
            badComponentString = " Green";
        }
        if (b < 0 || b > 255) {
            rangeError = true;
            badComponentString = " Blue";
        }
        if (a < 0 || a > 255) {
            rangeError = true;
            badComponentString = " Alpha";
        }


        if (rangeError) {
            throw new IllegalArgumentException("DBBColor parameter outside of expected range:" + badComponentString);
        }
    }
}
