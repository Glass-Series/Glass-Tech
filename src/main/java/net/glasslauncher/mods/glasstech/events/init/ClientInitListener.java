package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.blocks.machine.canner.CannerBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.canner.CannerScreen;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.KeyBinding;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingEvent;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import org.lwjgl.input.Keyboard;

import java.lang.invoke.MethodHandles;

import static net.glasslauncher.mods.glasstech.events.init.InitListener.NAMESPACE;

public class ClientInitListener {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    public static Atlas.Sprite energySlotIndex = null;

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
        event.register(NAMESPACE.id("furnace"), new GuiHandler((player, inventory, packet) -> new ElectricFurnaceScreen(player.inventory, (ElectricFurnaceBlockEntity) inventory), ElectricFurnaceBlockEntity::new));
        event.register(NAMESPACE.id("induction_furnace"), new GuiHandler((player, inventory, packet) -> new InductionFurnaceScreen(player.inventory, (InductionFurnaceBlockEntity) inventory), InductionFurnaceBlockEntity::new));
        event.register(NAMESPACE.id("macerator"), new GuiHandler((player, inventory, packet) -> new MaceratorScreen(player.inventory, (MaceratorBlockEntity) inventory), MaceratorBlockEntity::new));
        event.register(NAMESPACE.id("canner"), new GuiHandler((player, inventory, packet) -> new CannerScreen(player.inventory, (CannerBlockEntity) inventory), CannerBlockEntity::new));
        event.register(NAMESPACE.id("electrolyzer"), new GuiHandler((player, inventory, packet) -> new ElectrolyzerScreen(player.inventory, (ElectrolyzerBlockEntity) inventory), ElectrolyzerBlockEntity::new));
        event.register(NAMESPACE.id("extractor"), new GuiHandler((player, inventory, packet) -> new ExtractorScreen(player.inventory, (ExtractorBlockEntity) inventory), ExtractorBlockEntity::new));
    }

    @EventListener
    private static void textureInit(TextureRegisterEvent event) {
        energySlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/battery_slot"));

        InitListener.copperIngot.setTexture(NAMESPACE.id("item/resource/copper_ingot"));
        InitListener.advancedAlloy.setTexture(NAMESPACE.id("item/resource/advanced_alloy"));
        InitListener.scrap.setTexture(NAMESPACE.id("item/resource/scrap"));
        InitListener.copperDust.setTexture(NAMESPACE.id("item/resource/copper_dust"));
        InitListener.tinIngot.setTexture(NAMESPACE.id("item/resource/tin_ingot"));
        InitListener.tinDust.setTexture(NAMESPACE.id("item/resource/tin_dust"));
        InitListener.bronzeIngot.setTexture(NAMESPACE.id("item/resource/bronze_ingot"));
        InitListener.bronzeDust.setTexture(NAMESPACE.id("item/resource/bronze_dust"));
        InitListener.goldDust.setTexture(NAMESPACE.id("item/resource/gold_dust"));
        InitListener.ironDust.setTexture(NAMESPACE.id("item/resource/iron_dust"));
        InitListener.iridium.setTexture(NAMESPACE.id("item/resource/iridium_ore"));
        InitListener.iridiumPlate.setTexture(NAMESPACE.id("item/resource/iridium_plate"));
        InitListener.uraniumIngot.setTexture(NAMESPACE.id("item/resource/uranium_ingot"));
        InitListener.rubber.setTexture(NAMESPACE.id("item/resource/rubber"));
        InitListener.resin.setTexture(NAMESPACE.id("item/resource/resin"));
        InitListener.emptyCell.setTexture(NAMESPACE.id("item/resource/empty_cell"));
        InitListener.lavaCell.setTexture(NAMESPACE.id("item/resource/lava_cell"));
        InitListener.waterCell.setTexture(NAMESPACE.id("item/resource/water_cell"));
        InitListener.mixedMetalIngot.setTexture(NAMESPACE.id("item/resource/mixed_metal_ingot"));
        InitListener.refinedIronIngot.setTexture(NAMESPACE.id("item/resource/refined_iron_ingot"));
        InitListener.uranium.setTexture(NAMESPACE.id("item/resource/uranium_ore"));
        InitListener.uuMatter.setTexture(NAMESPACE.id("item/resource/uu_matter"));
        InitListener.coalDust.setTexture(NAMESPACE.id("item/resource/coal_dust"));
        InitListener.fertilizer.setTexture(NAMESPACE.id("item/resource/fertilizer"));

        InitListener.circuit.setTexture(NAMESPACE.id("item/electronic_circuit"));
        InitListener.advancedCircuit.setTexture(NAMESPACE.id("item/advanced_circuit"));
        InitListener.scrapBox.setTexture(NAMESPACE.id("item/scrap_box"));

        emptyBattery = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/empty_battery"));
        emptyEnergyCrystal = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/empty_energy_crystal"));
        emptyLapotronCrystal = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/empty_lapotron_crystal"));
        emptyBatteryPack = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/storage/empty_battery_pack"));

        InitListener.reBattery.setTextureId(emptyBattery.index);
        InitListener.energyCrystal.setTextureId(emptyEnergyCrystal.index);
        InitListener.lapotronCrystal.setTextureId(emptyLapotronCrystal.index);
        InitListener.batteryPack.setTextureId(emptyBatteryPack.index);

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

        InitListener.wrench.setTexture(NAMESPACE.id("item/tool/wrench"));
        InitListener.electricWrench.setTexture(NAMESPACE.id("item/tool/electric_wrench"));
        InitListener.chainsaw.setTexture(NAMESPACE.id("item/tool/chainsaw"));
        InitListener.diamondChainsaw.setTexture(NAMESPACE.id("item/tool/diamond_chainsaw"));
        InitListener.drill.setTexture(NAMESPACE.id("item/tool/drill"));
        InitListener.diamondDrill.setTexture(NAMESPACE.id("item/tool/diamond_drill"));
    }
}
