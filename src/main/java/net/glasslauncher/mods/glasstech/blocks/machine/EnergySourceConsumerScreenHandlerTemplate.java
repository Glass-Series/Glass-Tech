package net.glasslauncher.mods.glasstech.blocks.machine;

import net.glasslauncher.mods.glassguis.screen.AutoSyncingScreenHandler;
import net.glasslauncher.mods.networking.GlassNetworking;
import net.glasslauncher.mods.networking.GlassPacket;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.ScreenHandlerPropertyUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;

public class EnergySourceConsumerScreenHandlerTemplate<T extends EnergySourceConsumerBlockEntityTemplate> extends ScreenHandler implements AutoSyncingScreenHandler {
    private final PlayerInventory playerInventory;
    private final T blockEntity;

    private int cachedEnergy = 0;

    public EnergySourceConsumerScreenHandlerTemplate(PlayerInventory playerInventory, T blockEntity, boolean hasPlayerInventory) {
        this.playerInventory = playerInventory;
        this.blockEntity = blockEntity;
        if (hasPlayerInventory) {
            glassguis_setupPlayerInventory(8, 167, playerInventory);
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        if (blockEntity instanceof Inventory inventory) {
            return inventory.canPlayerUse(player);
        }
        return player.getSquaredDistance(blockEntity.x + 0.5, blockEntity.y + 0.5, blockEntity.z + 0.5) <= 64;
    }

    @Override
    public void addListener(ScreenHandlerListener listener) {
        super.addListener(listener);
        // Ah yes, cursed shit, my favourite
        ServerPlayerEntity player = (ServerPlayerEntity) listener;
        NbtCompound data = new NbtCompound();
        data.putInt("syncId", syncId);
        data.putInt("propertyId", 100);
        data.putInt("value", blockEntity.getEnergyStored());
        GlassNetworking.sendToPlayer(player, new GlassPacket("glassguis", "int", data));
        cachedEnergy = blockEntity.getEnergyStored();
    }

    @Override
    public void sendContentUpdates() {
        super.sendContentUpdates();
        boolean updateEnergy = blockEntity.getEnergyStored() != cachedEnergy;

        if (updateEnergy) {
            cachedEnergy = blockEntity.getEnergyStored();
        }

        for (Object o : listeners) {
            if (o instanceof ServerPlayerEntity player) {
                if (updateEnergy) {
                    NbtCompound data = new NbtCompound();
                    data.putInt("syncId", syncId);
                    data.putInt("propertyId", 100);
                    data.putInt("value", blockEntity.getEnergyStored());
                    GlassNetworking.sendToPlayer(player, new GlassPacket("glassguis", "int", data));
                }
            }
        }
    }

    @Override
    public void setProperty(int syncID, int int_) {
        super.setProperty(syncID, int_);

        switch (syncID) {
            case 100:
                blockEntity.setEnergy(int_);
        }
    }

    @Override
    public BlockEntity getBlockEntity() {
        return blockEntity;
    }
}