package net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class ElectricFurnaceScreen extends MachineScreenTemplate<ElectricFurnaceBlockEntity> {

    public ElectricFurnaceScreen(PlayerInventory playerInventory, ElectricFurnaceBlockEntity electricFurnaceBlockEntity) {
        super(new ElectricFurnaceScreenHandler(playerInventory, electricFurnaceBlockEntity), playerInventory, electricFurnaceBlockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta) {
        drawGauge();
        drawArrow(80, 40, blockEntity.progress / (float) blockEntity.getMaxProgress());
    }
}