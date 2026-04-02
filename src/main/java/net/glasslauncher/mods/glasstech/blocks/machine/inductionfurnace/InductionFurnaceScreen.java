package net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class InductionFurnaceScreen extends MachineScreenTemplate<InductionFurnaceBlockEntity> {

    public InductionFurnaceScreen(PlayerInventory playerInventory, InductionFurnaceBlockEntity inductionFurnaceBlockEntity) {
        super(new InductionFurnaceScreenHandler(playerInventory, inductionFurnaceBlockEntity), playerInventory, inductionFurnaceBlockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta) {
        drawGauge();
        drawArrow(80, 40, blockEntity.progress / (float) blockEntity.getMaxProgress());
    }
}