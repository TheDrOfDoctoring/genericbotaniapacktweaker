package com.doctor.genericbotaniapacktweaker.client;

import com.doctor.genericbotaniapacktweaker.block.entity.FastArcaneCoreRenderer;
import com.doctor.genericbotaniapacktweaker.block.entity.ManaMotorBlockEntity;
import com.doctor.genericbotaniapacktweaker.block.entity.ManaMotorRenderer;
import com.doctor.genericbotaniapacktweaker.init.BlockEntityRegistry;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import com.hollingsworth.arsnouveau.client.renderer.tile.ArcaneCoreRenderer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.backend.instancing.InstancedRenderRegistry;
import com.jozufozu.flywheel.backend.instancing.blockentity.BlockEntityInstance;
import com.jozufozu.flywheel.backend.instancing.blockentity.SimpleBlockEntityInstancingController;
import com.jozufozu.flywheel.core.Materials;
import com.jozufozu.flywheel.util.NonNullSupplier;
import com.simibubi.create.content.contraptions.base.GeneratingKineticTileEntity;
import com.simibubi.create.content.contraptions.base.HalfShaftInstance;
import com.simibubi.create.foundation.data.CreateTileEntityBuilder;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vazkii.botania.client.render.block_entity.SpecialFlowerBlockEntityRenderer;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class ClientRegistry {
    //why does this work but not setting the model as cutout in the json (but only for the floating)??


    static void registerLayers(FMLClientSetupEvent event) {
        registerRenderTypes(ItemBlockRenderTypes::setRenderLayer);
    }
    public static void registerRenderTypes(BiConsumer<Block, RenderType> r) {
        RenderType cutout = RenderType.cutout();
        r.accept(BlockRegistry.dieselotus.get(), cutout);
        r.accept(BlockRegistry.dieselotusFloating.get(), cutout);
        r.accept(BlockRegistry.spinerette.get(), cutout);
        r.accept(BlockRegistry.spineretteFloating.get(), cutout);
        r.accept(BlockRegistry.fastArcane.get(), cutout);
    }
    static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.DIESELLOTUS.get(), SpecialFlowerBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.SPINERETTE.get(), SpecialFlowerBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.MANAMOTOR.get(), ManaMotorRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.FAST_CORE.get(), FastArcaneCoreRenderer::new);
    }

    static void registerInstance(FMLClientSetupEvent event) {
        InstancedRenderRegistry.BlockEntityConfig<ManaMotorBlockEntity> registry = InstancedRenderRegistry.configure(BlockEntityRegistry.MANAMOTOR.get());
        registry.factory(HalfShaftInstance::new);
        registry.apply();

    }
}
