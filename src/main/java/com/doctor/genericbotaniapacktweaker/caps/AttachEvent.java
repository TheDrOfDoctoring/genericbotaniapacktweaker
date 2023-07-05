package com.doctor.genericbotaniapacktweaker.caps;

import appeng.api.config.Actionable;
import appeng.blockentity.networking.EnergyCellBlockEntity;
import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.flowers.DieselotusBlockEntity;
import com.doctor.genericbotaniapacktweaker.flowers.SpineretteBlockEntity;
import com.hollingsworth.arsnouveau.common.block.tile.SourceJarTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.botania.api.BotaniaForgeCapabilities;
import vazkii.botania.api.BotaniaForgeClientCapabilities;
import vazkii.botania.api.block_entity.BindableSpecialFlowerBlockEntity;
import vazkii.botania.api.block_entity.FunctionalFlowerBlockEntity;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.mana.ManaCollector;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.api.mana.ManaReceiver;
import vazkii.botania.forge.CapabilityUtil;
import java.util.EnumMap;
import java.util.Map;

@Mod.EventBusSubscriber

public class AttachEvent {
    @SubscribeEvent
    public static void attachBlockEntityCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {
        if (event.getObject() instanceof SourceJarTile be) {
            Capability<ManaReceiver> cap = BotaniaForgeCapabilities.MANA_RECEIVER;
            var manaReceiver = new ManaReceiver() {
                @Override
                public Level getManaReceiverLevel() {
                    return be.getLevel();
                }

                @Override
                public BlockPos getManaReceiverPos() {
                    return be.getBlockPos();
                }

                @Override
                public int getCurrentMana() {
                    return be.getSource() * 10;
                }

                @Override
                public boolean isFull() {
                    return be.getSource() >= be.getMaxSource();
                }

                @Override
                public void receiveMana(int i) {
                    be.addSource(i / 10);
                }

                @Override
                public boolean canReceiveManaFromBursts() {
                    return !isFull();
                }
            };
            event.addCapability(new ResourceLocation(GenericBotaniaPackTweaker.MODID, "mana_receiver"), CapabilityUtil.makeProvider(cap, manaReceiver));
        }
        if (event.getObject() instanceof EnergyCellBlockEntity be) {
            Capability<ManaReceiver> cap = BotaniaForgeCapabilities.MANA_RECEIVER;
            var manaReceiver = new ManaReceiver() {
                @Override
                public Level getManaReceiverLevel() {
                    return be.getLevel();
                }

                @Override
                public BlockPos getManaReceiverPos() {
                    return be.getBlockPos();
                }

                @Override
                public int getCurrentMana() {
                    return (int) Math.ceil(be.getAECurrentPower() / 5);
                }

                @Override
                public boolean isFull() {
                    return be.getAECurrentPower() >= be.getAEMaxPower();
                }

                @Override
                public void receiveMana(int i) {

                    if (be.getActionableNode() != null) {
                        be.injectAEPower(i * 5, Actionable.MODULATE);
                    }

                }

                @Override
                public boolean canReceiveManaFromBursts() {
                    return !isFull();
                }
            };
            event.addCapability(new ResourceLocation(GenericBotaniaPackTweaker.MODID, "energy_cell_mana_receiver"), CapabilityUtil.makeProvider(cap, manaReceiver));
        }
        if (event.getObject() instanceof DieselotusBlockEntity dieselotusBlockEntity) {
            event.addCapability(new ResourceLocation(GenericBotaniaPackTweaker.MODID, "wand_hud"),
                    CapabilityUtil.makeProvider(BotaniaForgeClientCapabilities.WAND_HUD, new BindableSpecialFlowerBlockEntity.BindableFlowerWandHud<>(dieselotusBlockEntity)));
        } else if (event.getObject() instanceof SpineretteBlockEntity spineretteBlockEntity) {
            event.addCapability(new ResourceLocation(GenericBotaniaPackTweaker.MODID, "wand_hud"),
                    CapabilityUtil.makeProvider(BotaniaForgeClientCapabilities.WAND_HUD, new BindableSpecialFlowerBlockEntity.BindableFlowerWandHud<>(spineretteBlockEntity)));
        }
    }

}
