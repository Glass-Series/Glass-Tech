package net.glasslauncher.mods.glasstech.blocks.batbox.mesu;

import net.glasslauncher.mods.glasstech.blocks.machine.EnergySourceConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatteryChargeSlot;
import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.minecraft.entity.player.PlayerInventory;

public class MESUScreenHandler extends EnergySourceConsumerScreenHandlerTemplate<MESUBlockEntity> {
    public MESUScreenHandler(PlayerInventory playerInventory, MESUBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        addSlot(new BatteryChargeSlot(blockEntity, 0, 80, 26));
        addSlot(new BatterySlot(blockEntity, 1, 80, 54));
    }
}
