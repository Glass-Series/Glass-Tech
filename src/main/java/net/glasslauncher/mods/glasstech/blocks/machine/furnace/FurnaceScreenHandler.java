package net.glasslauncher.mods.glasstech.blocks.machine.furnace;

import net.glasslauncher.mods.glassguis.packet.s2c.ScreenHandlerPropertyUpdateIntegerS2CPacket;
import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.Slot;

class FurnaceScreenHandler extends ScreenHandler {
    protected final PlayerInventory playerInventory;
    protected final FurnaceBlockEntity furnaceBlockEntity;

    public FurnaceScreenHandler(PlayerInventory playerInventory, FurnaceBlockEntity furnaceBlockEntity) {
        super();
        this.playerInventory = playerInventory;
        this.furnaceBlockEntity = furnaceBlockEntity;
        addSlot(new Slot(furnaceBlockEntity, 0, 10, 40));
        addSlot(new BatterySlot(furnaceBlockEntity, 1, 32, 40));
        glassguis_setupPlayerInventory(8, 167, playerInventory);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return furnaceBlockEntity.canPlayerUse(player);
    }

    @Override
    public void addListener(ScreenHandlerListener listener) {
        super.addListener(listener);
        // Ah yes, cursed shit, my favourite
        ServerPlayerEntity player = (ServerPlayerEntity) listener;
        player.networkHandler.sendPacket(new ScreenHandlerPropertyUpdateIntegerS2CPacket(syncId, 0, furnaceBlockEntity.getEnergyStored()));
        player.networkHandler.sendPacket(new ScreenHandlerPropertyUpdateIntegerS2CPacket(syncId, 1, furnaceBlockEntity.getCurrentSmeltTime()));
    }

    @Override
    public void setProperty(int syncID, int int_) {
        switch (syncID) {
            case 0:
                furnaceBlockEntity.setEnergy(int_);
            case 1:
                furnaceBlockEntity.setCurrentSmeltTime(int_);
        }
    }
}