package com.doctor.genericbotaniapacktweaker.mixin;

import alexthw.ars_elemental.common.items.armor.AAMaterials;
import com.hollingsworth.arsnouveau.api.client.IVariantColorProvider;
import com.hollingsworth.arsnouveau.api.mana.IManaEquipment;
import com.hollingsworth.arsnouveau.api.perk.IPerkHolder;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import com.hollingsworth.arsnouveau.common.armor.AnimatedMagicArmor;
import com.hollingsworth.arsnouveau.common.armor.Materials;
import com.hollingsworth.arsnouveau.common.crafting.recipes.IDyeable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import software.bernie.ars_nouveau.geckolib3.core.IAnimatable;

@Mixin(AnimatedMagicArmor.class)
public abstract class AnimatedMagicArmorMixin  extends ArmorItem implements IManaEquipment, IDyeable, IAnimatable, IVariantColorProvider<ItemStack> {

    public AnimatedMagicArmorMixin(ArmorMaterial material, EquipmentSlot p_40387_, Properties p_40388_) {
        super(material, p_40387_, p_40388_);
    }

    @Redirect(method = "getAttributeModifiers", at = @At(value = "INVOKE", target = "Lcom/hollingsworth/arsnouveau/api/perk/IPerkHolder;getTier()I"), remap = false)
    public int getAttributeModifiers(IPerkHolder perkholder) {
        int result;
        if (material == Materials.LIGHT) {
            result = perkholder.getTier();
        } else if (material == Materials.MEDIUM) {
            result = perkholder.getTier() + 1;
        } else if (material == Materials.HEAVY || material == AAMaterials.air || material == AAMaterials.earth || material == AAMaterials.fire || material == AAMaterials.water){
            result = perkholder.getTier() + 2;
        } else {
            result = perkholder.getTier();
        }
        return result;
    }
}

