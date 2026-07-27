package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class DynamoScreen extends GeneratorScreenTemplate<DynamoBlockEntity> {

    public DynamoScreen(PlayerInventory playerInventory, DynamoBlockEntity dynamoBlockEntity) {
        super(new DynamoScreenHandler(playerInventory, dynamoBlockEntity), playerInventory, dynamoBlockEntity);
        glassguis_setName(dynamoBlockEntity.getName());
    }

    @Override
    public void drawMachineForeground(float tickDelta, int mouseX, int mouseY) {
        drawGauge(mouseX, mouseY);
    }
}