package com.doctor.genericbotaniapacktweaker.block;

import com.doctor.genericbotaniapacktweaker.block.entity.FastArcaneCoreBlockEntity;
import com.hollingsworth.arsnouveau.common.block.ArcaneCore;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class FastArcaneCore extends ArcaneCore {

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FastArcaneCoreBlockEntity(pos, state);
    }

}
