package net.glasslauncher.mods.glasstech.blocks.machine.pump;

import net.glasslauncher.mods.glassguis.DrawDirection;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;
import net.modificationstation.stationapi.api.client.render.model.BakedModel;

public class PumpScreen extends MachineScreenTemplate<PumpBlockEntity> {

    public PumpScreen(PlayerInventory playerInventory, PumpBlockEntity blockEntity) {
        super(new PumpScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
    }

    @Override
    public void drawMachineForeground(float tickDelta, int mouseX, int mouseY) {
        drawGauge(mouseX, mouseY);
        drawFluidGauge(mouseX, mouseY);
        float progress = blockEntity.progress / (float) blockEntity.getMaxProgress();
        int x = 79;
        int y = 34;
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/pump_bg.png", x + 1, y + 1);
        glassguis_drawImagePercentage(this, "/assets/glasstech/stationapi/textures/block/pump_on.png", x, y, progress, DrawDirection.UP);
    }
}
