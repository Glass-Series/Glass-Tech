package net.glasslauncher.mods.glasstech.blocks.machine.recycler;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class RecyclerScreen extends MachineScreenTemplate<RecyclerBlockEntity> {

    public RecyclerScreen(PlayerInventory playerInventory, RecyclerBlockEntity blockEntity) {
        super(new RecyclerScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta, int mouseX, int mouseY) {
        drawGauge(mouseX, mouseY);
        drawArrow(79, 34, blockEntity.progress / (float) blockEntity.getCurrentShortestTime());
    }
}
