package com.doctor.genericbotaniapacktweaker.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.common.block.flower.generating.EntropinnyumBlockEntity;

@Mixin(EntropinnyumBlockEntity.class)
public class EntropinnyumBlockEntityMixin {
    @ModifyConstant(method = "getMaxMana", constant = @Constant(intValue = 6500), remap = false)
    private int mana(int mana) {
        return 4500;
    }

}
