package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.blocks.cable.IronCableBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.furnace.Furnace;
import net.glasslauncher.mods.glasstech.blocks.machine.furnace.FurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.furnace.FurnaceScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorScreen;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.util.Namespace;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class InitListener {
    @SuppressWarnings("UnstableApiUsage")
    public static final Namespace NAMESPACE = Namespace.resolve();
    public static final Logger LOGGER = NAMESPACE.getLogger("GlassTech");

    public static Block generatorBlock;
    public static Block furnaceBlock;
    public static Block ironCableBlock;

    public static Atlas.Sprite energySlotIndex = null;

    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    @EventListener
    private static void init(InitEvent event) {
        LOGGER.info(NAMESPACE.toString());
    }

    @EventListener
    private static void blockInit(BlockRegistryEvent event) {
        generatorBlock = new GeneratorBlock(NAMESPACE.id("generator"), Material.METAL).setTranslationKey(NAMESPACE.id("generator"));
        furnaceBlock = new Furnace(NAMESPACE.id("furnace"), Material.METAL).setTranslationKey(NAMESPACE.id("furnace"));
        ironCableBlock = new IronCableBlock(NAMESPACE.id("iron_cable")).setTranslationKey(NAMESPACE.id("iron_cable"));
    }

    @EventListener
    private static void tileEntityInit(BlockEntityRegisterEvent event) {
        event.register(GeneratorBlockEntity.class, NAMESPACE.id("generator").toString());
        event.register(FurnaceBlockEntity.class, NAMESPACE.id("furnace").toString());
    }

    @EventListener
    private static void screenInit(GuiHandlerRegistryEvent event) {
        event.register(NAMESPACE.id("generator"), new GuiHandler((player, inventory, packet) -> new GeneratorScreen(player.inventory, (GeneratorBlockEntity) inventory), GeneratorBlockEntity::new));
        event.register(NAMESPACE.id("furnace"), new GuiHandler((player, inventory, packet) -> new FurnaceScreen(player.inventory, (FurnaceBlockEntity) inventory), FurnaceBlockEntity::new));
    }

    @EventListener
    private static void textureInit(TextureRegisterEvent event) {
        energySlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/battery_slot"));
    }
}
