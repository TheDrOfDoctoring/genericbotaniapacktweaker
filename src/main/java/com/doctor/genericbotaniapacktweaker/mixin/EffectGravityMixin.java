package com.doctor.genericbotaniapacktweaker.mixin;

import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import com.hollingsworth.arsnouveau.api.spell.SpellStats;
import com.hollingsworth.arsnouveau.common.spell.effect.EffectGravity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(EffectGravity.class)
public class EffectGravityMixin {
    @Inject(method = "onResolveBlock", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private void onResolveBlock(BlockHitResult rayTraceResult, Level world, LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver, CallbackInfo ci) {
        if(world.getBlockState(rayTraceResult.getBlockPos()).getBlock() instanceof CakeBlock) {
            ci.cancel();
        }
    }
}
