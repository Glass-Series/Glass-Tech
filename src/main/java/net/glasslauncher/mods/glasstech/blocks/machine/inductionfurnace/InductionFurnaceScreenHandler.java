package net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;

class InductionFurnaceScreenHandler extends ConsumerScreenHandlerTemplate<InductionFurnaceBlockEntity> {

    public InductionFurnaceScreenHandler(PlayerInventory playerInventory, InductionFurnaceBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        addSlot(new Slot(blockEntity, 0, 47, 17));
        addSlot(new Slot(blockEntity, 1, 65, 17));
        addSlot(new Slot(blockEntity,2, 116, 35));
        addSlot(new Slot(blockEntity,3, 134, 35));
        addSlot(new BatterySlot(blockEntity, 4, 56, 53));
    }
}