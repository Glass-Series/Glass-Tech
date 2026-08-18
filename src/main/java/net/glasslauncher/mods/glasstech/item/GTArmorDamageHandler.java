package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.entity.DamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface GTArmorDamageHandler {
    boolean shouldDamage(LivingEntity entity, ItemStack armor, int damage, DamageSource source);
}
