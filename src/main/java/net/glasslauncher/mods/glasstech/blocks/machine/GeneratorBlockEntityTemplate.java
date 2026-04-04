package net.glasslauncher.mods.glasstech.blocks.machine;

import lombok.Getter;
import lombok.Setter;
import net.danygames2014.nyalib.energy.template.block.entity.EnergySourceBlockEntityTemplate;
import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class GeneratorBlockEntityTemplate extends EnergySourceBlockEntityTemplate {
    @Setter @ServerSyncedField
    private int energyCapacity;
    @Setter
    private int maxEnergyOutput;
    @Setter
    private int outputVoltage;
    @Setter
    private int maxOutputVoltage;
    @Getter
    private int maxInputAmps = 1;

    public GeneratorBlockEntityTemplate(VoltageTier voltageTier) {
        outputVoltage = voltageTier.maxVoltage;
        maxEnergyOutput = outputVoltage * maxInputAmps;
    }

    public void setMaxInputAmps(int amps) {
        maxInputAmps = amps;
        maxEnergyOutput = amps * maxOutputVoltage;
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
