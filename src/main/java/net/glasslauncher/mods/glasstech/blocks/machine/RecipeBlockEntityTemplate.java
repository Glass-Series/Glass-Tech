package net.glasslauncher.mods.glasstech.blocks.machine;

import lombok.Getter;
import lombok.Setter;
import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.item.ItemStack;

public abstract class RecipeBlockEntityTemplate<R extends BasicMachineRecipe> extends ProgressMachineBlockEntityTemplate {
    @Getter @Setter
    private int currentShortestTime = Integer.MAX_VALUE;

    public RecipeBlockEntityTemplate(VoltageTier tier, int maxProgress, int energyConsumption, int energyCapacity) {
        super(tier, maxProgress, energyConsumption, energyCapacity);
    }

    public RecipeBlockEntityTemplate(VoltageTier tier, int maxProgress, int energyConsumption) {
        super(tier, maxProgress, energyConsumption);
    }

    @Override
    public boolean canProcess() {
        currentShortestTime = Integer.MAX_VALUE;
        return tryCraft(true);
    }

    public boolean tryCraft(boolean simulate) {
        return craft(null, simulate);
    }

    /**
     * Fetches the current recipe according to the input
     */
    public abstract R fetchRecipe(ItemStack[] input);

    @Override
    public void craftRecipe() {
        if (!canProcess()) {
            return;
        }

        tryCraft(false);
    }

    public boolean craft(int[] inputIndexes, boolean simulate) {
        if (inputIndexes == null) {
            inputIndexes = getInputIndexes();
        }
        if (!canProcessRecipe(inputIndexes)) {
            return false;
        }

        ItemStack[] inputs = getInputs(inputIndexes);
        R recipe = fetchRecipe(inputs);

        if (!simulate) {
            recipe.consume(inputs);

            for (int i = 0; i < inputs.length; i++) {
                ItemStack stack = inputs[i];
                if (stack == null) {
                    continue;
                }
                if (stack.count <= 0) {
                    inventory[inputIndexes[i]] = null;
                }
            }
        }

        int recipeTime = maxProgress * recipe.progressModifier;
        if (recipeTime < currentShortestTime) {
            currentShortestTime = recipeTime;
        }

        for (RecipeOutput output : recipe.outputs) {
            if (!pushOutput(output.type, output.getOutput(simulate ? null : random), simulate)) {
                if (simulate) {
                    return false;
                }
                else {
                    throw new RuntimeException("UH OH");
                }
            }
        }
        return true;
    }

    public boolean canProcessRecipe(int[] inputIndexes) {
        ItemStack[] inputs = getInputs(inputIndexes);
        R recipe = fetchRecipe(inputs);

        if (recipe == null) {
            return false;
        }

        if (!recipe.matches(inputs)) {
            return false;
        }

        for (RecipeOutput output : recipe.outputs) {
            if (!pushOutput(output.type, output.getOutput(null), true)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int getMaxProgress() {
        return currentShortestTime;
    }
}
