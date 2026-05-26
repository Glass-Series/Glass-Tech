package net.glasslauncher.mods.glasstech.blocks.batbox.esu;

import net.glasslauncher.mods.glasstech.blocks.batbox.BatBoxBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.batbox.BatBoxScreenHandler;
import net.glasslauncher.mods.glasstech.blocks.machine.EnergySourceConsumerScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;

public class ESUScreen extends EnergySourceConsumerScreenTemplate<ESUBlockEntity> {

    public ESUScreen(PlayerInventory playerInventory, ESUBlockEntity blockEntity) {
        super(new ESUScreenHandler(playerInventory, blockEntity), playerInventory, blockEntity);
        glassguis_setName(blockEntity.getName());
    }


    @Override
    public void drawMachineForeground(float tickDelta) {
        drawGauge();
    }
}
