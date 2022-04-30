package io.github.firetamer.dbb.modules.gui_module.test_screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.firetamer.dbb.modules.gui_module.player_screen.children.MenuSelectorGui;
import io.github.firetamer.dbb.modules.gui_module.test_screen.children.SelectorGUI;
import io.github.firetamer.dbb.modules.gui_module.util.GUIHelper;
import io.github.firetamer.dbb.modules.gui_module.util.element_definitions.GUIColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.util.text.StringTextComponent;

public class TestScreen extends Screen {

    private final SelectorGUI selectorGui = new SelectorGUI();

    public TestScreen() {
        super(new StringTextComponent(""));
    }

    @Override
    protected void init() {
        super.init();

        this.selectorGui.init(minecraft, this.width, this.height);
        this.children.add(this.selectorGui);
        this.setInitialFocus(this.selectorGui);

        //When the screen is opened, sets the player camera to third person
        //Need to figure out how to "remember" the camera mode the player was in before opening the GUI
        Minecraft.getInstance().options.setCameraType(PointOfView.THIRD_PERSON_FRONT);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void onClose() {
        super.onClose();

        //When the screen is closed, resets the player camera to first person
        //Need to figure out how to "remember" the camera mode the player was in before opening the GUI
        Minecraft.getInstance().options.setCameraType(PointOfView.FIRST_PERSON);
    }

    /******************************************************************************************************************/
    //Rendering
    /******************************************************************************************************************/

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);

        //Other render content
        this.selectorGui.render(stack, mouseX, mouseY, partialTicks); //Adds the Menu Selector GUI

        super.render(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderBackground(MatrixStack stack) {
        //Makes the fill color
        GUIColor backgroundFillColor = new GUIColor(100, 100, 100, 150);
        //Renders the slightly transparent overlay across the entire screen behind other GUI elements
        GUIHelper.drawFillColor(stack.last().pose(), 0, 0, this.width, this.height, backgroundFillColor);



        //GUIColor contentBoxColor = new GUIColor(255, 0, 0, 100);
        //GUIColor contentBoxBorderColor = new GUIColor(0, 0, 255, 100);
        //GUIElementBounds contentBoxBounds = new GUIElementBounds(150, 150, 300, 300);
        //GUIHelper.drawContentBoxWithSimpleBorder(stack.last().pose(), contentBoxBounds, 2, contentBoxBorderColor, contentBoxColor);

    }
}
