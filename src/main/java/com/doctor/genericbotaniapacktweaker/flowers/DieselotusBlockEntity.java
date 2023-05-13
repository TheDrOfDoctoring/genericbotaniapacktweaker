package com.doctor.genericbotaniapacktweaker.flowers;

import com.doctor.genericbotaniapacktweaker.init.BlockEntityRegistry;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import com.doctor.genericbotaniapacktweaker.init.RecipeRegistry;
import com.doctor.genericbotaniapacktweaker.recipe.DieselotusRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;
import vazkii.botania.api.recipe.PureDaisyRecipe;
import vazkii.botania.client.fx.WispParticleData;
import vazkii.botania.common.block.BotaniaFlowerBlocks;
import vazkii.botania.common.block.flower.generating.FluidGeneratorBlockEntity;
import vazkii.botania.common.handler.BotaniaSounds;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DieselotusBlockEntity extends GeneratingFlowerBlockEntity {
    //basically just a mix of the fluid generator be and the thermalily
    private int burnTime;
    private int cooldown;
    private static final BlockPos[] OFFSETS = new BlockPos[]{new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(-1, 0, 1), new BlockPos(-1, 0, -1), new BlockPos(1, 0, 1), new BlockPos(1, 0, -1)};
    public DieselotusBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.DIESELLOTUS.get(), pos, state);
    }


    @Override
    public int getMaxMana() {
        return 25000;
    }
    public int getGenerationDelay() {
        return 1;
    }
    @Nullable
    private DieselotusRecipe findRecipe(BlockPos coords) {
        BlockState state = getLevel().getBlockState(coords);

         for (Recipe<?> recipe : level.getRecipeManager().getAllRecipesFor(RecipeRegistry.DIESELOTUS_RECIPE_TYPE.get())) {
            if (recipe instanceof DieselotusRecipe dieselotusRecipe && dieselotusRecipe.matches(getLevel(), coords, state, this)) {
                return dieselotusRecipe;
            }
        }
        return null;
    }
    public void tickFlower() {
        super.tickFlower();
        if (this.cooldown > 0) {
            --this.cooldown;

            for(int i = 0; i < 3; ++i) {
                WispParticleData data = WispParticleData.wisp((float)Math.random() / 6.0F, 0.1F, 0.1F, 0.1F, 1.0F);
                this.emitParticle(data, 0.5 + Math.random() * 0.2 - 0.1, 0.5 + Math.random() * 0.2 - 0.1, 0.5 + Math.random() * 0.2 - 0.1, 0.0, (double)((float)Math.random() / 30.0F), 0.0);
            }
        }

        if (!this.getLevel().isClientSide && this.burnTime > 0 && this.ticksExisted % this.getGenerationDelay() == 0) {
            this.addMana(25);
            this.sync();
        }

        if (this.burnTime == 0) {
            if (this.getMana() < this.getMaxMana() && !this.getLevel().isClientSide) {
                List<BlockPos> offsets = Arrays.asList(OFFSETS);
                Collections.shuffle(offsets);
                for (BlockPos offset : offsets) {
                    BlockPos pos = this.getEffectivePos().offset(offset);
                    BlockState bstate = this.getLevel().getBlockState(pos);
                    DieselotusRecipe recipe = findRecipe(pos);
                    if (recipe != null) {
                        this.getLevel().setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                        if (this.cooldown == 0) {
                            this.burnTime += recipe.getBurnTime();
                        } else {
                            this.cooldown = 4500;
                        }
                        this.sync();
                        this.playSound();
                        break;
                    }
                }
            }
        } else {
            if (this.getLevel().random.nextInt(8) == 0) {
                this.doBurnParticles();
            }

            --this.burnTime;
            if (this.burnTime == 0) {
                this.cooldown = 4500;
                this.sync();
            }
        }

    }

    public int getColor() {
        return 13646848;
    }

    public void doBurnParticles() {
        WispParticleData data = WispParticleData.wisp((float)Math.random() / 6.0F, 0.7F, 0.05F, 0.05F, 1.0F);
        this.emitParticle(data, 0.5 + Math.random() * 0.2 - 0.1, 0.9 + Math.random() * 0.2 - 0.1, 0.5 + Math.random() * 0.2 - 0.1, 0.0, (double)((float)Math.random() / 60.0F), 0.0);
    }

    public void playSound() {
        this.getLevel().playSound((Player)null, this.getEffectivePos(), BotaniaSounds.thermalily, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
    public void writeToPacketNBT(CompoundTag cmp) {
        super.writeToPacketNBT(cmp);
        cmp.putInt("burnTime", this.burnTime);
        cmp.putInt("cooldown", this.cooldown);
    }

    public void readFromPacketNBT(CompoundTag cmp) {
        super.readFromPacketNBT(cmp);
        this.burnTime = cmp.getInt("burnTime");
        this.cooldown = cmp.getInt("cooldown");
    }

    @Override
    public @Nullable RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(this.getEffectivePos(), 1);
    }
}
