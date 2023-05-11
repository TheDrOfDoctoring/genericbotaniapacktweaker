package com.doctor.genericbotaniapacktweaker.flowers;

import com.doctor.genericbotaniapacktweaker.init.BlockEntityRegistry;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.client.fx.WispParticleData;
import vazkii.botania.common.block.BotaniaFlowerBlocks;
import vazkii.botania.common.block.flower.generating.FluidGeneratorBlockEntity;
import vazkii.botania.common.handler.BotaniaSounds;

public class DieselotusBlockEntity extends FluidGeneratorBlockEntity {
    //a very minimally modified version of the thermalilly
    public DieselotusBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.DIESELLOTUS.get(), pos, state, FluidTags.WATER, 900, 200, 6000);
    }
    
    @Override
    public void tickFlower() {
        super.tickFlower();
    }


    public int getColor() {
        return 13646848;
    }

    public void doBurnParticles() {
        WispParticleData data = WispParticleData.wisp((float)Math.random() / 6.0F, 0.7F, 0.05F, 0.05F, 1.0F);
        this.emitParticle(data, 0.5 + Math.random() * 0.2 - 0.1, 0.9 + Math.random() * 0.2 - 0.1, 0.5 + Math.random() * 0.2 - 0.1, 0.0, (double)((float)Math.random() / 60.0F), 0.0);
    }

    public void playSound() {
        this.getLevel().playSound((Player)null, this.getEffectivePos(), BotaniaSounds.thermalily, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public int getMaxMana() {
        return 50000;

    }
}
