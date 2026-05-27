package net.glasslauncher.mods.glasstech.blocks.transformer;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.PowerStorageBlockEntityTemplate;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import static net.modificationstation.stationapi.api.state.property.Properties.FACING;

public abstract class TransformerBlockEntity extends PowerStorageBlockEntityTemplate {
    public VoltageTier stepDown;
    public VoltageTier stepUp;
    public boolean steppingUp = false;

    public TransformerBlockEntity() {
        super(VoltageTier.LV, VoltageTier.LV.maxVoltage * 2);
        VoltageTier voltageTier = getTier();
        stepDown = voltageTier;
        stepUp = voltageTier.stepUp();
        setCapacity(voltageTier.maxVoltage * 2);
        setAmps(getAmps()); // Voltage tier might have changed.
    }

    protected abstract VoltageTier getTier();

    @Override
    public void tick() {
        super.tick();
        steppingUp = world.getPowerLevel(x, y, z) > 0;
    }

    @Override
    public int getMaxEnergyOutput(@Nullable Direction direction) {
        if (direction == world.getBlockState(x, y, z).get(FACING)) {
            return steppingUp ? stepUp.maxVoltage : stepDown.maxVoltage;
        }
        return 0;
    }

    @Override
    public int getMaxEnergyInput(@Nullable Direction direction) {
        if (direction == world.getBlockState(x, y, z).get(FACING)) {
            return 0;
        }
        return steppingUp ? stepDown.maxVoltage : stepUp.maxVoltage;
    }

    @Override
    public int getMaxInputVoltage(@Nullable Direction direction) {
        if (direction == world.getBlockState(x, y, z).get(FACING)) {
            return steppingUp ? stepUp.maxVoltage : stepDown.maxVoltage;
        }
        return steppingUp ? stepDown.maxVoltage : stepUp.maxVoltage;
    }

    @Override
    public String getName() {
        return stepDown.name() + " Transformer";
    }
}
