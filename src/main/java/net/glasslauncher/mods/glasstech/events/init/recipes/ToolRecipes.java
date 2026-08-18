package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

import static net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks.*;
import static net.glasslauncher.mods.glasstech.events.init.GlassTechItems.*;
import static net.glasslauncher.mods.glasstech.util.TagHelper.tagKey;

public class ToolRecipes {

    public static void initRecipes() {
        add(treeTap, " P ", "PPP", "P  ", 'P', tagKey("planks"));
        add(painter, " CC", " IC", "I  ", 'C', tagKey("wools"), 'I', tagKey("ingots/iron"));
        add(drill, " I ", "ICI", "IBI", 'I', tagKey("ingots/steel"), 'B', reBattery, 'C', circuit);
        add(diamondDrill, " D ", "DdD", 'D', tagKey("gems/diamond"), 'd', drill);
        add(chainsaw, " II", "ICI", "BI ", 'I', tagKey("ingots/steel"), 'B', reBattery, 'C', circuit);
        add(diamondChainsaw, " D ", "DdD", 'D', tagKey("gems/diamond"), 'd', chainsaw);
        add(scanner, " G ", "CBC", "ccc", 'B', reBattery, 'c', copperCableBlock, 'G', tagKey("dusts/glowstone"), 'C', circuit);
        add(advancedScanner, " G ", "GCG", "cSc", 'S', scanner, 'c', copperCableBlock, 'G', tagKey("dusts/glowstone"), 'C', advancedCircuit);
        add(electricWrench, "  W", " C ", "B  ", 'W', wrench, 'B', reBattery, 'C', circuit);
        add(nanoHelmet, "CcC", "CGC", 'C', carbonPlate, 'c', energyCrystal, 'G', tagKey("glass_blocks"));
        add(nanoChestplate, "C C", "CcC", "CCC", 'C', carbonPlate, 'c', energyCrystal);
        add(nanoLeggings, "CcC", "C C", "C C", 'C', carbonPlate, 'c', energyCrystal);
        add(nanoBoots, "C C", "CcC", 'C', carbonPlate, 'c', energyCrystal);
        add(nanoSaber, "GA ", "GA ", "CcC", 'C', carbonPlate, 'c', energyCrystal, 'G', tagKey("dusts/glowstone"), 'A', advancedAlloy);
        addN(dynamiteBlock, 8, "S", "T", 'S', tagKey("strings"), 'T', industrialTNTBlock);
        addN(stickyDynamite, 8, "DDD", "DRD", "DDD", 'D', dynamiteBlock, 'R', tagKey("resins"));
        add(rubberBoots, "R R", "R R", "RCR", 'R', tagKey("rubbers"), 'C', tagKey("wools"));
        add(voltageMeter, " G ", "cCc", "c c", 'G', tagKey("dusts/glowstone"), 'c', copperCableBlock, 'C', circuit);
        add(miningLaser, "Rcc", "AAC", " AA", 'A', advancedAlloy, 'C', advancedCircuit, 'c', energyCrystal, 'R', tagKey("dusts/redstone"));
        add(cfSprayer, "SS ", "Ss ", "  S", 'S', tagKey("cobblestones"), 's', tagKey("sticks/wooden"));
        add(quantumHelmet, "ILI", "CGC", 'I', iridiumPlate, 'L', lapotronCrystal, 'G', reinforcedGlassBlock, 'C', advancedCircuit);
        add(quantumChestplate, "A A", "ILI", "IAI", 'I', iridiumPlate, 'L', lapotronCrystal, 'A', advancedAlloy);
        add(quantumLeggings, "MLM", "I I", "G G", 'I', iridiumPlate, 'L', lapotronCrystal, 'G', tagKey("dusts/glowstone"), 'M', machineBlock);
        add(quantumBoots, "I I", "RLR", 'I', iridiumPlate, 'L', lapotronCrystal, 'R', rubberBoots);
        add(Item.DIAMOND_PICKAXE, "DDD", " S ", " S ", 'S', tagKey("sticks/wooden"), 'D', industrialDiamond);
        add(Item.DIAMOND_HOE, "DD ", " S ", " S ", 'S', tagKey("sticks/wooden"), 'D', industrialDiamond);
        add(Item.DIAMOND_SHOVEL, "D", "S", "S", 'S', tagKey("sticks/wooden"), 'D', industrialDiamond);
        add(Item.DIAMOND_AXE, "DD ", "DS ", " S ", 'S', tagKey("sticks/wooden"), 'D', industrialDiamond);
        add(Item.DIAMOND_SWORD, "D", "D", "S", 'S', tagKey("sticks/wooden"), 'D', industrialDiamond);
        add(frequencyTransmitter, "c", "C", "C", 'C', circuit, 'c', copperCableBlock);
        add(batteryPack, "BCB", "BTB", "B B", 'T', tagKey("ingots/tin"), 'C', circuit, 'B', reBattery);
        add(lappack, "LAL", "LBL", "L L", 'L', tagKey("storage_blocks/lapis"), 'A', advancedCircuit, 'B', batteryPack);
        add(wireCutter, "A A", " A ", "I I", 'A', tagKey("ingots/steel"), 'I', tagKey("ingots/iron"));
        add(bronzePickaxe, "BBB", " S ", " S ", 'B', tagKey("ingots/bronze"), 'S', tagKey("sticks/wooden"));
        add(bronzeAxe, "BB", "SB", "S ", 'B', tagKey("ingots/bronze"), 'S', tagKey("sticks/wooden"));
        add(bronzeHoe, "BB", "S ", "S ", 'B', tagKey("ingots/bronze"), 'S', tagKey("sticks/wooden"));
        add(bronzeSword, "B", "B", "S", 'B', tagKey("ingots/bronze"), 'S', tagKey("sticks/wooden"));
        add(bronzeShovel, " B ", " S ", " S ", 'B', tagKey("ingots/bronze"), 'S', tagKey("sticks/wooden"));
        add(bronzeHelmet, "BBB", "B B", 'B', tagKey("ingots/bronze"));
        add(bronzeChestplate, "B B", "BBB", "BBB", 'B', tagKey("ingots/bronze"));
        add(bronzeLeggings, "BBB", "B B", "B B", 'B', tagKey("ingots/bronze"));
        add(bronzeBoots, "B B", "B B", 'B', tagKey("ingots/bronze"));
        add(wrench, "B B", "BBB", " B ", 'B', tagKey("ingots/bronze"));
        add(jetpack, "ICI", "IFI", "R R", 'I', tagKey("ingots/bronze"), 'C', circuit, 'F', emptyFuelCan, 'R', tagKey("dusts/redstone"));
        add(electricJetpack, "ICI", "IBI", "G G", 'I', tagKey("ingots/bronze"), 'C', advancedCircuit, 'B', batBoxBlock, 'G', tagKey("dusts/glowstone"));
        add(cfPack, "SCS", "FTF", "F F", 'T', tagKey("ingots/tin"), 'C', circuit, 'F', emptyFuelCan, 'S', cfSprayer);
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
