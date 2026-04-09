package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.WireMaterial;
import net.glasslauncher.mods.glasstech.blocks.TemplateCableBlock;
import net.glasslauncher.mods.glasstech.blocks.batbox.BatBoxBlock;
import net.glasslauncher.mods.glasstech.blocks.batbox.BatBoxBlockEntity;
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
import net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace.InductionFurnaceBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace.InductionFurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorBlockEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;

import java.lang.invoke.MethodHandles;

import static net.glasslauncher.mods.glasstech.GlassTech.LOGGER;
import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;
import static net.glasslauncher.mods.glasstech.blocks.TemplateCableBlock.PIXEL_SIZE;

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
    public static Block reinforcedDoorBlock;
    public static Block teleporterBlock;
    public static Block teslaCoilBlock;
    public static Block woodenScaffoldBlock;

    public static Block lvTransformerBlock;
    public static Block mvTransformerBlock;
    public static Block hvTransformerBlock;

    public static Block geothermalGeneratorBlock;
    public static Block waterGeneratorBlock;
    public static Block solarGeneratorBlock;
    public static Block windGeneratorBlock;

    public static Block refinedIronFenceBlock;

    public static Block recyclerBlock;
    public static Block illuminatorBlock;
    public static Block minerBlock;
    public static Block pumpBlock;
    public static Block magnetizerBlock;
    public static Block reactorChamberBlock;
    public static Block reactorCoreBlock;
    public static Block massFabricatorBlock;
    public static Block woodScaffoldBlock;
    public static Block industrialTNTBlock;
    public static Block nukeBlock;

    public static Block rubberLogBlock;
    public static Block rubberLeavesBlock;

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
        energyStorageUnitBlock = new BatBoxBlock(NAMESPACE.id("energy_storage_unit"), Material.METAL);
        megaEnergyStorageUnitBlock = new BatBoxBlock(NAMESPACE.id("mega_energy_storage_unit"), Material.METAL);

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

        copperOreBlock = new TemplateBlock(NAMESPACE.id("copper_ore"), Material.STONE).setTranslationKey(NAMESPACE.id("copper_ore"));
        tinOreBlock = new TemplateBlock(NAMESPACE.id("tin_ore"), Material.STONE).setTranslationKey(NAMESPACE.id("tin_ore"));
        uraniumOreBlock = new TemplateBlock(NAMESPACE.id("uranium_ore"), Material.STONE).setTranslationKey(NAMESPACE.id("uranium_ore"));

        bronzeBlock = new TemplateBlock(NAMESPACE.id("bronze_block"), Material.METAL).setTranslationKey(NAMESPACE.id("bronze"));
        copperBlock = new TemplateBlock(NAMESPACE.id("copper_block"), Material.METAL).setTranslationKey(NAMESPACE.id("copper"));
        tinBlock = new TemplateBlock(NAMESPACE.id("tin_block"), Material.METAL).setTranslationKey(NAMESPACE.id("tin"));
        uraniumBlock = new TemplateBlock(NAMESPACE.id("uranium_block"), Material.METAL).setTranslationKey(NAMESPACE.id("uranium"));
        refinedIronBlock = new TemplateBlock(NAMESPACE.id("refined_iron"), Material.METAL).setTranslationKey(NAMESPACE.id("refined_iron"));

        reinforcedGlassBlock = new TemplateBlock(NAMESPACE.id("reinforced_glass"), Material.STONE).setTranslationKey(NAMESPACE.id("reinforced_glass"));
        reinforcedStoneBlock = new TemplateBlock(NAMESPACE.id("reinforced_stone"), Material.STONE).setTranslationKey(NAMESPACE.id("reinforced_stone"));

        resinSheetBlock = new TemplateBlock(NAMESPACE.id("resin_sheet"), Material.WOOL).setTranslationKey(NAMESPACE.id("resin_sheet"));
        rubberSheetBlock = new TemplateBlock(NAMESPACE.id("rubber_sheet"), Material.WOOL).setTranslationKey(NAMESPACE.id("rubber_sheet"));

        machineBlock = new TemplateBlock(NAMESPACE.id("machine_block"), Material.METAL).setTranslationKey(NAMESPACE.id("machine"));
        advancedMachineBlock = new TemplateBlock(NAMESPACE.id("advanced_machine_block"), Material.METAL).setTranslationKey(NAMESPACE.id("advanced_machine"));

        constructionFoamBlock = new TemplateBlock(NAMESPACE.id("construction_foam"), Material.METAL).setTranslationKey(NAMESPACE.id("construction_foam"));
        hardenedConstructionFoamBlock = new TemplateBlock(NAMESPACE.id("hardened_construction_foam"), Material.METAL).setTranslationKey(NAMESPACE.id("hardened_construction_foam"));
        dynamiteBlock = new TemplateBlock(NAMESPACE.id("dynamite"), Material.METAL).setTranslationKey(NAMESPACE.id("dynamite"));
        metalScaffoldBlock = new TemplateBlock(NAMESPACE.id("metal_scaffold"), Material.METAL).setTranslationKey(NAMESPACE.id("metal_scaffold"));
        miningPipeBlock = new TemplateBlock(NAMESPACE.id("mining_pipe"), Material.METAL).setTranslationKey(NAMESPACE.id("mining_pipe"));
        personalSafeBlock = new TemplateBlock(NAMESPACE.id("personal_safe"), Material.METAL).setTranslationKey(NAMESPACE.id("personal_safe"));
        reinforcedDoorBlock = new TemplateBlock(NAMESPACE.id("reinforced_door"), Material.METAL).setTranslationKey(NAMESPACE.id("reinforced_door"));
        teleporterBlock = new TemplateBlock(NAMESPACE.id("teleporter"), Material.METAL).setTranslationKey(NAMESPACE.id("teleporter"));
        teslaCoilBlock = new TemplateBlock(NAMESPACE.id("tesla_coil"), Material.METAL).setTranslationKey(NAMESPACE.id("tesla_coil"));
        woodenScaffoldBlock = new TemplateBlock(NAMESPACE.id("wooden_scaffold"), Material.WOOD).setTranslationKey(NAMESPACE.id("wooden_scaffold"));

        lvTransformerBlock = new TemplateBlock(NAMESPACE.id("lv_transformer"), Material.WOOD).setTranslationKey(NAMESPACE.id("lv_transformer"));
        mvTransformerBlock = new TemplateBlock(NAMESPACE.id("mv_transformer"), Material.METAL).setTranslationKey(NAMESPACE.id("mv_transformer"));
        hvTransformerBlock = new TemplateBlock(NAMESPACE.id("hv_transformer"), Material.METAL).setTranslationKey(NAMESPACE.id("hv_transformer"));

        geothermalGeneratorBlock = new TemplateBlock(NAMESPACE.id("geothermal_generator"), Material.METAL).setTranslationKey(NAMESPACE.id("geothermal_generator"));
        waterGeneratorBlock = new TemplateBlock(NAMESPACE.id("water_generator"), Material.METAL).setTranslationKey(NAMESPACE.id("water_generator"));
        solarGeneratorBlock = new TemplateBlock(NAMESPACE.id("solar_generator"), Material.METAL).setTranslationKey(NAMESPACE.id("solar_generator"));
        windGeneratorBlock = new TemplateBlock(NAMESPACE.id("wind_generator"), Material.METAL).setTranslationKey(NAMESPACE.id("wind_generator"));

        refinedIronFenceBlock = new TemplateBlock(NAMESPACE.id("refined_iron_fence"), Material.METAL).setTranslationKey(NAMESPACE.id("refined_iron_fence"));

        recyclerBlock = new TemplateBlock(NAMESPACE.id("recycler"), Material.METAL).setTranslationKey(NAMESPACE.id("recycler"));
        illuminatorBlock = new TemplateBlock(NAMESPACE.id("illuminator"), Material.METAL).setTranslationKey(NAMESPACE.id("illuminator"));
        minerBlock = new TemplateBlock(NAMESPACE.id("miner"), Material.METAL).setTranslationKey(NAMESPACE.id("miner"));
        pumpBlock = new TemplateBlock(NAMESPACE.id("pump"), Material.METAL).setTranslationKey(NAMESPACE.id("pump"));
        magnetizerBlock = new TemplateBlock(NAMESPACE.id("magnetizer"), Material.METAL).setTranslationKey(NAMESPACE.id("magnetizer"));
        reactorChamberBlock = new TemplateBlock(NAMESPACE.id("reactor_chamber"), Material.METAL).setTranslationKey(NAMESPACE.id("reactor_chamber"));
        reactorCoreBlock = new TemplateBlock(NAMESPACE.id("reactor_core"), Material.METAL).setTranslationKey(NAMESPACE.id("reactor_core"));
        massFabricatorBlock = new TemplateBlock(NAMESPACE.id("mass_fabricator"), Material.METAL).setTranslationKey(NAMESPACE.id("mass_fabricator"));
        woodScaffoldBlock = new TemplateBlock(NAMESPACE.id("wood_scaffold"), Material.WOOD).setTranslationKey(NAMESPACE.id("wood_scaffold"));
        industrialTNTBlock = new TemplateBlock(NAMESPACE.id("industrial_tnt"), Material.TNT).setTranslationKey(NAMESPACE.id("industrial_t_n_t"));
        nukeBlock = new TemplateBlock(NAMESPACE.id("nuke"), Material.TNT).setTranslationKey(NAMESPACE.id("nuke"));

        rubberLogBlock = new TemplateBlock(NAMESPACE.id("rubber_log_block"), Material.WOOD).setTranslationKey(NAMESPACE.id("rubber_log_block"));
        rubberLeavesBlock = new TemplateBlock(NAMESPACE.id("rubber_leaves_block"), Material.LEAVES).setTranslationKey(NAMESPACE.id("rubber_leaves_block"));
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

        event.register(IronFurnaceBlockEntity.class, NAMESPACE.id("iron_furnace").toString());

        event.register(BatBoxBlockEntity.class, NAMESPACE.id("battery_box").toString());
    }
}
