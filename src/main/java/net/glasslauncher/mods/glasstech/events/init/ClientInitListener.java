package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace.ElectricFurnaceBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace.ElectricFurnaceScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.GeneratorScreen;
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

import static net.glasslauncher.mods.glasstech.events.init.InitListener.NAMESPACE;

public class ClientInitListener {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    public static Atlas.Sprite energySlotIndex = null;

    @EventListener
    private static void screenInit(GuiHandlerRegistryEvent event) {
        event.register(NAMESPACE.id("generator"), new GuiHandler((player, inventory, packet) -> new GeneratorScreen(player.inventory, (GeneratorBlockEntity) inventory), GeneratorBlockEntity::new));
        event.register(NAMESPACE.id("furnace"), new GuiHandler((player, inventory, packet) -> new ElectricFurnaceScreen(player.inventory, (ElectricFurnaceBlockEntity) inventory), ElectricFurnaceBlockEntity::new));
        event.register(NAMESPACE.id("macerator"), new GuiHandler((player, inventory, packet) -> new MaceratorScreen(player.inventory, (MaceratorBlockEntity) inventory), MaceratorBlockEntity::new));
    }

    @EventListener
    private static void textureInit(TextureRegisterEvent event) {
        energySlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/battery_slot"));
    }
}
