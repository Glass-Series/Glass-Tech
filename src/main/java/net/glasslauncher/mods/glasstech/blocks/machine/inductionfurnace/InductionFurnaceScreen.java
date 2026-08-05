package net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

import java.awt.*;

public class InductionFurnaceScreen extends MachineScreenTemplate<InductionFurnaceBlockEntity> {

    public InductionFurnaceScreen(PlayerInventory playerInventory, InductionFurnaceBlockEntity inductionFurnaceBlockEntity) {
        super(new InductionFurnaceScreenHandler(playerInventory, inductionFurnaceBlockEntity), playerInventory, inductionFurnaceBlockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta, int mouseX, int mouseY) {
        drawGauge(mouseX, mouseY);
        drawArrow(80, 40, blockEntity.progress / (float) blockEntity.getMaxProgress());
        // TODO: Eventually replace this with a nice graphic
        float heatPercent = Math.round((blockEntity.getHeat() / (float) blockEntity.getMaxHeat()) * 1000f) / 10f;
        glassguis_drawText(this, "Heat: " + heatPercent + "%", 30, 40, new Color(1f - ((heatPercent / 100) * 0.2f), (100 - heatPercent) / 100,(100 - heatPercent) / 100).getRGB());
    }
}