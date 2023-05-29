package com.doctor.genericbotaniapacktweaker.data;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import vazkii.botania.common.lib.BotaniaTags;

public class BlockTagProvider extends BlockTagsProvider {
    public BlockTagProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, GenericBotaniaPackTweaker.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(BotaniaTags.Blocks.GENERATING_SPECIAL_FLOWERS).add(BlockRegistry.dieselotus.get()).add(BlockRegistry.spinerette.get());
        tag(BotaniaTags.Blocks.SPECIAL_FLOATING_FLOWERS).add(BlockRegistry.dieselotusFloating.get()).add(BlockRegistry.spineretteFloating.get());
        tag(BotaniaTags.Blocks.FLOATING_FLOWERS).add(BlockRegistry.dieselotusFloating.get()).add(BlockRegistry.spineretteFloating.get());

    }
}
