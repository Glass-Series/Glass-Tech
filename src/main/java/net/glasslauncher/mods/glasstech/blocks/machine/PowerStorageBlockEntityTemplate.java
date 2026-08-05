package net.glasslauncher.mods.glasstech.blocks.machine;

import lombok.Getter;
import lombok.Setter;
import net.danygames2014.nyalib.capability.CapabilityHelper;
import net.danygames2014.nyalib.capability.item.energyhandler.EnergyStorageItemCapability;
import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.explosion.Explosion;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public abstract class PowerStorageBlockEntityTemplate extends EnergySourceConsumerBlockEntityTemplate implements Inventory {

    @Setter
    private int maxVoltage;
    @Setter
    private int maxEnergyTransfer;
    @ServerSyncedField @Setter
    private int capacity;
    @Getter
    private int amps = 1;

    protected ItemStack[] inventory = new ItemStack[2];

    public PowerStorageBlockEntityTemplate(VoltageTier voltageTier, int energyCapacity) {
        maxVoltage = voltageTier.maxVoltage;
        capacity = energyCapacity;
        setAmps(amps);
    }

    public void setAmps(int amps) {
        this.amps = amps;
        maxEnergyTransfer = maxVoltage * amps;
    }

    @Override
    public int getMaxOutputVoltage(@Nullable Direction direction) {
        return maxVoltage;
    }

    @Override
    public int getOutputVoltage(@Nullable Direction direction) {
        return maxVoltage;
    }

    @Override
    public int getMaxEnergyOutput(@Nullable Direction direction) {
        return maxEnergyTransfer;
    }

    @Override
    public boolean canExtractEnergy(@Nullable Direction direction) {
        return direction == null || direction == world.getBlockState(x, y, z).get(Properties.FACING);
    }

    @Override
    public boolean canConnectEnergy(Direction direction) {
        return true;
    }

    @Override
    public int getEnergyCapacity() {
        return capacity;
    }

    @Override
    public int getMaxInputVoltage(@Nullable Direction direction) {
        return maxVoltage;
    }

    @Override
    public int getMaxEnergyInput(@Nullable Direction direction) {
        return maxEnergyTransfer;
    }

    @Override
    public boolean canReceiveEnergy(@Nullable Direction direction) {
        BlockState state = world.getBlockState(x, y, z);
        if (!state.contains(Properties.FACING)) {
            return false; // Ruh roh stapi broke again
        }
        return direction == null || direction != state.get(Properties.FACING);
    }

    @Override
    public void onOvervoltage(@Nullable Direction direction, double voltage) {
        Explosion yesRicoKaboom = new Explosion(world, null, x, y, z, 5F);
        yesRicoKaboom.playExplosionSound(true);
        yesRicoKaboom.explode();
    }

    // Inventory Interface
    @Override
    public int size() {
        return inventory.length;
    }

    @Override
    public ItemStack getStack(int slot) {
        if (slot < 0 || slot >= inventory.length) {
            return null;
        }

        return inventory[slot];
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (slot < 0 || slot >= inventory.length) {
            return null;
        }

        ItemStack stack = null;

        if (this.inventory[slot] != null) {
            if (this.inventory[slot].count <= amount) {
                stack = this.inventory[slot];
                this.inventory[slot] = null;
            } else {
                stack = this.inventory[slot].split(amount);
                if (this.inventory[slot].count == 0) {
                    this.inventory[slot] = null;
                }
            }
        }

        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot < 0 || slot >= inventory.length) {
            return;
        }

        this.inventory[slot] = stack;
        if (stack != null && stack.count > this.getMaxCountPerStack()) {
            stack.count = this.getMaxCountPerStack();
        }
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.x, this.y, this.z) != this) {
            return false;
        } else {
            return !(player.getSquaredDistance(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D) > 64.0D);
        }
    }

    // NBT
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        // Inventory
        NbtList itemsNbt = nbt.getList("Items");

        for (int slot = 0; slot < itemsNbt.size(); slot++) {
            NbtCompound stackNbt = (NbtCompound) itemsNbt.get(slot);
            if (slot < inventory.length) {
                inventory[slot] = new ItemStack(stackNbt);
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        // Inventory
        // Input
        NbtList itemsNbt = new NbtList();

        for (ItemStack itemStack : inventory) {
            if (itemStack == null) {
                continue;
            }

            NbtCompound stackNbt = new NbtCompound();
            itemStack.writeNbt(stackNbt);
            itemsNbt.add(stackNbt);
        }

        nbt.put("Items", itemsNbt);
    }

    @Override
    public void tick() {
        super.tick();
        if (world.isRemote) {
            return;
        }

        if (inventory[0] != null) {
            // charging item
            EnergyStorageItemCapability energyStorage = CapabilityHelper.getCapability(inventory[0], EnergyStorageItemCapability.class);
            if (energyStorage != null && getEnergyStored() > 0 && energyStorage.getEnergyStored() < energyStorage.getEnergyCapacity() && VoltageTier.get(getMaxOutputVoltage(null)).maxVoltage >= energyStorage.getMaxEnergyInput()) {
                energyStorage.addEnergy(removeEnergy(Math.min(energyStorage.getRemainingCapacity(), Math.min(energyStorage.getMaxEnergyInput(), getMaxEnergyOutput(null)))));
            }
        }

        if (inventory[1] != null) {
            // discharging item
            EnergyStorageItemCapability energyStorage = CapabilityHelper.getCapability(inventory[1], EnergyStorageItemCapability.class);
            if (energyStorage != null && energyStorage.getEnergyStored() > 0) {
                addEnergy(energyStorage.extractEnergy(Math.min(getRemainingCapacity(), Math.min(energyStorage.getMaxEnergyOutput(), getMaxEnergyInput(null)))));
            }
        }
    }
}
