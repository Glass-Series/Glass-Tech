package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatteryChargeSlot;
import net.minecraft.entity.player.PlayerInventory;

class SolarGeneratorScreenHandler extends GeneratorScreenHandlerTemplate<SolarGeneratorBlockEntity> {

    public SolarGeneratorScreenHandler(PlayerInventory playerInventory, SolarGeneratorBlockEntity solarGeneratorBlockEntity) {
        super(playerInventory, solarGeneratorBlockEntity, true);
        addSlot(new BatteryChargeSlot(solarGeneratorBlockEntity, 0, 80, 22));
    }
}