package net.glasslauncher.mods.glasstech.blocks.machine.compressor;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class CompressorScreen extends MachineScreenTemplate<CompressorBlockEntity> {

    public CompressorScreen(PlayerInventory playerInventory, CompressorBlockEntity blockEntity) {
        super(new CompressorScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta) {
        drawGauge();
        drawArrow(79, 34, blockEntity.progress / (float) (blockEntity.currentRecipe == null ? blockEntity.maxProgress : blockEntity.currentRecipe.time));
    }
}
