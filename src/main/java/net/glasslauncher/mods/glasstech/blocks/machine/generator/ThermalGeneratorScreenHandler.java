package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatteryChargeSlot;
import net.glasslauncher.mods.glasstech.gui.GeothermalBucketSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.FurnaceOutputSlot;

class ThermalGeneratorScreenHandler extends GeneratorScreenHandlerTemplate<ThermalGeneratorBlockEntity> {

    public ThermalGeneratorScreenHandler(PlayerInventory playerInventory, ThermalGeneratorBlockEntity thermalGeneratorBlockEntity) {
        super(playerInventory, thermalGeneratorBlockEntity, true);
        addSlot(new GeothermalBucketSlot(thermalGeneratorBlockEntity, 0, 80, 58));
        addSlot(new FurnaceOutputSlot(playerInventory.player, thermalGeneratorBlockEntity, 1, 102, 58));
        addSlot(new BatteryChargeSlot(thermalGeneratorBlockEntity, 2, 80, 22));
    }
}