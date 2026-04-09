package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.blocks.batbox.BatBoxBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.batbox.BatBoxScreen;
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
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace.InductionFurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace.InductionFurnaceScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorScreen;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;

import java.lang.invoke.MethodHandles;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class ClientInitListener {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    public static Atlas.Sprite batterySlotIndex = null;
    public static Atlas.Sprite batteryChargeSlotIndex = null;
    public static Atlas.Sprite fuelSlotIndex = null;

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

        event.register(NAMESPACE.id("compressor"), new GuiHandler((player, inventory, packet) -> new CompressorScreen(player.inventory, (CompressorBlockEntity) inventory), CompressorBlockEntity::new));
        event.register(NAMESPACE.id("electric_furnace"), new GuiHandler((player, inventory, packet) -> new ElectricFurnaceScreen(player.inventory, (ElectricFurnaceBlockEntity) inventory), ElectricFurnaceBlockEntity::new));
        event.register(NAMESPACE.id("induction_furnace"), new GuiHandler((player, inventory, packet) -> new InductionFurnaceScreen(player.inventory, (InductionFurnaceBlockEntity) inventory), InductionFurnaceBlockEntity::new));
        event.register(NAMESPACE.id("macerator"), new GuiHandler((player, inventory, packet) -> new MaceratorScreen(player.inventory, (MaceratorBlockEntity) inventory), MaceratorBlockEntity::new));
        event.register(NAMESPACE.id("canner"), new GuiHandler((player, inventory, packet) -> new CannerScreen(player.inventory, (CannerBlockEntity) inventory), CannerBlockEntity::new));
        event.register(NAMESPACE.id("electrolyzer"), new GuiHandler((player, inventory, packet) -> new ElectrolyzerScreen(player.inventory, (ElectrolyzerBlockEntity) inventory), ElectrolyzerBlockEntity::new));
        event.register(NAMESPACE.id("extractor"), new GuiHandler((player, inventory, packet) -> new ExtractorScreen(player.inventory, (ExtractorBlockEntity) inventory), ExtractorBlockEntity::new));

        event.register(NAMESPACE.id("iron_furnace"), new GuiHandler((player, inventory, packet) -> new IronFurnaceScreen(player.inventory, (IronFurnaceBlockEntity) inventory), IronFurnaceBlockEntity::new));

        event.register(NAMESPACE.id("battery_box"), new GuiHandler((player, inventory, packet) -> new BatBoxScreen(player.inventory, (BatBoxBlockEntity) inventory), BatBoxBlockEntity::new));
    }

    @EventListener
    private static void textureInit(TextureRegisterEvent event) {
        batterySlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/gui/battery_slot"));
        batteryChargeSlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/gui/battery_charge_slot"));
        fuelSlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/gui/fuel_slot"));

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

        GlassTechBlocks.copperOreBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("copper_ore")).index;
        GlassTechBlocks.tinOreBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("tin_ore")).index;
        GlassTechBlocks.uraniumOreBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("uranium_ore")).index;

        GlassTechBlocks.bronzeBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("bronze_block")).index;
        GlassTechBlocks.copperBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("copper_block")).index;
        GlassTechBlocks.tinBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("tin_block")).index;
        GlassTechBlocks.uraniumBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("uranium_block")).index;

        GlassTechBlocks.reinforcedGlassBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("reinforced_glass")).index;
        GlassTechBlocks.reinforcedStoneBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("reinforced_stone")).index;

        GlassTechBlocks.resinSheetBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("resin_sheet")).index;
        GlassTechBlocks.rubberSheetBlock.textureId = Atlases.getTerrain().addTexture(NAMESPACE.id("rubber_sheet")).index;
    }
}
