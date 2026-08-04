package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.ElectrolyzerRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;

public class ElectrolyzerRecipes {
    public static void initRecipes() {
        ElectrolyzerRecipeRegistry registry = ElectrolyzerRecipeRegistry.INSTANCE;
//        registry.register(NAMESPACE.id("electrolyzewater"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.waterCell)), 200, new RecipeOutput(new ItemStack(GlassTechItems.electrolyzedWaterCell))));
    }

    public static class ElectrolyzerRecipe extends BasicMachineRecipe {
        public ElectrolyzerRecipe(RecipeInput[] inputs, RecipeOutput[] outputs) {
            super(inputs, outputs);
        }

        public ElectrolyzerRecipe(RecipeInput[] inputs, RecipeOutput[] outputs, int time) {
            super(inputs, outputs, time);
        }

        public ElectrolyzerRecipe(RecipeInput input, int time, RecipeOutput output) {
            super(input, time, output);
        }

        public ElectrolyzerRecipe(RecipeInput input, RecipeOutput output) {
            super(input, output);
        }
    }
}
