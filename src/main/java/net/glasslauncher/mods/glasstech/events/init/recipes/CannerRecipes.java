package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.CannerRecipeRegistry;
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
                    //noinspection DataFlowIssue we got bigger issues if something in the registry has a null id
                    registry.register(NAMESPACE.id(ItemRegistry.INSTANCE.getId(foodItem).toString().replace(":", ".") + "2cannedfood"), new CannerRecipe(new StackRecipeInput(new ItemStack(foodItem)), 200, new RecipeOutput(new ItemStack(GlassTechItems.cannedFood, Math.min(20, Math.floorDiv(healthGiven, 2))))));
                }
            }
        });

        registry.register(NAMESPACE.id("coalfuelintofuelcan"), new CannerRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.coalfuelCell, 6)), 200, new RecipeOutput(new ItemStack(GlassTechItems.fullFuelCan))));
        registry.register(NAMESPACE.id("biofuelintofuelcan"), new CannerRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.biofuelCell, 6)), 200, new RecipeOutput(new ItemStack(GlassTechItems.fullFuelCan))));
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
