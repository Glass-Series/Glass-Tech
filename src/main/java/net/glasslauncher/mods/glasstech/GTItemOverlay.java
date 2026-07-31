package net.glasslauncher.mods.glasstech;

import net.minecraft.item.ItemStack;

public interface GTItemOverlay {
    int getEnergyStored(ItemStack stack);
    int getEnergyCapacity(ItemStack stack);
}
