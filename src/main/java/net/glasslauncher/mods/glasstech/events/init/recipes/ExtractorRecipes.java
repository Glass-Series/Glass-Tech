package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.ExtractorRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.item.ItemStack;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class ExtractorRecipes {
    public static void initRecipes() {
        ExtractorRecipeRegistry registry = ExtractorRecipeRegistry.INSTANCE;
//        registry.register(NAMESPACE.id("biocell2biofuelcell"), new ExtractorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.bioCell)), 200, new RecipeOutput(new ItemStack(GlassTechItems.biofuelCell))));
        registry.register(NAMESPACE.id("resin2rubber"), new ExtractorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.resin)), 200, new RecipeOutput(new ItemStack(GlassTechItems.rubber, 3))));
//        registry.register(NAMESPACE.id("rubberwood2rubber"), new ExtractorRecipe(new StackRecipeInput(new ItemStack(GlassTechBlocks.rubberWoodLog)), 200, new RecipeOutput(new ItemStack(GlassTechItems.rubber))));
//        registry.register(NAMESPACE.id("rubberwood2rubber"), new ExtractorRecipe(new StackRecipeInput(new ItemStack(GlassTechBlocks.rubberSapling)), 200, new RecipeOutput(new ItemStack(GlassTechItems.rubber))));
//        registry.register(NAMESPACE.id("hydratedcoalcell2coalfuelcell"), new ExtractorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.hydratedCoalCell)), 200, new RecipeOutput(new ItemStack(GlassTechItems.coalfuelCell))));
//        registry.register(NAMESPACE.id("water2coolant"), new ExtractorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.waterCell)), 200, new RecipeOutput(new ItemStack(GlassTechItems.coolantCell))));
    }

    public static class ExtractorRecipe extends BasicMachineRecipe {
        public ExtractorRecipe(RecipeInput[] inputs, RecipeOutput[] outputs) {
            super(inputs, outputs);
        }

        public ExtractorRecipe(RecipeInput[] inputs, RecipeOutput[] outputs, int time) {
            super(inputs, outputs, time);
        }

        public ExtractorRecipe(RecipeInput input, int time, RecipeOutput output) {
            super(input, time, output);
        }

        public ExtractorRecipe(RecipeInput input, RecipeOutput output) {
            super(input, output);
        }
    }
}
