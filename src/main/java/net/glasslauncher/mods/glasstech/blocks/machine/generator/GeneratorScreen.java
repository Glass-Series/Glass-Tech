package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class GeneratorScreen extends GeneratorScreenTemplate<GeneratorBlockEntity> {

    public GeneratorScreen(PlayerInventory playerInventory, GeneratorBlockEntity generatorBlockEntity) {
        super(new GeneratorScreenHandler(playerInventory, generatorBlockEntity), playerInventory, generatorBlockEntity);
        glassguis_setName(generatorBlockEntity.getName());
    }

    @Override
    public void drawMachineForeground(float tickDelta, int mouseX, int mouseY) {
        drawGauge(mouseX, mouseY);
        drawFlame(80, 40, blockEntity.getInitialFuelTicks() <= 0 ? 0 : blockEntity.getFuelTicks() / (float) blockEntity.getInitialFuelTicks());
    }
}