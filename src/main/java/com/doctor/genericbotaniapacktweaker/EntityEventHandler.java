package com.doctor.genericbotaniapacktweaker;

import com.doctor.genericbotaniapacktweaker.block.entity.ManaMotorBlockEntity;
import com.doctor.genericbotaniapacktweaker.flowers.SpineretteBlockEntity;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import com.doctor.genericbotaniapacktweaker.init.ItemRegistry;
import com.doctor.genericbotaniapacktweaker.mixin.CreeperAccessor;
import com.hollingsworth.arsnouveau.common.block.ModBlock;
import com.ninni.twigs.registry.TwigsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.botania.common.item.BotaniaItems;

@Mod.EventBusSubscriber
public class EntityEventHandler {

    @SubscribeEvent
    public static void entityDeath(LivingDeathEvent event) {
        if(event.getEntity() instanceof Creeper creeper && !creeper.getCommandSenderWorld().isClientSide) {
            int swelling = ((CreeperAccessor) creeper).getSwell();
            if(swelling > 10 && swelling < 30 ) {
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
    @SubscribeEvent
    public static void bonemealApplied(BonemealEvent event) {
        BlockState block = event.getBlock();
        if(event.getStack().is(BotaniaItems.livingroot) && block.is(BlockTags.SAPLINGS) && !event.getLevel().isClientSide && block.getBlock() instanceof SaplingBlock sapling) {
            block.setValue(BlockStateProperties.STAGE, 1);
            sapling.advanceTree((ServerLevel) event.getLevel(), event.getPos(), event.getBlock(), event.getLevel().random);

        }
    }
}
