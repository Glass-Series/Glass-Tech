package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.CompressorRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.TagRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;
import static net.glasslauncher.mods.glasstech.util.TagHelper.tagKey;

public class CompressorRecipes {
    public static void initRecipes() {
        CompressorRecipeRegistry registry = CompressorRecipeRegistry.INSTANCE;
        registry.register(NAMESPACE.id("plantball2compressedplant"), new CompressorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.plantBall)), new RecipeOutput(new ItemStack(GlassTechItems.compressedPlantBall))));
        registry.register(NAMESPACE.id("uranium2uraniumingot"), new CompressorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.uranium)), new RecipeOutput(new ItemStack(GlassTechItems.uraniumIngot))));
        registry.register(NAMESPACE.id("hydratedcoaldust2hydratedcoalball"), new CompressorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.hydratedCoalDust)), new RecipeOutput(new ItemStack(GlassTechItems.compressedHydratedCoal))));
        registry.register(NAMESPACE.id("mixedmetal2advancedalloy"), new CompressorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.mixedMetalIngot)), new RecipeOutput(new ItemStack(GlassTechItems.advancedAlloy))));
        registry.register(NAMESPACE.id("coalchunk2inddiamond"), new CompressorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.coalChunk)), new RecipeOutput(new ItemStack(GlassTechItems.industrialDiamond))));
        registry.register(NAMESPACE.id("carbonmesh2carbonplate"), new CompressorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.carbonMesh)), new RecipeOutput(new ItemStack(GlassTechItems.carbonPlate))));
        registry.register(NAMESPACE.id("water2snowball"), new CompressorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.waterCell)), new RecipeOutput(new ItemStack(Item.SNOWBALL))));
        registry.register(NAMESPACE.id("sand2sandstone"), new CompressorRecipe(new TagRecipeInput(tagKey("sands")), new RecipeOutput(new ItemStack(Block.SANDSTONE))));
        registry.register(NAMESPACE.id("coalball2compressedcoal"), new CompressorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.coalBall)), new RecipeOutput(new ItemStack(GlassTechItems.compressedCoalBall))));
        registry.register(NAMESPACE.id("snowball2ice"), new CompressorRecipe(new StackRecipeInput(new ItemStack(Item.SNOWBALL)), new RecipeOutput(new ItemStack(Block.ICE))));
    }

    public static class CompressorRecipe extends BasicMachineRecipe {
        public CompressorRecipe(RecipeInput[] inputs, RecipeOutput[] outputs) {
            super(inputs, outputs);
        }

        public CompressorRecipe(RecipeInput[] inputs, RecipeOutput[] outputs, int time) {
            super(inputs, outputs, time);
        }

        public CompressorRecipe(RecipeInput input, int time, RecipeOutput output) {
            super(input, time, output);
        }

        public CompressorRecipe(RecipeInput input, RecipeOutput output) {
            super(input, output);
        }
    }
}
