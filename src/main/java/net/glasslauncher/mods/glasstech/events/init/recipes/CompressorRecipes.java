package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.CompressorRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class CompressorRecipes {
    public static void initRecipes() {
        CompressorRecipeRegistry registry = CompressorRecipeRegistry.INSTANCE;
        registry.register(NAMESPACE.id("plantball2compressedplant"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.plantBall)), 200, new RecipeOutput(new ItemStack(GlassTechItems.compressedPlantBall))));
        registry.register(NAMESPACE.id("uranium2uraniumingot"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.uranium)), 200, new RecipeOutput(new ItemStack(GlassTechItems.uraniumIngot))));
        registry.register(NAMESPACE.id("hydratedcoaldust2hydratedcoalball"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.hydratedCoalDust)), 200, new RecipeOutput(new ItemStack(GlassTechItems.compressedHydratedCoal))));
        registry.register(NAMESPACE.id("mixedmetal2advancedalloy"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.mixedMetalIngot)), 200, new RecipeOutput(new ItemStack(GlassTechItems.advancedAlloy))));
        registry.register(NAMESPACE.id("coalchunk2inddiamond"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.coalChunk)), 200, new RecipeOutput(new ItemStack(GlassTechItems.industrialDiamond))));
        registry.register(NAMESPACE.id("water2snowball"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.waterCell)), 200, new RecipeOutput(new ItemStack(Item.SNOWBALL))));
        registry.register(NAMESPACE.id("sand2sandstone"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Block.SAND)), 200, new RecipeOutput(new ItemStack(Block.SANDSTONE))));
        registry.register(NAMESPACE.id("coalball2compressedcoal"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.coalBall)), 200, new RecipeOutput(new ItemStack(GlassTechItems.compressedCoalBall))));
        registry.register(NAMESPACE.id("snowball2ice"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Item.SNOWBALL)), 200, new RecipeOutput(new ItemStack(Block.ICE))));
    }
}
