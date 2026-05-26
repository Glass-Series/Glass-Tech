package net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class ElectrolyzerScreen extends MachineScreenTemplate<ElectrolyzerBlockEntity> {

    public ElectrolyzerScreen(PlayerInventory playerInventory, ElectrolyzerBlockEntity blockEntity) {
        super(new ElectrolyzerScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta, int mouseX, int mouseY) {
        drawGauge(mouseX, mouseY);
        drawArrow(79, 34, blockEntity.progress / (float) blockEntity.getCurrentShortestTime());
    }
}
