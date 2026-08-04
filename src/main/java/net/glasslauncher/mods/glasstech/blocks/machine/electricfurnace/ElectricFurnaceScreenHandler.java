package net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.minecraft.entity.player.PlayerInventory;

class ElectricFurnaceScreenHandler extends ConsumerScreenHandlerTemplate<ElectricFurnaceBlockEntity> {

    public ElectricFurnaceScreenHandler(PlayerInventory playerInventory, ElectricFurnaceBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        SlotLayout.createBasic(this, blockEntity);
    }
}