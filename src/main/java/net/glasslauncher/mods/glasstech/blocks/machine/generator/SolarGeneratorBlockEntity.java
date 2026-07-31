package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import lombok.Getter;
import lombok.Setter;
import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorWithInventoryBlockEntityTemplate;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.property.Properties;

public class SolarGeneratorBlockEntity extends GeneratorWithInventoryBlockEntityTemplate implements Inventory {
    @Getter @Setter
    protected int generationAmount = 1; // eu/t
    @ServerSyncedField
    boolean hasSun = false;
    @ServerSyncedField
    boolean hasSky = false;

    public SolarGeneratorBlockEntity() {
        super(1, VoltageTier.LV);
        setEnergyCapacity(100);
    }

    @Override
    public void tick() {
        super.tick();
        if (world.isRemote) {
            return;
        }


        hasSun = world.canMonsterSpawn();
        hasSky = world.hasSkyLight(x, y + 1, z);

        BlockState state = world.getBlockState(x, y, z);
        if (!hasSky || !hasSun) {
            if (state.get(Properties.LIT)) {
                FurnaceBlock.ignoreBlockRemoval = true;
                world.setBlockState(x, y, z, state.with(Properties.LIT, false));
                FurnaceBlock.ignoreBlockRemoval = false;
            }
            return;
        }

        energy += generationAmount; // in eu/t

        if (state.contains(Properties.LIT) && !state.get(Properties.LIT)) {
            FurnaceBlock.ignoreBlockRemoval = true;
            world.setBlockState(x, y, z, state.with(Properties.LIT, true));
            FurnaceBlock.ignoreBlockRemoval = false;
        }

        if (energy > getEnergyCapacity()) {
            energy = getEnergyCapacity();
        }

        markDirty();
    }

    @Override
    public String getName() {
        return "Solar Generator";
    }

    @Override
    public int getGeneratingCurrent() {
        return hasSun ? generationAmount : 0;
    }
}
