package com.doctor.genericbotaniapacktweaker.data;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import vazkii.botania.common.lib.BotaniaTags;

public class BlockTagProvider extends BlockTagsProvider {
    public BlockTagProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, GenericBotaniaPackTweaker.MODID, helper);
    }
    @Override
    protected void addTags() {
        tag(BotaniaTags.Blocks.GENERATING_SPECIAL_FLOWERS).add(BlockRegistry.dieselLotus.get());
        tag(BotaniaTags.Blocks.SPECIAL_FLOATING_FLOWERS).add(BlockRegistry.dieselLotusFloating.get());
        tag(BotaniaTags.Blocks.FLOATING_FLOWERS).add(BlockRegistry.dieselLotusFloating.get());

    }
}
