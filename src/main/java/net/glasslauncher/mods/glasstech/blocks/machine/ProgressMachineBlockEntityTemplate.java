package net.glasslauncher.mods.glasstech.blocks.machine;

import lombok.Getter;
import net.glasslauncher.mods.glasstech.VoltageTier;

public abstract class ProgressMachineBlockEntityTemplate extends MachineBlockEntityTemplate {

    // Progress
    public int progress;
    @Getter
    public int maxProgress;

    public ProgressMachineBlockEntityTemplate(VoltageTier tier, int maxProgress, int energyConsumption, int energyCapacity) {
        super(tier, energyConsumption, energyCapacity);
        // Progress
        this.progress = 0;
        this.maxProgress = maxProgress;
    }


    public abstract void craftRecipe();

    @Override
    public void processTick() {
        // Check if we can process the current input
        if (canProcess()) {
            if (this.energy > 0) {
                // If we can process and have the energy, process the recipe
                progress += removeEnergy(energyConsumption);
                lit = true;
            } else {
                // If we can process but don't have the energy, slowly revert
                progress -= 2;
                lit = false;
            }
        } else {
            // If we can't process, revert progress to 0
            progress = 0;
            lit = false;
        }

        if (progress < 0) {
            // If progress is less than zero, clamp it to zero
            progress = 0;
        } else if (progress >= getMaxProgress()) {
            // If the progress has reached maximum, craft the recipe
            progress = 0;
            craftRecipe();
        }
    }
}
