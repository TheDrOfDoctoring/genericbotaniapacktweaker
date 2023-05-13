package com.doctor.genericbotaniapacktweaker.init;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.flowers.DieselotusBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.botania.common.block.BotaniaFlowerBlocks;

import java.util.function.BiConsumer;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GenericBotaniaPackTweaker.MODID);
    public static final RegistryObject<BlockEntityType<DieselotusBlockEntity>> DIESELLOTUS = BLOCK_ENTITY_TYPE.register("dieselotus", () -> create(DieselotusBlockEntity::new, BlockRegistry.dieselotusFloating.get(), BlockRegistry.dieselotus.get()));

    private static <T extends BlockEntity> BlockEntityType<T> create(BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory, blocks).build(null);
    }


}
