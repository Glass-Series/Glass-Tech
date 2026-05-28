package net.glasslauncher.mods.glasstech.events.init;

import net.danygames2014.nyalib.event.ItemCapabilityProviderRegisterEvent;
import net.glasslauncher.mods.glasstech.blocks.GTDoorBlock;
import net.glasslauncher.mods.glasstech.item.PainterItem;
import net.glasslauncher.mods.glasstech.item.SingleUsePowerCapability;
import net.glasslauncher.mods.glasstech.item.SingleUsePowerCapabilityProvider;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

import java.lang.invoke.MethodHandles;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class GlassTechItems {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    public static Item copperIngot;
    public static Item advancedAlloy;
    public static Item scrap;
    public static Item copperDust;
    public static Item tinIngot;
    public static Item tinDust;
    public static Item bronzeIngot;
    public static Item bronzeDust;
    public static Item goldDust;
    public static Item ironDust;
    public static Item iridium;
    public static Item iridiumPlate;
    public static Item uraniumIngot;
    public static Item rubber;
    public static Item resin;
    public static Item emptyCell;
    public static Item lavaCell;
    public static Item waterCell;
    public static Item mixedMetalIngot;
    public static Item refinedIronIngot;
    public static Item uranium;
    public static Item uuMatter;
    public static Item coalDust;
    public static Item fertilizer;

    public static Item circuit;
    public static Item advancedCircuit;
    public static Item scrapBox;

    public static Item reBattery;
    public static Item energyCrystal;
    public static Item lapotronCrystal;
    public static Item batteryPack;

    public static Item wrench;
    public static Item electricWrench;
    public static Item chainsaw;
    public static Item diamondChainsaw;
    public static Item drill;
    public static Item diamondDrill;

    public static Item carbonFibre;
    public static Item clayDust;
    public static Item carbonMesh;
    public static Item coalBall;
    public static Item industrialDiamond;
    public static Item carbonPlate;
    public static Item cfPellet;
    public static Item compressedCoalBall;
    public static Item coalChunk;
    public static Item silverDust;
    public static Item bronzeAxe;
    public static Item bronzeHoe;
    public static Item bronzePickaxe;
    public static Item bronzeShovel;
    public static Item bronzeSword;
    public static Item cfSprayer;
    public static Item frequencyTransmitter;
    public static Item insulationCutter;
    public static Item painter;
    public static Item nanoSaber;

    public static Item compressedHydratedCoal;
    public static Item compressedPlantBall;
    public static Item coolantCell;
    public static Item depletedUraniumCell;
    public static Item hydratedCoalDust;
    public static Item plantBall;
    public static Item suBattery;
    public static Item uraniumCell;

    public static Item nanoBoots;
    public static Item nanoChestplate;
    public static Item nanoHelmet;
    public static Item nanoLeggings;
    public static Item quantumBoots;
    public static Item quantumChestplate;
    public static Item quantumHelmet;
    public static Item quantumLeggings;
    public static Item jetpack;
    public static Item electricJetpack;
    public static Item bronzeBoots;
    public static Item bronzeChestplate;
    public static Item bronzeHelmet;
    public static Item bronzeLeggings;

    public static Item reactorCooler;
    public static Item reactorPlating;
    public static Item emptyFuelCan;
    public static Item dynamiteRemote;
    public static Item reEnrichedUraniumCell;
    public static Item bioCell;
    public static Item coalfuelCell;
    public static Item compressedHydratedCoalDust;
    public static Item itemCellUranDepleted;
    public static Item itemCellUranEmpty;

    public static Item rubberSapling;
    public static Item reinforcedDoor;

    public static Item biofuelCell;
    public static Item fullFuelCan;
    public static Item cannedFood;

    public static Item scanner;
    public static Item advancedScanner;
//    public static Item dynamite;
    public static Item stickyDynamite;
    public static Item rubberBoots;
    public static Item voltageMeter;
    public static Item miningLaser;
    public static Item lappack;
    public static Item cfPack;
    public static Item compositeChestplate;

    public static Item treeTap;

    @EventListener
    private static void itemInit(ItemRegistryEvent event) {
        copperIngot = new TemplateItem(NAMESPACE.id("copper_ingot")).setTranslationKey(NAMESPACE.id("copper_ingot"));
        advancedAlloy = new TemplateItem(NAMESPACE.id("advanced_alloy")).setTranslationKey(NAMESPACE.id("advanced_alloy"));
        scrap = new TemplateItem(NAMESPACE.id("scrap")).setTranslationKey(NAMESPACE.id("scrap"));
        copperDust = new TemplateItem(NAMESPACE.id("copper_dust")).setTranslationKey(NAMESPACE.id("copper_dust"));
        tinIngot = new TemplateItem(NAMESPACE.id("tin_ingot")).setTranslationKey(NAMESPACE.id("tin_ingot"));
        tinDust = new TemplateItem(NAMESPACE.id("tin_dust")).setTranslationKey(NAMESPACE.id("tin_dust"));
        bronzeIngot = new TemplateItem(NAMESPACE.id("bronze_ingot")).setTranslationKey(NAMESPACE.id("bronze_ingot"));
        bronzeDust = new TemplateItem(NAMESPACE.id("bronze_dust")).setTranslationKey(NAMESPACE.id("bronze_dust"));
        goldDust = new TemplateItem(NAMESPACE.id("gold_dust")).setTranslationKey(NAMESPACE.id("gold_dust"));
        ironDust = new TemplateItem(NAMESPACE.id("iron_dust")).setTranslationKey(NAMESPACE.id("iron_dust"));
        iridium = new TemplateItem(NAMESPACE.id("iridium")).setTranslationKey(NAMESPACE.id("iridium"));
        iridiumPlate = new TemplateItem(NAMESPACE.id("iridium_plate")).setTranslationKey(NAMESPACE.id("iridium_plate"));
        uraniumIngot = new TemplateItem(NAMESPACE.id("uranium_ingot")).setTranslationKey(NAMESPACE.id("uranium_ingot"));
        rubber = new TemplateItem(NAMESPACE.id("rubber")).setTranslationKey(NAMESPACE.id("rubber"));
        resin = new TemplateItem(NAMESPACE.id("resin")).setTranslationKey(NAMESPACE.id("resin"));
        emptyCell = new TemplateItem(NAMESPACE.id("empty_cell")).setTranslationKey(NAMESPACE.id("empty_cell"));
        lavaCell = new TemplateItem(NAMESPACE.id("lava_cell")).setTranslationKey(NAMESPACE.id("lava_cell"));
        waterCell = new TemplateItem(NAMESPACE.id("water_cell")).setTranslationKey(NAMESPACE.id("water_cell"));
        mixedMetalIngot = new TemplateItem(NAMESPACE.id("mixed_metal_ingot")).setTranslationKey(NAMESPACE.id("mixed_metal_ingot"));
        refinedIronIngot = new TemplateItem(NAMESPACE.id("refined_iron_ingot")).setTranslationKey(NAMESPACE.id("refined_iron_ingot"));
        uranium = new TemplateItem(NAMESPACE.id("uranium")).setTranslationKey(NAMESPACE.id("uranium"));
        uuMatter = new TemplateItem(NAMESPACE.id("uu_matter")).setTranslationKey(NAMESPACE.id("uu_matter"));
        coalDust = new TemplateItem(NAMESPACE.id("coal_dust")).setTranslationKey(NAMESPACE.id("coal_dust"));
        fertilizer = new TemplateItem(NAMESPACE.id("fertilizer")).setTranslationKey(NAMESPACE.id("fertilizer"));
        carbonFibre = new TemplateItem(NAMESPACE.id("carbon_fiber")).setTranslationKey(NAMESPACE.id("carbon_fiber"));
        clayDust = new TemplateItem(NAMESPACE.id("clay_dust")).setTranslationKey(NAMESPACE.id("clay_dust"));
        carbonMesh = new TemplateItem(NAMESPACE.id("carbon_mesh")).setTranslationKey(NAMESPACE.id("carbon_mesh"));
        coalBall = new TemplateItem(NAMESPACE.id("coal_ball")).setTranslationKey(NAMESPACE.id("coal_ball"));
        industrialDiamond = new TemplateItem(NAMESPACE.id("industrial_diamond")).setTranslationKey(NAMESPACE.id("industrial_diamond"));
        carbonPlate = new TemplateItem(NAMESPACE.id("carbon_plate")).setTranslationKey(NAMESPACE.id("carbon_plate"));
        cfPellet = new TemplateItem(NAMESPACE.id("cf_pellet")).setTranslationKey(NAMESPACE.id("cf_pellet"));
        compressedCoalBall = new TemplateItem(NAMESPACE.id("compressed_coal_ball")).setTranslationKey(NAMESPACE.id("compressed_coal_ball"));
        coalChunk = new TemplateItem(NAMESPACE.id("coal_chunk")).setTranslationKey(NAMESPACE.id("coal_chunk"));
        silverDust = new TemplateItem(NAMESPACE.id("silver_dust")).setTranslationKey(NAMESPACE.id("silver_dust"));

        circuit = new TemplateItem(NAMESPACE.id("circuit")).setTranslationKey(NAMESPACE.id("circuit"));
        advancedCircuit = new TemplateItem(NAMESPACE.id("advanced_circuit")).setTranslationKey(NAMESPACE.id("advanced_circuit"));
        scrapBox = new TemplateItem(NAMESPACE.id("scrap_box")).setTranslationKey(NAMESPACE.id("scrap_box"));

        reBattery = new TemplateItem(NAMESPACE.id("re_battery")).setTranslationKey(NAMESPACE.id("re_battery"));
        energyCrystal = new TemplateItem(NAMESPACE.id("energy_crystal")).setTranslationKey(NAMESPACE.id("energy_crystal"));
        lapotronCrystal = new TemplateItem(NAMESPACE.id("lapotron_crystal")).setTranslationKey(NAMESPACE.id("lapotron_crystal"));
        batteryPack = new TemplateItem(NAMESPACE.id("battery_pack")).setTranslationKey(NAMESPACE.id("battery_pack"));

        wrench = new TemplateItem(NAMESPACE.id("wrench")).setTranslationKey(NAMESPACE.id("wrench"));
        electricWrench = new TemplateItem(NAMESPACE.id("electric_wrench")).setTranslationKey(NAMESPACE.id("electric_wrench"));
        chainsaw = new TemplateItem(NAMESPACE.id("chainsaw")).setTranslationKey(NAMESPACE.id("chainsaw"));
        diamondChainsaw = new TemplateItem(NAMESPACE.id("diamond_chainsaw")).setTranslationKey(NAMESPACE.id("diamond_chainsaw"));
        drill = new TemplateItem(NAMESPACE.id("drill")).setTranslationKey(NAMESPACE.id("drill"));
        diamondDrill = new TemplateItem(NAMESPACE.id("diamond_drill")).setTranslationKey(NAMESPACE.id("diamond_drill"));
        bronzeAxe = new TemplateItem(NAMESPACE.id("bronze_axe")).setTranslationKey(NAMESPACE.id("bronze_axe"));
        bronzeHoe = new TemplateItem(NAMESPACE.id("bronze_hoe")).setTranslationKey(NAMESPACE.id("bronze_hoe"));
        bronzePickaxe = new TemplateItem(NAMESPACE.id("bronze_pickaxe")).setTranslationKey(NAMESPACE.id("bronze_pickaxe"));
        bronzeShovel = new TemplateItem(NAMESPACE.id("bronze_shovel")).setTranslationKey(NAMESPACE.id("bronze_shovel"));
        bronzeSword = new TemplateItem(NAMESPACE.id("bronze_sword")).setTranslationKey(NAMESPACE.id("bronze_sword"));
        cfSprayer = new TemplateItem(NAMESPACE.id("cf_sprayer")).setTranslationKey(NAMESPACE.id("cf_sprayer"));
        frequencyTransmitter = new TemplateItem(NAMESPACE.id("frequency_transmitter")).setTranslationKey(NAMESPACE.id("frequency_transmitter"));
        insulationCutter = new TemplateItem(NAMESPACE.id("insulation_cutter")).setTranslationKey(NAMESPACE.id("insulation_cutter"));
        painter = new PainterItem(NAMESPACE.id("painter")).setTranslationKey(NAMESPACE.id("painter"));
        nanoSaber = new TemplateItem(NAMESPACE.id("nano_saber")).setTranslationKey(NAMESPACE.id("nano_saber"));

        treeTap = new TemplateItem(NAMESPACE.id("tree_tap")).setTranslationKey(NAMESPACE.id("tree_tap"));

        compressedHydratedCoal = new TemplateItem(NAMESPACE.id("compressed_hydrated_coal")).setTranslationKey(NAMESPACE.id("compressed_hydrated_coal"));
        compressedPlantBall = new TemplateItem(NAMESPACE.id("compressed_plant_ball")).setTranslationKey(NAMESPACE.id("compressed_plant_ball"));
        coolantCell = new TemplateItem(NAMESPACE.id("coolant_cell")).setTranslationKey(NAMESPACE.id("coolant_cell"));
        depletedUraniumCell = new TemplateItem(NAMESPACE.id("depleted_uranium_cell")).setTranslationKey(NAMESPACE.id("depleted_uranium_cell"));
        hydratedCoalDust = new TemplateItem(NAMESPACE.id("hydrated_coal_dust")).setTranslationKey(NAMESPACE.id("hydrated_coal_dust"));
        plantBall = new TemplateItem(NAMESPACE.id("plant_ball")).setTranslationKey(NAMESPACE.id("plant_ball"));
        suBattery = new TemplateItem(NAMESPACE.id("su_battery")).setTranslationKey(NAMESPACE.id("su_battery"));
        uraniumCell = new TemplateItem(NAMESPACE.id("uranium_cell")).setTranslationKey(NAMESPACE.id("uranium_cell"));
        nanoBoots = new TemplateItem(NAMESPACE.id("nano_boots")).setTranslationKey(NAMESPACE.id("nano_boots"));
        nanoChestplate = new TemplateItem(NAMESPACE.id("nano_chestplate")).setTranslationKey(NAMESPACE.id("nano_chestplate"));
        nanoHelmet = new TemplateItem(NAMESPACE.id("nano_helmet")).setTranslationKey(NAMESPACE.id("nano_helmet"));
        nanoLeggings = new TemplateItem(NAMESPACE.id("nano_leggings")).setTranslationKey(NAMESPACE.id("nano_leggings"));
        quantumBoots = new TemplateItem(NAMESPACE.id("quantum_boots")).setTranslationKey(NAMESPACE.id("quantum_boots"));
        quantumChestplate = new TemplateItem(NAMESPACE.id("quantum_chestplate")).setTranslationKey(NAMESPACE.id("quantum_chestplate"));
        quantumHelmet = new TemplateItem(NAMESPACE.id("quantum_helmet")).setTranslationKey(NAMESPACE.id("quantum_helmet"));
        quantumLeggings = new TemplateItem(NAMESPACE.id("quantum_leggings")).setTranslationKey(NAMESPACE.id("quantum_leggings"));
        jetpack = new TemplateItem(NAMESPACE.id("jetpack")).setTranslationKey(NAMESPACE.id("jetpack"));
        electricJetpack = new TemplateItem(NAMESPACE.id("electric_jetpack")).setTranslationKey(NAMESPACE.id("electric_jetpack"));

        bronzeBoots = new TemplateItem(NAMESPACE.id("bronze_boots")).setTranslationKey(NAMESPACE.id("bronze_boots"));
        bronzeChestplate = new TemplateItem(NAMESPACE.id("bronze_chestplate")).setTranslationKey(NAMESPACE.id("bronze_chestplate"));
        bronzeHelmet = new TemplateItem(NAMESPACE.id("bronze_helmet")).setTranslationKey(NAMESPACE.id("bronze_helmet"));
        bronzeLeggings = new TemplateItem(NAMESPACE.id("bronze_leggings")).setTranslationKey(NAMESPACE.id("bronze_leggings"));

        reactorCooler = new TemplateItem(NAMESPACE.id("reactor_cooler")).setTranslationKey(NAMESPACE.id("reactor_cooler"));
        reactorPlating = new TemplateItem(NAMESPACE.id("reactor_plating")).setTranslationKey(NAMESPACE.id("reactor_plating"));
        emptyFuelCan = new TemplateItem(NAMESPACE.id("empty_fuel_can")).setTranslationKey(NAMESPACE.id("empty_fuel_can"));
        dynamiteRemote = new TemplateItem(NAMESPACE.id("dynamite_remote")).setTranslationKey(NAMESPACE.id("dynamite_remote"));
        reEnrichedUraniumCell = new TemplateItem(NAMESPACE.id("re_enriched_uranium_cell")).setTranslationKey(NAMESPACE.id("re_enriched_uranium_cell"));
        bioCell = new TemplateItem(NAMESPACE.id("bio_cell")).setTranslationKey(NAMESPACE.id("bio_cell"));
        coalfuelCell = new TemplateItem(NAMESPACE.id("coalfuel_cell")).setTranslationKey(NAMESPACE.id("coalfuel_cell"));
        compressedHydratedCoalDust = new TemplateItem(NAMESPACE.id("compressed_hydrated_coal_dust")).setTranslationKey(NAMESPACE.id("compressed_hydrated_coal_dust"));
        itemCellUranDepleted = new TemplateItem(NAMESPACE.id("item_cell_uran_depleted")).setTranslationKey(NAMESPACE.id("item_cell_uran_depleted"));
        itemCellUranEmpty = new TemplateItem(NAMESPACE.id("item_cell_uran_empty")).setTranslationKey(NAMESPACE.id("item_cell_uran_empty"));
        rubberSapling = new TemplateItem(NAMESPACE.id("rubber_sapling")).setTranslationKey(NAMESPACE.id("rubber_sapling"));
        reinforcedDoor = new GTDoorBlock.GTDoorItem(NAMESPACE.id("reinforced_door"), Material.METAL, GlassTechBlocks.reinforcedDoorBlock).setTranslationKey(NAMESPACE.id("reinforced_door"));
        biofuelCell = new TemplateItem(NAMESPACE.id("biofuel_cell")).setTranslationKey(NAMESPACE.id("biofuel_cell"));
        fullFuelCan = new TemplateItem(NAMESPACE.id("full_fuel_can")).setTranslationKey(NAMESPACE.id("full_fuel_can"));
        cannedFood = new TemplateItem(NAMESPACE.id("canned_food")).setTranslationKey(NAMESPACE.id("canned_food"));

        scanner = new TemplateItem(NAMESPACE.id("scanner")).setTranslationKey(NAMESPACE.id("scanner"));
        advancedScanner = new TemplateItem(NAMESPACE.id("advanced_scanner")).setTranslationKey(NAMESPACE.id("advanced_scanner"));
//        dynamite = new TemplateItem(NAMESPACE.id("dynamite")).setTranslationKey(NAMESPACE.id("dynamite"));
        stickyDynamite = new TemplateItem(NAMESPACE.id("sticky_dynamite")).setTranslationKey(NAMESPACE.id("sticky_dynamite"));
        rubberBoots = new TemplateItem(NAMESPACE.id("rubber_boots")).setTranslationKey(NAMESPACE.id("rubber_boots"));
        voltageMeter = new VoltMeterItem(NAMESPACE.id("voltage_meter")).setTranslationKey(NAMESPACE.id("voltage_meter"));
        miningLaser = new TemplateItem(NAMESPACE.id("mining_laser")).setTranslationKey(NAMESPACE.id("mining_laser"));
        lappack = new TemplateItem(NAMESPACE.id("lappack")).setTranslationKey(NAMESPACE.id("lappack"));
        cfPack = new TemplateItem(NAMESPACE.id("cf_pack")).setTranslationKey(NAMESPACE.id("cf_pack"));
        compositeChestplate = new TemplateItem(NAMESPACE.id("composite_chestplate")).setTranslationKey(NAMESPACE.id("composite_chestplate"));
    }

    @EventListener
    private static void registerCapabilities(ItemCapabilityProviderRegisterEvent event) {
        event.register(SingleUsePowerCapability.IDENTIFIER, new SingleUsePowerCapabilityProvider());

    }
}
