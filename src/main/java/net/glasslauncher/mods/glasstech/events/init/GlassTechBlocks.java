package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.WireMaterial;
import net.glasslauncher.mods.glasstech.blocks.*;
import net.glasslauncher.mods.glasstech.blocks.batbox.BatBoxBlock;
import net.glasslauncher.mods.glasstech.blocks.batbox.BatBoxBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.batbox.esu.ESUBlock;
import net.glasslauncher.mods.glasstech.blocks.batbox.esu.ESUBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.batbox.mesu.MESUBlock;
import net.glasslauncher.mods.glasstech.blocks.batbox.mesu.MESUBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.ironfurnace.IronFurnaceBlock;
import net.glasslauncher.mods.glasstech.blocks.ironfurnace.IronFurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.canner.CannerBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.canner.CannerBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.compressor.CompressorBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.compressor.CompressorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace.ElectricFurnace;
import net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace.ElectricFurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer.ElectrolyzerBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer.ElectrolyzerBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.extractor.ExtractorBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.extractor.ExtractorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.DynamoBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.DynamoBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts.WaterWheelBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts.WindSailsBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts.WindSailsBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace.InductionFurnaceBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace.InductionFurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts.WaterWheelBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.pump.PumpBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.pump.PumpBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.transformer.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;

import java.lang.invoke.MethodHandles;

import static net.glasslauncher.mods.glasstech.GlassTech.LOGGER;
import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;
import static net.glasslauncher.mods.glasstech.blocks.TemplateCableBlock.PIXEL_SIZE;
import static net.minecraft.block.Block.*;

public class GlassTechBlocks {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    public static Block generatorBlock;

    public static Block electricFurnaceBlock;
    public static Block compressorBlock;
    public static Block inductionFurnaceBlock;
    public static Block maceratorBlock;
    public static Block cannerBlock;
    public static Block electrolyzerBlock;
    public static Block extractorBlock;

    public static Block ironFurnaceBlock;

    public static Block batBoxBlock;
    public static Block energyStorageUnitBlock;
    public static Block megaEnergyStorageUnitBlock;

    public static Block tinCableBlock;
    public static Block copperCableBlock;
    public static Block copperInsulatedCableBlock;
    public static Block goldCableBlock;
    public static Block goldInsulatedCableBlock;
    public static Block goldInsulatedX2CableBlock;
    public static Block refinedIronCableBlock;
    public static Block refinedIronInsulatedCableBlock;
    public static Block refinedIronInsulatedX2CableBlock;
    public static Block refinedIronInsulatedX3CableBlock;
    public static Block glassFibreCableBlock;

    public static Block bronzeBlock;
    public static Block copperBlock;
    public static Block copperOreBlock;
    public static Block refinedIronBlock;
    public static Block reinforcedGlassBlock;
    public static Block reinforcedStoneBlock;
    public static Block resinSheetBlock;
    public static Block rubberSheetBlock;
    public static Block tinBlock;
    public static Block tinOreBlock;
    public static Block uraniumBlock;
    public static Block uraniumOreBlock;

    public static Block machineBlock;
    public static Block advancedMachineBlock;

    public static Block constructionFoamBlock;
    public static Block hardenedConstructionFoamBlock;
    public static Block dynamiteBlock;
    public static Block metalScaffoldBlock;
    public static Block miningPipeBlock;
    public static Block personalSafeBlock;
    public static GTDoorBlock reinforcedDoorBlock;
    public static Block teleporterBlock;
    public static Block teslaCoilBlock;
    public static Block woodenScaffoldBlock;

    public static Block lvTransformerBlock;
    public static Block mvTransformerBlock;
    public static Block hvTransformerBlock;

    public static Block thermalGeneratorBlock;
    public static Block dynamoBlock;
    public static Block solarGeneratorBlock;

    public static Block refinedIronFenceBlock;

    public static Block recyclerBlock;
    public static Block illuminatorBlock;
    public static Block minerBlock;
    public static Block pumpBlock;
    public static Block magnetizerBlock;
    public static Block reactorChamberBlock;
    public static Block reactorCoreBlock;
    public static Block massFabricatorBlock;
    public static Block industrialTNTBlock;
    public static Block nukeBlock;

    public static Block rubberLogBlock;
    public static Block rubberLeavesBlock;

    public static Block waterWheelBlock;
    public static Block windSailsBlock;

    @EventListener
    private static void init(InitEvent event) {
        LOGGER.info(NAMESPACE.toString());
    }

    @EventListener
    private static void blockInit(BlockRegistryEvent event) {
        generatorBlock = new GeneratorBlock(NAMESPACE.id("generator"), Material.METAL);

        electricFurnaceBlock = new ElectricFurnace(NAMESPACE.id("electric_furnace"), Material.METAL);
        compressorBlock = new CompressorBlock(NAMESPACE.id("compressor"), Material.METAL);
        inductionFurnaceBlock = new InductionFurnaceBlock(NAMESPACE.id("induction_furnace"), Material.METAL);
        maceratorBlock = new MaceratorBlock(NAMESPACE.id("macerator"), Material.METAL);
        cannerBlock = new CannerBlock(NAMESPACE.id("canner"), Material.METAL);
        electrolyzerBlock = new ElectrolyzerBlock(NAMESPACE.id("electrolyzer"), Material.METAL);
        extractorBlock = new ExtractorBlock(NAMESPACE.id("extractor"), Material.METAL);

        ironFurnaceBlock = new IronFurnaceBlock(NAMESPACE.id("iron_furnace"), Material.METAL);

        batBoxBlock = new BatBoxBlock(NAMESPACE.id("battery_box"), Material.METAL);
        energyStorageUnitBlock = new ESUBlock(NAMESPACE.id("energy_storage_unit"), Material.METAL);
        megaEnergyStorageUnitBlock = new MESUBlock(NAMESPACE.id("mega_energy_storage_unit"), Material.METAL);

        tinCableBlock = new TemplateCableBlock(NAMESPACE.id("tin_cable"), WireMaterial.TIN, PIXEL_SIZE * 2);
        copperCableBlock = new TemplateCableBlock(NAMESPACE.id("copper_cable"), WireMaterial.COPPER, PIXEL_SIZE * 2);
        copperInsulatedCableBlock = new TemplateCableBlock(NAMESPACE.id("copper_cable_insulated"), WireMaterial.COPPER_INSULATED, PIXEL_SIZE * 6);
        goldCableBlock = new TemplateCableBlock(NAMESPACE.id("gold_cable"), WireMaterial.GOLD, PIXEL_SIZE * 2);
        goldInsulatedCableBlock = new TemplateCableBlock(NAMESPACE.id("gold_cable_insulated"), WireMaterial.GOLD_INSULATED, PIXEL_SIZE * 6);
        goldInsulatedX2CableBlock = new TemplateCableBlock(NAMESPACE.id("gold_cable_insulated_x2"), WireMaterial.GOLD_INSULATED_X2, PIXEL_SIZE * 10);
        refinedIronCableBlock = new TemplateCableBlock(NAMESPACE.id("refined_iron_cable"), WireMaterial.REFINED_IRON, PIXEL_SIZE * 2);
        refinedIronInsulatedCableBlock = new TemplateCableBlock(NAMESPACE.id("refined_iron_cable_insulated"), WireMaterial.REFINED_IRON_INSULATED, PIXEL_SIZE * 6);
        refinedIronInsulatedX2CableBlock = new TemplateCableBlock(NAMESPACE.id("refined_iron_cable_insulated_x2"), WireMaterial.REFINED_IRON_INSULATED_X2, PIXEL_SIZE * 10);
        refinedIronInsulatedX3CableBlock = new TemplateCableBlock(NAMESPACE.id("refined_iron_cable_insulated_x3"), WireMaterial.REFINED_IRON_INSULATED_X3, PIXEL_SIZE * 14);
        glassFibreCableBlock = new TemplateCableBlock(NAMESPACE.id("glass_fibre_cable"), WireMaterial.GLASS_FIBRE, PIXEL_SIZE * 4);

        copperOreBlock = new GTTemplateBlock(NAMESPACE.id("copper_ore"), Material.STONE, STONE_SOUND_GROUP);
        tinOreBlock = new GTTemplateBlock(NAMESPACE.id("tin_ore"), Material.STONE, STONE_SOUND_GROUP);
        uraniumOreBlock = new GTTemplateBlock(NAMESPACE.id("uranium_ore"), Material.STONE, STONE_SOUND_GROUP);

        bronzeBlock = new GTTemplateBlock(NAMESPACE.id("bronze_block"), Material.METAL, METAL_SOUND_GROUP);
        copperBlock = new GTTemplateBlock(NAMESPACE.id("copper_block"), Material.METAL, METAL_SOUND_GROUP);
        tinBlock = new GTTemplateBlock(NAMESPACE.id("tin_block"), Material.METAL, METAL_SOUND_GROUP);
        uraniumBlock = new GTTemplateBlock(NAMESPACE.id("uranium_block"), Material.METAL, METAL_SOUND_GROUP);
        refinedIronBlock = new GTTemplateBlock(NAMESPACE.id("refined_iron"), Material.METAL, METAL_SOUND_GROUP);

        reinforcedGlassBlock = new GTGlassBlock(NAMESPACE.id("reinforced_glass"));
        reinforcedStoneBlock = new GTTemplateBlock(NAMESPACE.id("reinforced_stone"), Material.STONE, STONE_SOUND_GROUP);

        resinSheetBlock = new GTSheetBlock(NAMESPACE.id("resin_sheet"), GTSheetBlock.Type.STICKY);
        rubberSheetBlock = new GTSheetBlock(NAMESPACE.id("rubber_sheet"), GTSheetBlock.Type.BOUNCY);

        machineBlock = new GTTemplateBlock(NAMESPACE.id("machine_block"), Material.METAL, METAL_SOUND_GROUP);
        advancedMachineBlock = new GTTemplateBlock(NAMESPACE.id("advanced_machine_block"), Material.METAL, METAL_SOUND_GROUP);

        constructionFoamBlock = new GTFoamBlock(NAMESPACE.id("construction_foam"));
        hardenedConstructionFoamBlock = new GTHardenedFoamBlock(NAMESPACE.id("hardened_construction_foam"));
        dynamiteBlock = new GTDynamiteBlock(NAMESPACE.id("dynamite"));
        metalScaffoldBlock = new GTScaffoldBlock(NAMESPACE.id("metal_scaffold"), new Material(Material.METAL.mapColor).setDestroyPistonBehavior(), METAL_SOUND_GROUP, 7);
        miningPipeBlock = new MiningPipeBlock(NAMESPACE.id("mining_pipe"), Material.METAL, METAL_SOUND_GROUP);
        personalSafeBlock = new GTSafeBlock(NAMESPACE.id("personal_safe"));
        reinforcedDoorBlock = new GTDoorBlock(NAMESPACE.id("reinforced_door_block"));
        teleporterBlock = new TeleporterBlock(NAMESPACE.id("teleporter"));
        teslaCoilBlock = new TeslaCoilBlock(NAMESPACE.id("tesla_coil"));
        woodenScaffoldBlock = new GTScaffoldBlock(NAMESPACE.id("wooden_scaffold"), Material.LEAVES, WOOD_SOUND_GROUP, 3);

        lvTransformerBlock = new TransformerBlock(NAMESPACE.id("lv_transformer"), Material.WOOD, LVTransformerBlockEntity.class);
        mvTransformerBlock = new TransformerBlock(NAMESPACE.id("mv_transformer"), Material.METAL, MVTransformerBlockEntity.class);
        hvTransformerBlock = new TransformerBlock(NAMESPACE.id("hv_transformer"), Material.METAL, HVTransformerBlockEntity.class);

        thermalGeneratorBlock = new GTThermalGeneratorBlock(NAMESPACE.id("thermal_generator"));
        dynamoBlock = new DynamoBlock(NAMESPACE.id("dynamo"), Material.METAL);
        solarGeneratorBlock = new GTSolarPanelBlock(NAMESPACE.id("solar_generator"));

        waterWheelBlock = new WaterWheelBlock(NAMESPACE.id("water_wheel"), Material.WOOD);
        windSailsBlock = new WindSailsBlock(NAMESPACE.id("wind_sails"), Material.WOOD);

        refinedIronFenceBlock = new GTFenceBlock(NAMESPACE.id("refined_iron_fence"));

        recyclerBlock = new RecyclerBlock(NAMESPACE.id("recycler"));
        illuminatorBlock = new IlluminatorBlock(NAMESPACE.id("illuminator"));
        minerBlock = new MinerBlock(NAMESPACE.id("miner"));
        pumpBlock = new PumpBlock(NAMESPACE.id("pump"), Material.METAL);
        magnetizerBlock = new MagnetizerBlock(NAMESPACE.id("magnetizer"));
        reactorChamberBlock = new ReactorChamberBlock(NAMESPACE.id("reactor_chamber"));
        reactorCoreBlock = new ReactorCoreBlock(NAMESPACE.id("reactor_core"));
        massFabricatorBlock = new MassFabricatorBlock(NAMESPACE.id("mass_fabricator"));
        industrialTNTBlock = new GTExplosiveBlock(NAMESPACE.id("industrial_tnt"), Material.TNT, 40, 4);
        nukeBlock = new GTExplosiveBlock(NAMESPACE.id("nuke"), Material.TNT, 120, 55, true);

        rubberLogBlock = new RubberLogBlock(NAMESPACE.id("rubber_log"));
        rubberLeavesBlock = new LeavesBlockTemplate(NAMESPACE.id("rubber_leaves"));
    }

    @EventListener
    private static void tileEntityInit(BlockEntityRegisterEvent event) {
        event.register(GeneratorBlockEntity.class, NAMESPACE.id("generator").toString());
        event.register(CompressorBlockEntity.class, NAMESPACE.id("compressor").toString());
        event.register(ElectricFurnaceBlockEntity.class, NAMESPACE.id("electric_furnace").toString());
        event.register(InductionFurnaceBlockEntity.class, NAMESPACE.id("induction_furnace").toString());
        event.register(MaceratorBlockEntity.class, NAMESPACE.id("macerator").toString());
        event.register(CannerBlockEntity.class, NAMESPACE.id("canner").toString());
        event.register(ElectrolyzerBlockEntity.class, NAMESPACE.id("electrolyzer").toString());
        event.register(ExtractorBlockEntity.class, NAMESPACE.id("extractor").toString());
        event.register(PumpBlockEntity.class, NAMESPACE.id("pump").toString());

        event.register(IronFurnaceBlockEntity.class, NAMESPACE.id("iron_furnace").toString());

        event.register(LVTransformerBlockEntity.class, NAMESPACE.id("lv_transformer").toString());
        event.register(MVTransformerBlockEntity.class, NAMESPACE.id("mv_transformer").toString());
        event.register(HVTransformerBlockEntity.class, NAMESPACE.id("hv_transformer").toString());

        event.register(BatBoxBlockEntity.class, NAMESPACE.id("battery_box").toString());
        event.register(ESUBlockEntity.class, NAMESPACE.id("esu").toString());
        event.register(MESUBlockEntity.class, NAMESPACE.id("mesu").toString());

        event.register(DynamoBlockEntity.class, NAMESPACE.id("water_mill").toString());
        event.register(WaterWheelBlockEntity.class, NAMESPACE.id("water_wheel").toString());
        event.register(WindSailsBlockEntity.class, NAMESPACE.id("wind_sails").toString());
    }
}
