package io.github.firetamer.dbb.modules.gui_testing.test_screen.base_classes;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.INestedGuiEventHandler;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public abstract class DBB_BaseScreen extends DBB_AbstractGUI implements IRenderable, INestedGuiEventHandler {
    public static final ResourceLocation DIRT_BACKGROUND_LOCATION = new ResourceLocation("textures/gui/options_background.png");
    protected final ITextComponent screenTitle;
    protected final List<IGuiEventListener> children = Lists.newArrayList();
    protected final List<Widget> buttons = Lists.newArrayList();
    protected Minecraft mc;
    protected ItemRenderer itemRenderer;
    public int width;
    public int height;
    protected FontRenderer font;

    public DBB_BaseScreen(ITextComponent inputTitle, Minecraft minecraftInstance, int inputX, int inputY, int inputWidth, int inputHeight, int inputZLevel) {
        super(inputX, inputY, inputWidth, inputHeight, inputZLevel);
        this.screenTitle = inputTitle;
        this.mc = minecraftInstance;
        this.itemRenderer = minecraftInstance.getItemRenderer();
        this.font = minecraftInstance.font;
        this.width = inputWidth;
        this.height = inputHeight;
    }

    public ITextComponent getTitle() {
        return this.screenTitle;
    }

    public boolean shouldCloseOnEsc() {
        return true;
    }

    public void onClose() {
        this.mc.popGuiLayer();
    }

    protected <T extends Widget> T addButton(T p_230480_1_) {
        this.buttons.add(p_230480_1_);
        return this.addWidget(p_230480_1_);
    }

    protected <T extends IGuiEventListener> T addWidget(T pListener) {
        this.children.add(pListener);
        return pListener;
    }

    protected void insertText(String pText, boolean pOverwrite) {}

    public boolean handleComponentClicked(@Nullable Style pStyle) {
        if (pStyle != null) {
            ClickEvent clickevent = pStyle.getClickEvent();
            if (hasShiftDown()) {
                if (pStyle.getInsertion() != null) {
                    this.insertText(pStyle.getInsertion(), false);
                }
            } else if (clickevent != null) {
                return true;
            }

        }

        return false;
    }

    public void sendMessage(String pText, boolean pAddToChat) {
        pText = net.minecraftforge.event.ForgeEventFactory.onClientSendMessage(pText);
        if (pText.isEmpty()) return;
        if (pAddToChat) {
            this.mc.gui.getChat().addRecentChat(pText);
        }

        this.mc.player.chat(pText);
    }

    public List<? extends IGuiEventListener> children() {
        return this.children;
    }

    protected void init() {}

    public void tick() {}

    public void removed() {}

    public boolean isPauseScreen() { return true; }

    public static void wrapScreenError(Runnable pAction, String pErrorDesc, String pScreenName) {
        try {
            pAction.run();
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.forThrowable(throwable, pErrorDesc);
            CrashReportCategory crashreportcategory = crashreport.addCategory("Affected screen");
            crashreportcategory.setDetail("Screen name", () -> {
                return pScreenName;
            });
            throw new ReportedException(crashreport);
        }
    }

    protected boolean isValidCharacterForName(String pText, char pCharTyped, int pCursorPos) {
        int i = pText.indexOf(58);
        int j = pText.indexOf(47);
        if (pCharTyped == ':') {
            return (j == -1 || pCursorPos <= j) && i == -1;
        } else if (pCharTyped == '/') {
            return pCursorPos > i;
        } else {
            return pCharTyped == '_' || pCharTyped == '-' || pCharTyped >= 'a' && pCharTyped <= 'z' || pCharTyped >= '0' && pCharTyped <= '9' || pCharTyped == '.';
        }
    }

    public boolean isMouseOver(double pMouseX, double pMouseY) {
        return true;
    }

    public Minecraft getMinecraft() {
        return this.mc;
    }




    /******************************************************************************************************************/
    //Render Stuff
    /******************************************************************************************************************/

    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        for(int i = 0; i < this.buttons.size(); ++i) {
            this.buttons.get(i).render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        }
    }

    public void renderBackground(MatrixStack pMatrixStack) {
        this.renderBackground(pMatrixStack, 0);
    }

    public void renderBackground(MatrixStack pMatrixStack, int pVOffset) {
        if (this.mc.level != null) {
            this.fillGradient(pMatrixStack, 0, 0, this.width, this.height, -1072689136, -804253680);
            //net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.BackgroundDrawnEvent(this, pMatrixStack));
        } else {
            this.renderDirtBackground(pVOffset);
        }

    }

    public void renderDirtBackground(int pVOffset) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        this.mc.getTextureManager().bind(DIRT_BACKGROUND_LOCATION);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.vertex(0.0D, (double)this.height, 0.0D).uv(0.0F, (float)this.height / 32.0F + (float)pVOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex((double)this.width, (double)this.height, 0.0D).uv((float)this.width / 32.0F, (float)this.height / 32.0F + (float)pVOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex((double)this.width, 0.0D, 0.0D).uv((float)this.width / 32.0F, (float)pVOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, 0.0D).uv(0.0F, (float)pVOffset).color(64, 64, 64, 255).endVertex();
        tessellator.end();
        //net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.BackgroundDrawnEvent(this, new MatrixStack()));
    }

    public void renderTooltip(MatrixStack pMatrixStack, ITextComponent pText, int pMouseX, int pMouseY) {
        this.renderComponentTooltip(pMatrixStack, Arrays.asList(pText), pMouseX, pMouseY);
    }

    public void renderComponentTooltip(MatrixStack pPoseStack, List<ITextComponent> pTooltips, int pMouseX, int pMouseY) {
        this.renderWrappedToolTip(pPoseStack, pTooltips, pMouseX, pMouseY, font);
    }

    public void renderWrappedToolTip(MatrixStack matrixStack, List<? extends net.minecraft.util.text.ITextProperties> tooltips, int mouseX, int mouseY, FontRenderer font) {
        net.minecraftforge.fml.client.gui.GuiUtils.drawHoveringText(matrixStack, tooltips, mouseX, mouseY, width, height, -1, font);
    }

    public void renderTooltip(MatrixStack pMatrixStack, List<? extends IReorderingProcessor> pTooltips, int pMouseX, int pMouseY) {
        this.renderToolTip(pMatrixStack, pTooltips, pMouseX, pMouseY, font);
    }

    public void renderToolTip(MatrixStack pMatrixStack, List<? extends IReorderingProcessor> pTooltips, int pMouseX, int pMouseY, FontRenderer font) {
        if (!pTooltips.isEmpty()) {
            int i = 0;

            for(IReorderingProcessor ireorderingprocessor : pTooltips) {
                int j = this.font.width(ireorderingprocessor);
                if (j > i) {
                    i = j;
                }
            }

            int i2 = pMouseX + 12;
            int j2 = pMouseY - 12;
            int k = 8;
            if (pTooltips.size() > 1) {
                k += 2 + (pTooltips.size() - 1) * 10;
            }

            if (i2 + i > this.width) {
                i2 -= 28 + i;
            }

            if (j2 + k + 6 > this.height) {
                j2 = this.height - k - 6;
            }

            pMatrixStack.pushPose();
            int l = -267386864;
            int i1 = 1347420415;
            int j1 = 1344798847;
            int k1 = 400;
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuilder();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
            Matrix4f matrix4f = pMatrixStack.last().pose();
            fillGradient(matrix4f, bufferbuilder, i2 - 3, j2 - 4, i2 + i + 3, j2 - 3, 400, -267386864, -267386864);
            fillGradient(matrix4f, bufferbuilder, i2 - 3, j2 + k + 3, i2 + i + 3, j2 + k + 4, 400, -267386864, -267386864);
            fillGradient(matrix4f, bufferbuilder, i2 - 3, j2 - 3, i2 + i + 3, j2 + k + 3, 400, -267386864, -267386864);
            fillGradient(matrix4f, bufferbuilder, i2 - 4, j2 - 3, i2 - 3, j2 + k + 3, 400, -267386864, -267386864);
            fillGradient(matrix4f, bufferbuilder, i2 + i + 3, j2 - 3, i2 + i + 4, j2 + k + 3, 400, -267386864, -267386864);
            fillGradient(matrix4f, bufferbuilder, i2 - 3, j2 - 3 + 1, i2 - 3 + 1, j2 + k + 3 - 1, 400, 1347420415, 1344798847);
            fillGradient(matrix4f, bufferbuilder, i2 + i + 2, j2 - 3 + 1, i2 + i + 3, j2 + k + 3 - 1, 400, 1347420415, 1344798847);
            fillGradient(matrix4f, bufferbuilder, i2 - 3, j2 - 3, i2 + i + 3, j2 - 3 + 1, 400, 1347420415, 1347420415);
            fillGradient(matrix4f, bufferbuilder, i2 - 3, j2 + k + 2, i2 + i + 3, j2 + k + 3, 400, 1344798847, 1344798847);
            RenderSystem.enableDepthTest();
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.shadeModel(7425);
            bufferbuilder.end();
            WorldVertexBufferUploader.end(bufferbuilder);
            RenderSystem.shadeModel(7424);
            RenderSystem.disableBlend();
            RenderSystem.enableTexture();
            IRenderTypeBuffer.Impl irendertypebuffer$impl = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
            pMatrixStack.translate(0.0D, 0.0D, 400.0D);

            for(int l1 = 0; l1 < pTooltips.size(); ++l1) {
                IReorderingProcessor ireorderingprocessor1 = pTooltips.get(l1);
                if (ireorderingprocessor1 != null) {
                    this.font.drawInBatch(ireorderingprocessor1, (float)i2, (float)j2, -1, true, matrix4f, irendertypebuffer$impl, false, 0, 15728880);
                }

                if (l1 == 0) {
                    j2 += 2;
                }

                j2 += 10;
            }

            irendertypebuffer$impl.endBatch();
            pMatrixStack.popPose();
        }
    }

    protected void renderComponentHoverEffect(MatrixStack pMatrixStack, @Nullable Style pStyle, int pMouseX, int pMouseY) {
        if (pStyle != null && pStyle.getHoverEvent() != null) {
            HoverEvent hoverevent = pStyle.getHoverEvent();
            HoverEvent.ItemHover hoverevent$itemhover = hoverevent.getValue(HoverEvent.Action.SHOW_ITEM);
            if (hoverevent$itemhover != null) {
                this.renderTooltip(pMatrixStack, hoverevent$itemhover.getItemStack(), pMouseX, pMouseY);
            } else {
                HoverEvent.EntityHover hoverevent$entityhover = hoverevent.getValue(HoverEvent.Action.SHOW_ENTITY);
                if (hoverevent$entityhover != null) {
                    if (this.mc.options.advancedItemTooltips) {
                        this.renderComponentTooltip(pMatrixStack, hoverevent$entityhover.getTooltipLines(), pMouseX, pMouseY);
                    }
                } else {
                    ITextComponent itextcomponent = hoverevent.getValue(HoverEvent.Action.SHOW_TEXT);
                    if (itextcomponent != null) {
                        this.renderTooltip(pMatrixStack, this.mc.font.split(itextcomponent, Math.max(this.width / 2, 200)), pMouseX, pMouseY);
                    }
                }
            }

        }
    }

    protected void renderTooltip(MatrixStack pMatrixStack, ItemStack pItemStack, int pMouseX, int pMouseY) {
        FontRenderer font = pItemStack.getItem().getFontRenderer(pItemStack);
        net.minecraftforge.fml.client.gui.GuiUtils.preItemToolTip(pItemStack);
        this.renderWrappedToolTip(pMatrixStack, this.getTooltipFromItem(pItemStack), pMouseX, pMouseY, (font == null ? this.font : font));
        net.minecraftforge.fml.client.gui.GuiUtils.postItemToolTip();
    }

    public List<ITextComponent> getTooltipFromItem(ItemStack pItemStack) {
        return pItemStack.getTooltipLines(this.mc.player, this.mc.options.advancedItemTooltips ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL);
    }





    /******************************************************************************************************************/
    //Input Events (Pressed Keys/Key Combos)
    /******************************************************************************************************************/

    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == 256 && this.shouldCloseOnEsc()) {
            this.onClose();
            return true;
        } else if (pKeyCode == 258) {
            boolean flag = !hasShiftDown();
            if (!this.changeFocus(flag)) {
                this.changeFocus(flag);
            }

            return false;
        } else {
            return keyPressed(pKeyCode, pScanCode, pModifiers);
        }
    }

    public static boolean hasControlDown() {
        if (Minecraft.ON_OSX) {
            return InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 343) || InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 347);
        } else {
            return InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 341) || InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 345);
        }
    }

    public static boolean hasShiftDown() {
        return InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 340) || InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 344);
    }

    public static boolean hasAltDown() {
        return InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 342) || InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 346);
    }

    public static boolean isCut(int pKeyCode) {
        return pKeyCode == 88 && hasControlDown() && !hasShiftDown() && !hasAltDown();
    }

    public static boolean isPaste(int pKeyCode) {
        return pKeyCode == 86 && hasControlDown() && !hasShiftDown() && !hasAltDown();
    }

    public static boolean isCopy(int pKeyCode) {
        return pKeyCode == 67 && hasControlDown() && !hasShiftDown() && !hasAltDown();
    }

    public static boolean isSelectAll(int pKeyCode) {
        return pKeyCode == 65 && hasControlDown() && !hasShiftDown() && !hasAltDown();
    }
}
