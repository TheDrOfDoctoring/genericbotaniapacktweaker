package com.doctor.genericbotaniapacktweaker.init;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.block.entity.FastArcaneCoreBlockEntity;
import com.doctor.genericbotaniapacktweaker.block.entity.ManaMotorBlockEntity;
import com.doctor.genericbotaniapacktweaker.flowers.DieselotusBlockEntity;
import com.doctor.genericbotaniapacktweaker.flowers.SpineretteBlockEntity;
import com.hollingsworth.arsnouveau.common.block.ArcaneCore;
import com.hollingsworth.arsnouveau.common.block.tile.ArcaneCoreTile;
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
    public static final RegistryObject<BlockEntityType<FastArcaneCoreBlockEntity>> FAST_CORE = BLOCK_ENTITY_TYPE.register("fast_arcane_core", () -> create(FastArcaneCoreBlockEntity::new, BlockRegistry.fastArcane.get()));
    public static final RegistryObject<BlockEntityType<ManaMotorBlockEntity>> MANAMOTOR = BLOCK_ENTITY_TYPE.register("mana_motor", () -> create(ManaMotorBlockEntity::new, BlockRegistry.manaMotor.get()));

    public static final RegistryObject<BlockEntityType<SpineretteBlockEntity>> SPINERETTE = BLOCK_ENTITY_TYPE.register("spinerette", () -> create(SpineretteBlockEntity::new, BlockRegistry.spineretteFloating.get(), BlockRegistry.spinerette.get()));
    public static final RegistryObject<BlockEntityType<DieselotusBlockEntity>> DIESELLOTUS = BLOCK_ENTITY_TYPE.register("dieselotus", () -> create(DieselotusBlockEntity::new, BlockRegistry.dieselotusFloating.get(), BlockRegistry.dieselotus.get()));

    private static <T extends BlockEntity> BlockEntityType<T> create(BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory, blocks).build(null);
    }


}
