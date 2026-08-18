package net.glasslauncher.mods.glasstech.gui;

import net.glasslauncher.mods.glassguis.screen.slot.GlassSlot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class BigOutputSlot extends Slot implements GlassSlot {
    public BigOutputSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public int getBackgroundWidth() {
        return 24;
    }

    @Override
    public int getBackgroundHeight() {
        return 24;
    }
}
