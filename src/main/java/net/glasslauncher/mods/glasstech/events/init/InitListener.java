package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.WireMaterial;
import net.glasslauncher.mods.glasstech.WireProperties;
import net.glasslauncher.mods.glasstech.blocks.TemplateCableBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace.ElectricFurnace;
import net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace.ElectricFurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorBlockEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.util.Namespace;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

import static net.glasslauncher.mods.glasstech.blocks.TemplateCableBlock.PIXEL_SIZE;

public class InitListener {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    @SuppressWarnings("UnstableApiUsage")
    public static final Namespace NAMESPACE = Namespace.resolve();
    public static final Logger LOGGER = NAMESPACE.getLogger("GlassTech");

    public static Block generatorBlock;
    public static Block furnaceBlock;
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
        furnaceBlock = new MaceratorBlock(NAMESPACE.id("macerator"), Material.METAL).setTranslationKey(NAMESPACE.id("macerator"));
        copperCableBlock = new TemplateCableBlock(NAMESPACE.id("copper_cable"), WireProperties.createIfAbsent(InitListener.NAMESPACE.id("copper"), PIXEL_SIZE * 2, false, WireMaterial.COPPER)).setTranslationKey(NAMESPACE.id("copper_cable"));
        insulatedCopperCableBlock = new TemplateCableBlock(NAMESPACE.id("copper_cable_insulated"), WireProperties.createIfAbsent(InitListener.NAMESPACE.id("insulated_copper"), PIXEL_SIZE * 6, true, WireMaterial.COPPER)).setTranslationKey(NAMESPACE.id("insulated_copper_cable"));
    }

    @EventListener
    private static void tileEntityInit(BlockEntityRegisterEvent event) {
        event.register(GeneratorBlockEntity.class, NAMESPACE.id("generator").toString());
        event.register(ElectricFurnaceBlockEntity.class, NAMESPACE.id("furnace").toString());
        event.register(MaceratorBlockEntity.class, NAMESPACE.id("macerator").toString());
    }
}
