package com.doctor.genericbotaniapacktweaker.mixin;

import com.doctor.genericbotaniapacktweaker.util.StackHelper;
import de.markusbordihn.easymobfarm.block.ModBlocks;
import de.markusbordihn.easymobfarm.block.entity.MobFarmBlockEntity;
import de.markusbordihn.easymobfarm.block.entity.MobFarmBlockEntityData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import java.util.ArrayList;
import java.util.List;

@Mixin(MobFarmBlockEntity.class)
public abstract class MobFarmBlockEntityMixin extends MobFarmBlockEntityData  {
    public MobFarmBlockEntityMixin(BlockEntityType<?> blockEntity, BlockPos blockPos, BlockState blockState) {
        super(blockEntity, blockPos, blockState);
    }
    //This is a bit flawed, since if no drops happen at all there's nothing to multiply by, which is honestly fairly common.
    @ModifyVariable(method = "processResult", at = @At(value = "STORE"), ordinal = 0, remap = false)
    private List<ItemStack> itemStacks(List<ItemStack> originalStacks ) {
        List<ItemStack> modified = new ArrayList<ItemStack>();
        if(ModBlocks.MONSTER_PLAINS_CAVE_FARM.get() == this.getBlockState().getBlock()) {
            modified = StackHelper.multiplyStack(originalStacks, 3);
        } else if(ModBlocks.SWAMP_FARM.get() == this.getBlockState().getBlock()) {
            modified = StackHelper.multiplyStack(originalStacks,5);
        } else if(ModBlocks.CREATIVE_MOB_FARM.get() == this.getBlockState().getBlock()) {
            modified = StackHelper.multiplyStack(originalStacks,10);
        }
        return modified;
    }
}
