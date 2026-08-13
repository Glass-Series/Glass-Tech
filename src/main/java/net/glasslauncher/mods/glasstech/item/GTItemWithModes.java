package net.glasslauncher.mods.glasstech.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface GTItemWithModes {
    void cycleMode(PlayerEntity player, ItemStack stack);
}
