package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.glasslauncher.mods.glasstech.recipe.machine.ElectrolyzerMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.ElectrolyzerRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.item.ItemStack;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class ElectrolyzerRecipes {
    public static void initRecipes() {
        ElectrolyzerRecipeRegistry registry = ElectrolyzerRecipeRegistry.INSTANCE;
        registry.register(NAMESPACE.id("electrolyzewater"), new ElectrolyzerMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.waterCell)), 200, new RecipeOutput(new ItemStack(GlassTechItems.electrolyzedWaterCell)), -20000));
        registry.register(NAMESPACE.id("unelectrolyzewater"), new ElectrolyzerMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.electrolyzedWaterCell)), 200, new RecipeOutput(new ItemStack(GlassTechItems.waterCell)), 18000));
    }
}
