package net.glasslauncher.mods.glasstech.blocks.machine;

import lombok.Setter;
import net.danygames2014.nyalib.energy.template.block.entity.EnergySourceBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class GeneratorBlockEntityTemplate extends EnergySourceBlockEntityTemplate {
    @Setter
    private int energyCapacity;
    @Setter
    private int maxEnergyOutput;
    @Setter
    private int outputVoltage;
    @Setter
    private int maxOutputVoltage;

    public GeneratorBlockEntityTemplate(VoltageTier voltageTier) {
        outputVoltage = voltageTier.maxVoltage;
        maxOutputVoltage = voltageTier.maxVoltage;
    }

    @Override
    public int getMaxOutputVoltage(@Nullable Direction direction) {
        return maxOutputVoltage;
    }

    @Override
    public int getOutputVoltage(@Nullable Direction direction) {
        return outputVoltage;
    }

    @Override
    public int getMaxEnergyOutput(@Nullable Direction direction) {
        return maxEnergyOutput;
    }

    @Override
    public boolean canExtractEnergy(@Nullable Direction direction) {
        return true;
    }

    @Override
    public boolean canConnectEnergy(Direction direction) {
        return true;
    }

    @Override
    public int getEnergyCapacity() {
        return energyCapacity;
    }
}
