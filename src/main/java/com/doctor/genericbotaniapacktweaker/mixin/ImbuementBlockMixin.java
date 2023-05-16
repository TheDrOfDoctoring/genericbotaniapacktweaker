package com.doctor.genericbotaniapacktweaker.mixin;

import com.hollingsworth.arsnouveau.common.block.ImbuementBlock;
import com.hollingsworth.arsnouveau.common.block.tile.ImbuementTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ImbuementBlock.class)
public abstract class ImbuementBlockMixin {
    //
    public boolean hasAnalogOutputSignal(BlockState p_49618_) {
        return true;
    }
    public int getAnalogOutputSignal(BlockState p_49620_, Level level, BlockPos pos) {
        ImbuementTile tile = (ImbuementTile) level.getBlockEntity(pos);
        return tile.getRecipeNow() != null ? 15 : 0;
    }
}
