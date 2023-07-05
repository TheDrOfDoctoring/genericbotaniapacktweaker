package com.doctor.genericbotaniapacktweaker.init;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.block.FastArcaneCore;
import com.doctor.genericbotaniapacktweaker.block.ImbuedDirtBlock;
import com.doctor.genericbotaniapacktweaker.block.ManaMotor;
import com.doctor.genericbotaniapacktweaker.flowers.DieselotusBlockEntity;
import com.doctor.genericbotaniapacktweaker.flowers.SpineretteFloatingFlowerBlock;
import com.doctor.genericbotaniapacktweaker.flowers.SpineretteFlowerBlock;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
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
    public static final RegistryObject<ImbuedDirtBlock> imbuedDirt = BLOCKS.register("imbued_dirt", () -> new ImbuedDirtBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<ManaMotor> manaMotor = BLOCKS.register("mana_motor", () -> new ManaMotor(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<SpineretteFlowerBlock> spinerette= BLOCKS.register("spinerette", () -> new SpineretteFlowerBlock(MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.copy(Blocks.POPPY), BlockEntityRegistry.SPINERETTE::get));
    public static final RegistryObject<SpineretteFloatingFlowerBlock> spineretteFloating = BLOCKS.register("spinerette_floating", () -> new SpineretteFloatingFlowerBlock(BotaniaBlocks.FLOATING_PROPS, BlockEntityRegistry.SPINERETTE::get));

    public static final RegistryObject<ForgeSpecialFlowerBlock> dieselotus = BLOCKS.register("dieselotus", () -> new ForgeSpecialFlowerBlock(MobEffects.ABSORPTION, 100, BlockBehaviour.Properties.copy(Blocks.POPPY), BlockEntityRegistry.DIESELLOTUS::get));
    public static final RegistryObject<FloatingSpecialFlowerBlock> dieselotusFloating = BLOCKS.register("dieselotus_floating", () -> new FloatingSpecialFlowerBlock(BotaniaBlocks.FLOATING_PROPS, BlockEntityRegistry.DIESELLOTUS::get));
    public static final RegistryObject<FastArcaneCore> fastArcane = BLOCKS.register("fast_arcane_core", FastArcaneCore::new);
}
