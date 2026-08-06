package net.glasslauncher.mods.glasstech.blocks.machine.massfab;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class MassFabricatorScreen extends MachineScreenTemplate<MassFabricatorBlockEntity> {

    public MassFabricatorScreen(PlayerInventory playerInventory, MassFabricatorBlockEntity blockEntity) {
        super(new MassFabricatorScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta, int mouseX, int mouseY) {
        drawGauge(mouseX, mouseY);
        drawArrow(79, 34, blockEntity.progress / (float) blockEntity.getMaxProgress());
    }
}
