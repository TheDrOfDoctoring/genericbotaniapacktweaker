package com.doctor.genericbotaniapacktweaker.glyphs;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.item.BotaniaItems;

import java.util.Set;

public class EffectPebbleSpawn extends AbstractEffect {

    public static final EffectPebbleSpawn INSTANCE = new EffectPebbleSpawn(new ResourceLocation(GenericBotaniaPackTweaker.MODID, "glyph_pebble"), "Pebblespawn");

    public EffectPebbleSpawn(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    public int getDefaultManaCost() {
        return 50;
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return setOf(AugmentAmplify.INSTANCE);
    }

    @Override
    public void onResolveBlock(BlockHitResult rayTraceResult, Level world, @NotNull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if(world.getBlockState(rayTraceResult.getBlockPos()).getBlock() == Blocks.GRASS_BLOCK) {
            BlockPos pos = rayTraceResult.getBlockPos();
            Item item = BotaniaItems.pebble.asItem();
            for (int i = 0; i < 15 * (spellStats.getAmpMultiplier() + 1); i++) {
                world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, item.getDefaultInstance()));
            }
        }

    }

    @Override
    public boolean defaultedStarterGlyph() {
        return true;
    }


}
