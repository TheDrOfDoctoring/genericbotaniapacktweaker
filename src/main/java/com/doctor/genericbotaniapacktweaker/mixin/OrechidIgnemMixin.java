package com.doctor.genericbotaniapacktweaker.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.OrechidIgnemBlockEntity;

@Mixin(OrechidIgnemBlockEntity.class)
public class OrechidIgnemMixin {
    @Inject(method = "canOperate", at = @At(value = "RETURN"), remap = false, cancellable = true )
    private void canOperate(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

}
