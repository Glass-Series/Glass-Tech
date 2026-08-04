package net.glasslauncher.mods.glasstech.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface GTTickingArmor {
    void armorTick(ItemStack stack, World world, Entity entity, int slot);
}
