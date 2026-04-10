package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

import static net.glasslauncher.mods.glasstech.events.init.GlassTechItems.*;
import static net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks.*;

public class BlockRecipes {
    public static void initRecipes() {
        cables();
        ingots(refinedIronIngot, refinedIronBlock);
        ingots(copperIngot, copperBlock);
        ingots(tinIngot, tinBlock);
        ingots(uraniumIngot, uraniumBlock);
        materials();

        addN(miningPipeBlock, 8, "I I", "I I", "ITI", 'I', refinedIronIngot, 'T', treeTap);
        addN(refinedIronFenceBlock, 12, "III", "III", 'I', refinedIronIngot);

        add(machineBlock, " I ", "I I", "IFI", 'I', refinedIronIngot, 'F', Block.FURNACE);
        add(machineBlock, "III", "I I", "III", 'I', refinedIronIngot);
        add(advancedMachineBlock, " A ", "CMC", " A ", 'A', advancedAlloy, 'C', carbonPlate, 'M', machineBlock);

        add(generatorBlock, " B ", "III", " F ", 'I', refinedIronIngot, 'B', reBattery, 'F', ironFurnaceBlock);
        add(geothermalGeneratorBlock, "GCG", "GCG", "IEI", 'I', refinedIronIngot, 'G', Block.GLASS, 'E', generatorBlock);
        addN(waterGeneratorBlock, 2, "SPS", "PGP", "SPS", 'S', Item.STICK, 'P', Block.PLANKS, 'G', generatorBlock);
        add(solarGeneratorBlock, "CgC", "gCg", "cGc", 'G', generatorBlock, 'C', coalDust, 'g', Block.GLASS, 'c', circuit);
        // Maybe?
        add(windGeneratorBlock, "I I", " G ", "I I", 'I', Item.IRON_INGOT, 'G', machineBlock);

        add(lvTransformerBlock, "PCP", "ccc", "PCP", 'P', Block.PLANKS, 'C', copperInsulatedCableBlock, 'c', copperIngot);
        add(mvTransformerBlock, " C ", " M ", " C ", 'M', machineBlock, 'C', copperInsulatedCableBlock);
        add(hvTransformerBlock, " c ", "CED", " c ", 'E', mvTransformerBlock, 'c', copperInsulatedCableBlock, 'D', energyCrystal, 'C', circuit);

        add(batBoxBlock, "PCP", "BBB", "PPP", 'P', Block.PLANKS, 'C', copperInsulatedCableBlock, 'B', reBattery);
        add(energyStorageUnitBlock, "cCc", "CMC", "cCc", 'M', machineBlock, 'c', copperInsulatedCableBlock, 'C', energyCrystal);
        add(megaEnergyStorageUnitBlock, "LCL", "LML", "LAL", 'M', energyStorageUnitBlock, 'A', advancedMachineBlock, 'C', advancedCircuit, 'L', lapotronCrystal);

        add(recyclerBlock, " I ", "DCD", "IDI", 'I', refinedIronIngot, 'D', Block.DIRT, 'C', compressorBlock);
        add(inductionFurnaceBlock, "CCC", "CFC", "CMC", 'C', copperIngot, 'F', electricFurnaceBlock, 'M', advancedMachineBlock);
        add(teslaCoilBlock, "RRR", "RMR", "ICI", 'M', mvTransformerBlock, 'R', Item.REDSTONE, 'C', circuit, 'I', refinedIronIngot);
        addN(illuminatorBlock, 8, "ICI", "GTG", "GGG", 'G', Block.GLASS, 'I', refinedIronIngot, 'T', tinCableBlock, 'C', copperInsulatedCableBlock);
        add(cannerBlock, "TCT", "TMT", "TTT", 'T', tinIngot, 'M', machineBlock, 'C', circuit);
        add(cannerBlock, "TCT", "TMT", "TTT", 'T', tinIngot, 'M', machineBlock, 'C', circuit);
        add(electricFurnaceBlock, " C ", "RFR", 'C', circuit, 'R', Item.REDSTONE, 'F', ironFurnaceBlock);
        add(maceratorBlock, "FFF", "SMS", " C ", 'F', Item.FLINT, 'S', Block.COBBLESTONE, 'M', machineBlock, 'C', circuit);
        add(extractorBlock, "TMT", "TCT", 'T', treeTap, 'M', machineBlock, 'C', circuit);
        add(compressorBlock, "S S", "SMS", "SCS", 'S', Block.STONE, 'M', machineBlock, 'C', circuit);
        add(minerBlock, "CMC", " P ", " P ", 'P', miningPipeBlock, 'M', machineBlock, 'C', circuit);
        add(pumpBlock, "cCc", "cMc", "PTP", 'c', emptyCell, 'T', treeTap, 'P', miningPipeBlock, 'M', machineBlock, 'C', circuit);
        add(magnetizerBlock, "RFR", "RMR", "RFR", 'R', Item.REDSTONE, 'F', refinedIronBlock, 'M', machineBlock);
        add(electrolyzerBlock, "c c", "cCc", "EME", 'E', emptyCell, 'c', copperInsulatedCableBlock, 'M', machineBlock, 'C', circuit);
        add(reactorChamberBlock, "ACA", "PMP", "APA", 'A', advancedAlloy, 'C', reactorCooler, 'P', reactorPlating, 'M', machineBlock);
        add(reactorCoreBlock, "AcA", "CGC", "AcA", 'A', advancedAlloy, 'C', reactorChamberBlock, 'c', advancedCircuit, 'G', generatorBlock);
        add(massFabricatorBlock, "GCG", "ALA", "GCG", 'A', advancedMachineBlock, 'L', lapotronCrystal, 'G', Item.GLOWSTONE_DUST, 'C', advancedCircuit);

        addN(woodenScaffoldBlock, 16, "PPP", " s ", "s s", 'P', Block.PLANKS, 's', Item.STICK);

        add(personalSafeBlock, "C", "M", "H", 'C', circuit, 'H', Block.CHEST, 'M', machineBlock);

        addN(industrialTNTBlock, 4, "FFF", "TTT", "FFF", 'F', Item.FLINT, 'T', Block.TNT);
        addN(industrialTNTBlock, 8, " c ", "GCG", "TTT", 'c', copperCableBlock, 'G', Item.GLOWSTONE_DUST, 'C', circuit, 'T', Block.TNT);
        add(nukeBlock, "GUG", "UGU", "GUG", 'G', Item.GUNPOWDER, 'U', uraniumIngot);

        add(teleporterBlock, "GFG", "CMC", "GDG", 'M', advancedMachineBlock, 'C', copperInsulatedCableBlock, 'F', frequencyTransmitter, 'G', Item.GLOWSTONE_DUST, 'D', industrialDiamond);
        add(teleporterBlock, "GFG", "CMC", "GDG", 'M', advancedMachineBlock, 'C', copperInsulatedCableBlock, 'F', frequencyTransmitter, 'G', Item.GLOWSTONE_DUST, 'D', Item.DIAMOND);

        addS(Block.STICKY_PISTON, Block.PISTON, resin);
        addSN(constructionFoamBlock, 3, clayDust, Item.WATER_BUCKET, Item.REDSTONE, coalDust);
        addSN(constructionFoamBlock, 3, clayDust, waterCell, Item.REDSTONE, coalDust);
        addN(rubberSheetBlock, 3, "RRR", "RRR", 'R', rubber);
        add(Block.TORCH, "R", "I", 'R', resin, 'I', Item.STICK);
    }

    private static void ingots(Item ingot, Block block) {
        add(block, "III", "III", "III", 'I', ingot);
        addSN(ingot, 9, block);
    }

    private static void cables() {
        addN(tinCableBlock, 9, "III", 'I', tinIngot);
        
        addN(copperCableBlock, 6, "III", 'I', copperIngot);
        addN(copperInsulatedCableBlock, 6, "RRR", "III", "RRR", 'I', copperIngot, 'R', rubber);
        addS(copperInsulatedCableBlock, copperCableBlock, rubber);
        
        addN(refinedIronCableBlock, 12, "III", 'I', refinedIronIngot);
        addN(refinedIronInsulatedCableBlock, 4, " R ", "RIR", " R ", 'I', refinedIronIngot, 'R', rubber);
        addS(refinedIronInsulatedCableBlock, refinedIronCableBlock, rubber);
        addS(refinedIronInsulatedX2CableBlock, refinedIronInsulatedCableBlock, rubber);
        addS(refinedIronInsulatedX3CableBlock, refinedIronCableBlock, rubber, rubber, rubber);
        addS(refinedIronInsulatedX3CableBlock, refinedIronInsulatedCableBlock, rubber, rubber);
        addS(refinedIronInsulatedX3CableBlock, refinedIronInsulatedX2CableBlock, rubber);

        addN(goldCableBlock, 12, "III", 'I', Item.GOLD_INGOT);
        addN(goldInsulatedCableBlock, 4, " R ", "RIR", " R ", 'I', Item.GOLD_INGOT, 'R', rubber);
        addS(goldInsulatedX2CableBlock, goldCableBlock, rubber, rubber);
        addS(goldInsulatedX2CableBlock, goldInsulatedCableBlock, rubber);

        addN(goldInsulatedCableBlock, 16, "GGG", "RDR", "GGG", 'D', Item.DIAMOND, 'R', Item.REDSTONE, 'G', Block.GLASS);
        addN(goldInsulatedCableBlock, 16, "GGG", "RDR", "GGG", 'D', industrialDiamond, 'R', Item.REDSTONE, 'G', Block.GLASS);
    }

    public static void materials() {
        addSN(Block.PLANKS, 3, rubberLogBlock);

        addN(reinforcedStoneBlock, 8, "SSS", "SAS", "SSS", 'S', Block.STONE, 'A', advancedAlloy);
        addN(reinforcedGlassBlock, 7, "GAG", "GGG", "GAG", 'G', Block.GLASS, 'A', advancedAlloy);
        add(reinforcedDoorBlock, "SS", "SS", "SS", 'S', reinforcedStoneBlock);
    }

    private static void addN(Block block, int number, Object... recipe) {
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

    private static void addSN(Block block, int number, Object... recipe) {
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
