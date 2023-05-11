package com.doctor.genericbotaniapacktweaker.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.generating.EndoflameBlockEntity;
import vazkii.botania.common.block.mana.ManaSpreaderBlock;
import vazkii.botania.xplat.XplatAbstractions;

@Mixin(EndoflameBlockEntity.class)
public class EndoflameBlockEntityMixin {

    @Inject(method = "getBurnTime", at = @At(value = "RETURN"), remap = false, cancellable = true )
    private void getBurnTime(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (cir.getReturnValue() > 0) {
            cir.setReturnValue(cir.getReturnValue() / 2);
        }
    }
}
