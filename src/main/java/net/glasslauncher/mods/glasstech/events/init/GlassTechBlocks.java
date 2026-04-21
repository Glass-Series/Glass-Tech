package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.WireMaterial;
import net.glasslauncher.mods.glasstech.blocks.*;
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
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.math.Direction;

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
    public static Block industrialTNTBlock;
    public static Block nukeBlock;

    public static Block rubberLogBlock;
    public static Block rubberLeavesBlock;

    public static Block waterWheelBlock;

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
        miningPipeBlock = new GTTemplateBlock(NAMESPACE.id("mining_pipe"), Material.METAL, METAL_SOUND_GROUP);
        personalSafeBlock = new GTSafeBlock(NAMESPACE.id("personal_safe"));
        reinforcedDoorBlock = new GTDoorBlock(NAMESPACE.id("reinforced_door_block"));
        teleporterBlock = new TeleporterBlock(NAMESPACE.id("teleporter"));
        teslaCoilBlock = new TeslaCoilBlock(NAMESPACE.id("tesla_coil"));
        woodenScaffoldBlock = new GTScaffoldBlock(NAMESPACE.id("wooden_scaffold"), Material.LEAVES, WOOD_SOUND_GROUP, 3);

        lvTransformerBlock = new GTTransformerBlock(NAMESPACE.id("lv_transformer"), Material.WOOD, VoltageTier.LV);
        mvTransformerBlock = new GTTransformerBlock(NAMESPACE.id("mv_transformer"), Material.METAL, VoltageTier.MV);
        hvTransformerBlock = new GTTransformerBlock(NAMESPACE.id("hv_transformer"), Material.METAL, VoltageTier.HV);

        thermalGeneratorBlock = new GTThermalGeneratorBlock(NAMESPACE.id("thermal_generator"));
        waterGeneratorBlock = new GTWaterMillBlock(NAMESPACE.id("water_generator"));
        solarGeneratorBlock = new GTSolarPanelBlock(NAMESPACE.id("solar_generator"));
        windGeneratorBlock = new GTWindMillBlock(NAMESPACE.id("wind_generator"));

        refinedIronFenceBlock = new GTFenceBlock(NAMESPACE.id("refined_iron_fence"));

        recyclerBlock = new RecyclerBlock(NAMESPACE.id("recycler"));
        illuminatorBlock = new IlluminatorBlock(NAMESPACE.id("illuminator"));
        minerBlock = new MinerBlock(NAMESPACE.id("miner"));
        pumpBlock = new PumpBlock(NAMESPACE.id("pump"));
        magnetizerBlock = new MagnetizerBlock(NAMESPACE.id("magnetizer"));
        reactorChamberBlock = new ReactorChamberBlock(NAMESPACE.id("reactor_chamber"));
        reactorCoreBlock = new ReactorCoreBlock(NAMESPACE.id("reactor_core"));
        massFabricatorBlock = new MassFabricatorBlock(NAMESPACE.id("mass_fabricator"));
        industrialTNTBlock = new GTExplosiveBlock(NAMESPACE.id("industrial_tnt"), Material.TNT, 40, 4);
        nukeBlock = new GTExplosiveBlock(NAMESPACE.id("nuke"), Material.TNT, 120, 55, true);

        rubberLogBlock = new RubberLogBlock(NAMESPACE.id("rubber_log_block"));
        rubberLeavesBlock = new LeavesBlockTemplate(NAMESPACE.id("rubber_leaves_block"));

        waterWheelBlock = new GTTemplateBlock(NAMESPACE.id("water_wheel"), Material.WOOD, WOOD_SOUND_GROUP) {
            @Override
            public BlockState getPlacementState(ItemPlacementContext context) {
                return super.getPlacementState(context).with(Properties.FACING, context.getHorizontalPlayerFacing().getOpposite());
            }

            @Override
            public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
                super.appendProperties(builder);
                builder.add(Properties.FACING);
            }
        };
        waterWheelBlock.setDefaultState(waterWheelBlock.getDefaultState().with(Properties.FACING, Direction.NORTH));
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
