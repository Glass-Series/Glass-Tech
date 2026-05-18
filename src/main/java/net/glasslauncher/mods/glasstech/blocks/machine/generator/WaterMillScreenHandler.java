package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatteryChargeSlot;
import net.glasslauncher.mods.glasstech.gui.FuelSlot;
import net.minecraft.entity.player.PlayerInventory;

class WaterMillScreenHandler extends GeneratorScreenHandlerTemplate<WaterMillBlockEntity> {

    public WaterMillScreenHandler(PlayerInventory playerInventory, WaterMillBlockEntity waterMillBlockEntity) {
        super(playerInventory, waterMillBlockEntity, true);
        addSlot(new BatteryChargeSlot(waterMillBlockEntity, 1, 80, 22));
    }
}