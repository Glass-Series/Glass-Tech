package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.blocks.cable.IronCableBlock;
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

public class InitListener {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    @SuppressWarnings("UnstableApiUsage")
    public static final Namespace NAMESPACE = Namespace.resolve();
    public static final Logger LOGGER = NAMESPACE.getLogger("GlassTech");

    public static Block generatorBlock;
    public static Block furnaceBlock;
    public static Block ironCableBlock;

    @EventListener
    private static void init(InitEvent event) {
        LOGGER.info(NAMESPACE.toString());
    }

    @EventListener
    private static void blockInit(BlockRegistryEvent event) {
        generatorBlock = new GeneratorBlock(NAMESPACE.id("generator"), Material.METAL).setTranslationKey(NAMESPACE.id("generator"));
        furnaceBlock = new ElectricFurnace(NAMESPACE.id("furnace"), Material.METAL).setTranslationKey(NAMESPACE.id("furnace"));
        furnaceBlock = new MaceratorBlock(NAMESPACE.id("macerator"), Material.METAL).setTranslationKey(NAMESPACE.id("macerator"));
        ironCableBlock = new IronCableBlock(NAMESPACE.id("iron_cable")).setTranslationKey(NAMESPACE.id("iron_cable"));
    }

    @EventListener
    private static void tileEntityInit(BlockEntityRegisterEvent event) {
        event.register(GeneratorBlockEntity.class, NAMESPACE.id("generator").toString());
        event.register(ElectricFurnaceBlockEntity.class, NAMESPACE.id("furnace").toString());
        event.register(MaceratorBlockEntity.class, NAMESPACE.id("macerator").toString());
    }
}
