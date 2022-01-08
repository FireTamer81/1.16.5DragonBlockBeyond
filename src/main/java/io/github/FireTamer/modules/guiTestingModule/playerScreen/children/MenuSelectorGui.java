package io.github.FireTamer.modules.guiTestingModule.playerScreen.children;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.FireTamer.DBB_Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.util.ResourceLocation;

public class MenuSelectorGui extends AbstractGui implements IRenderable, IGuiEventListener {
    public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(DBB_Main.MOD_ID, "textures/gui/menu_selector.png");

    private int width;
    private int height;
    private int topLeftX;
    private int topLeftY;

    private ToggleWidget playerInventoryButton;
    private boolean isPlayerInventoryButtonActive = false;
    private ToggleWidget playerStatsButton;
    private boolean isPlayerStatsButtonActive = false;
    private ToggleWidget playerSkillsButton;
    private boolean isPlayerSkillsButtonActive = false;
    private ToggleWidget playerTrainingButton;
    private boolean isPlayerTrainingButtonActive = false;
    private ToggleWidget playerStoryButton;
    private boolean isPlayerStoryButtonActive = false;
    private ToggleWidget playerTravelButton;
    private boolean isPlayerTravelButtonActive = false;

    protected Minecraft mc;

    public MenuSelectorGui() {}

    public void init(Minecraft minecraft, int widthIn, int heightIn) {
        this.mc = minecraft;
        this.width = widthIn;
        this.height = heightIn;
        this.topLeftX = 0;
        this.topLeftY = 95;

        this.initButtons();
    }

    public void initButtons() {
        this.playerInventoryButton = new ToggleWidget(topLeftX + 5, topLeftY + 17, 43, 30, isPlayerInventoryButtonActive);
        this.playerInventoryButton.initTextureValues(79, 0, 43, 30, BACKGROUND_TEXTURE);

        this.playerStatsButton = new ToggleWidget(topLeftX + 5, topLeftY + 55, 43, 30, isPlayerStatsButtonActive);
        this.playerStatsButton.initTextureValues(79, 0, 43, 30, BACKGROUND_TEXTURE);

        this.playerSkillsButton = new ToggleWidget(topLeftX + 5, topLeftY + 93, 43, 30, isPlayerSkillsButtonActive);
        this.playerSkillsButton.initTextureValues(79, 0, 43, 30, BACKGROUND_TEXTURE);

        this.playerTrainingButton = new ToggleWidget(topLeftX + 5, topLeftY + 131, 43, 30, isPlayerTrainingButtonActive);
        this.playerTrainingButton.initTextureValues(79, 0, 43, 30, BACKGROUND_TEXTURE);

        this.playerStoryButton = new ToggleWidget(topLeftX + 5, topLeftY + 169, 43, 30, isPlayerStoryButtonActive);
        this.playerStoryButton.initTextureValues(79, 0, 43, 30, BACKGROUND_TEXTURE);

        this.playerTravelButton = new ToggleWidget(topLeftX + 5, topLeftY + 207, 43, 30, isPlayerTravelButtonActive);
        this.playerTravelButton.initTextureValues(79, 0, 43, 30, BACKGROUND_TEXTURE);
    }

    @Override
    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        if (this.playerInventoryButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isPlayerInventoryButtonActive) {
                this.playerInventoryButton.setStateTriggered(false);
                isPlayerInventoryButtonActive = false;
            } else {
                this.playerInventoryButton.setStateTriggered(true);
                isPlayerInventoryButtonActive = true;
            }
            return true;
        } else if (this.playerStatsButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isPlayerStatsButtonActive) {
                this.playerStatsButton.setStateTriggered(false);
                isPlayerStatsButtonActive = false;
            } else {
                this.playerStatsButton.setStateTriggered(true);
                isPlayerStatsButtonActive = true;
            }
            return true;
        } else if (this.playerSkillsButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isPlayerSkillsButtonActive) {
                this.playerSkillsButton.setStateTriggered(false);
                isPlayerSkillsButtonActive = false;
            } else {
                this.playerSkillsButton.setStateTriggered(true);
                isPlayerSkillsButtonActive = true;
            }
            return true;
        } else if (this.playerTrainingButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isPlayerTrainingButtonActive ) {
                this.playerTrainingButton.setStateTriggered(false);
                isPlayerTrainingButtonActive  = false;
            } else {
                this.playerTrainingButton.setStateTriggered(true);
                isPlayerTrainingButtonActive  = true;
            }
            return true;
        } else if (this.playerStoryButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isPlayerStoryButtonActive) {
                this.playerStoryButton.setStateTriggered(false);
                isPlayerStoryButtonActive = false;
            } else {
                this.playerStoryButton.setStateTriggered(true);
                isPlayerStoryButtonActive = true;
            }
            return true;
        } else if (this.playerTravelButton.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            if (isPlayerTravelButtonActive) {
                this.playerTravelButton.setStateTriggered(false);
                isPlayerTravelButtonActive = false;
            } else {
                this.playerTravelButton.setStateTriggered(true);
                isPlayerTravelButtonActive = true;
            }
            return true;
        }

        else {
            return false;
        }
    }

    @Override
    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        RenderSystem.pushMatrix();
        RenderSystem.translatef(0.0f, 0.0f, 100.0f);
        this.mc.getTextureManager().bind(BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.blit(stack, topLeftX, topLeftY, 0, 0, 61, 256);      //Main Background
        RenderSystem.popMatrix();

        this.playerInventoryButton.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
        this.playerStatsButton.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
        this.playerSkillsButton.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
        this.playerTrainingButton.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
        this.playerStoryButton.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
        this.playerTravelButton.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
    }
}
