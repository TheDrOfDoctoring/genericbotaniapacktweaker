package com.doctor.genericbotaniapacktweaker.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import vazkii.botania.api.item.SortableTool;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.CustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.function.Consumer;
//THIS IS ALL ESSENTIALLY JUST MANASTEEL TOOLS
public class ManaelectrumShovelItem extends ShovelItem implements CustomDamageItem, SortableTool {

    private static final int MANA_PER_DAMAGE = 75;

    public ManaelectrumShovelItem(Tier tier, float thing1, float thing2, Item.Properties properties) {
        super(tier, thing1, thing2, properties);
    }


    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        int manaPer = ((ManaelectrumShovelItem) stack.getItem()).getManaPerDamage();
        return ToolCommons.damageItemIfPossible(stack, amount, entity, manaPer);
    }

    public int getManaPerDamage() {
        return MANA_PER_DAMAGE;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!world.isClientSide && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true)) {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }
}
