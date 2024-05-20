package com.doctor.genericbotaniapacktweaker.recipe;

import com.doctor.genericbotaniapacktweaker.init.RecipeRegistry;
import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import net.minecraft.commands.CommandFunction;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.api.recipe.StateIngredient;
import vazkii.botania.common.crafting.*;

public class DieselotusRecipe implements IDieselotusRecipe {
    private final ResourceLocation id;
    protected final StateIngredient input;
    private final CommandFunction.CacheableFunction function;
    protected final int burnTime;

    public DieselotusRecipe(ResourceLocation id, StateIngredient input, int burnTime, CommandFunction.CacheableFunction function) {
        this.id = id;
        this.input = input;
        this.burnTime = burnTime;
        this.function = function;
    }


    @Override
    public boolean matches(Level world, BlockPos pos, BlockState state, SpecialFlowerBlockEntity be) {
        if(world.getFluidState(pos).isSource()) {
            return this.input.test(state);
        }
        return false;
    }

    @Override
    public boolean set(Level world, BlockState state, SpecialFlowerBlockEntity be, BlockPos pos) {
        if (!world.isClientSide) {
            boolean success = world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            if (success) {
                ServerLevel serverLevel = (ServerLevel)world;
                MinecraftServer server = serverLevel.getServer();
                this.function.get(server.getFunctions()).ifPresent((command) -> {
                    CommandSourceStack context = server.getFunctions().getGameLoopSender().withLevel((ServerLevel)world).withPosition(Vec3.atBottomCenterOf(pos));
                    server.getFunctions().execute(command, context);
                });
            }

            return success;
        } else {
            return true;
        }
    }

    public StateIngredient getInput() {
        return this.input;
    }
    public int getBurnTime() {
        return this.burnTime;
    }

    public CommandFunction.CacheableFunction getSuccessFunction() {
        return this.function;
    }


    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.DIESELOTUS_SERIALIZER.get();
    }

    public static class Serializer extends RecipeSerializerBase<DieselotusRecipe> {

        public @NotNull DieselotusRecipe fromJson(@NotNull ResourceLocation id, JsonObject object) {
            StateIngredient input = StateIngredientHelper.deserialize(GsonHelper.getAsJsonObject(object, "input"));
            String functionIdString = GsonHelper.getAsString(object, "success_function", (String)null);
            int burnTime = GsonHelper.getAsInt(object, "burntime");
            ResourceLocation functionId = functionIdString == null ? null : new ResourceLocation(functionIdString);
            CommandFunction.CacheableFunction function = functionId == null ? CommandFunction.CacheableFunction.NONE : new CommandFunction.CacheableFunction(functionId);
            return new DieselotusRecipe(id, input, burnTime, function);
        }

        public void toNetwork(@NotNull FriendlyByteBuf buf, DieselotusRecipe recipe) {
            recipe.input.write(buf);
            buf.writeVarInt(recipe.burnTime);
        }

        public @NotNull DieselotusRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
            StateIngredient input = StateIngredientHelper.read(buf);
            int burnTime = buf.readVarInt();
            return new DieselotusRecipe(id, input, burnTime, CommandFunction.CacheableFunction.NONE);
        }
    }
}
