package com.doctor.genericbotaniapacktweaker.recipe;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import net.minecraft.commands.CommandFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.api.recipe.StateIngredient;
import vazkii.botania.common.crafting.BlockStateIngredient;

public interface IDieselotusRecipe extends Recipe<Container> {
    ResourceLocation TYPE_ID = new ResourceLocation(GenericBotaniaPackTweaker.MODID, "dieselotus");

    boolean matches(Level world, BlockPos pos, BlockState state, SpecialFlowerBlockEntity be);

    boolean set(Level level, BlockState state, SpecialFlowerBlockEntity be, BlockPos pos);

    StateIngredient getInput();

    CommandFunction.CacheableFunction getSuccessFunction();

    default RecipeType<?> getType() {
        return (RecipeType) Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    default boolean matches(Container p_77569_1_, Level p_77569_2_) {
        return false;
    }

    default ItemStack assemble(Container p_77572_1_) {
        return ItemStack.EMPTY;
    }

    default boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
        return false;
    }

    default ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    default boolean isSpecial() {
        return true;
    }
}
