package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.recipe.machine.ElectrolyzerRecipeRegistry;

public class ElectrolyzerRecipes {
    public static void initRecipes() {
        ElectrolyzerRecipeRegistry registry = ElectrolyzerRecipeRegistry.INSTANCE;
//        registry.register(NAMESPACE.id("electrolyzewater"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.waterCell)), 200, new RecipeOutput(new ItemStack(GlassTechItems.electrolyzedWaterCell))));
    }
}
