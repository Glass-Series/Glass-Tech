package net.glasslauncher.mods.glasstech.blocks.machine.miner;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;

class MinerScreenHandler extends ConsumerScreenHandlerTemplate<MinerBlockEntity> {
    public MinerScreenHandler(PlayerInventory playerInventory, MinerBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        addSlot(new Slot(blockEntity, 0, 56, 17));
        addSlot(new BatterySlot(blockEntity, 1, 56, 53));
    }
}
