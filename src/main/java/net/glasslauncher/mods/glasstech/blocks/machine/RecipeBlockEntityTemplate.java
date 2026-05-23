package net.glasslauncher.mods.glasstech.blocks.machine;

import lombok.Getter;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public abstract class RecipeBlockEntityTemplate<R extends BasicMachineRecipe> extends ProgressMachineBlockEntityTemplate {
    @Getter
    private int currentShortestTime = Integer.MAX_VALUE;

    public RecipeBlockEntityTemplate(VoltageTier tier, int maxProgress, int energyConsumption, int energyCapacity) {
        super(tier, maxProgress, energyConsumption, energyCapacity);
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

        if (recipe.time / energyConsumption < currentShortestTime) {
            currentShortestTime = recipe.time / energyConsumption;
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

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean pushOutput(RecipeOutputType type, ItemStack output, boolean simulate) {
        int[] slotIndexes = getOutputIndexes(type);
        ItemStack[] slots = getOutputs(type, simulate);

        // Existing stacks? Fill those first.
        for (ItemStack slot : slots) {
            if (slot == null) {
                continue;
            }
            if (slot.isItemEqual(output)) {
                int amountTransferred = Math.min(Math.min(getMaxCountPerStack(), slot.getMaxCount()), slot.count + output.count) - slot.count;
                output.count -= amountTransferred;
                slot.count += amountTransferred;
                if (output.count == 0) {
                    return true;
                }
            }
        }
        // Okay now just dump the item
        for (int i = 0; i < slots.length; i++) {
            ItemStack slot = slots[i];
            if (slot == null) {
                if (!simulate) {
                    inventory[slotIndexes[i]] = output;
                }
                return true;
            }
        }
        return false;
    }

    public ItemStack[] getInputs(int[] inputIndexes) {
        ArrayList<ItemStack> out = new ArrayList<>();
        for (int inputSlot : inputIndexes) {
            out.add(inventory[inputSlot]);
        }

        return out.toArray(new ItemStack[0]);
    }

    @Override
    public int getMaxProgress() {
        return currentShortestTime;
    }
}
