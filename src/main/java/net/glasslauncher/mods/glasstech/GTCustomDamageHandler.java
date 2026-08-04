package net.glasslauncher.mods.glasstech;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public interface GTCustomDamageHandler {
    void onTakeDamage(Entity entity, ItemStack stack, int damage);
    boolean canAbsorbDamage(Entity entity, ItemStack stack);

    int getMaxArmorDamage(Entity entity, ItemStack stack);
    int getCurrentArmorDamage(Entity entity, ItemStack stack);
}
