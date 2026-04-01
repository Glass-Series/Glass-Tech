package net.glasslauncher.mods.glasstech.blocks.machine.furnace;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;

class FurnaceScreenHandler extends ConsumerScreenHandlerTemplate<FurnaceBlockEntity> {

    public FurnaceScreenHandler(PlayerInventory playerInventory, FurnaceBlockEntity furnaceBlockEntity) {
        super(playerInventory, furnaceBlockEntity, true);
        addSlot(new Slot(furnaceBlockEntity, 0, 10, 40));
        addSlot(new BatterySlot(furnaceBlockEntity, 1, 32, 40));
    }
}