package io.github.firetamer.dbb.client.armour;

import com.matyrobbrt.lib.util.Utils;
import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.firetamer.dbb.client.model.CustomArmourModel;
import io.github.firetamer.dbb.item.api.IRenderableArmour;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DBBArmourLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>>
        extends BipedArmorLayer<T, M, A> {

    public DBBArmourLayer(IEntityRenderer<T, M> entityRenderer, A modelLeggings, A modelArmor) {
        super(entityRenderer, modelLeggings, modelArmor);
    }

    @Override
    public void render(MatrixStack matrix, IRenderTypeBuffer renderer, int packedLight, T entity, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        renderPart(matrix, renderer, entity, EquipmentSlotType.CHEST, packedLight, partialTicks);
        renderPart(matrix, renderer, entity, EquipmentSlotType.LEGS, packedLight, partialTicks);
        renderPart(matrix, renderer, entity, EquipmentSlotType.FEET, packedLight, partialTicks);
        renderPart(matrix, renderer, entity, EquipmentSlotType.HEAD, packedLight, partialTicks);
    }

    @SuppressWarnings("unchecked")
    private void renderPart(MatrixStack matrix, IRenderTypeBuffer renderer, T entity, EquipmentSlotType slot, int light,
                            float partialTicks) {
        ItemStack stack = entity.getItemBySlot(slot);
        Item item = stack.getItem();
        Utils.instanceOf(item, ArmorItem.class).ifPresent(armourItem -> {
            if (armourItem.getSlot() == slot) {
                Utils.instanceOf(item, IRenderableArmour.class).ifPresent(renderable -> {
                    CustomArmourModel model = renderable.getModel(stack);
                    getParentModel().copyPropertiesTo((BipedModel<T>) model);
                    setPartVisibility((A) model, slot);
                    model.render(matrix, renderer, light, OverlayTexture.NO_OVERLAY, partialTicks, stack.hasFoil(),
                            entity, stack);
                });
            }
        });
    }

}
