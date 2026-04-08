package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.MaceratorRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class MaceratorRecipes {
    public static void initRecipes() {
        MaceratorRecipeRegistry registry = MaceratorRecipeRegistry.INSTANCE;
        registry.register(NAMESPACE.id("refinediron2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.refinedIronIngot)), 200, new RecipeOutput(new ItemStack(GlassTechItems.ironDust, 2))));
        registry.register(NAMESPACE.id("iron2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Item.IRON_INGOT)), 200, new RecipeOutput(new ItemStack(GlassTechItems.ironDust, 2))));
        registry.register(NAMESPACE.id("gold2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Item.GOLD_INGOT)), 200, new RecipeOutput(new ItemStack(GlassTechItems.goldDust, 2))));
        registry.register(NAMESPACE.id("tin2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.tinIngot)), 200, new RecipeOutput(new ItemStack(GlassTechItems.tinDust, 2))));
        registry.register(NAMESPACE.id("copper2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.copperIngot)), 200, new RecipeOutput(new ItemStack(GlassTechItems.copperDust, 2))));
        registry.register(NAMESPACE.id("ironore2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Block.IRON_ORE)), 200, new RecipeOutput(new ItemStack(GlassTechItems.ironDust, 2))));
        registry.register(NAMESPACE.id("goldore2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Block.GOLD_ORE)), 200, new RecipeOutput(new ItemStack(GlassTechItems.goldDust, 2))));
        registry.register(NAMESPACE.id("tinore2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechBlocks.tinOre)), 200, new RecipeOutput(new ItemStack(GlassTechItems.tinDust, 2))));
        registry.register(NAMESPACE.id("copperore2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechBlocks.copperOre)), 200, new RecipeOutput(new ItemStack(GlassTechItems.copperDust, 2))));
        registry.register(NAMESPACE.id("coal2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Item.COAL)), 200, new RecipeOutput(new ItemStack(GlassTechItems.coalDust))));
        registry.register(NAMESPACE.id("bronze2dust"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.bronzeIngot)), 200, new RecipeOutput(new ItemStack(GlassTechItems.bronzeDust))));
        registry.register(NAMESPACE.id("cobble2sand"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Block.COBBLESTONE)), 200, new RecipeOutput(new ItemStack(Block.SAND))));
        registry.register(NAMESPACE.id("sandstone2sand"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Block.SANDSTONE)), 200, new RecipeOutput(new ItemStack(Block.SAND))));
        registry.register(NAMESPACE.id("wool2string"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Block.WOOL)), 200, new RecipeOutput(new ItemStack(Item.STRING, 2))));
        registry.register(NAMESPACE.id("gravel2flint"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Block.GRAVEL)), 200, new RecipeOutput(new ItemStack(Item.FLINT, 1))));
        registry.register(NAMESPACE.id("ice2snowball"), new BasicMachineRecipe(new StackRecipeInput(new ItemStack(Block.ICE)), 200, new RecipeOutput(new ItemStack(Item.SNOWBALL))));
    }
}
