package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatteryChargeSlot;
import net.glasslauncher.mods.glasstech.gui.GeothermalBucketSlot;
import net.glasslauncher.mods.glasstech.gui.OutputSlot;
import net.minecraft.entity.player.PlayerInventory;

class ThermalGeneratorScreenHandler extends GeneratorScreenHandlerTemplate<ThermalGeneratorBlockEntity> {

    public ThermalGeneratorScreenHandler(PlayerInventory playerInventory, ThermalGeneratorBlockEntity thermalGeneratorBlockEntity) {
        super(playerInventory, thermalGeneratorBlockEntity, true);
        addSlot(new GeothermalBucketSlot(thermalGeneratorBlockEntity, 0, 80, 58));
        addSlot(new OutputSlot(thermalGeneratorBlockEntity, 1, 102, 58));
        addSlot(new BatteryChargeSlot(thermalGeneratorBlockEntity, 2, 80, 22));
    }

    @Override
    protected void initSlots() {
        addSlot(new GeothermalBucketSlot(blockEntity, 0, 80, 58));
        addSlot(new OutputSlot(blockEntity, 1, 102, 58));
        addSlot(new BatteryChargeSlot(blockEntity, 2, 80, 22));
    }
}