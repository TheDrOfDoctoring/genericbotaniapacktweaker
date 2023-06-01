package com.doctor.genericbotaniapacktweaker;

import com.doctor.genericbotaniapacktweaker.client.ClientRegistryHandler;
import com.doctor.genericbotaniapacktweaker.data.BlockTagProvider;
import com.doctor.genericbotaniapacktweaker.data.ItemTagProvider;
import com.doctor.genericbotaniapacktweaker.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(GenericBotaniaPackTweaker.MODID)
public class GenericBotaniaPackTweaker
{
    public static final String MODID = "genericbotaniapacktweaker";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GenericBotaniaPackTweaker() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::gatherData);

        ItemRegistry.ITEMS.register(modEventBus);
        GlyphRegistry.registerGlyphs();
        RecipeRegistry.RECIPE_TYPES.register(modEventBus);
        RecipeRegistry.RECIPE_SERIALIZERS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITY_TYPE.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientRegistryHandler::init);
    }
    private void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        BlockTagsProvider blockProvider = new BlockTagProvider(generator, event.getExistingFileHelper());

        generator.addProvider(event.includeServer(), blockProvider);
        generator.addProvider(event.includeServer(), new ItemTagProvider(generator, blockProvider, GenericBotaniaPackTweaker.MODID, event.getExistingFileHelper()));
    }




}
