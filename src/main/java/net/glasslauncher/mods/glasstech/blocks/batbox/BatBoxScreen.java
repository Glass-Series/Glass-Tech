package net.glasslauncher.mods.glasstech.blocks.batbox;

import net.glasslauncher.mods.glasstech.blocks.machine.EnergySourceConsumerScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class BatBoxScreen extends EnergySourceConsumerScreenTemplate<BatBoxBlockEntity> {

    public BatBoxScreen(PlayerInventory playerInventory, BatBoxBlockEntity blockEntity) {
        super(new BatBoxScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
        glassguis_setName(blockEntity.getName());
    }


    @Override
    public void drawMachineForeground(float tickDelta) {
        drawGauge();
    }
}
