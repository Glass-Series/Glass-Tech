package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glassguis.DrawDirection;
import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class WaterMillScreen extends GeneratorScreenTemplate<WaterMillBlockEntity> {

    public WaterMillScreen(PlayerInventory playerInventory, WaterMillBlockEntity waterMillBlockEntity) {
        super(new WaterMillScreenHandler(playerInventory, waterMillBlockEntity), playerInventory, waterMillBlockEntity);
        glassguis_setName(waterMillBlockEntity.getName());
    }

    @Override
    public void drawMachineForeground(float tickDelta) {
        drawGauge();

//        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/flame_bg.png", 81, 41);
//        glassguis_drawImagePercentage(this, "/assets/glasstech/stationapi/textures/gui/flame_fg.png", 80, 40, blockEntity.getInitialFuelTicks() <= 0 ? 0 : blockEntity.getFuelTicks() / (float) blockEntity.getInitialFuelTicks(), DrawDirection.UP);
    }
}