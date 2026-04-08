package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatteryChargeSlot;
import net.glasslauncher.mods.glasstech.gui.FuelSlot;
import net.minecraft.entity.player.PlayerInventory;

class GeneratorScreenHandler extends GeneratorScreenHandlerTemplate<GeneratorBlockEntity> {

    public GeneratorScreenHandler(PlayerInventory playerInventory, GeneratorBlockEntity generatorBlockEntity) {
        super(playerInventory, generatorBlockEntity, true);
        addSlot(new FuelSlot(generatorBlockEntity, 0, 80, 58));
        addSlot(new BatteryChargeSlot(generatorBlockEntity, 1, 80, 22));
    }
}