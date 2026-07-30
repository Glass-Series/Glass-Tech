package net.glasslauncher.mods.glasstech.blocks.machine.miner;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class MinerScreen extends MachineScreenTemplate<MinerBlockEntity> {

    public MinerScreen(PlayerInventory playerInventory, MinerBlockEntity blockEntity) {
        super(new MinerScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta, int mouseX, int mouseY) {
        drawGauge(mouseX, mouseY);
        drawArrow(79, 34, blockEntity.progress / (float) blockEntity.getMaxProgress());
    }
}
