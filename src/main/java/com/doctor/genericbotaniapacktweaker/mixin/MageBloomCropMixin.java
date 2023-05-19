package com.doctor.genericbotaniapacktweaker.mixin;

import com.hollingsworth.arsnouveau.common.block.MageBloomCrop;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MageBloomCrop.class)
public abstract class MageBloomCropMixin extends CropBlock {
    public MageBloomCropMixin(Properties p_52247_) {
        super(p_52247_);
    }
    @Override
    public boolean isValidBonemealTarget(BlockGetter p_52258_, BlockPos p_52259_, BlockState p_52260_, boolean p_52261_) {
        return false;
    }
}
