package com.doctor.genericbotaniapacktweaker.init;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.block.entity.FastArcaneCoreRenderer;
import com.doctor.genericbotaniapacktweaker.items.*;
import com.hollingsworth.arsnouveau.common.items.AnimBlockItem;
import com.hollingsworth.arsnouveau.common.items.RendererBlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GenericBotaniaPackTweaker.MODID);
    public static final RegistryObject<ManaelectrumPickaxeItem> MANAELECTRUM_PICKAXE = ITEMS.register("manaelectrum_pickaxe", () -> new ManaelectrumPickaxeItem(Tiers.GOLD, 1, -2.8F, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<ManaelectrumShovelItem> MANAELECTRUM_SHOVEL = ITEMS.register("manaelectrum_shovel", () -> new ManaelectrumShovelItem(Tiers.GOLD, 1.5F, -3.0F, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<ManaelectrumAxeItem> MANAELECTRUM_AXE = ITEMS.register("manaelectrum_axe", () -> new ManaelectrumAxeItem(Tiers.GOLD, 6.0F, -3.0F, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<ManaelectrumSwordItem> MANAELECTRUM_SWORD = ITEMS.register("manaelectrum_sword", () -> new ManaelectrumSwordItem(Tiers.GOLD, 3, -2.4F, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<ManaelectrumHoeItem> MANAELECTRUM_HOE = ITEMS.register("manaelectrum_hoe", () -> new ManaelectrumHoeItem(Tiers.GOLD, 0, -3.0F,new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> DIESELOTUS_FLOATING_ITEM = ITEMS.register("dieselotus_floating", () -> new BlockItem(BlockRegistry.dieselotusFloating.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> DIESELOTUS_ITEM = ITEMS.register("dieselotus", () -> new BlockItem(BlockRegistry.dieselotus.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> SPINERETTE_FLOATING_ITEM = ITEMS.register("spinerette_floating", () -> new BlockItem(BlockRegistry.spineretteFloating.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> SPINERETTE_ITEM = ITEMS.register("spinerette", () -> new BlockItem(BlockRegistry.spinerette.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> MANA_MOTOR_ITEM = ITEMS.register("mana_motor", () -> new BlockItem(BlockRegistry.manaMotor.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> IMBUED_DIRT = ITEMS.register("imbued_dirt", () -> new BlockItem(BlockRegistry.imbuedDirt.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<RendererBlockItem> FAST_CORE = ITEMS.register("fast_arcane_core", () -> new RendererBlockItem(BlockRegistry.fastArcane.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)) {
        @Override
        public Supplier<BlockEntityWithoutLevelRenderer> getRenderer() {
            return FastArcaneCoreRenderer::getISTER;
        }
    });
}
