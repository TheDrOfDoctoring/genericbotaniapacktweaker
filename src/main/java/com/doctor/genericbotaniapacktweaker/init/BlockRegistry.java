package com.doctor.genericbotaniapacktweaker.init;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.flowers.DieselotusBlockEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.FloatingSpecialFlowerBlock;
import vazkii.botania.forge.block.ForgeSpecialFlowerBlock;

import java.util.function.Supplier;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GenericBotaniaPackTweaker.MODID);

    public static final RegistryObject<ForgeSpecialFlowerBlock> dieselLotus = BLOCKS.register("diesellotus", () -> new ForgeSpecialFlowerBlock(MobEffects.ABSORPTION, 100, BlockBehaviour.Properties.copy(Blocks.POPPY), BlockEntityRegistry.DIESELLOTUS::get));
    public static final RegistryObject<FloatingSpecialFlowerBlock> dieselLotusFloating = BLOCKS.register("diesellotus_floating", () -> new FloatingSpecialFlowerBlock(BotaniaBlocks.FLOATING_PROPS, BlockEntityRegistry.DIESELLOTUS::get));
}
