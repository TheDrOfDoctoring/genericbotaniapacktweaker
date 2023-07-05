package com.doctor.genericbotaniapacktweaker.mixin;

import com.hollingsworth.arsnouveau.api.spell.AbstractEffect;
import com.hollingsworth.arsnouveau.api.spell.SpellStats;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CakeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractEffect.class)
public class AbstractEffectMixin {

    @Inject(method = "canBlockBeHarvested", at = @At(value = "RETURN"), remap = false, cancellable = true )
    private void canBlockBeHarvested(SpellStats stats, Level world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if(world.getBlockState(pos).getBlock() instanceof CakeBlock) {
            cir.setReturnValue(false);
        }
    }
}
