package net.glasslauncher.mods.glasstech.blocks.machine.canner;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.glasslauncher.mods.glasstech.gui.BigOutputSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;

class CannerScreenHandler extends ConsumerScreenHandlerTemplate<CannerBlockEntity> {
    public CannerScreenHandler(PlayerInventory playerInventory, CannerBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        addSlot(new Slot(blockEntity, 0, 56, 17));
        addSlot(new Slot(blockEntity, 1, 56, 53));
        addSlot(new BigOutputSlot(blockEntity,2, 116, 35));
        addSlot(new BatterySlot(blockEntity, 3, 30, 53));
    }
}
