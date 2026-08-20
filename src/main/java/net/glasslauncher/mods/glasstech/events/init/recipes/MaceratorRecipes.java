package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.MaceratorRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.TagRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;
import static net.glasslauncher.mods.glasstech.util.TagHelper.tagKey;

public class MaceratorRecipes {
    public static void initRecipes() {
        MaceratorRecipeRegistry registry = MaceratorRecipeRegistry.INSTANCE;
        registry.register(NAMESPACE.id("refinediron2dust"), new MaceratorRecipe(new StackRecipeInput(new ItemStack(GlassTechItems.refinedIronIngot)), new RecipeOutput(new ItemStack(GlassTechItems.ironDust))));
        registry.register(NAMESPACE.id("iron2dust"), new MaceratorRecipe(new TagRecipeInput(tagKey("ingots/iron")), new RecipeOutput(new ItemStack(GlassTechItems.ironDust))));
        registry.register(NAMESPACE.id("gold2dust"), new MaceratorRecipe(new TagRecipeInput(tagKey("ingots/gold")), new RecipeOutput(new ItemStack(GlassTechItems.goldDust))));
        registry.register(NAMESPACE.id("tin2dust"), new MaceratorRecipe(new TagRecipeInput(tagKey("ingots/tin")), new RecipeOutput(new ItemStack(GlassTechItems.tinDust))));
        registry.register(NAMESPACE.id("copper2dust"), new MaceratorRecipe(new TagRecipeInput(tagKey("ingots/copper")), new RecipeOutput(new ItemStack(GlassTechItems.copperDust))));
        registry.register(NAMESPACE.id("ironore2dust"), new MaceratorRecipe(new TagRecipeInput(tagKey("ores/iron")), new RecipeOutput(new ItemStack(GlassTechItems.ironDust, 2))));
        registry.register(NAMESPACE.id("goldore2dust"), new MaceratorRecipe(new TagRecipeInput(tagKey("ores/gold")), new RecipeOutput(new ItemStack(GlassTechItems.goldDust, 2))));
        registry.register(NAMESPACE.id("tinore2dust"), new MaceratorRecipe(new TagRecipeInput(tagKey("ores/tin")), new RecipeOutput(new ItemStack(GlassTechItems.tinDust, 2))));
        registry.register(NAMESPACE.id("copperore2dust"), new MaceratorRecipe(new TagRecipeInput(tagKey("ores/copper")), new RecipeOutput(new ItemStack(GlassTechItems.copperDust, 2))));
        registry.register(NAMESPACE.id("coal2dust"), new MaceratorRecipe(new TagRecipeInput(tagKey("coals")), new RecipeOutput(new ItemStack(GlassTechItems.coalDust))));
        registry.register(NAMESPACE.id("bronze2dust"), new MaceratorRecipe(new TagRecipeInput(tagKey("ingots/bronze")), new RecipeOutput(new ItemStack(GlassTechItems.bronzeDust))));
        registry.register(NAMESPACE.id("cobble2sand"), new MaceratorRecipe(new TagRecipeInput(tagKey("cobblestones/normal")), new RecipeOutput(new ItemStack(Block.SAND))));
        registry.register(NAMESPACE.id("sandstone2sand"), new MaceratorRecipe(new TagRecipeInput(tagKey("sandstone/blocks")), new RecipeOutput(new ItemStack(Block.SAND))));
        registry.register(NAMESPACE.id("wool2string"), new MaceratorRecipe(new TagRecipeInput(tagKey("wools")), new RecipeOutput(new ItemStack(Item.STRING, 2))));
        registry.register(NAMESPACE.id("gravel2flint"), new MaceratorRecipe(new TagRecipeInput(tagKey("gravels")), new RecipeOutput(new ItemStack(Item.FLINT, 1))));
        registry.register(NAMESPACE.id("ice2snowball"), new MaceratorRecipe(new TagRecipeInput(tagKey("snow")), new RecipeOutput(new ItemStack(Item.SNOWBALL))));
    }

    public static class MaceratorRecipe extends BasicMachineRecipe {
        public MaceratorRecipe(RecipeInput[] inputs, RecipeOutput[] outputs) {
            super(inputs, outputs);
        }

        public MaceratorRecipe(RecipeInput[] inputs, RecipeOutput[] outputs, int time) {
            super(inputs, outputs, time);
        }

        public MaceratorRecipe(RecipeInput input, int time, RecipeOutput output) {
            super(input, time, output);
        }

        public MaceratorRecipe(RecipeInput input, RecipeOutput output) {
            super(input, output);
        }
    }
}
