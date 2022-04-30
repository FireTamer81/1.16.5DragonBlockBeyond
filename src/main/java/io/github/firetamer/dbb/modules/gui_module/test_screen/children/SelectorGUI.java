package io.github.firetamer.dbb.modules.gui_module.test_screen.children;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.modules.gui_module.test_screen.widgets.TransparentIconToggleWidget;
import io.github.firetamer.dbb.modules.gui_module.util.element_definitions.GUIColor;
import io.github.firetamer.dbb.modules.gui_module.util.element_definitions.GUIPositionEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.util.ResourceLocation;

public class SelectorGUI extends AbstractGui implements IRenderable, IGuiEventListener {
    public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(DragonBlockBeyond.MOD_ID, "textures/gui/menu_selector.png");

    protected Minecraft mc;
    private int width;
    private int height;

    private TransparentIconToggleWidget inventoryToggleButton;
    private boolean isInventoryToggleButtonActive = false;
    private TransparentIconToggleWidget statsToggleButton;
    private boolean isStatsToggleButtonActive = false;
    private TransparentIconToggleWidget skillsToggleButton;
    private boolean isSkillsToggleButtonActive = false;
    private TransparentIconToggleWidget storyToggleButton;
    private boolean isStoryToggleButtonActive = false;
    private TransparentIconToggleWidget travelToggleButton;
    private boolean isTravelToggleButtonActive = false;

    public SelectorGUI() {}

    public void init(Minecraft minecraft, int widthIn, int heightIn) {
        this.mc = minecraft;
        this.width = widthIn;
        this.height = heightIn;

        this.initButtons();
    }

    public void initButtons() {
        int topCenterX = this.width / 2;
        int topCenterY = 0;

        GUIColor buttonBackgroundColor = new GUIColor(0, 0,0, 140);
        GUIColor buttonHoverColor = new GUIColor(0, 0, 0, 150);

        this.inventoryToggleButton = new TransparentIconToggleWidget(topCenterX - 155, topCenterY, 40, 34, isInventoryToggleButtonActive, buttonBackgroundColor, buttonHoverColor);
        this.inventoryToggleButton.initIconTextureValues(0, 42, 40, 34, BACKGROUND_TEXTURE);

        this.statsToggleButton = new TransparentIconToggleWidget(topCenterX - 115, topCenterY, 40, 34, isStatsToggleButtonActive, buttonBackgroundColor, buttonHoverColor);
        this.statsToggleButton.initIconTextureValues(0, 78, 40, 34, BACKGROUND_TEXTURE);

        this.skillsToggleButton = new TransparentIconToggleWidget(topCenterX - 75, topCenterY, 40, 34, isSkillsToggleButtonActive, buttonBackgroundColor, buttonHoverColor);
        this.skillsToggleButton.initIconTextureValues(0, 114, 40, 34, BACKGROUND_TEXTURE);

        this.storyToggleButton = new TransparentIconToggleWidget(topCenterX + 35, topCenterY, 40, 34, isStoryToggleButtonActive, buttonBackgroundColor, buttonHoverColor);
        this.storyToggleButton.initIconTextureValues(0, 150, 40, 34, BACKGROUND_TEXTURE);

        this.travelToggleButton = new TransparentIconToggleWidget(topCenterX + 75, topCenterY, 40, 34, isTravelToggleButtonActive, buttonBackgroundColor, buttonHoverColor);
        this.travelToggleButton.initIconTextureValues(0, 186, 40, 34, BACKGROUND_TEXTURE);
    }


    @Override
    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        if (this.inventoryToggleButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isInventoryToggleButtonActive) {
                this.inventoryToggleButton.setStateTriggered(false);
                isInventoryToggleButtonActive = false;
            } else {
                this.inventoryToggleButton.setStateTriggered(true);
                isInventoryToggleButtonActive = true;
            }
            return true;
        }

        else if (this.statsToggleButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isStatsToggleButtonActive) {
                this.statsToggleButton.setStateTriggered(false);
                isStatsToggleButtonActive = false;
            } else {
                this.statsToggleButton.setStateTriggered(true);
                isStatsToggleButtonActive = true;
            }
            return true;
        }

        else if (this.skillsToggleButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isSkillsToggleButtonActive) {
                this.skillsToggleButton.setStateTriggered(false);
                isSkillsToggleButtonActive = false;
            } else {
                this.skillsToggleButton.setStateTriggered(true);
                isSkillsToggleButtonActive = true;
            }
            return true;
        }

        else if (this.storyToggleButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isStoryToggleButtonActive) {
                this.storyToggleButton.setStateTriggered(false);
                isStoryToggleButtonActive = false;
            } else {
                this.storyToggleButton.setStateTriggered(true);
                isStoryToggleButtonActive = true;
            }
            return true;
        }

        else if (this.travelToggleButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isTravelToggleButtonActive) {
                this.travelToggleButton.setStateTriggered(false);
                isTravelToggleButtonActive = false;
            } else {
                this.travelToggleButton.setStateTriggered(true);
                isTravelToggleButtonActive = true;
            }
            return true;
        }

        else {
            return false;
        }
    }



    @SuppressWarnings("deprecation")
    @Override
    public void render(MatrixStack stack, int pMouseX, int pMouseY, float pPartialTicks) {
        RenderSystem.pushMatrix();
        RenderSystem.translatef(0.0f, 0.0f, 100.0f);
        this.mc.getTextureManager().bind(BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        int topCenterX = this.width / 2;
        int topCenterY = 0;

        this.blit(stack, topCenterX - 163, topCenterY, 0, 0, 128, 42);      //Left Piece
        this.blit(stack, topCenterX - 35, topCenterY, 93, 43, 70, 62);      //Center Piece
        this.blit(stack, topCenterX + 35, topCenterY, 128, 0, 128, 42);    //Right Piece

        RenderSystem.popMatrix();

        this.inventoryToggleButton.render(stack, pMouseX, pMouseY, pPartialTicks);
        this.statsToggleButton.render(stack, pMouseX, pMouseY, pPartialTicks);
        this.skillsToggleButton.render(stack, pMouseX, pMouseY, pPartialTicks);
        this.storyToggleButton.render(stack, pMouseX, pMouseY, pPartialTicks);
        this.travelToggleButton.render(stack, pMouseX, pMouseY, pPartialTicks);
    }
}
