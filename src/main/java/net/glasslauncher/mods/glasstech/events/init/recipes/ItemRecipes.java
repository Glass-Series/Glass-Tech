package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

import static net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks.copperInsulatedCableBlock;
import static net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks.machineBlock;
import static net.glasslauncher.mods.glasstech.events.init.GlassTechItems.*;
import static net.glasslauncher.mods.glasstech.util.TagHelper.tagKey;

public class ItemRecipes {
    public static void initRecipes() {
        add(reactorCooler, "ICI", "OPO", 'O', coolantCell, 'C', advancedCircuit, 'I', tagKey("ingots/copper"), 'P', reactorPlating);
        add(reactorPlating, " C ", "CAC", " C ", 'C', tagKey("ingots/copper"), 'A', advancedAlloy);
        add(circuit, "CCC", "RIR", "CCC", 'I', tagKey("ingots/copper"), 'R', tagKey("dusts/redstone"), 'C', copperInsulatedCableBlock);
        add(reBattery, " C ", "TRT", "TRT", 'T', tagKey("ingots/tin"), 'R', tagKey("dusts/redstone"), 'C', copperInsulatedCableBlock);
        addN(emptyCell, 16, " T ", "T T", " T ", 'T', tagKey("ingots/tin"));
        add(advancedCircuit, "RGR", "LCL", "RGR", 'L', new ItemStack(Item.DYE, 1, 4), 'G', tagKey("dusts/glowstone"), 'R', tagKey("dusts/redstone"), 'C', circuit);
        add(emptyFuelCan, " TT", "T T", "TTT", 'T', tagKey("ingots/tin"));
        add(dynamiteRemote, " C ", "TLT", " F ", 'C', copperInsulatedCableBlock, 'F', frequencyTransmitter, 'L', new ItemStack(Item.DYE, 1, 4), 'T', tagKey("ingots/tin"));
        add(mixedMetalIngot, "III", "BBB", "TTT", 'I', refinedIronIngot, 'B', tagKey("ingots/bronze"), 'T', tagKey("ingots/tin"));
        addN(depletedUraniumCell, 8, "CCC", "CUC", "CCC", 'C', emptyCell, 'U', tagKey("ingots/uranium"));
        addS(uraniumCell, emptyCell, tagKey("ingots/uranium"));
        addSN(refinedIronIngot, 8, machineBlock);
        addN(suBattery, 5, "C", "R", "D", 'D', tagKey("dusts/coal"), 'R', tagKey("dusts/redstone"), 'C', tagKey("ingots/copper"));
        add(energyCrystal, "RRR", "RDR", "RRR", 'D', tagKey("gems/diamond"), 'R', tagKey("dusts/redstone"));
        add(lapotronCrystal, "LCL", "LDL", "LCL", 'D', energyCrystal, 'C', circuit, 'L', new ItemStack(Item.DYE, 1, 4));
        addN(hydratedCoalDust, 8, "CCC", "CWC", "CCC", 'C', tagKey("dusts/coal"), 'W', tagKey("buckets/water"));
        addN(hydratedCoalDust, 8, "CCC", "CWC", "CCC", 'C', tagKey("dusts/coal"), 'W', waterCell);
        add(carbonFibre, "CC", "CC", 'C', tagKey("dusts/coal"));
        addS(carbonMesh, carbonFibre, carbonFibre);
        addN(Item.GLOWSTONE_DUST, 4, "RGR", "GRG", "RGR", 'R', tagKey("dusts/redstone"), 'G', tagKey("dusts/gold"));
        addN(Item.GUNPOWDER, 3, "RCR", "CRC", "RCR", 'R', tagKey("dusts/redstone"), 'C', tagKey("dusts/coal"));
        addN(suBattery, 8, "c", "C", "R", 'R', tagKey("dusts/redstone"), 'C', tagKey("dusts/coal"), 'c', tagKey("ingots/copper"));
        addN(suBattery, 8, "c", "R", "C", 'R', tagKey("dusts/redstone"), 'C', tagKey("dusts/coal"), 'c', tagKey("ingots/copper"));
        addSN(bronzeDust, 2, tagKey("dusts/tin"), tagKey("dusts/copper"), tagKey("dusts/copper"));

        addS(uraniumCell, reEnrichedUraniumCell, tagKey("dusts/coal"));
        addS(bioCell, emptyCell, compressedPlantBall);
        addS(hydratedCoalCell, emptyCell, compressedHydratedCoalDust);
        addS(waterCell, emptyCell, tagKey("buckets/water"));
        addS(lavaCell, emptyCell, tagKey("buckets/lava"));
        addS(Block.OBSIDIAN, waterCell, waterCell, lavaCell, lavaCell);
        addS(hydratedCoalDust, tagKey("dusts/coal"), tagKey("buckets/water"));
        addS(hydratedCoalDust, tagKey("dusts/coal"), waterCell);
        add(sail, "SWW", "SWW", "SWW", 'S', tagKey("sticks"), 'W', tagKey("wools"));

        addS(new ItemStack(nearDepletedUraniumCell, 1, 9999), new ItemStack(nearDepletedUraniumCell), tagKey("dusts/coal"));

        add(iridiumPlate, "IAI", "ADA", "IAI", 'I', iridium, 'A', advancedAlloy, 'D', tagKey("gems/diamond"));

        add(scrapBox, "SSS", "SSS", "SSS", 'S', scrap);

        add(coalBall, "CCC", "CFC", "CCC", 'C', tagKey("dusts/coal"), 'F', Item.FLINT);
        add(coalChunk, "BBB", "BOB", "BBB", 'B', compressedCoalBall, 'O', tagKey("obsidians"));
        add(coalChunk, "BBB", "BOB", "BBB", 'B', compressedCoalBall, 'O', tagKey("storage_blocks/iron"));
        add(coalChunk, "BBB", "BOB", "BBB", 'B', compressedCoalBall, 'O', tagKey("brick_blocks"));

        plantBall();
    }

    private static void plantBall() {
        addN(plantBall, 2, "PPP", "P P", "PPP", 'P', tagKey("saplings"));
        addN(plantBall, 1, "PPP", "P P", "PPP", 'P', tagKey("crops"));
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
