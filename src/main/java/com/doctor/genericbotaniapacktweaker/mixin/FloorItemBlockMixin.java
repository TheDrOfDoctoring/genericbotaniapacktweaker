package com.doctor.genericbotaniapacktweaker.mixin;

import com.ninni.twigs.block.FloorItemBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FloorItemBlock.class)
public class FloorItemBlockMixin {

    @Inject(method = "canSurvive", at = @At(value = "RETURN"), remap = false, cancellable = true)
    public void canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

}
