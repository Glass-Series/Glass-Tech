package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.blocks.batbox.BatBoxBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.batbox.BatBoxScreen;
import net.glasslauncher.mods.glasstech.blocks.batbox.esu.ESUBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.batbox.esu.ESUScreen;
import net.glasslauncher.mods.glasstech.blocks.batbox.mesu.MESUBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.batbox.mesu.MESUScreen;
import net.glasslauncher.mods.glasstech.blocks.ironfurnace.IronFurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.ironfurnace.IronFurnaceScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.canner.CannerBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.canner.CannerScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.compressor.CompressorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.compressor.CompressorScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace.ElectricFurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace.ElectricFurnaceScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer.ElectrolyzerBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer.ElectrolyzerScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.extractor.ExtractorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.extractor.ExtractorScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.*;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.SolarGeneratorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.SolarGeneratorScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts.WaterWheelBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts.WindSailsBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace.InductionFurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace.InductionFurnaceScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.miner.MinerBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.miner.MinerScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.pump.PumpBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.pump.PumpScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.recycler.RecyclerBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.recycler.RecyclerScreen;
import net.glasslauncher.mods.glasstech.blocks.renderer.WaterWheelBlockEntityRenderer;
import net.glasslauncher.mods.glasstech.blocks.renderer.WindSailsBlockEntityRenderer;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.block.entity.BlockEntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;

import java.lang.invoke.MethodHandles;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class GlassTechClient {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    public static Atlas.Sprite batterySlotIndex = null;
    public static Atlas.Sprite batteryChargeSlotIndex = null;
    public static Atlas.Sprite fuelSlotIndex = null;
    public static Atlas.Sprite bucketSlotIndex = null;

    public static Atlas.Sprite emptyBattery;
    public static Atlas.Sprite emptyEnergyCrystal;
    public static Atlas.Sprite emptyLapotronCrystal;
    public static Atlas.Sprite emptyBatteryPack;

    public static Atlas.Sprite almostEmptyBattery;
    public static Atlas.Sprite almostEmptyEnergyCrystal;
    public static Atlas.Sprite almostEmptyLapotronCrystal;
    public static Atlas.Sprite almostEmptyBatteryPack;

    public static Atlas.Sprite almostFullBattery;
    public static Atlas.Sprite almostFullEnergyCrystal;
    public static Atlas.Sprite almostFullLapotronCrystal;
    public static Atlas.Sprite almostFullBatteryPack;

    public static Atlas.Sprite fullBattery;
    public static Atlas.Sprite fullEnergyCrystal;
    public static Atlas.Sprite fullLapotronCrystal;
    public static Atlas.Sprite fullBatteryPack;

    @EventListener
    private static void screenInit(GuiHandlerRegistryEvent event) {
        event.register(NAMESPACE.id("generator"), new GuiHandler((player, inventory, packet) -> new GeneratorScreen(player.inventory, (GeneratorBlockEntity) inventory), GeneratorBlockEntity::new));
        event.register(NAMESPACE.id("solar_generator"), new GuiHandler((player, inventory, packet) -> new SolarGeneratorScreen(player.inventory, (SolarGeneratorBlockEntity) inventory), SolarGeneratorBlockEntity::new));
        event.register(NAMESPACE.id("thermal_generator"), new GuiHandler((player, inventory, packet) -> new ThermalGeneratorScreen(player.inventory, (ThermalGeneratorBlockEntity) inventory), ThermalGeneratorBlockEntity::new));
        event.register(NAMESPACE.id("dynamo"), new GuiHandler((player, inventory, packet) -> new DynamoScreen(player.inventory, (DynamoBlockEntity) inventory), DynamoBlockEntity::new));

        event.register(NAMESPACE.id("compressor"), new GuiHandler((player, inventory, packet) -> new CompressorScreen(player.inventory, (CompressorBlockEntity) inventory), CompressorBlockEntity::new));
        event.register(NAMESPACE.id("electric_furnace"), new GuiHandler((player, inventory, packet) -> new ElectricFurnaceScreen(player.inventory, (ElectricFurnaceBlockEntity) inventory), ElectricFurnaceBlockEntity::new));
        event.register(NAMESPACE.id("induction_furnace"), new GuiHandler((player, inventory, packet) -> new InductionFurnaceScreen(player.inventory, (InductionFurnaceBlockEntity) inventory), InductionFurnaceBlockEntity::new));
        event.register(NAMESPACE.id("macerator"), new GuiHandler((player, inventory, packet) -> new MaceratorScreen(player.inventory, (MaceratorBlockEntity) inventory), MaceratorBlockEntity::new));
        event.register(NAMESPACE.id("canner"), new GuiHandler((player, inventory, packet) -> new CannerScreen(player.inventory, (CannerBlockEntity) inventory), CannerBlockEntity::new));
        event.register(NAMESPACE.id("electrolyzer"), new GuiHandler((player, inventory, packet) -> new ElectrolyzerScreen(player.inventory, (ElectrolyzerBlockEntity) inventory), ElectrolyzerBlockEntity::new));
        event.register(NAMESPACE.id("extractor"), new GuiHandler((player, inventory, packet) -> new ExtractorScreen(player.inventory, (ExtractorBlockEntity) inventory), ExtractorBlockEntity::new));
        event.register(NAMESPACE.id("recycler"), new GuiHandler((player, inventory, packet) -> new RecyclerScreen(player.inventory, (RecyclerBlockEntity) inventory), RecyclerBlockEntity::new));
        event.register(NAMESPACE.id("miner"), new GuiHandler((player, inventory, packet) -> new MinerScreen(player.inventory, (MinerBlockEntity) inventory), MinerBlockEntity::new));
        event.register(NAMESPACE.id("pump"), new GuiHandler((player, inventory, packet) -> new PumpScreen(player.inventory, (PumpBlockEntity) inventory), PumpBlockEntity::new));

        event.register(NAMESPACE.id("iron_furnace"), new GuiHandler((player, inventory, packet) -> new IronFurnaceScreen(player.inventory, (IronFurnaceBlockEntity) inventory), IronFurnaceBlockEntity::new));

        event.register(NAMESPACE.id("battery_box"), new GuiHandler((player, inventory, packet) -> new BatBoxScreen(player.inventory, (BatBoxBlockEntity) inventory), BatBoxBlockEntity::new));
        event.register(NAMESPACE.id("esu"), new GuiHandler((player, inventory, packet) -> new ESUScreen(player.inventory, (ESUBlockEntity) inventory), ESUBlockEntity::new));
        event.register(NAMESPACE.id("mesu"), new GuiHandler((player, inventory, packet) -> new MESUScreen(player.inventory, (MESUBlockEntity) inventory), MESUBlockEntity::new));
    }

    @EventListener
    private static void textureInit(TextureRegisterEvent event) {
        batterySlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/gui/battery_slot"));
        batteryChargeSlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/gui/battery_charge_slot"));
        fuelSlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/gui/fuel_slot"));
        bucketSlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/gui/bucket_slot"));

        GlassTechItems.copperIngot.setTexture(NAMESPACE.id("item/resource/copper_ingot"));
        GlassTechItems.advancedAlloy.setTexture(NAMESPACE.id("item/resource/advanced_alloy"));
        GlassTechItems.scrap.setTexture(NAMESPACE.id("item/resource/scrap"));
        GlassTechItems.copperDust.setTexture(NAMESPACE.id("item/resource/copper_dust"));
        GlassTechItems.tinIngot.setTexture(NAMESPACE.id("item/resource/tin_ingot"));
        GlassTechItems.tinDust.setTexture(NAMESPACE.id("item/resource/tin_dust"));
        GlassTechItems.bronzeIngot.setTexture(NAMESPACE.id("item/resource/bronze_ingot"));
        GlassTechItems.bronzeDust.setTexture(NAMESPACE.id("item/resource/bronze_dust"));
        GlassTechItems.goldDust.setTexture(NAMESPACE.id("item/resource/gold_dust"));
        GlassTechItems.ironDust.setTexture(NAMESPACE.id("item/resource/iron_dust"));
        GlassTechItems.iridium.setTexture(NAMESPACE.id("item/resource/iridium_ore"));
        GlassTechItems.iridiumPlate.setTexture(NAMESPACE.id("item/resource/iridium_plate"));
        GlassTechItems.uraniumIngot.setTexture(NAMESPACE.id("item/resource/uranium_ingot"));
        GlassTechItems.rubber.setTexture(NAMESPACE.id("item/resource/rubber"));
        GlassTechItems.resin.setTexture(NAMESPACE.id("item/resource/resin"));
        GlassTechItems.emptyCell.setTexture(NAMESPACE.id("item/resource/empty_cell"));
        GlassTechItems.lavaCell.setTexture(NAMESPACE.id("item/resource/lava_cell"));
        GlassTechItems.waterCell.setTexture(NAMESPACE.id("item/resource/water_cell"));
        GlassTechItems.mixedMetalIngot.setTexture(NAMESPACE.id("item/resource/mixed_metal_ingot"));
        GlassTechItems.refinedIronIngot.setTexture(NAMESPACE.id("item/resource/refined_iron_ingot"));
        GlassTechItems.uranium.setTexture(NAMESPACE.id("item/resource/uranium_ore"));
        GlassTechItems.uuMatter.setTexture(NAMESPACE.id("item/resource/uu_matter"));
        GlassTechItems.coalDust.setTexture(NAMESPACE.id("item/resource/coal_dust"));
        GlassTechItems.fertilizer.setTexture(NAMESPACE.id("item/resource/fertilizer"));
        GlassTechItems.carbonFibre.setTexture(NAMESPACE.id("item/resource/carbon_fiber"));
        GlassTechItems.clayDust.setTexture(NAMESPACE.id("item/resource/clay_dust"));
        GlassTechItems.carbonMesh.setTexture(NAMESPACE.id("item/resource/carbon_mesh"));
        GlassTechItems.coalBall.setTexture(NAMESPACE.id("item/resource/coal_ball"));
        GlassTechItems.industrialDiamond.setTexture(NAMESPACE.id("item/resource/industrial_diamond"));
        GlassTechItems.carbonPlate.setTexture(NAMESPACE.id("item/resource/carbon_plate"));
        GlassTechItems.cfPellet.setTexture(NAMESPACE.id("item/resource/cf_pellet"));
        GlassTechItems.compressedCoalBall.setTexture(NAMESPACE.id("item/resource/compressed_coal_ball"));
        GlassTechItems.coalChunk.setTexture(NAMESPACE.id("item/resource/coal_chunk"));
        GlassTechItems.silverDust.setTexture(NAMESPACE.id("item/resource/silver_dust"));

        GlassTechItems.circuit.setTexture(NAMESPACE.id("item/electronic_circuit"));
        GlassTechItems.advancedCircuit.setTexture(NAMESPACE.id("item/advanced_circuit"));
        GlassTechItems.scrapBox.setTexture(NAMESPACE.id("item/scrap_box"));

        emptyBattery = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/empty_battery"));
        emptyEnergyCrystal = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/empty_energy_crystal"));
        emptyLapotronCrystal = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/empty_lapotron_crystal"));
        emptyBatteryPack = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/empty_battery_pack"));

        GlassTechItems.reBattery.setTextureId(emptyBattery.index);
        GlassTechItems.energyCrystal.setTextureId(emptyEnergyCrystal.index);
        GlassTechItems.lapotronCrystal.setTextureId(emptyLapotronCrystal.index);
        GlassTechItems.batteryPack.setTextureId(emptyBatteryPack.index);

        almostEmptyBattery = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/almost_empty_battery"));
        almostEmptyEnergyCrystal = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/almost_empty_energy_crystal"));
        almostEmptyLapotronCrystal = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/almost_empty_lapotron_crystal"));
        almostEmptyBatteryPack = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/almost_empty_battery_pack"));

        almostFullBattery = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/almost_full_battery"));
        almostFullEnergyCrystal = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/almost_full_energy_crystal"));
        almostFullLapotronCrystal = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/almost_full_lapotron_crystal"));
        almostFullBatteryPack = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/almost_full_battery_pack"));

        fullBattery = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/full_battery"));
        fullEnergyCrystal = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/full_energy_crystal"));
        fullLapotronCrystal = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/full_lapotron_crystal"));
        fullBatteryPack = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/full_battery_pack"));

        GlassTechItems.wrench.setTexture(NAMESPACE.id("item/tool/wrench"));
        GlassTechItems.electricWrench.setTexture(NAMESPACE.id("item/tool/electric_wrench"));
        GlassTechItems.chainsaw.setTexture(NAMESPACE.id("item/tool/chainsaw"));
        GlassTechItems.diamondChainsaw.setTexture(NAMESPACE.id("item/tool/diamond_chainsaw"));
        GlassTechItems.drill.setTexture(NAMESPACE.id("item/tool/drill"));
        GlassTechItems.diamondDrill.setTexture(NAMESPACE.id("item/tool/diamond_drill"));
        GlassTechItems.bronzeAxe.setTexture(NAMESPACE.id("item/tool/bronze_axe"));
        GlassTechItems.bronzeHoe.setTexture(NAMESPACE.id("item/tool/bronze_hoe"));
        GlassTechItems.bronzePickaxe.setTexture(NAMESPACE.id("item/tool/bronze_pickaxe"));
        GlassTechItems.bronzeShovel.setTexture(NAMESPACE.id("item/tool/bronze_shovel"));
        GlassTechItems.bronzeSword.setTexture(NAMESPACE.id("item/tool/bronze_sword"));
        GlassTechItems.cfSprayer.setTexture(NAMESPACE.id("item/tool/cf_sprayer"));
        GlassTechItems.frequencyTransmitter.setTexture(NAMESPACE.id("item/tool/frequency_transmitter"));
        GlassTechItems.insulationCutter.setTexture(NAMESPACE.id("item/tool/insulation_cutter"));
        GlassTechItems.painter.setTexture(NAMESPACE.id("item/tool/painter"));
        GlassTechItems.nanoSaber.setTexture(NAMESPACE.id("item/tool/nano_saber"));

        GlassTechBlocks.copperOreBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/copper_ore")).index;
        GlassTechBlocks.tinOreBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/tin_ore")).index;
        GlassTechBlocks.uraniumOreBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/uranium_ore")).index;

        GlassTechBlocks.bronzeBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/bronze_block")).index;
        GlassTechBlocks.copperBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/copper_block")).index;
        GlassTechBlocks.tinBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/tin_block")).index;
        GlassTechBlocks.uraniumBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/uranium_block")).index;

        GlassTechBlocks.reinforcedGlassBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/reinforced_glass")).index;
        GlassTechBlocks.reinforcedStoneBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/reinforced_stone")).index;

        GlassTechBlocks.resinSheetBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/resin_sheet")).index;
        GlassTechBlocks.rubberSheetBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/resource/rubber_sheet")).index;

        GlassTechBlocks.constructionFoamBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/construction_foam")).index;
        GlassTechBlocks.hardenedConstructionFoamBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/hardened_construction_foam")).index;

        GlassTechBlocks.reinforcedDoorBlock.topTextureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/reinforced_door_top")).index;
        GlassTechBlocks.reinforcedDoorBlock.bottomTextureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/reinforced_door_bottom")).index;

        GlassTechBlocks.machineBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/mv")).index;
        GlassTechBlocks.advancedMachineBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/hv")).index;

        GlassTechBlocks.sealedPlanksBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/sealed_planks")).index;
        GlassTechBlocks.rubberLogBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/rubber_log")).index;
        GlassTechBlocks.rubberLogBlock.endTextureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/rubber_log_top")).index;

        GlassTechItems.compressedHydratedCoal.setTexture(NAMESPACE.id("item/resource/compressed_hydrated_coal"));
        GlassTechItems.compressedPlantBall.setTexture(NAMESPACE.id("item/resource/compressed_plant_ball"));
        GlassTechItems.coolantCell.setTexture(NAMESPACE.id("item/resource/coolant_cell"));
        GlassTechItems.depletedUraniumCell.setTexture(NAMESPACE.id("item/resource/depleted_uranium_cell"));
        GlassTechItems.hydratedCoalDust.setTexture(NAMESPACE.id("item/resource/hydrated_coal"));
        GlassTechItems.plantBall.setTexture(NAMESPACE.id("item/resource/plant_ball"));
        GlassTechItems.suBattery.setTexture(NAMESPACE.id("item/resource/single_use_battery"));
        GlassTechItems.uraniumCell.setTexture(NAMESPACE.id("item/resource/uranium_cell"));
        GlassTechItems.nanoBoots.setTexture(NAMESPACE.id("item/armor/nano_boots"));
        GlassTechItems.nanoChestplate.setTexture(NAMESPACE.id("item/armor/nano_chestplate"));
        GlassTechItems.nanoHelmet.setTexture(NAMESPACE.id("item/armor/nano_helmet"));
        GlassTechItems.nanoLeggings.setTexture(NAMESPACE.id("item/armor/nano_leggings"));
        GlassTechItems.quantumBoots.setTexture(NAMESPACE.id("item/armor/quantum_boots"));
        GlassTechItems.quantumChestplate.setTexture(NAMESPACE.id("item/armor/quantum_chestplate"));
        GlassTechItems.quantumHelmet.setTexture(NAMESPACE.id("item/armor/quantum_helmet"));
        GlassTechItems.quantumLeggings.setTexture(NAMESPACE.id("item/armor/quantum_leggings"));
        GlassTechItems.jetpack.setTexture(NAMESPACE.id("item/armor/jetpack"));
        GlassTechItems.electricJetpack.setTexture(NAMESPACE.id("item/armor/electric_jetpack"));
        GlassTechItems.bronzeBoots.setTexture(NAMESPACE.id("item/armor/bronze_boots"));
        GlassTechItems.bronzeChestplate.setTexture(NAMESPACE.id("item/armor/bronze_chestplate"));
        GlassTechItems.bronzeHelmet.setTexture(NAMESPACE.id("item/armor/bronze_helmet"));
        GlassTechItems.bronzeLeggings.setTexture(NAMESPACE.id("item/armor/bronze_leggings"));
        GlassTechItems.reactorCooler.setTexture(NAMESPACE.id("item/resource/reactor_cooler"));
        GlassTechItems.reactorPlating.setTexture(NAMESPACE.id("item/resource/reactor_plating"));
        GlassTechItems.emptyFuelCan.setTexture(NAMESPACE.id("item/resource/empty_fuel_can"));
        GlassTechItems.dynamiteRemote.setTexture(NAMESPACE.id("item/tool/dynamite_remote"));
        GlassTechItems.reEnrichedUraniumCell.setTexture(NAMESPACE.id("item/resource/re_enriched_uranium_cell"));
        GlassTechItems.nearDepletedUraniumCell.setTexture(NAMESPACE.id("item/resource/near_depleted_uranium_cell"));
        GlassTechItems.bioCell.setTexture(NAMESPACE.id("item/resource/bio_cell"));
        GlassTechItems.coalfuelCell.setTexture(NAMESPACE.id("item/resource/coalfuel_cell"));
        GlassTechItems.compressedHydratedCoalDust.setTexture(NAMESPACE.id("item/resource/compressed_hydrated_coal"));
        GlassTechItems.sail.setTexture(NAMESPACE.id("item/resource/sail"));
        GlassTechItems.reinforcedDoor.setTexture(NAMESPACE.id("item/reinforced_door"));
        GlassTechItems.biofuelCell.setTexture(NAMESPACE.id("item/resource/biofuel_cell"));
        GlassTechItems.fullFuelCan.setTexture(NAMESPACE.id("item/resource/full_fuel_can"));
        GlassTechItems.cannedFood.setTexture(NAMESPACE.id("item/resource/canned_food"));
        GlassTechItems.scanner.setTexture(NAMESPACE.id("item/tool/scanner"));
        GlassTechItems.advancedScanner.setTexture(NAMESPACE.id("item/tool/advanced_scanner"));
        GlassTechItems.stickyDynamite.setTexture(NAMESPACE.id("item/tool/sticky_dynamite"));
        GlassTechItems.rubberBoots.setTexture(NAMESPACE.id("item/armor/rubber_boots"));
        GlassTechItems.voltageMeter.setTexture(NAMESPACE.id("item/tool/voltage_meter"));
        GlassTechItems.miningLaser.setTexture(NAMESPACE.id("item/tool/mining_laser"));
        GlassTechItems.lappack.setTexture(NAMESPACE.id("item/armor/lappack"));
        GlassTechItems.cfPack.setTexture(NAMESPACE.id("item/armor/cf_pack"));
        GlassTechItems.compositeChestplate.setTexture(NAMESPACE.id("item/armor/composite_chestplate"));
        GlassTechItems.treeTap.setTexture(NAMESPACE.id("item/tool/tree_tap"));

        GlassTechBlocks.rubberSaplingBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("block/rubber_sapling")).index;
        GlassTechBlocks.rubberSaplingBlock.asItem().setTextureId(GlassTechBlocks.rubberSaplingBlock.textureId);
        GlassTechBlocks.rubberLeavesBlock.textureId = 52;
        GlassTechBlocks.rubberLeavesBlock.fastTextureId = 53;
    }

    @EventListener
    public static void blockEntityRendererInit(BlockEntityRendererRegisterEvent event) {
        event.renderers.put(WaterWheelBlockEntity.class, new WaterWheelBlockEntityRenderer());
        event.renderers.put(WindSailsBlockEntity.class, new WindSailsBlockEntityRenderer());
    }
}
