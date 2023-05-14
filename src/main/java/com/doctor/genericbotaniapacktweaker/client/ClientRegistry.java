package com.doctor.genericbotaniapacktweaker.client;

import com.doctor.genericbotaniapacktweaker.init.BlockEntityRegistry;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vazkii.botania.client.render.block_entity.SpecialFlowerBlockEntityRenderer;

import java.util.function.BiConsumer;

public class ClientRegistry {
    //why does this work but not setting the model as cutout in the json (but only for the floating)??
    static void registerLayers(FMLClientSetupEvent event) {
        registerRenderTypes(ItemBlockRenderTypes::setRenderLayer);
    }
    public static void registerRenderTypes(BiConsumer<Block, RenderType> r) {
        RenderType cutout = RenderType.cutout();
        r.accept(BlockRegistry.dieselotus.get(), cutout);
        r.accept(BlockRegistry.dieselotusFloating.get(), cutout);
    }
    static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.DIESELLOTUS.get(), SpecialFlowerBlockEntityRenderer::new);
    }
}
