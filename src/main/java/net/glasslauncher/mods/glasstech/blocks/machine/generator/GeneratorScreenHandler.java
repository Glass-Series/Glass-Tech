package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glassguis.packet.s2c.ScreenHandlerPropertyUpdateIntegerS2CPacket;
import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.Slot;

class GeneratorScreenHandler extends ScreenHandler {
    private final PlayerInventory playerInventory;
    private final GeneratorBlockEntity generatorBlockEntity;

    public GeneratorScreenHandler(PlayerInventory playerInventory, GeneratorBlockEntity generatorBlockEntity) {
        this.playerInventory = playerInventory;
        this.generatorBlockEntity = generatorBlockEntity;
        addSlot(new Slot(generatorBlockEntity, 0, 10, 40));
        addSlot(new BatterySlot(generatorBlockEntity, 1, 32, 40));
        glassguis_setupPlayerInventory(8, 167, playerInventory);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return generatorBlockEntity.canPlayerUse(player);
    }

    @Override
    public void addListener(ScreenHandlerListener listener) {
        super.addListener(listener);
        // Ah yes, cursed shit, my favourite
        ServerPlayerEntity player = (ServerPlayerEntity) listener;
        player.networkHandler.sendPacket(new ScreenHandlerPropertyUpdateIntegerS2CPacket(syncId, 0, generatorBlockEntity.getEnergyStored()));
        player.networkHandler.sendPacket(new ScreenHandlerPropertyUpdateIntegerS2CPacket(syncId, 1, generatorBlockEntity.getFuelTicks()));
        player.networkHandler.sendPacket(new ScreenHandlerPropertyUpdateIntegerS2CPacket(syncId, 2, generatorBlockEntity.getInitialFuelTicks()));
    }

    @Override
    public void setProperty(int syncID, int int_) {
        switch (syncID) {
            case 0:
                generatorBlockEntity.setEnergy(int_);
            case 1:
                generatorBlockEntity.setFuelTicks(int_);
            case 2:
                generatorBlockEntity.setInitialFuelTicks(int_);
        }
    }
}