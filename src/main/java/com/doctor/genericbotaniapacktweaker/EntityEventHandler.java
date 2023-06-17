package com.doctor.genericbotaniapacktweaker;

import com.doctor.genericbotaniapacktweaker.block.entity.ManaMotorBlockEntity;
import com.doctor.genericbotaniapacktweaker.flowers.SpineretteBlockEntity;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import com.doctor.genericbotaniapacktweaker.init.ItemRegistry;
import com.hollingsworth.arsnouveau.common.block.ModBlock;
import com.ninni.twigs.registry.TwigsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityEventHandler {

    @SubscribeEvent
    public static void entityDeath(LivingDeathEvent event) {
        if(event.getEntity() instanceof Creeper creeper) {
            if(creeper.getSwelling(1) > 10 && creeper.getSwelling(1) < 30 ) {
                creeper.spawnAtLocation(ItemRegistry.CREEPER_GLAND.get());
            }
        }
        if(event.getEntity().getBlockStateOn().getBlock() == Blocks.STONE) {
            LivingEntity entity = event.getEntity();
            if(!entity.getCommandSenderWorld().isClientSide) {
                entity.getCommandSenderWorld().setBlock(entity.getOnPos(), TwigsBlocks.BLOODSTONE.get().defaultBlockState(), 3);
            }
        }
    }
    //is there like, onBlockBroken or something I can override in the flower BE? or a way to check when its being removed that it's not being removed on unload? eitherway this seems to work for now
    @SubscribeEvent
    public static void blockBroken(BlockEvent.BreakEvent event) {
        LevelAccessor level = event.getLevel();
        if (level.getBlockEntity(event.getPos()) != null && !level.isClientSide()) {
            BlockEntity be = event.getLevel().getBlockEntity(event.getPos());
            if(be instanceof SpineretteBlockEntity) {
                SpineretteBlockEntity spinerette = (SpineretteBlockEntity) be;
                BlockPos bindPos = spinerette.getBindPos();
                if(level.getBlockEntity(bindPos) != null && level.getBlockEntity(bindPos) instanceof ManaMotorBlockEntity) {
                    ManaMotorBlockEntity manaMotorBlockEntity = (ManaMotorBlockEntity) level.getBlockEntity(bindPos);
                    manaMotorBlockEntity.removeBoundFlower(spinerette.getBlockPos());
                }
            }

        }
    }
}
