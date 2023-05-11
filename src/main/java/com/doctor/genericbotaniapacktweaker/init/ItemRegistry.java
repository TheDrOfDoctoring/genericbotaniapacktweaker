package com.doctor.genericbotaniapacktweaker.init;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.items.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GenericBotaniaPackTweaker.MODID);
    public static final RegistryObject<ManaelectrumPickaxeItem> MANAELECTRUM_PICKAXE = ITEMS.register("manaelectrum_pickaxe", () -> new ManaelectrumPickaxeItem(Tiers.GOLD, 1, -2.8F, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<ManaelectrumShovelItem> MANAELECTRUM_SHOVEL = ITEMS.register("manaelectrum_shovel", () -> new ManaelectrumShovelItem(Tiers.GOLD, 1.5F, -3.0F, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<ManaelectrumAxeItem> MANAELECTRUM_AXE = ITEMS.register("manaelectrum_axe", () -> new ManaelectrumAxeItem(Tiers.GOLD, 6.0F, -3.0F, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<ManaelectrumSwordItem> MANAELECTRUM_SWORD = ITEMS.register("manaelectrum_sword", () -> new ManaelectrumSwordItem(Tiers.GOLD, 3, -2.4F, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<ManaelectrumHoeItem> MANAELECTRUM_HOE = ITEMS.register("manaelectrum_hoe", () -> new ManaelectrumHoeItem(Tiers.GOLD, 0, -3.0F,new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> DIESELLOTUS_FLOATING_ITEM = ITEMS.register("diesellotus_floating", () -> new BlockItem(BlockRegistry.dieselLotusFloating.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> DIESELLOTUS_ITEM = ITEMS.register("diesellotus", () -> new BlockItem(BlockRegistry.dieselLotus.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
