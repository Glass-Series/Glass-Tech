package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.ElectrolyzerRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.item.ItemStack;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class ElectrolyzerRecipes {
    public static void initRecipes() {
        ElectrolyzerRecipeRegistry registry = ElectrolyzerRecipeRegistry.INSTANCE;
        registry.register(NAMESPACE.id("electrolyzewater"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.waterCell)), 200, new RecipeOutput(new ItemStack(GlassTechItems.electrolyzedWaterCell))));
    }
}
