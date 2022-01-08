package io.github.firetamer.dbb.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public abstract class CustomArmourModel extends BipedModel<LivingEntity> {

    protected CustomArmourModel(float size) {
        super(size);
    }

    public abstract void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light,
                                int overlayLight, float partialTicks, boolean hasEffect, LivingEntity entity, ItemStack stack);

}
