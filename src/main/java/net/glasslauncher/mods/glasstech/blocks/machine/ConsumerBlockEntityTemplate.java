package net.glasslauncher.mods.glasstech.blocks.machine;

import lombok.Getter;
import lombok.Setter;
import net.danygames2014.nyalib.energy.template.block.entity.EnergyConsumerBlockEntityTemplate;
import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.world.explosion.Explosion;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class ConsumerBlockEntityTemplate extends EnergyConsumerBlockEntityTemplate {
    @Setter @ServerSyncedField
    private int energyCapacity;
    private int maxEnergyInput;
    @Setter
    private int maxInputVoltage;
    @Getter
    private int maxInputAmps = 1;

    public ConsumerBlockEntityTemplate(VoltageTier voltageTier) {
        maxInputVoltage = voltageTier.maxVoltage;
        maxEnergyInput = voltageTier.maxVoltage * maxInputAmps;
    }

    public void setMaxInputAmps(int amps) {
        maxInputAmps = amps;
        maxEnergyInput = amps * maxInputVoltage;
    }

    @Override
    public int getMaxInputVoltage(@Nullable Direction direction) {
        return maxInputVoltage;
    }

    @Override
    public int getMaxEnergyInput(@Nullable Direction direction) {
        return maxEnergyInput;
    }

    @Override
    public boolean canReceiveEnergy(@Nullable Direction direction) {
        return true;
    }

    @Override
    public void onOvervoltage(@Nullable Direction direction, double voltage) {
        Explosion dio = new Explosion(world, null, x, y, z, 5F);
        dio.playExplosionSound(true);
        dio.explode();
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
