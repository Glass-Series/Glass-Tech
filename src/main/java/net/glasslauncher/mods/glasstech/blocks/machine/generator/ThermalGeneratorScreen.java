package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glassguis.DrawDirection;
import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class ThermalGeneratorScreen extends GeneratorScreenTemplate<ThermalGeneratorBlockEntity> {

    public ThermalGeneratorScreen(PlayerInventory playerInventory, ThermalGeneratorBlockEntity thermalGeneratorBlockEntity) {
        super(new ThermalGeneratorScreenHandler(playerInventory, thermalGeneratorBlockEntity), playerInventory, thermalGeneratorBlockEntity);
        glassguis_setName(thermalGeneratorBlockEntity.getName());
    }

    @Override
    public void drawMachineForeground(float tickDelta, int mouseX, int mouseY) {
        drawGauge(mouseX, mouseY);
        drawFluidGauge(mouseX, mouseY);
        drawFlame(80, 40, blockEntity.getInitialFuelTicks() <= 0 ? 0 : blockEntity.getFuelTicks() / (float) blockEntity.getInitialFuelTicks());
    }


}