package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.WireMaterial;
import net.glasslauncher.mods.glasstech.WireProperties;
import net.glasslauncher.mods.glasstech.blocks.TemplateCableBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.canner.CannerBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.canner.CannerBlockEntity;
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
    public static Block inductionFurnaceBlock;
    public static Block maceratorBlock;
    public static Block cannerBlock;
    public static Block electrolyzerBlock;
    public static Block extractorBlock;

    public static Block copperCableBlock;
    public static Block insulatedCopperCableBlock;

    @EventListener
    private static void init(InitEvent event) {
        LOGGER.info(NAMESPACE.toString());
    }

    @EventListener
    private static void blockInit(BlockRegistryEvent event) {
        generatorBlock = new GeneratorBlock(NAMESPACE.id("generator"), Material.METAL).setTranslationKey(NAMESPACE.id("generator"));

        furnaceBlock = new ElectricFurnace(NAMESPACE.id("furnace"), Material.METAL).setTranslationKey(NAMESPACE.id("furnace"));
        inductionFurnaceBlock = new InductionFurnaceBlock(NAMESPACE.id("induction_furnace"), Material.METAL).setTranslationKey(NAMESPACE.id("induction_furnace"));
        maceratorBlock = new MaceratorBlock(NAMESPACE.id("macerator"), Material.METAL).setTranslationKey(NAMESPACE.id("macerator"));
        cannerBlock = new CannerBlock(NAMESPACE.id("canner"), Material.METAL).setTranslationKey(NAMESPACE.id("canner"));
        electrolyzerBlock = new ElectrolyzerBlock(NAMESPACE.id("electrolyzer"), Material.METAL).setTranslationKey(NAMESPACE.id("electrolyzer"));
        extractorBlock = new ExtractorBlock(NAMESPACE.id("extractor"), Material.METAL).setTranslationKey(NAMESPACE.id("extractor"));

        copperCableBlock = new TemplateCableBlock(NAMESPACE.id("copper_cable"), WireProperties.createIfAbsent(NAMESPACE.id("copper"), PIXEL_SIZE * 2, false, WireMaterial.COPPER)).setTranslationKey(NAMESPACE.id("copper_cable"));
        insulatedCopperCableBlock = new TemplateCableBlock(NAMESPACE.id("copper_cable_insulated"), WireProperties.createIfAbsent(NAMESPACE.id("insulated_copper"), PIXEL_SIZE * 6, true, WireMaterial.COPPER)).setTranslationKey(NAMESPACE.id("insulated_copper_cable"));
    }

    @EventListener
    private static void tileEntityInit(BlockEntityRegisterEvent event) {
        event.register(GeneratorBlockEntity.class, NAMESPACE.id("generator").toString());
        event.register(ElectricFurnaceBlockEntity.class, NAMESPACE.id("furnace").toString());
        event.register(InductionFurnaceBlockEntity.class, NAMESPACE.id("induction_furnace").toString());
        event.register(MaceratorBlockEntity.class, NAMESPACE.id("macerator").toString());
        event.register(CannerBlockEntity.class, NAMESPACE.id("canner").toString());
        event.register(ElectrolyzerBlockEntity.class, NAMESPACE.id("electrolyzer").toString());
        event.register(ExtractorBlockEntity.class, NAMESPACE.id("extractor").toString());
    }
}
