package io.github.FireTamer.modules.guiTestingModule.objects.playerScreen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.FireTamer.DBB_Main;
import io.github.FireTamer.modules.guiTestingModule.objects.playerScreen.children.MenuSelectorGui;
import io.github.FireTamer.modules.guiTestingModule.objects.playerScreen.children.PlayerStatGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.StringTextComponent;

public class PlayerScreen extends Screen {
    public static final ResourceLocation GEAR_LOCATION = new ResourceLocation(DBB_Main.MOD_ID, "textures/gui/dragonball_gear.png");

    private final MenuSelectorGui menuSelectorGui = new MenuSelectorGui();
    public static final PlayerStatGui playerStatsScreen = new PlayerStatGui();

    private float xMouse; //For the movement of the Rendered Entity
    private float yMouse;

    public PlayerScreen() {
        //super(new TranslationTextComponent("menu.player_screen"));
        super(new StringTextComponent(""));
    }

    @Override
    protected void init() {
        super.init();

        this.menuSelectorGui.init(minecraft, this.width, this.height);
        this.children.add(this.menuSelectorGui);
        this.setInitialFocus(this.menuSelectorGui);

        this.playerStatsScreen.init(minecraft, this.width, this.height);
        this.children.add(this.playerStatsScreen);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
    }





    /******************************************************************************************************************/
    //Rendering Stuff
    /******************************************************************************************************************/

    @Override
    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(stack);
        drawCenteredString(stack, this.font, this.title, this.width / 2, 40, 16777215);

        this.menuSelectorGui.render(stack, p_230430_2_, p_230430_3_, p_230430_4_); //Adds the Menu Selector GUI
        this.playerStatsScreen.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
        this.xMouse = (float)p_230430_2_;
        this.yMouse = (float)p_230430_3_;

        super.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
    }

    @Override
    public void renderBackground(MatrixStack stack) {
        //the first int in the blit method is x position on screen
        //the second is y position on screen
        //3rd is start x position on texture
        //4th is start y position on texture
        //5th is texture width (or a section of that texture)       [Max size of 256]
        //6th is texture height (or a section of that texture)      [Max size of 256]
        super.renderBackground(stack);
        GlStateManager._color4f(1, 1, 1, 1);
        this.minecraft.getTextureManager().bind(GEAR_LOCATION);
        this.blit(stack, 0, 0, 0, 0, 97, 95);      //Top Piece
        renderEntityInInventory(this.minecraft.player, this.width - 51, this.height - 75, 30, (float)(this.width - 51) - this.xMouse, (float)(this.height - 75 - 50) - this.yMouse);
    }

    public static void renderEntityInInventory(LivingEntity renderedEntity, int p_228187_0_, int p_228187_1_, int p_228187_2_, float p_228187_3_, float p_228187_4_) {
        float f = (float)Math.atan((double)(p_228187_3_ / 40.0F));
        float f1 = (float)Math.atan((double)(p_228187_4_ / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)p_228187_0_, (float)p_228187_1_, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.translate(0.0D, 0.0D, 1000.0D);
        matrixstack.scale((float)p_228187_2_, (float)p_228187_2_, (float)p_228187_2_);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        matrixstack.mulPose(quaternion);
        float f2 = renderedEntity.yBodyRot;
        float f3 = renderedEntity.yRot;
        float f4 = renderedEntity.xRot;
        float f5 = renderedEntity.yHeadRotO;
        float f6 = renderedEntity.yHeadRot;
        renderedEntity.yBodyRot = 180.0F + f * 20.0F;
        renderedEntity.yRot = 180.0F + f * 40.0F;
        renderedEntity.xRot = -f1 * 20.0F;
        renderedEntity.yHeadRot = renderedEntity.yRot;
        renderedEntity.yHeadRotO = renderedEntity.yRot;
        EntityRendererManager entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderermanager.overrideCameraOrientation(quaternion1);
        entityrenderermanager.setRenderShadow(false);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderermanager.render(renderedEntity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixstack, irendertypebuffer$impl, 15728880);
        });
        irendertypebuffer$impl.endBatch();
        entityrenderermanager.setRenderShadow(true);
        renderedEntity.yBodyRot = f2;
        renderedEntity.yRot = f3;
        renderedEntity.xRot = f4;
        renderedEntity.yHeadRotO = f5;
        renderedEntity.yHeadRot = f6;
        RenderSystem.popMatrix();
    }
}
