package io.github.firetamer.dbb.api.client;

import net.minecraft.client.gui.screen.Screen;

import net.minecraft.util.text.ITextComponent;

public abstract class DBBBaseScreen extends Screen {

    protected DBBBaseScreen(ITextComponent pTitle) {
        super(pTitle);
    }

}
