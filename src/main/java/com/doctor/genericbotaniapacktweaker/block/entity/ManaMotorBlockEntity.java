package com.doctor.genericbotaniapacktweaker.block.entity;

import com.doctor.genericbotaniapacktweaker.block.ManaMotor;
import com.doctor.genericbotaniapacktweaker.flowers.SpineretteBlockEntity;
import com.doctor.genericbotaniapacktweaker.init.BlockEntityRegistry;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.GeneratingKineticTileEntity;
import com.simibubi.create.content.contraptions.components.motor.CreativeMotorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.block.Bound;

import static com.simibubi.create.content.contraptions.relays.gauge.GaugeBlock.Type.STRESS;

public class ManaMotorBlockEntity extends GeneratingKineticTileEntity {
    public ManaMotorBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.MANAMOTOR.get(), pos, state);
        setLazyTickRate(20);
    }
    //im certain there's a better way to do this but i don't know what it is
    private BlockPos[] boundFlowers = {Bound.UNBOUND_POS, Bound.UNBOUND_POS, Bound.UNBOUND_POS, Bound.UNBOUND_POS, Bound.UNBOUND_POS};
    private int numberBoundFlowers = 0;
    public static final int maxBound = 4;

    public boolean canAcceptFlower() {
        return numberBoundFlowers < 5;
    }
    public void initialize() {
        super.initialize();
        if (!this.hasSource() || this.getGeneratedSpeed() > this.getTheoreticalSpeed()) {
            this.updateGeneratedRotation();
        }

    }
    public void removeBoundFlower(BlockPos pos) {
        for(int i = 0; i < maxBound; i++) {
            if(boundFlowers[i].getX() == pos.getX() && boundFlowers[i].getY() == pos.getY() && boundFlowers[i].getZ() == pos.getZ()) {
                boundFlowers[i] = Bound.UNBOUND_POS;
                numberBoundFlowers = Math.max(0, this.numberBoundFlowers - 1);
                this.updateGeneratedRotation();
                this.updateSpeed = true;
                this.calculateAddedStressCapacity();

            }
        }
    }

    public void addBoundFlower(BlockPos pos) {
        boolean flowerAdded = false;
        for(int i = 0; i < maxBound; i++) {
            if(boundFlowers[i].getY() == Integer.MIN_VALUE && !flowerAdded) {
                boundFlowers[i] = pos;
                flowerAdded = true;
                numberBoundFlowers += 1;
                this.updateGeneratedRotation();
                this.updateSpeed = true;
                this.calculateAddedStressCapacity();
            }
        }
    }


    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        if(boundFlowers != null) {
            for (int i = 0; i < boundFlowers.length; i++) {
                BlockPos pos = boundFlowers[i];
                numberBoundFlowers = compound.getInt("BOUND_FLOWERS");
                boundFlowers[i] = new BlockPos(
                        compound.getInt("TAG_BIND_X" + i),
                        compound.getInt("TAG_BIND_Y" + i),
                        compound.getInt("TAG_BIND_Z" + i)
                );
            }
        }
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        if(boundFlowers != null) {
            for (int i = 0; i < boundFlowers.length; i++) {
                BlockPos pos = boundFlowers[i];
                compound.putInt("BOUND_FLOWERS", numberBoundFlowers);
                compound.putInt("TAG_BIND_X" + i, pos.getX());
                compound.putInt("TAG_BIND_Y" + i, pos.getY());
                compound.putInt("TAG_BIND_Z" + i, pos.getZ());
            }
        }
    }
    public float getRPM() {
        float rpm = 0.0f;
            for (BlockPos boundFlower : boundFlowers) {
                if (boundFlower != Bound.UNBOUND_POS && this.level.getBlockEntity(boundFlower) instanceof SpineretteBlockEntity) {
                    SpineretteBlockEntity spinerette = (SpineretteBlockEntity) this.level.getBlockEntity(boundFlower);
                    if (spinerette != null) {
                        rpm = Math.min(256, rpm + spinerette.getRpm());
                    } else {
                        rpm = 0.0f;
                    }
                }
            }
        return rpm;
    }

    @Override
    public float getGeneratedSpeed() {
        return convertToDirection((float)this.getRPM(), (Direction)this.getBlockState().getValue(ManaMotor.FACING));
    }
    @Override
    public float calculateAddedStressCapacity() {
        float capacity = Math.round((float) 48 / (numberBoundFlowers * 4 ));
        this.lastCapacityProvided = capacity;
        return capacity;
    }
    public int getGeneratedStress() {
        return (int) calculateAddedStressCapacity();
    }



    @Override
    public void destroy() {
            for (BlockPos boundFlower : boundFlowers) {
                if(boundFlower != Bound.UNBOUND_POS && this.level.getBlockEntity(boundFlower) instanceof SpineretteBlockEntity) {
                    SpineretteBlockEntity blockEntity = (SpineretteBlockEntity) level.getBlockEntity(boundFlower);
                    if (blockEntity != null) {
                        blockEntity.setBindPos(Bound.UNBOUND_POS);
                        blockEntity.sync();
                   }
                }
            }
        super.destroy();
        }
}
