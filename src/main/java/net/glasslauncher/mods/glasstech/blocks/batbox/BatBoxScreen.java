package net.glasslauncher.mods.glasstech.blocks.batbox;

import net.glasslauncher.mods.glassguis.DrawDirection;
import net.glasslauncher.mods.glassguis.events.init.GlassGUIs;
import net.glasslauncher.mods.glasstech.blocks.machine.EnergySourceConsumerScreenTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

import java.awt.*;

import static net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout.GAUGE_LOCATION;

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
