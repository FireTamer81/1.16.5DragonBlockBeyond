package io.github.firetamer.dbb.item;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.client.model.CustomArmourModel;
import io.github.firetamer.dbb.client.model.SunglassesRenderer;
import io.github.firetamer.dbb.api.item.IRenderableArmour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SunglassesItem extends ArmorItem implements IRenderableArmour {

    public SunglassesItem(IArmorMaterial material, EquipmentSlotType slot, Properties props) {
        super(material, slot, props);
    }

    @Override
	@OnlyIn(Dist.CLIENT)
    public CustomArmourModel getModel(ItemStack stack) {
        GlassesModel glassesModel = getGlassesModel(stack);
        // TODO add the second model as well, along with fixing the model
        return SunglassesRenderer.RENDERER_2;
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
        if (player.isShiftKeyDown()) {
            cycleGlassesModel(player.getItemInHand(hand));
        }
        return super.use(level, player, hand);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return DragonBlockBeyond.MOD_ID + ":render/null_armour.png";
    }

    public static void setGlassesModel(ItemStack stack, GlassesModel model) {
        stack.getOrCreateTag().putString("glasses_model", model.name());
    }

    public static void cycleGlassesModel(ItemStack stack) {
        GlassesModel old = getGlassesModel(stack);
        setGlassesModel(stack, old.next());
    }

    public static GlassesModel getGlassesModel(ItemStack stack) {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.contains("glasses_model")) {
            return GlassesModel.valueOf(nbt.getString("glasses_model"));
        }
        return GlassesModel.SUNGLASSES_1;
    }

    enum GlassesModel {
        SUNGLASSES_1, SUNGLASSES_2;

        public GlassesModel next() {
            if (this == SUNGLASSES_1) {
                return SUNGLASSES_2;
            } else {
                return SUNGLASSES_1;
            }
        }
    }
}
