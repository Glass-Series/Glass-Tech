package net.glasslauncher.mods.glasstech.recipe.machine;

import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BasicMachineRecipe {
    public final RecipeInput[] inputs;
    public final RecipeOutput[] outputs;

    public final int progressModifier;

    public BasicMachineRecipe(RecipeInput[] inputs, RecipeOutput[] outputs) {
        this(inputs, outputs, 1);
    }

    public BasicMachineRecipe(RecipeInput[] inputs, RecipeOutput[] outputs, int progressModifier) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.progressModifier = progressModifier;
    }

    public BasicMachineRecipe(RecipeInput input, int progressModifier, RecipeOutput output) {
        this.inputs = new RecipeInput[]{input};
        this.outputs = new RecipeOutput[]{output};
        this.progressModifier = progressModifier;
    }

    public BasicMachineRecipe(RecipeInput input, RecipeOutput output) {
        this.inputs = new RecipeInput[]{input};
        this.outputs = new RecipeOutput[]{output};
        this.progressModifier = 1;
    }

    public boolean matches(ItemStack[] inputs) {
        // This is used to keep track of whether an ingredient has already been "consumed"
        boolean[] used = new boolean[inputs.length];

        if (this.inputs.length == 0) {
            return false;
        }

        // Loop over the required ingredients
        for (RecipeInput input : this.inputs) {
            // Check all the provided ingredients
            boolean satisfied = false;

            for (int i = 0; i < inputs.length; i++) {
                if (used[i]) {
                    continue;
                }

                ItemStack inputStack = inputs[i];
                if (inputStack == null) {
                    continue;
                }

                if (input.matches(inputStack)) {
                    satisfied = true;
                    used[i] = true;
                    break;
                }
            }

            // If not satisfied, return false
            if (!satisfied) {
                return false;
            }
        }

        return true;
    }

    public boolean consume(ItemStack[] inputs) {
        // Check if the inputs actually match the recipe
        if (matches(inputs)) {
            // This is used to keep track of whether an ingredient has already been "consumed"
            boolean[] used = new boolean[inputs.length];

            // Loop over the required ingredients
            for (RecipeInput input : this.inputs) {
                // Check all the provided ingredients
                for (int i = 0; i < inputs.length; i++) {
                    if (used[i]) {
                        continue;
                    }

                    ItemStack inputStack = inputs[i];
                    if (inputStack == null) {
                        continue;
                    }

                    if (input.matches(inputStack)) {
                        used[i] = true;
                        inputStack.count -= input.getRequiredAmount();
                        break;
                    }
                }
            }

            return true;
        }

        return false;
    }

    public Map<RecipeOutputType, ArrayList<ItemStack>> getOutputs(Random random) {
        HashMap<RecipeOutputType, ArrayList<ItemStack>> out = new HashMap<>();

        for (RecipeOutput recipeOutput : this.outputs) {
            if (!out.containsKey(recipeOutput.type)) {
                out.put(recipeOutput.type, new ArrayList<>());
            }

            ItemStack outputStack = recipeOutput.getOutput(random);
            if (outputStack != null) {
                out.get(recipeOutput.type).add(outputStack);
            }
        }

        return out;
    }

    public ArrayList<ItemStack> getCompactOutputs(Random random) {
        ArrayList<ItemStack> outputs = new ArrayList<>();

        for (RecipeOutput recipeOutput : this.outputs) {
            ItemStack outputStack = recipeOutput.getOutput(random);

            boolean found = false;
            for (var output : outputs) {
                if (output.equals(outputStack)) {
                    int excess = 0;

                    if (output.count + outputStack.count > output.getMaxCount()) {
                        excess = output.getMaxCount() - (output.count + outputStack.count);

                        ItemStack excessStack = outputStack.copy();
                        excessStack.count = excess;
                        outputs.add(excessStack);
                    }

                    output.count += (outputStack.count - excess);
                    found = true;
                    break;
                }
            }

            if (!found) {
                outputs.add(outputStack);
            }
        }

        return outputs;
    }
}
