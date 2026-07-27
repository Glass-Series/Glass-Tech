package net.glasslauncher.mods.glasstech.events.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.glasslauncher.mods.glasstech.entity.GTNukeEntity;
import net.glasslauncher.mods.glasstech.entity.GTTntEntity;
import net.glasslauncher.mods.glasstech.entity.renderer.GTTntEntityRenderer;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.EntityHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;

import java.lang.invoke.MethodHandles;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class GlassTechEntities {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    @EventListener
    private static void initEntities(EntityRegister event) {
        event.register(GTTntEntity.class, NAMESPACE.id("tnt").toString());
        event.register(GTNukeEntity.class, NAMESPACE.id("nuke").toString());
    }

    @EventListener
    private static void initHandlers(EntityHandlerRegistryEvent event) {
        event.register(NAMESPACE.id("tnt"), GTTntEntity::new);
        event.register(NAMESPACE.id("nuke"), GTNukeEntity::new);
    }

    @Environment(EnvType.CLIENT)
    @EventListener
    private static void initRenderers(EntityRendererRegisterEvent event) {
        event.renderers.put(GTTntEntity.class, new GTTntEntityRenderer(GlassTechBlocks.industrialTNTBlock));
        event.renderers.put(GTNukeEntity.class, new GTTntEntityRenderer(GlassTechBlocks.nukeBlock));
    }
}
