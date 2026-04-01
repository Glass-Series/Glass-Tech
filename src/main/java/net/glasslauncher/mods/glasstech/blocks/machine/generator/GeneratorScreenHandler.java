package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;

class GeneratorScreenHandler extends GeneratorScreenHandlerTemplate<GeneratorBlockEntity> {

    public GeneratorScreenHandler(PlayerInventory playerInventory, GeneratorBlockEntity generatorBlockEntity) {
        super(playerInventory, generatorBlockEntity, true);
        addSlot(new Slot(generatorBlockEntity, 0, 10, 40));
        addSlot(new BatterySlot(generatorBlockEntity, 1, 32, 40));
    }
}