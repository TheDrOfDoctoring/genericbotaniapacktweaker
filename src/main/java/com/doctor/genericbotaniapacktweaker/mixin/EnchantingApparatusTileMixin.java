package com.doctor.genericbotaniapacktweaker.mixin;

import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import com.hollingsworth.arsnouveau.api.block.IPedestalMachine;
import com.hollingsworth.arsnouveau.common.block.ITickable;
import com.hollingsworth.arsnouveau.common.block.tile.EnchantingApparatusTile;
import com.hollingsworth.arsnouveau.common.block.tile.IAnimationListener;
import com.hollingsworth.arsnouveau.common.block.tile.SingleItemTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.bernie.ars_nouveau.geckolib3.core.IAnimatable;

@Mixin(EnchantingApparatusTile.class)
public abstract class EnchantingApparatusTileMixin extends SingleItemTile implements Container, IPedestalMachine, ITickable, IAnimatable, IAnimationListener {
    @Shadow private int counter;

    public EnchantingApparatusTileMixin(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    @Inject(method = "tick", at = @At("HEAD"), remap = false)
    public void tick(CallbackInfo ci) {
        if(this.level.getBlockState(getBlockPos().below()).getBlock() == BlockRegistry.fastArcane.get()) {
            this.counter = 211;
        }

    }
}
