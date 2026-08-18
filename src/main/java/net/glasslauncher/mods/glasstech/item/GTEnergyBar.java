package net.glasslauncher.mods.glasstech.item;

import net.minecraft.item.ItemStack;

import java.awt.*;

public interface GTEnergyBar {
    default int getColor(int colorOffset) {
        return new Color(255 - Math.max((colorOffset / 2) - 130, 100), 255 - colorOffset, 255 - (colorOffset / 2)).getRGB();
    }
    int getEnergyStored(ItemStack stack);
    int getEnergyCapacity(ItemStack stack);

    default boolean addTooltip() {return true;}
}
