package net.glasslauncher.mods.glasstech.gui;

import net.glasslauncher.mods.glassguis.screen.widget.slot.GlassSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.FurnaceOutputSlot;

public class BigOutputSlot extends FurnaceOutputSlot implements GlassSlot {
    public BigOutputSlot(PlayerEntity player, Inventory inventory, int index, int x, int y) {
        super(player, inventory, index, x, y);
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
