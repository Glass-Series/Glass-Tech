package net.glasslauncher.mods.glasstech;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public interface GTCustomAttackDamage {
    int glasstech$getAttackDamage(ItemStack itemStack, Entity attacked);
    default void dealtDamage(ItemStack stack, Entity attacked, Entity attacker) {}
}
