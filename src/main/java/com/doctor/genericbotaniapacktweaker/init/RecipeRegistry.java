package com.doctor.genericbotaniapacktweaker.init;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.recipe.DieselotusRecipe;
import com.doctor.genericbotaniapacktweaker.recipe.IDieselotusRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

public class RecipeRegistry {
    public static DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, GenericBotaniaPackTweaker.MODID);
    public static DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GenericBotaniaPackTweaker.MODID);
    public static final RegistryObject<RecipeSerializer<DieselotusRecipe>> DIESELOTUS_SERIALIZER= RECIPE_SERIALIZERS.register("dieselotus", DieselotusRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<DieselotusRecipe>> DIESELOTUS_RECIPE_TYPE = RECIPE_TYPES.register("dieselotus", () -> new RecipeType<>() {
        public String toString() {
            return "dieselotus";
        }
    });

}
