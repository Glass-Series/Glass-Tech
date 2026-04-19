package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

import static net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks.*;
import static net.glasslauncher.mods.glasstech.events.init.GlassTechItems.*;

public class ToolRecipes {

    public static void initRecipes() {
        add(treeTap, " P ", "PPP", "P  ", 'P', Block.PLANKS);
        add(painter, " CC", " IC", "I  ", 'C', Block.WOOL, 'I', Item.IRON_INGOT);
        add(drill, " D ", "DdD", 'D', Item.DIAMOND, 'd', drill);
        add(diamondDrill, " D ", "DdD", 'D', industrialDiamond, 'd', drill);
        add(chainsaw, " II", "ICI", "BI ", 'I', refinedIronIngot, 'B', reBattery, 'C', circuit);
        add(diamondChainsaw, " D ", "DdD", 'D', industrialDiamond, 'd', chainsaw);
        add(scanner, " G ", "CBC", "ccc", 'B', reBattery, 'c', copperCableBlock, 'G', Item.GLOWSTONE_DUST, 'C', circuit);
        add(advancedScanner, " G ", "GCG", "cSc", 'S', scanner, 'c', copperCableBlock, 'G', Item.GLOWSTONE_DUST, 'C', advancedCircuit);
        add(electricWrench, "  W", " C ", "B  ", 'W', wrench, 'B', reBattery, 'C', circuit);
        add(nanoHelmet, "CcC", "CGC", 'C', carbonPlate, 'c', energyCrystal, 'G', Block.GLASS);
        add(nanoChestplate, "C C", "CcC", "CCC", 'C', carbonPlate, 'c', energyCrystal);
        add(nanoLeggings, "CcC", "C C", "C C", 'C', carbonPlate, 'c', energyCrystal);
        add(nanoBoots, "C C", "CcC", 'C', carbonPlate, 'c', energyCrystal);
        add(nanoSaber, "GA ", "GA ", "CcC", 'C', carbonPlate, 'c', energyCrystal, 'G', Item.GLOWSTONE_DUST, 'A', advancedAlloy);
        addN(dynamiteBlock, 8, "S", "T", 'S', Item.STRING, 'T', industrialTNTBlock);
        addN(stickyDynamite, 8, "DDD", "DRD", "DDD", 'D', dynamiteBlock, 'R', resin);
        add(rubberBoots, "R R", "R R", "RCR", 'R', rubber, 'C', Block.WOOL);
        add(voltageMeter, " G ", "cCc", "c c", 'G', Item.GLOWSTONE_DUST, 'c', copperCableBlock, 'C', circuit);
        add(miningLaser, "Rcc", "AAC", " AA", 'A', advancedAlloy, 'C', advancedCircuit, 'c', energyCrystal, 'R', Item.REDSTONE);
        add(cfSprayer, "SS ", "Ss ", "  S", 'S', Block.COBBLESTONE, 's', Item.STICK);
        add(quantumHelmet, "ILI", "CGC", 'I', iridiumPlate, 'L', lapotronCrystal, 'G', reinforcedGlassBlock, 'C', advancedCircuit);
        add(quantumChestplate, "A A", "ILI", "IAI", 'I', iridiumPlate, 'L', lapotronCrystal, 'A', advancedAlloy);
        add(quantumLeggings, "MLM", "I I", "G G", 'I', iridiumPlate, 'L', lapotronCrystal, 'G', Item.GLOWSTONE_DUST, 'M', machineBlock);
        add(quantumBoots, "I I", "RLR", 'I', iridiumPlate, 'L', lapotronCrystal, 'R', rubberBoots);
        add(Item.DIAMOND_PICKAXE, "DDD", " S ", " S ", 'S', Item.STICK, 'D', industrialDiamond);
        add(Item.DIAMOND_HOE, "DD ", " S ", " S ", 'S', Item.STICK, 'D', industrialDiamond);
        add(Item.DIAMOND_SHOVEL, "D", "S", "S", 'S', Item.STICK, 'D', industrialDiamond);
        add(Item.DIAMOND_AXE, "DD ", "DS ", " S ", 'S', Item.STICK, 'D', industrialDiamond);
        add(Item.DIAMOND_SWORD, "D", "D", "S", 'S', Item.STICK, 'D', industrialDiamond);
        add(frequencyTransmitter, "c", "C", "C", 'C', circuit, 'c', copperCableBlock);
        add(batteryPack, "BCB", "BTB", "B B", 'T', tinIngot, 'C', circuit, 'B', reBattery);
        add(lappack, "LAL", "LBL", "L L", 'L', Block.LAPIS_BLOCK, 'A', advancedCircuit, 'B', batteryPack);
        add(insulationCutter, "A A", " A ", "I I", 'A', refinedIronIngot, 'I', Item.IRON_INGOT);
        add(bronzePickaxe, "BBB", " S ", " S ", 'B', bronzeIngot, 'S', Item.STICK);
        add(bronzeAxe, "BB", "SB", "S ", 'B', bronzeIngot, 'S', Item.STICK);
        add(bronzeHoe, "BB", "S ", "S ", 'B', bronzeIngot, 'S', Item.STICK);
        add(bronzeSword, "B", "B", "S", 'B', bronzeIngot, 'S', Item.STICK);
        add(bronzeShovel, " B ", " S ", " S ", 'B', bronzeIngot, 'S', Item.STICK);
        add(bronzeHelmet, "BBB", "B B", 'B', bronzeIngot);
        add(bronzeChestplate, "B B", "BBB", "BBB", 'B', bronzeIngot);
        add(bronzeLeggings, "BBB", "B B", "B B", 'B', bronzeIngot);
        add(bronzeBoots, "B B", "B B", 'B', bronzeIngot);
        add(wrench, "B B", "BBB", " B ", 'B', bronzeIngot);
        add(jetpack, "ICI", "IFI", "R R", 'I', bronzeIngot, 'C', circuit, 'F', emptyFuelCan, 'R', Item.REDSTONE);
        add(electricJetpack, "ICI", "IBI", "G G", 'I', bronzeIngot, 'C', advancedCircuit, 'B', batBoxBlock, 'G', Item.GLOWSTONE_DUST);
        add(cfPack, "SCS", "FTF", "F F", 'T', tinIngot, 'C', circuit, 'F', emptyFuelCan, 'S', cfSprayer);
        add(compositeChestplate , "A A", "ALA", "AIA", 'L', Item.LEATHER_CHESTPLATE, 'I', Item.IRON_CHESTPLATE, 'A', advancedAlloy);

        addS(cfSprayer, cfSprayer, cfPellet);
    }

    private static void addN(Block block, int number, Object... recipe) {
        CraftingRegistry.addShapedRecipe(new ItemStack(block, number), recipe);
    }

    private static void addN(Item item, int number, Object... recipe) {
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
