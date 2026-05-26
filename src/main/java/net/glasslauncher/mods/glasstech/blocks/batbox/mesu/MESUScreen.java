package net.glasslauncher.mods.glasstech.blocks.batbox.mesu;

import net.glasslauncher.mods.glasstech.blocks.machine.EnergySourceConsumerScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class MESUScreen extends EnergySourceConsumerScreenTemplate<MESUBlockEntity> {

    public MESUScreen(PlayerInventory playerInventory, MESUBlockEntity blockEntity) {
        super(new MESUScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
        glassguis_setName(blockEntity.getName());
    }


    @Override
    public void drawMachineForeground(float tickDelta) {
        drawGauge();
    }
}
