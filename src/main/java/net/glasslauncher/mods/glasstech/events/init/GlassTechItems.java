package net.glasslauncher.mods.glasstech.events.init;

import net.danygames2014.nyalib.event.ItemCapabilityProviderRegisterEvent;
import net.glasslauncher.mods.glasstech.item.SingleUsePowerCapability;
import net.glasslauncher.mods.glasstech.item.SingleUsePowerCapabilityProvider;
import net.mine_diver.unsafeevents.listener.EventListener;
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
    }

    @EventListener
    private static void registerCapabilities(ItemCapabilityProviderRegisterEvent event) {
        event.register(SingleUsePowerCapability.IDENTIFIER, new SingleUsePowerCapabilityProvider());

    }
}
