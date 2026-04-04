package net.glasslauncher.mods.glasstech.blocks.batbox;

import net.glasslauncher.mods.glasstech.blocks.machine.EnergySourceConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatteryChargeSlot;
import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;

public class BatBoxScreenHandler extends EnergySourceConsumerScreenHandlerTemplate<BatBoxBlockEntity> {
    public BatBoxScreenHandler(PlayerInventory playerInventory, BatBoxBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        addSlot(new BatteryChargeSlot(blockEntity, 0, 80, 26));
        addSlot(new BatterySlot(blockEntity, 1, 80, 54));
    }
}
