package net.glasslauncher.mods.glasstech.events.init;

import net.danygames2014.nyalib.event.ItemCapabilityProviderRegisterEvent;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.GTDoorBlock;
import net.glasslauncher.mods.glasstech.item.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.material.Material;
import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.template.item.*;

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

    public static ChangingSpritePowerItem reBattery;
    public static ChangingSpritePowerItem energyCrystal;
    public static ChangingSpritePowerItem lapotronCrystal;
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
    public static Item wireCutter;
    public static Item painter;
    public static NanoSaberItem nanoSaber;

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
    public static Item nearDepletedUraniumCell;
    public static Item bioCell;
    public static Item coalfuelCell;
    public static Item compressedHydratedCoalDust;
    public static Item sail;

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

    @EventListener
    private static void itemInit(ItemRegistryEvent event) {
        // Otherwise the canner gives 21 hearts worth of food.
        // That doesn't seem particularly balanced if other mods that add apples are in play.
        ((FoodItem) Item.GOLDEN_APPLE).healthRestored = 20;

        copperIngot = new TemplateItem(NAMESPACE.id("copper_ingot"));
        advancedAlloy = new TemplateItem(NAMESPACE.id("advanced_alloy"));
        scrap = new Scrap(NAMESPACE.id("scrap"));
        copperDust = new TemplateItem(NAMESPACE.id("copper_dust"));
        tinIngot = new TemplateItem(NAMESPACE.id("tin_ingot"));
        tinDust = new TemplateItem(NAMESPACE.id("tin_dust"));
        bronzeIngot = new TemplateItem(NAMESPACE.id("bronze_ingot"));
        bronzeDust = new TemplateItem(NAMESPACE.id("bronze_dust"));
        goldDust = new TemplateItem(NAMESPACE.id("gold_dust"));
        ironDust = new TemplateItem(NAMESPACE.id("iron_dust"));
        iridium = new TemplateItem(NAMESPACE.id("iridium"));
        iridiumPlate = new TemplateItem(NAMESPACE.id("iridium_plate"));
        uraniumIngot = new TemplateItem(NAMESPACE.id("uranium_ingot"));
        rubber = new TemplateItem(NAMESPACE.id("rubber"));
        resin = new TemplateItem(NAMESPACE.id("resin"));
        emptyCell = new TemplateItem(NAMESPACE.id("empty_cell"));
        lavaCell = new TemplateItem(NAMESPACE.id("lava_cell"));
        waterCell = new TemplateItem(NAMESPACE.id("water_cell"));
        mixedMetalIngot = new TemplateItem(NAMESPACE.id("mixed_metal_ingot"));
        refinedIronIngot = new TemplateItem(NAMESPACE.id("refined_iron_ingot"));
        uranium = new TemplateItem(NAMESPACE.id("uranium"));
        uuMatter = new TemplateItem(NAMESPACE.id("uu_matter"));
        coalDust = new TemplateItem(NAMESPACE.id("coal_dust"));
        fertilizer = new TemplateItem(NAMESPACE.id("fertilizer"));
        carbonFibre = new TemplateItem(NAMESPACE.id("carbon_fibre"));
        clayDust = new TemplateItem(NAMESPACE.id("clay_dust"));
        carbonMesh = new TemplateItem(NAMESPACE.id("carbon_mesh"));
        coalBall = new TemplateItem(NAMESPACE.id("coal_ball"));
        industrialDiamond = new TemplateItem(NAMESPACE.id("industrial_diamond"));
        carbonPlate = new TemplateItem(NAMESPACE.id("carbon_plate"));
        cfPellet = new TemplateItem(NAMESPACE.id("cf_pellet"));
        compressedCoalBall = new TemplateItem(NAMESPACE.id("compressed_coal_ball"));
        coalChunk = new TemplateItem(NAMESPACE.id("coal_chunk"));
        silverDust = new TemplateItem(NAMESPACE.id("silver_dust"));

        circuit = new TemplateItem(NAMESPACE.id("circuit"));
        advancedCircuit = new TemplateItem(NAMESPACE.id("advanced_circuit"));
        scrapBox = new ScrapBox(NAMESPACE.id("scrap_box"));

        reBattery = new RechargeableCellItem(NAMESPACE.id("re_battery"), VoltageTier.LV, VoltageTier.LV.maxVoltage * 64);
        energyCrystal = new RechargeableCellItem(NAMESPACE.id("energy_crystal"), VoltageTier.MV, VoltageTier.MV.maxVoltage * 64);
        lapotronCrystal = new RechargeableCellItem(NAMESPACE.id("lapotron_crystal"), VoltageTier.HV, VoltageTier.HV.maxVoltage * 64);

        batteryPack = new PowerStorageArmor(NAMESPACE.id("battery_pack"), 1, VoltageTier.LV, VoltageTier.LV.maxVoltage * 640);
        lappack = new PowerStorageArmor(NAMESPACE.id("lappack"), 1, VoltageTier.HV, VoltageTier.HV.maxVoltage * 640);

        cfPack = new TemplateItem(NAMESPACE.id("cf_pack"));

        wrench = new Wrench(NAMESPACE.id("wrench"));
        electricWrench = new ElectricWrench(NAMESPACE.id("electric_wrench"), VoltageTier.LV, VoltageTier.LV.maxVoltage * 256);

        chainsaw = new ElectricAxe(NAMESPACE.id("chainsaw"), ToolMaterial.IRON, VoltageTier.MV, VoltageTier.MV.maxVoltage * 160);
        diamondChainsaw = new ElectricAxe(NAMESPACE.id("diamond_chainsaw"), ToolMaterial.DIAMOND, VoltageTier.HV, VoltageTier.HV.maxVoltage * 160);
        drill = new ElectricPickaxe(NAMESPACE.id("drill"), ToolMaterial.IRON, VoltageTier.MV, VoltageTier.MV.maxVoltage * 160);
        diamondDrill = new ElectricPickaxe(NAMESPACE.id("diamond_drill"), ToolMaterial.DIAMOND, VoltageTier.HV, VoltageTier.HV.maxVoltage * 160);

        bronzeAxe = new TemplateAxeItem(NAMESPACE.id("bronze_axe"), ToolMaterial.IRON);
        bronzeHoe = new TemplateHoeItem(NAMESPACE.id("bronze_hoe"), ToolMaterial.IRON);
        bronzePickaxe = new TemplatePickaxeItem(NAMESPACE.id("bronze_pickaxe"), ToolMaterial.IRON);
        bronzeShovel = new TemplateShovelItem(NAMESPACE.id("bronze_shovel"), ToolMaterial.IRON);
        bronzeSword = new TemplateSwordItem(NAMESPACE.id("bronze_sword"), ToolMaterial.IRON);

        cfSprayer = new TemplateItem(NAMESPACE.id("cf_sprayer"));
        frequencyTransmitter = new TemplateItem(NAMESPACE.id("frequency_transmitter"));
        wireCutter = new WireCutters(NAMESPACE.id("wire_cutters"));
        painter = new PainterItem(NAMESPACE.id("painter"));
        nanoSaber = new NanoSaberItem(NAMESPACE.id("nano_saber"), VoltageTier.MV, VoltageTier.MV.maxVoltage * 320);

        compressedHydratedCoal = new TemplateItem(NAMESPACE.id("compressed_hydrated_coal"));
        compressedPlantBall = new TemplateItem(NAMESPACE.id("compressed_plant_ball"));
        coolantCell = new TemplateItem(NAMESPACE.id("coolant_cell"));
        depletedUraniumCell = new TemplateItem(NAMESPACE.id("depleted_uranium_cell"));
        hydratedCoalDust = new TemplateItem(NAMESPACE.id("hydrated_coal_dust"));
        plantBall = new TemplateItem(NAMESPACE.id("plant_ball"));
        suBattery = new TemplateItem(NAMESPACE.id("su_battery"));
        uraniumCell = new TemplateItem(NAMESPACE.id("uranium_cell"));

        nanoBoots = new NanoArmor(NAMESPACE.id("nano_boots"), 3, VoltageTier.MV, VoltageTier.MV.maxVoltage * 320);
        nanoChestplate = new NanoArmor(NAMESPACE.id("nano_chestplate"), 1, VoltageTier.MV, VoltageTier.MV.maxVoltage * 320);
        nanoHelmet = new NanoArmor(NAMESPACE.id("nano_helmet"), 0, VoltageTier.MV, VoltageTier.MV.maxVoltage * 320);
        nanoLeggings = new NanoArmor(NAMESPACE.id("nano_leggings"), 2, VoltageTier.MV, VoltageTier.MV.maxVoltage * 320);

        quantumBoots = new QuantumArmor(NAMESPACE.id("quantum_boots"), 3, VoltageTier.HV, VoltageTier.HV.maxVoltage * 320);
        quantumChestplate = new QuantumArmor(NAMESPACE.id("quantum_chestplate"), 1, VoltageTier.HV, VoltageTier.HV.maxVoltage * 320);
        quantumHelmet = new QuantumArmor(NAMESPACE.id("quantum_helmet"), 0, VoltageTier.HV, VoltageTier.HV.maxVoltage * 320);
        quantumLeggings = new QuantumArmor(NAMESPACE.id("quantum_leggings"), 2, VoltageTier.HV, VoltageTier.HV.maxVoltage * 320);

        jetpack = new FuelJetPack(NAMESPACE.id("jetpack"), 1, VoltageTier.MV);
        electricJetpack = new ElectricJetPack(NAMESPACE.id("electric_jetpack"), 1, VoltageTier.MV, VoltageTier.MV.maxVoltage * 160);

        bronzeBoots = new BronzeArmor(NAMESPACE.id("bronze_boots"), 2, 0, 3);
        bronzeChestplate = new BronzeArmor(NAMESPACE.id("bronze_chestplate"), 2, 0, 1);
        bronzeHelmet = new BronzeArmor(NAMESPACE.id("bronze_helmet"), 2, 0, 0);
        bronzeLeggings = new BronzeArmor(NAMESPACE.id("bronze_leggings"), 2, 0, 2);

        reactorCooler = new TemplateItem(NAMESPACE.id("reactor_cooler"));
        reactorPlating = new TemplateItem(NAMESPACE.id("reactor_plating"));
        emptyFuelCan = new TemplateItem(NAMESPACE.id("empty_fuel_can"));
        dynamiteRemote = new TemplateItem(NAMESPACE.id("dynamite_remote"));
        reEnrichedUraniumCell = new TemplateItem(NAMESPACE.id("re_enriched_uranium_cell"));
        nearDepletedUraniumCell = new TemplateItem(NAMESPACE.id("near_depleted_uranium_cell"));
        bioCell = new TemplateItem(NAMESPACE.id("bio_cell"));
        coalfuelCell = new TemplateItem(NAMESPACE.id("coalfuel_cell"));
        compressedHydratedCoalDust = new TemplateItem(NAMESPACE.id("compressed_hydrated_coal_dust"));
        sail = new TemplateItem(NAMESPACE.id("sail"));
        reinforcedDoor = new GTDoorBlock.GTDoorItem(NAMESPACE.id("reinforced_door"), Material.METAL, GlassTechBlocks.reinforcedDoorBlock);
        biofuelCell = new TemplateItem(NAMESPACE.id("biofuel_cell"));
        fullFuelCan = new FuelCanItem(NAMESPACE.id("full_fuel_can"));
        cannedFood = new CannedFood(NAMESPACE.id("canned_food"));

        scanner = new TemplateItem(NAMESPACE.id("scanner"));
        advancedScanner = new TemplateItem(NAMESPACE.id("advanced_scanner"));
//        dynamite = new TemplateItem(NAMESPACE.id("dynamite"));
        stickyDynamite = new TemplateItem(NAMESPACE.id("sticky_dynamite"));
        rubberBoots = new RubberBoots(NAMESPACE.id("rubber_boots"), 0, 0, 3);
        voltageMeter = new VoltMeterItem(NAMESPACE.id("voltage_meter"));
        miningLaser = new TemplateItem(NAMESPACE.id("mining_laser"));
        compositeChestplate = new CompositeChestplate(NAMESPACE.id("composite_chestplate"), 0, 0, 1);

        ScrapBox.initDefaultDrops();
    }

    @EventListener
    private static void registerCapabilities(ItemCapabilityProviderRegisterEvent event) {
        event.register(SingleUsePowerCapability.IDENTIFIER, new SingleUsePowerCapabilityProvider());

    }
}
