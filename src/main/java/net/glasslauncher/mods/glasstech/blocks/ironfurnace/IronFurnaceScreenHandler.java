package net.glasslauncher.mods.glasstech.blocks.ironfurnace;

import net.glasslauncher.mods.glasstech.gui.BigOutputSlot;
import net.glasslauncher.mods.glasstech.gui.FuelSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class IronFurnaceScreenHandler extends ScreenHandler {

    private final IronFurnaceBlockEntity ironFurnaceBlockEntity;

    public IronFurnaceScreenHandler(PlayerInventory playerInventory, IronFurnaceBlockEntity ironFurnaceBlockEntity) {
        super();
        this.ironFurnaceBlockEntity = ironFurnaceBlockEntity;
        glassguis_setupPlayerInventory(8, 167, playerInventory);
        addSlot(new Slot(ironFurnaceBlockEntity, 0, 56, 17));
        addSlot(new FuelSlot(ironFurnaceBlockEntity, 1, 56, 53));
        addSlot(new BigOutputSlot(playerInventory.player, ironFurnaceBlockEntity, 2, 116, 35));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return ironFurnaceBlockEntity.canPlayerUse(player);
    }
}
