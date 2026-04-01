package net.glasslauncher.mods.glasstech.blocks.machine.macerator;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class MaceratorScreen extends MachineScreenTemplate<MaceratorBlockEntity> {

    public MaceratorScreen(PlayerInventory playerInventory, MaceratorBlockEntity blockEntity) {
        super(new MaceratorScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta) {
        drawGauge();
        drawArrow(79, 34, blockEntity.progress / (float) (blockEntity.currentRecipe == null ? blockEntity.maxProgress : blockEntity.currentRecipe.time));
    }
}
