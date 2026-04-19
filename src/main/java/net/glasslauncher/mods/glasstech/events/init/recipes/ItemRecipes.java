package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

import static net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks.copperInsulatedCableBlock;
import static net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks.machineBlock;
import static net.glasslauncher.mods.glasstech.events.init.GlassTechItems.*;

public class ItemRecipes {
    public static void initRecipes() {
        add(reactorCooler, "ICI", "OPO", 'O', coolantCell, 'C', advancedCircuit, 'I', copperIngot, 'P', reactorPlating);
        add(reactorPlating, " C ", "CAC", " C ", 'C', copperIngot, 'A', advancedAlloy);
        add(circuit, "CCC", "RIR", "CCC", 'I', copperIngot, 'R', Item.REDSTONE, 'C', copperInsulatedCableBlock);
        add(reBattery, " C ", "TRT", "TRT", 'T', tinIngot, 'R', Item.REDSTONE, 'C', copperInsulatedCableBlock);
        addN(emptyCell, 16, " T ", "T T", " T ", 'T', tinIngot);
        add(advancedCircuit, "RGR", "LCL", "RGR", 'L', new ItemStack(Item.DYE, 1, 4), 'G', Item.GLOWSTONE_DUST, 'R', Item.REDSTONE, 'C', circuit);
        add(emptyFuelCan, " TT", "T T", "TTT", 'T', tinIngot);
        add(dynamiteRemote, " C ", "TLT", " F ", 'C', copperInsulatedCableBlock, 'F', frequencyTransmitter, 'L', new ItemStack(Item.DYE, 1, 4), 'T', tinIngot);
        add(mixedMetalIngot, "III", "BBB", "TTT", 'I', refinedIronIngot, 'B', bronzeIngot, 'T', tinIngot);
        addN(depletedUraniumCell, 8, "CCC", "CUC", "CCC", 'C', emptyCell, 'U', uraniumIngot);
        addS(uraniumCell, emptyCell, uraniumIngot);
        addSN(refinedIronIngot, 8, machineBlock);
        addN(suBattery, 5, "C", "R", "D", 'D', coalDust, 'R', Item.REDSTONE, 'C', copperInsulatedCableBlock);
        add(energyCrystal, "RRR", "RDR", "RRR", 'D', Item.DIAMOND, 'R', Item.REDSTONE);
        add(energyCrystal, "RRR", "RDR", "RRR", 'D', industrialDiamond, 'R', Item.REDSTONE);
        add(lapotronCrystal, "LCL", "LDL", "LCL", 'D', energyCrystal, 'C', circuit, 'L', new ItemStack(Item.DYE, 1, 4));
        addN(hydratedCoalDust, 8, "CCC", "CWC", "CCC", 'C', coalDust, 'W', Item.WATER_BUCKET);
        addN(hydratedCoalDust, 8, "CCC", "CWC", "CCC", 'C', coalDust, 'W', waterCell);
        add(carbonFibre, "CC", "CC", 'C', coalDust);
        addS(carbonMesh, carbonFibre, carbonFibre);
        addN(Item.GLOWSTONE_DUST, 4, "RGR", "GRG", "RGR", 'R', Item.REDSTONE, 'G', goldDust);
        addN(Item.GUNPOWDER, 3, "RCR", "CRC", "RCR", 'R', Item.REDSTONE, 'C', coalDust);
        addN(suBattery, 8, "c", "C", "R", 'R', Item.REDSTONE, 'C', coalDust, 'c', copperInsulatedCableBlock);
        addN(suBattery, 8, "c", "R", "C", 'R', Item.REDSTONE, 'C', coalDust, 'c', copperInsulatedCableBlock);
        addSN(bronzeDust, 2, tinDust, copperDust, copperDust, copperDust);

        addS(uraniumCell, reEnrichedUraniumCell, coalDust);
        addS(bioCell, emptyCell, compressedPlantBall);
        addS(coalfuelCell, emptyCell, compressedHydratedCoalDust);
        addS(waterCell, emptyCell, Item.WATER_BUCKET);
        addS(lavaCell, emptyCell, Item.LAVA_BUCKET);
        addS(Block.OBSIDIAN, waterCell, waterCell, lavaCell, lavaCell);
        addS(hydratedCoalDust, coalDust, Item.WATER_BUCKET);
        addS(hydratedCoalDust, coalDust, waterCell);

        addS(new ItemStack(itemCellUranDepleted, 1, 9999), new ItemStack(itemCellUranEmpty), coalDust);

        add(iridiumPlate, "IAI", "ADA", "IAI", 'I', iridium, 'A', advancedAlloy, 'D', Item.DIAMOND);
        add(iridiumPlate, "IAI", "ADA", "IAI", 'I', iridium, 'A', advancedAlloy, 'D', industrialDiamond);

        add(scrapBox, "SSS", "SSS", "SSS", 'S', scrap);

        add(coalChunk, "BBB", "BOB", "BBB", 'B', compressedCoalBall, 'O', Block.OBSIDIAN);
        add(coalChunk, "BBB", "BOB", "BBB", 'B', compressedCoalBall, 'O', Block.IRON_BLOCK);
        add(coalChunk, "BBB", "BOB", "BBB", 'B', compressedCoalBall, 'O', Block.BRICKS);

        plantBall();
    }

    private static void plantBall() {
        addN(plantBall, 2, "PPP", "P P", "PPP", 'P', Block.SAPLING);
        addN(plantBall, 2, "PPP", "P P", "PPP", 'P', rubberSapling);
        addN(plantBall, 1, "PPP", "P P", "PPP", 'P', Item.WHEAT);
        addN(plantBall, 1, "PPP", "P P", "PPP", 'P', Item.SUGAR_CANE);
        addN(plantBall, 1, "PPP", "P P", "PPP", 'P', Block.CACTUS);
    }

    private static void add(Block block, int number, Object... recipe) {
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

    private static void addSN(Item item, int number, Object... recipe) {
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
