package com.doctor.genericbotaniapacktweaker;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.moddingplayground.twigs.init.TwigsBlocks;

@Mod.EventBusSubscriber
public class EntityEventHandler {

    @SubscribeEvent
    public static void entityDeath(LivingDeathEvent event) {
        if(event.getEntity().getBlockStateOn().getBlock() == Blocks.STONE) {
            LivingEntity entity = event.getEntity();
            if(!entity.getCommandSenderWorld().isClientSide) {
                entity.getCommandSenderWorld().setBlock(entity.getOnPos(), TwigsBlocks.BLOODSTONE.get().defaultBlockState(), 3);
            }
        }
    }
}
