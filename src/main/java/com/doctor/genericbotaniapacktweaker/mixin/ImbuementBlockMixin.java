package com.doctor.genericbotaniapacktweaker.mixin;

import com.hollingsworth.arsnouveau.common.block.ArcaneCore;
import com.hollingsworth.arsnouveau.common.block.ImbuementBlock;
import com.hollingsworth.arsnouveau.common.block.TickableModBlock;
import com.hollingsworth.arsnouveau.common.block.tile.ImbuementTile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ImbuementBlock.class)
public abstract class ImbuementBlockMixin extends TickableModBlock {
    //Provide a comparator signal whilst a recipe is in progres for automation purposes (such as locking a hopper)
    public boolean hasAnalogOutputSignal(BlockState p_49618_) {
        return true;
    }
    public int getAnalogOutputSignal(BlockState p_49620_, Level level, BlockPos pos) {
        ImbuementTile tile = (ImbuementTile) level.getBlockEntity(pos);
        return tile.getRecipeNow() != null ? 15 : 0;
    }


}
