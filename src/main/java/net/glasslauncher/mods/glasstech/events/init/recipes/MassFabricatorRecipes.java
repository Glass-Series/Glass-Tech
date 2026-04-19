package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

import static net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks.copperOreBlock;
import static net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks.tinOreBlock;
import static net.glasslauncher.mods.glasstech.events.init.GlassTechItems.*;

public class MassFabricatorRecipes {
    public static void initRecipes() {
        add(new ItemStack(Block.STONE, 16), "   ", " M ", "   ", 'M', uuMatter);
        add(new ItemStack(Block.GLASS, 32), " M ", "M M", " M ", 'M', uuMatter);
        add(new ItemStack(Block.GRASS, 16), "   ", "M  ", "M  ", 'M', uuMatter);
        add(new ItemStack(Block.MOSSY_COBBLESTONE, 16), "   ", " M ", "M M", 'M', uuMatter);
        add(new ItemStack(Block.SANDSTONE, 16), "   ", "  M", " M ", 'M', uuMatter);
        add(new ItemStack(Block.SNOW_BLOCK, 4), "M M", "   ", "   ", 'M', uuMatter);
        add(new ItemStack(Block.WATER, 1), "   ", " M ", " M ", 'M', uuMatter);
        add(new ItemStack(Block.LAVA, 1), " M ", " M ", " M ", 'M', uuMatter);
        add(new ItemStack(Item.COAL, 2), "  M", "M  ", "  M", 'M', uuMatter);
        add(new ItemStack(Block.IRON_ORE, 2), "M M", " M ", "M M", 'M', uuMatter);
        add(new ItemStack(Block.GOLD_ORE, 2), " M ", "MMM", " M ", 'M', uuMatter);
        add(new ItemStack(Item.DIAMOND, 1), "MMM", "MMM", "MMM", 'M', uuMatter);
        add(new ItemStack(Item.REDSTONE, 24), "   ", " M ", "MMM", 'M', uuMatter);
        add(new ItemStack(copperOreBlock, 5), "  M", "M M", "   ", 'M', uuMatter);
        add(new ItemStack(tinOreBlock, 5), "   ", "M M", "  M", 'M', uuMatter);
        add(new ItemStack(Block.OBSIDIAN, 12), "M M", "M M", "   ", 'M', uuMatter);
        add(new ItemStack(Block.NETHERRACK, 16), "  M", " M ", "M  ", 'M', uuMatter);
        add(new ItemStack(Block.GLOWSTONE, 8), " M ", "M M", "MMM", 'M', uuMatter);
        add(new ItemStack(Block.LOG, 8), " M ", "   ", "   ", 'M', uuMatter);
        add(new ItemStack(Item.DYE, 9, 4), " M ", " M ", " MM", 'M', uuMatter);
        add(new ItemStack(Item.FEATHER, 32), " M ", " M ", "M M", 'M', uuMatter);
        add(new ItemStack(Item.SNOWBALL, 16), "   ", "   ", "MMM", 'M', uuMatter);
        add(new ItemStack(Item.GUNPOWDER, 15), "MMM", "M  ", "MMM", 'M', uuMatter);
        add(new ItemStack(iridium, 1), "MMM", " M ", "MMM", 'M', uuMatter);
        add(new ItemStack(Item.CLAY, 48), "MM ", "M  ", "MM ", 'M', uuMatter);
        add(new ItemStack(Item.DYE, 32, 3), "MM ", "  M", "MM ", 'M', uuMatter);
        add(new ItemStack(Item.DYE, 48, 0), " MM", " MM", " M ", 'M', uuMatter);
        add(new ItemStack(resin, 21, 0), "M M", "   ", "M M", 'M', uuMatter);
        add(new ItemStack(Block.CACTUS, 48), " M ", "MMM", "M M", 'M', uuMatter);
        add(new ItemStack(Block.SUGAR_CANE, 48), "M M", "M M", "M M", 'M', uuMatter);
        add(new ItemStack(Item.FLINT, 32), " M ", "MM ", "MM ", 'M', uuMatter);
        add(new ItemStack(Block.WOOL, 12), "M M", "   ", " M ", 'M', uuMatter);
    }

    private static void add(Block block, int number, Object... recipe) {
        CraftingRegistry.addShapedRecipe(new ItemStack(block, number), recipe);
    }

    private static void add(Item item, int number, Object... recipe) {
        CraftingRegistry.addShapedRecipe(new ItemStack(item, number), recipe);
    }

    private static void add(Block block, Object... recipe) {
        CraftingRegistry.addShapedRecipe(new ItemStack(block), recipe);
    }

    private static void add(Item item, Object... recipe) {
        CraftingRegistry.addShapedRecipe(new ItemStack(item), recipe);
    }

    private static void add(ItemStack item, Object... recipe) {
        CraftingRegistry.addShapedRecipe(item, recipe);
    }

    private static void addS(Block block, int number, Object... recipe) {
        CraftingRegistry.addShapelessRecipe(new ItemStack(block, number), recipe);
    }

    private static void addS(Item item, int number, Object... recipe) {
        CraftingRegistry.addShapelessRecipe(new ItemStack(item, number), recipe);
    }

    private static void addS(Block block, Object... recipe) {
        CraftingRegistry.addShapelessRecipe(new ItemStack(block), recipe);
    }

    private static void addS(Item item, Object... recipe) {
        CraftingRegistry.addShapelessRecipe(new ItemStack(item), recipe);
    }

    private static void addS(ItemStack item, Object... recipe) {
        CraftingRegistry.addShapelessRecipe(item, recipe);
    }
}
