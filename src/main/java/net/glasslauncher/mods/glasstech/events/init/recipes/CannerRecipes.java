package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.CannerRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.ItemRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.item.FoodItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.registry.ItemRegistry;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class CannerRecipes {
    public static void initRecipes() {
        CannerRecipeRegistry registry = CannerRecipeRegistry.INSTANCE;

        ItemRegistry.INSTANCE.forEach(item -> {
            if (item instanceof FoodItem foodItem) {
                int healthGiven = foodItem.getHealthRestored();
                if (healthGiven > 1) {
                    int amount = Math.floorDiv(healthGiven, 2);
                    //noinspection DataFlowIssue we got bigger issues if something in the registry has a null id
                    registry.register(NAMESPACE.id(ItemRegistry.INSTANCE.getId(foodItem).toString().replace(":", ".") + "2cannedfood"), new CannerRecipe(new RecipeInput[]{new StackRecipeInput(new ItemStack(foodItem)), new ItemRecipeInput(GlassTechItems.emptyCell, amount)}, new RecipeOutput[]{new RecipeOutput(new ItemStack(GlassTechItems.cannedFood, amount))}));
                }
            }
        });

        registry.register(NAMESPACE.id("coalfuelintofuelcan"), new CannerRecipe(new RecipeInput[]{new StackRecipeInput(new ItemStack(GlassTechItems.coalfuelCell, 6)), new ItemRecipeInput(GlassTechItems.emptyFuelCan)}, new RecipeOutput[]{new RecipeOutput(new ItemStack(GlassTechItems.fullFuelCan))}));
        registry.register(NAMESPACE.id("biofuelintofuelcan"), new CannerRecipe(new RecipeInput[]{new StackRecipeInput(new ItemStack(GlassTechItems.biofuelCell, 6)), new ItemRecipeInput(GlassTechItems.emptyFuelCan)}, new RecipeOutput[]{new RecipeOutput(new ItemStack(GlassTechItems.fullFuelCan))}));
    }

    public static class CannerRecipe extends BasicMachineRecipe {
        public CannerRecipe(RecipeInput[] inputs, RecipeOutput[] outputs) {
            super(inputs, outputs);
        }

        public CannerRecipe(RecipeInput[] inputs, RecipeOutput[] outputs, int time) {
            super(inputs, outputs, time);
        }

        public CannerRecipe(RecipeInput input, int time, RecipeOutput output) {
            super(input, time, output);
        }

        public CannerRecipe(RecipeInput input, RecipeOutput output) {
            super(input, output);
        }
    }
}
