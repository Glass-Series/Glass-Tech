package net.glasslauncher.mods.glasstech.blocks.machine.canner;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class CannerScreen extends MachineScreenTemplate<CannerBlockEntity> {

    public CannerScreen(PlayerInventory playerInventory, CannerBlockEntity blockEntity) {
        super(new CannerScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta) {
        drawGauge();
        drawArrow(79, 34, blockEntity.progress / (float) (blockEntity.currentRecipe == null ? blockEntity.maxProgress : blockEntity.currentRecipe.time));
    }
}
