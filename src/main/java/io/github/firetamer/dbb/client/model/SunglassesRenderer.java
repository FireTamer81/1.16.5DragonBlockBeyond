package io.github.firetamer.dbb.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.firetamer.dbb.DragonBlockBeyond;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.function.Function;

// TODO fix rendering
public class SunglassesRenderer extends CustomArmourModel {

    public static final SunglassesRenderer RENDERER_2 = new SunglassesRenderer(0.5f, new SunglassesModel2(
            new ResourceLocation(DragonBlockBeyond.MOD_ID, "textures/entity/sunglasses2.png")));

    private final SunglassesModel model;

    protected SunglassesRenderer(float size, SunglassesModel model) {
        super(size);
        this.model = model;
    }

    @Override
    public void render(MatrixStack matrix, IRenderTypeBuffer renderer, int light, int overlayLight, float partialTicks,
                       boolean hasEffect, LivingEntity entity, ItemStack stack) {
        render(matrix, renderer, light, overlayLight, hasEffect);
    }

    private void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlayLight,
                        boolean hasEffect) {
        matrix.pushPose();
        body.translateAndRotate(matrix);
        matrix.translate(0, 0, 0.0);
        model.render(matrix, renderer, light, overlayLight, hasEffect);
        matrix.popPose();
    }

    abstract static class SunglassesModel extends Model {

        protected RenderType renderType;

        public SunglassesModel(Function<ResourceLocation, RenderType> renderTypeGetter) {
            super(renderTypeGetter);
        }

        public void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlayLight,
                           boolean hasEffect) {
            renderToBuffer(matrix, getVertexBuilder(renderer, renderType, hasEffect), light, overlayLight, 1, 1, 1, 1);
        }

        protected IVertexBuilder getVertexBuilder(@Nonnull IRenderTypeBuffer renderer, @Nonnull RenderType renderType,
                                                  boolean hasEffect) {
            return ItemRenderer.getFoilBufferDirect(renderer, renderType, false, hasEffect);
        }
    }

    static class SunglassesModel2 extends SunglassesModel {
        private final ModelRenderer bb_main;

        public SunglassesModel2(ResourceLocation texture) {
            super(RenderType::entitySolid);
            this.renderType = renderType(texture);
            texWidth = 32;
            texHeight = 32;

            bb_main = new ModelRenderer(this);
            bb_main.setPos(0.0F, 24.0F, 0.0F);
            bb_main.texOffs(0, 0).addBox(-4.0F, -31.95F, -4.0F, 8.0F, 8.0F, 8.0F, 0.01F, false);
        }

        @Override
        public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
            bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
        }

        public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
            modelRenderer.xRot = x;
            modelRenderer.yRot = y;
            modelRenderer.zRot = z;
        }
    }
}
