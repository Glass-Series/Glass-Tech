package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.gui.BatteryChargeSlot;
import net.minecraft.entity.player.PlayerInventory;

class DynamoScreenHandler extends GeneratorScreenHandlerTemplate<DynamoBlockEntity> {

    public DynamoScreenHandler(PlayerInventory playerInventory, DynamoBlockEntity dynamoBlockEntity) {
        super(playerInventory, dynamoBlockEntity, true);
        addSlot(new BatteryChargeSlot(dynamoBlockEntity, 0, 80, 22));
    }
}