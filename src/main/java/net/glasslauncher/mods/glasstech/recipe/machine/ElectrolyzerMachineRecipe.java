package net.glasslauncher.mods.glasstech.recipe.machine;

import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;

public class ElectrolyzerMachineRecipe extends BasicMachineRecipe {
    public final int energyDifference;

    public ElectrolyzerMachineRecipe(RecipeInput[] inputs, RecipeOutput[] outputs, int energyDifference) {
        super(inputs, outputs);
        this.energyDifference = energyDifference;
    }

    public ElectrolyzerMachineRecipe(RecipeInput[] inputs, RecipeOutput[] outputs, int time, int energyDifference) {
        super(inputs, outputs, time);
        this.energyDifference = energyDifference;
    }

    public ElectrolyzerMachineRecipe(RecipeInput input, int time, RecipeOutput output, int energyDifference) {
        super(input, time, output);
        this.energyDifference = energyDifference;
    }

    public ElectrolyzerMachineRecipe(RecipeInput input, RecipeOutput output, int energyDifference) {
        super(input, output);
        this.energyDifference = energyDifference;
    }
}
