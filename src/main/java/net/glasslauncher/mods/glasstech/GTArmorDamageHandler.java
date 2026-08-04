package net.glasslauncher.mods.glasstech;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface GTArmorDamageHandler {
    boolean shouldDamage(LivingEntity entity, ItemStack armor, int damage, DamageSource source);
}
