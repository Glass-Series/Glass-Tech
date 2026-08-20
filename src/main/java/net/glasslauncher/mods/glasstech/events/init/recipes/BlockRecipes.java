package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

import static net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks.*;
import static net.glasslauncher.mods.glasstech.events.init.GlassTechItems.*;
import static net.glasslauncher.mods.glasstech.util.TagHelper.tagKey;

public class BlockRecipes {
    public static void initRecipes() {
        cables();
        add(copperBlock, "III", "III", "III", 'I', tagKey("ingots/copper"));
        addSN(copperIngot, 9, tagKey("storage_blocks/copper"));
        add(tinBlock, "III", "III", "III", 'I', tagKey("ingots/tin"));
        addSN(tinIngot, 9, tagKey("storage_blocks/tin"));
        add(bronzeBlock, "III", "III", "III", 'I', tagKey("ingots/bronze"));
        addSN(bronzeIngot, 9, tagKey("storage_blocks/bronze"));
        add(uraniumBlock, "III", "III", "III", 'I', tagKey("ingots/uranium"));
        addSN(uraniumIngot, 9, tagKey("storage_blocks/uranium"));
        materials();

        addN(miningPipeBlock, 8, "I I", "I I", "ITI", 'I', tagKey("ingots/steel"), 'T', treeTap);
        addN(refinedIronFenceBlock, 12, "III", "III", 'I', tagKey("ingots/steel"));

        add(ironFurnaceBlock, " I ", "I I", "IFI", 'I', tagKey("ingots/iron"), 'F', Block.FURNACE);
        add(machineBlock, "III", "I I", "III", 'I', tagKey("ingots/steel"));
        add(advancedMachineBlock, " A ", "CMC", " A ", 'A', advancedAlloy, 'C', carbonPlate, 'M', machineBlock);

        add(generatorBlock, " B ", "III", " F ", 'I', tagKey("ingots/steel"), 'B', reBattery, 'F', ironFurnaceBlock);
        add(thermalGeneratorBlock, "GCG", "GCG", "IEI", 'I', tagKey("ingots/steel"), 'G', tagKey("glass_blocks"), 'E', generatorBlock);
        add(dynamoBlock, "SPS", "BGB", "SPS", 'S', carbonFibre, 'P', tagKey("ingots/copper"), 'B', tagKey("storage_blocks/copper"), 'G', generatorBlock);
        add(solarGeneratorBlock, "CgC", "gCg", "cGc", 'G', generatorBlock, 'C', coalDust, 'g', tagKey("glass_blocks"), 'c', circuit);

        add(waterWheelBlock, "PPP", "PIP", "PPP", 'P', sealedPlanksBlock, 'I', tagKey("ingots/steel"));
        add(windSailsBlock, "PSP", "SIS", "PSP", 'P', tagKey("planks"), 'I', tagKey("ingots/steel"), 'S', sail);

        addS(sealedPlanksBlock, tagKey("planks"), tagKey("resins"));

        add(lvTransformerBlock, "PCP", "ccc", "PCP", 'P', tagKey("planks"), 'C', copperInsulatedCableBlock, 'c', tagKey("ingots/copper"));
        add(mvTransformerBlock, " C ", " M ", " C ", 'M', machineBlock, 'C', copperInsulatedCableBlock);
        add(hvTransformerBlock, " c ", "CED", " c ", 'E', mvTransformerBlock, 'c', copperInsulatedCableBlock, 'D', energyCrystal, 'C', circuit);

        add(batBoxBlock, "PCP", "BBB", "PPP", 'P', tagKey("planks"), 'C', copperInsulatedCableBlock, 'B', reBattery);
        add(energyStorageUnitBlock, "cCc", "CMC", "cCc", 'M', machineBlock, 'c', copperInsulatedCableBlock, 'C', energyCrystal);
        add(megaEnergyStorageUnitBlock, "LCL", "LML", "LAL", 'M', energyStorageUnitBlock, 'A', advancedMachineBlock, 'C', advancedCircuit, 'L', lapotronCrystal);

        add(recyclerBlock, " I ", "DCD", "IDI", 'I', tagKey("ingots/steel"), 'D', tagKey("dirts"), 'C', compressorBlock);
        add(inductionFurnaceBlock, "CCC", "CFC", "CMC", 'C', tagKey("ingots/copper"), 'F', electricFurnaceBlock, 'M', advancedMachineBlock);
        add(teslaCoilBlock, "RRR", "RMR", "ICI", 'M', mvTransformerBlock, 'R', tagKey("dusts/redstone"), 'C', circuit, 'I', tagKey("ingots/steel"));
        addN(illuminatorBlock, 8, "ICI", "GTG", "GGG", 'G', tagKey("glass_blocks"), 'I', tagKey("ingots/steel"), 'T', tinCableBlock, 'C', copperInsulatedCableBlock);
        add(cannerBlock, "TCT", "TMT", "TTT", 'T', tagKey("ingots/tin"), 'M', machineBlock, 'C', circuit);
        add(cannerBlock, "TCT", "TMT", "TTT", 'T', tagKey("ingots/tin"), 'M', machineBlock, 'C', circuit);
        add(electricFurnaceBlock, " C ", "RFR", 'C', circuit, 'R', tagKey("dusts/redstone"), 'F', ironFurnaceBlock);
        add(maceratorBlock, "FFF", "SMS", " C ", 'F', Item.FLINT, 'S', tagKey("cobblestones/normal"), 'M', machineBlock, 'C', circuit);
        add(extractorBlock, "TMT", "TCT", 'T', treeTap, 'M', machineBlock, 'C', circuit);
        add(compressorBlock, "S S", "SMS", "SCS", 'S', tagKey("stones"), 'M', machineBlock, 'C', circuit);
        add(minerBlock, "CMC", " P ", " P ", 'P', miningPipeBlock, 'M', machineBlock, 'C', circuit);
        add(pumpBlock, "cCc", "cMc", "PTP", 'c', emptyCell, 'T', treeTap, 'P', miningPipeBlock, 'M', machineBlock, 'C', circuit);
        add(electrolyzerBlock, "c c", "cCc", "EME", 'E', emptyCell, 'c', copperInsulatedCableBlock, 'M', machineBlock, 'C', circuit);
//        add(reactorChamberBlock, "ACA", "PMP", "APA", 'A', advancedAlloy, 'C', reactorCooler, 'P', reactorPlating, 'M', machineBlock);
//        add(reactorCoreBlock, "AcA", "CGC", "AcA", 'A', advancedAlloy, 'C', reactorChamberBlock, 'c', advancedCircuit, 'G', generatorBlock);
        add(massFabricatorBlock, "GCG", "ALA", "GCG", 'A', advancedMachineBlock, 'L', lapotronCrystal, 'G', Item.GLOWSTONE_DUST, 'C', advancedCircuit);

        addN(woodenScaffoldBlock, 16, "PPP", " s ", "s s", 'P', tagKey("planks"), 's', tagKey("sticks/wooden"));

        add(personalSafeBlock, "C", "M", "H", 'C', circuit, 'H', Block.CHEST, 'M', machineBlock);

        addN(industrialTNTBlock, 4, "FFF", "TTT", "FFF", 'F', Item.FLINT, 'T', Block.TNT);
        addN(industrialTNTBlock, 8, " c ", "GCG", "TTT", 'c', copperCableBlock, 'G', tagKey("dusts/glowstone"), 'C', circuit, 'T', Block.TNT);
        add(nukeBlock, "GUG", "UGU", "GUG", 'G', tagKey("dusts/gunpowder"), 'U', uraniumIngot);

        add(teleporterBlock, "GFG", "CMC", "GDG", 'M', advancedMachineBlock, 'C', copperInsulatedCableBlock, 'F', frequencyTransmitter, 'G', tagKey("dusts/glowstone"), 'D', tagKey("gems/diamond"));

        addS(Block.STICKY_PISTON, Block.PISTON, tagKey("resins"));
        addSN(constructionFoamBlock, 3, tagKey("dusts/clay"), tagKey("buckets/water"), tagKey("dusts/redstone"), tagKey("dusts/coal"));
        addN(rubberSheetBlock, 3, "RRR", "RRR", 'R', tagKey("rubbers"));
        add(Block.TORCH, "R", "I", 'R', tagKey("resins"), 'I', tagKey("sticks/wooden"));
    }

    private static void cables() {
        addN(tinCableBlock, 9, "III", 'I', tagKey("ingots/tin"));
        
        addN(copperCableBlock, 6, "III", 'I', tagKey("ingots/copper"));
        addN(copperInsulatedCableBlock, 6, "RRR", "III", "RRR", 'I', tagKey("ingots/copper"), 'R', tagKey("rubbers"));
        addS(copperInsulatedCableBlock, copperCableBlock, tagKey("rubbers"));
        
        addN(refinedIronCableBlock, 12, "III", 'I', tagKey("ingots/steel"));
        addN(refinedIronInsulatedCableBlock, 4, " R ", "RIR", " R ", 'I', tagKey("ingots/steel"), 'R', tagKey("rubbers"));
        addS(refinedIronInsulatedCableBlock, refinedIronCableBlock, tagKey("rubbers"));
        addS(refinedIronInsulatedX2CableBlock, refinedIronInsulatedCableBlock, tagKey("rubbers"));
        addS(refinedIronInsulatedX3CableBlock, refinedIronCableBlock, tagKey("rubbers"), tagKey("rubbers"), tagKey("rubbers"));
        addS(refinedIronInsulatedX3CableBlock, refinedIronInsulatedCableBlock, tagKey("rubbers"), tagKey("rubbers"));
        addS(refinedIronInsulatedX3CableBlock, refinedIronInsulatedX2CableBlock, tagKey("rubbers"));

        addN(goldCableBlock, 12, "III", 'I', tagKey("ingots/gold"));
        addN(goldInsulatedCableBlock, 4, " R ", "RIR", " R ", 'I', tagKey("ingots/gold"), 'R', tagKey("rubbers"));
        addS(goldInsulatedX2CableBlock, goldCableBlock, tagKey("rubbers"), tagKey("rubbers"));
        addS(goldInsulatedX2CableBlock, goldInsulatedCableBlock, tagKey("rubbers"));

        addN(glassFibreCableBlock, 16, "GGG", "RDR", "GGG", 'D', tagKey("gems/diamond"), 'R', tagKey("dusts/redstone"), 'G', tagKey("glass_blocks"));
    }

    public static void materials() {
        addSN(sealedPlanksBlock, 2, rubberLogBlock);

        addN(reinforcedStoneBlock, 8, "SSS", "SAS", "SSS", 'S', tagKey("stones"), 'A', advancedAlloy);
        addN(reinforcedGlassBlock, 7, "GAG", "GGG", "GAG", 'G', tagKey("glass_blocks"), 'A', advancedAlloy);
        add(reinforcedDoor, "SS", "SS", "SS", 'S', reinforcedStoneBlock);
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
