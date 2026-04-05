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

import java.lang.invoke.MethodHandles;

import static net.glasslauncher.mods.glasstech.GlassTech.LOGGER;
import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;
import static net.glasslauncher.mods.glasstech.blocks.TemplateCableBlock.PIXEL_SIZE;

public class GlassTechBlocks {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    public static Block generatorBlock;

    public static Block furnaceBlock;
    public static Block compressorBlock;
    public static Block inductionFurnaceBlock;
    public static Block maceratorBlock;
    public static Block cannerBlock;
    public static Block electrolyzerBlock;
    public static Block extractorBlock;

    public static Block ironFurnaceBlock;

    public static Block batBoxBlock;
    public static Block energyStorageUnit;
    public static Block megaEnergyStorageUnit;

    public static Block tinCableBlock;
    public static Block copperCableBlock;
    public static Block copperInsulatedCableBlock;
    public static Block goldCableBlock;
    public static Block goldInsulatedCableBlock;
    public static Block goldInsulatedX2CableBlock;
    public static Block refinedIronCableBlock;
    public static Block refinedIronInsulatedCableBlock;
    public static Block refinedIronInsulatedX2CableBlock;
    public static Block refinedIronInsulatedX4CableBlock;
    public static Block glassFibreCableBlock;



    @EventListener
    private static void init(InitEvent event) {
        LOGGER.info(NAMESPACE.toString());
    }

    @EventListener
    private static void blockInit(BlockRegistryEvent event) {
        generatorBlock = new GeneratorBlock(NAMESPACE.id("generator"), Material.METAL);

        furnaceBlock = new ElectricFurnace(NAMESPACE.id("electric_furnace"), Material.METAL);
        compressorBlock = new CompressorBlock(NAMESPACE.id("compressor"), Material.METAL);
        inductionFurnaceBlock = new InductionFurnaceBlock(NAMESPACE.id("induction_furnace"), Material.METAL);
        maceratorBlock = new MaceratorBlock(NAMESPACE.id("macerator"), Material.METAL);
        cannerBlock = new CannerBlock(NAMESPACE.id("canner"), Material.METAL);
        electrolyzerBlock = new ElectrolyzerBlock(NAMESPACE.id("electrolyzer"), Material.METAL);
        extractorBlock = new ExtractorBlock(NAMESPACE.id("extractor"), Material.METAL);

        ironFurnaceBlock = new IronFurnaceBlock(NAMESPACE.id("iron_furnace"), Material.METAL);

        batBoxBlock = new BatBoxBlock(NAMESPACE.id("battery_box"), Material.METAL);
        energyStorageUnit = new BatBoxBlock(NAMESPACE.id("energy_storage_unit"), Material.METAL);
        megaEnergyStorageUnit = new BatBoxBlock(NAMESPACE.id("mega_energy_storage_unit"), Material.METAL);

        tinCableBlock = new TemplateCableBlock(NAMESPACE.id("tin_cable"), WireMaterial.TIN, PIXEL_SIZE * 2);
        copperCableBlock = new TemplateCableBlock(NAMESPACE.id("copper_cable"), WireMaterial.COPPER, PIXEL_SIZE * 2);
        copperInsulatedCableBlock = new TemplateCableBlock(NAMESPACE.id("copper_cable_insulated"), WireMaterial.COPPER_INSULATED, PIXEL_SIZE * 6);
        goldCableBlock = new TemplateCableBlock(NAMESPACE.id("gold_cable"), WireMaterial.GOLD, PIXEL_SIZE * 2);
        goldInsulatedCableBlock = new TemplateCableBlock(NAMESPACE.id("gold_cable_insulated"), WireMaterial.GOLD_INSULATED, PIXEL_SIZE * 6);
        goldInsulatedX2CableBlock = new TemplateCableBlock(NAMESPACE.id("gold_cable_insulated_x2"), WireMaterial.GOLD_INSULATED_X2, PIXEL_SIZE * 10);
        refinedIronCableBlock = new TemplateCableBlock(NAMESPACE.id("refined_iron_cable"), WireMaterial.REFINED_IRON, PIXEL_SIZE * 2);
        refinedIronInsulatedCableBlock = new TemplateCableBlock(NAMESPACE.id("refined_iron_cable_insulated"), WireMaterial.REFINED_IRON_INSULATED, PIXEL_SIZE * 6);
        refinedIronInsulatedX2CableBlock = new TemplateCableBlock(NAMESPACE.id("refined_iron_cable_insulated_x2"), WireMaterial.REFINED_IRON_INSULATED_X2, PIXEL_SIZE * 10);
        refinedIronInsulatedX4CableBlock = new TemplateCableBlock(NAMESPACE.id("refined_iron_cable_insulated_x4"), WireMaterial.REFINED_IRON_INSULATED_X4, PIXEL_SIZE * 14);
        glassFibreCableBlock = new TemplateCableBlock(NAMESPACE.id("glass_fibre_cable"), WireMaterial.GLASS_FIBRE, PIXEL_SIZE * 4);
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
