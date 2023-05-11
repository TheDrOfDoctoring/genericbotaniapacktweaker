package com.doctor.genericbotaniapacktweaker.util;


import net.minecraft.world.item.ItemStack;


import java.util.ArrayList;
import java.util.List;

public class StackHelper {
//idk if this is good but i like it :)
    public static List<ItemStack> multiplyStack(List<ItemStack> stacks, int multiplier) {
        List<ItemStack> modified = new ArrayList<ItemStack>();
        for (ItemStack stack : stacks) {
            if (stack.isStackable()) {
                stack.setCount(Math.min(stack.getCount() * multiplier, 64));
                modified.add(stack);
            } else {
                modified.add(stack);
            }
        }
        return modified;
    }

}
 