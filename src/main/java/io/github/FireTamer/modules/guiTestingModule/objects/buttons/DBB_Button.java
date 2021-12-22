package io.github.FireTamer.modules.guiTestingModule.objects.buttons;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;

public class DBB_Button extends Button {
    protected final Button.IPressable onPress;

    public DBB_Button(int scalableButtonWidth, int scalableButtonHeight, int xPosFromLeft, int yPosFromTop, ITextComponent buttonText, IPressable iPressable) {
        super(scalableButtonWidth, scalableButtonHeight, xPosFromLeft, yPosFromTop, buttonText, iPressable);
        this.onPress = iPressable;
    }
}
