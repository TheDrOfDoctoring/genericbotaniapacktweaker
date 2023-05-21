package com.doctor.genericbotaniapacktweaker.flowers;

import com.doctor.genericbotaniapacktweaker.block.entity.ManaMotorBlockEntity;
import com.doctor.genericbotaniapacktweaker.init.BlockEntityRegistry;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.block.Bound;
import vazkii.botania.api.block_entity.FunctionalFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;
import vazkii.botania.common.block.BotaniaBlock;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.proxy.Proxy;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class SpineretteBlockEntity extends FunctionalFlowerBlockEntity {
    private static final String TAG_BIND_X = "bindX";
    private static final String TAG_BIND_Y = "bindY";
    private static final String TAG_BIND_Z = "bindZ";
    private static final int BIND_RANGE = 6;
    private BlockPos bindPos = Bound.UNBOUND_POS;
    private int rpm;
    public SpineretteBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.SPINERETTE.get(), pos, state);
    }
    @Override
    public int getMaxMana() {
        return 15000;
    }
    @Override
    public int getColor() {
        return 14203392;
    }
    @Override
    public RadiusDescriptor getRadius() {
        return null;
    }

    @Override
    public void tickFlower() {
        super.tickFlower();
        if(this.ticksExisted % 4 == 0 && !this.level.isClientSide && this.getMana() > 0 ) {
            Block soil = level.getBlockState(this.getBlockPos().below()).getBlock();
            if(this.bindPos != Bound.UNBOUND_POS) {
                this.addMana(-4);
            }
            if(this.ticksExisted % 20 == 0 && this.bindPos != Bound.UNBOUND_POS) {
                ManaMotorBlockEntity mm = (ManaMotorBlockEntity) level.getBlockEntity(bindPos);
                mm.updateSpeed = true;
            }
            if(soil == Blocks.ROOTED_DIRT) {
                this.rpm = 16;
            } else if(soil == ModBlocks.RICH_SOIL.get()) {
                this.rpm = 32;
            } else if(soil == BlockRegistry.imbuedDirt.get()) {
                this.rpm = 64;
            } else if (soil == BotaniaBlocks.enchantedSoil) {
                this.rpm = 128;
            } else {
                this.rpm = 8;
            }

        }
    }
    public SpineretteBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public void setBindPos(BlockPos bindPos) {
        this.bindPos = bindPos;
    }

    public BlockPos getBindPos() {
        return bindPos;
    }

    @Override
    public RadiusDescriptor getSecondaryRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), BIND_RANGE);
    }
    @Override
    public void writeToPacketNBT(CompoundTag cmp) {
        super.writeToPacketNBT(cmp);
        cmp.putInt("RPM", this.rpm);
        cmp.putInt(TAG_BIND_X, bindPos.getX());
        cmp.putInt(TAG_BIND_Y, bindPos.getY());
        cmp.putInt(TAG_BIND_Z, bindPos.getZ());
    }
    @Override
    public void readFromPacketNBT(CompoundTag cmp) {
        super.readFromPacketNBT(cmp);
        this.rpm = cmp.getInt("RPM");
        bindPos = new BlockPos(
                cmp.getInt(TAG_BIND_X),
                cmp.getInt(TAG_BIND_Y),
                cmp.getInt(TAG_BIND_Z)
        );
    }


    @Override
    public boolean bindTo(Player player, ItemStack wand, BlockPos pos, Direction side) {
        boolean bound = super.bindTo(player, wand, pos, side);

        if (!bound && !pos.equals(bindPos) && pos.distSqr(getEffectivePos()) <= BIND_RANGE && !pos.equals(getEffectivePos()) && level.getBlockState(pos).getBlock() == BlockRegistry.manaMotor.get() && level.getBlockEntity(pos) instanceof ManaMotorBlockEntity manaMotor) {
            if(manaMotor.canAcceptFlower()) {
                if(level.getBlockEntity(bindPos) instanceof ManaMotorBlockEntity mm && bindPos != pos) {
                    mm.removeBoundFlower(this.worldPosition);
                }
                bindPos = pos;
                sync();
                manaMotor.addBoundFlower(this.worldPosition);
                return true;
            } else {
                return false;
            }
        }
        return bound;
    }


    @Override
    public BlockPos getBinding() {
        return Proxy.INSTANCE.getClientPlayer().isShiftKeyDown() && bindPos.getY() != Integer.MIN_VALUE ? bindPos : super.getBinding();
    }

    public int getRpm() {
        return this.rpm;
    }

}
