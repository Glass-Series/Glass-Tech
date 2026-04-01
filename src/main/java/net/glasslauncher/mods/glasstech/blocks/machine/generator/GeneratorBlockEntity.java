package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import lombok.Getter;
import lombok.Setter;
import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorBlockEntityTemplate;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;
import net.modificationstation.stationapi.api.state.property.Properties;

public class GeneratorBlockEntity extends GeneratorBlockEntityTemplate implements Inventory {
    @Getter @Setter @ServerSyncedField
    protected int initialFuelTicks;
    @Getter @Setter @ServerSyncedField
    protected int fuelTicks;

    protected ItemStack[] slots = new ItemStack[2];

    public GeneratorBlockEntity() {
        super(VoltageTier.LV);
        setEnergyCapacity(19200*4); // 4 coal worth
        setMaxEnergyOutput(12);
    }

    @Override
    public void tick() {
        super.tick();
        if (world.isRemote) {
            return;
        }
        if (slots[0] != null && fuelTicks < 1 && energy < getEnergyCapacity()) {
            int fuelTime = FuelRegistry.getFuelTime(slots[0]);
            if (fuelTime < 1) {
                return;
            }
            initialFuelTicks = fuelTime;
            fuelTicks = fuelTime;
            slots[0].count--;
            if (slots[0].count < 1) {
                slots[0] = null;
            }
        }
        BlockState state = world.getBlockState(x, y, z);
        if (fuelTicks > 0) {
            fuelTicks--;
            energy += 8; // 8 eu/t
            if (state.contains(Properties.LIT) && !state.get(Properties.LIT)) {
                FurnaceBlock.ignoreBlockRemoval = true;
                world.setBlockStateWithNotify(x, y, z, state.with(Properties.LIT, true));
                FurnaceBlock.ignoreBlockRemoval = false;
            }
        }
        else if (state.get(Properties.LIT)) {
            FurnaceBlock.ignoreBlockRemoval = true;
            world.setBlockStateWithNotify(x, y, z, state.with(Properties.LIT, false));
            FurnaceBlock.ignoreBlockRemoval = false;
        }
        if (energy > getEnergyCapacity()) {
            energy = getEnergyCapacity();
        }

        markDirty();
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.put("fuelTicks", new NbtInt(fuelTicks));
        tag.put("initialFuelTicks", new NbtInt(initialFuelTicks));
        for (int i = 0; i < slots.length; i++) {
            ItemStack slot = slots[i];
            if (slot != null) {
                NbtCompound item = new NbtCompound();
                slot.writeNbt(item);
                tag.put("item" + i, item);
            }
        }
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        fuelTicks = tag.getInt("fuelTicks");
        initialFuelTicks = tag.getInt("initialFuelTicks");
        for (int i = 0; i < slots.length; i++) {
            if (tag.contains("item" + i)) {
                slots[i] = new ItemStack(tag.getCompound("item" + i));
            }
        }
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public ItemStack getStack(int slot) {
        return slots[slot];
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = getStack(slot);
        if (stack == null) {
            return null;
        }

        if (stack.count == amount) {
            slots[slot] = null;
            return stack;
        }
        stack.count -= amount;
        stack = stack.copy();
        stack.count = amount;
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        slots[slot] = stack;
    }

    @Override
    public String getName() {
        return "Generator";
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return player.getSquaredDistance(x + 0.5, y + 0.5, z + 0.5) <= 64;
    }
}
