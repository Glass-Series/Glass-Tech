package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import lombok.Getter;
import lombok.Setter;
import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorWithInventoryBlockEntityTemplate;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;
import net.modificationstation.stationapi.api.state.property.Properties;

public class GeneratorBlockEntity extends GeneratorWithInventoryBlockEntityTemplate implements Inventory {
    @Getter @Setter @ServerSyncedField
    protected int initialFuelTicks;
    @Getter @Setter @ServerSyncedField
    protected int fuelTicks;
    @Getter @Setter
    protected int generationAmount = 10; // eu/t
    @Getter @Setter
    protected float fuelEfficiency = 0.25f;

    public GeneratorBlockEntity() {
        super(2, VoltageTier.LV);
        setEnergyCapacity(4000);
    }

    @Override
    public void tick() {
        super.tick();
        if (world.isRemote) {
            return;
        }
        if (slots[0] != null && fuelTicks < 1 && energy < getEnergyCapacity()) {
            int fuelTime = (int) (FuelRegistry.getFuelTime(slots[0]) * fuelEfficiency);
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
            energy += generationAmount; // in eu/t
            if (state.contains(Properties.LIT) && !state.get(Properties.LIT)) {
                FurnaceBlock.ignoreBlockRemoval = true;
                world.setBlockState(x, y, z, state.with(Properties.LIT, true));
                FurnaceBlock.ignoreBlockRemoval = false;
            }
        }
        else if (state.get(Properties.LIT)) {
            FurnaceBlock.ignoreBlockRemoval = true;
            world.setBlockState(x, y, z, state.with(Properties.LIT, false));
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
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        fuelTicks = tag.getInt("fuelTicks");
        initialFuelTicks = tag.getInt("initialFuelTicks");
    }

    @Override
    public String getName() {
        return "Generator";
    }

    @Override
    public int getGeneratingCurrent() {
        return fuelTicks > 1 ? 10 : 0;
    }
}
