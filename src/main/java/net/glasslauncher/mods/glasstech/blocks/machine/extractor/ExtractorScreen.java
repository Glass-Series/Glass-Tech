package net.glasslauncher.mods.glasstech.blocks.machine.extractor;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class ExtractorScreen extends MachineScreenTemplate<ExtractorBlockEntity> {

    public ExtractorScreen(PlayerInventory playerInventory, ExtractorBlockEntity blockEntity) {
        super(new ExtractorScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta) {
        drawGauge();
        drawArrow(79, 34, blockEntity.progress / (float) (blockEntity.currentRecipe == null ? blockEntity.maxProgress : blockEntity.currentRecipe.time));
    }
}
