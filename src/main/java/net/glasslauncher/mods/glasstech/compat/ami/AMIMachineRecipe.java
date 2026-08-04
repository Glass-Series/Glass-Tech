package net.glasslauncher.mods.glasstech.compat.ami;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AMIMachineRecipe implements RecipeWrapper {
    public final BasicMachineRecipe recipe;

    public AMIMachineRecipe(BasicMachineRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public List<?> getInputs() {
        ArrayList<Object> inputs = new ArrayList<>();
        for (RecipeInput input : recipe.inputs) {
            inputs.add(input.getRepresentingStacks());
        }
        return inputs;
    }

    @Override
    public List<?> getOutputs() {
        ArrayList<Object> outputs = new ArrayList<>();
        for (RecipeOutput output : recipe.outputs) {
            outputs.add(output.getOutput(null));
        }
        return outputs;
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

    }

    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight) {

    }

    @Override
    public @Nullable ArrayList<Object> getTooltip(int mouseX, int mouseY) {
        return null;
    }

    @Override
    public boolean handleClick(@NotNull Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return false;
    }
}
