package net.glasslauncher.mods.glasstech.events.init

import net.glasslauncher.mods.glasstech.blocks.cable.IronCableBlock
import net.glasslauncher.mods.glasstech.blocks.cable.IronCableBlockEntity
import net.glasslauncher.mods.glasstech.blocks.furnace.Furnace
import net.glasslauncher.mods.glasstech.blocks.furnace.FurnaceBlockEntity
import net.glasslauncher.mods.glasstech.blocks.furnace.FurnaceScreen
import net.glasslauncher.mods.glasstech.blocks.generator.GeneratorBlock
import net.glasslauncher.mods.glasstech.blocks.generator.GeneratorBlockEntity
import net.glasslauncher.mods.glasstech.blocks.generator.GeneratorScreen
import net.mine_diver.unsafeevents.listener.EventListener
import net.minecraft.block.Block
import net.minecraft.block.FurnaceBlock
import net.minecraft.block.material.Material
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler.ScreenFactoryNoMessage
import net.modificationstation.stationapi.api.client.texture.Sprite
import net.modificationstation.stationapi.api.client.texture.SpriteIdentifier
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent
import net.modificationstation.stationapi.api.event.mod.InitEvent
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager
import net.modificationstation.stationapi.api.state.property.EnumProperty
import net.modificationstation.stationapi.api.util.Namespace
import net.modificationstation.stationapi.api.util.math.Direction
import org.apache.logging.log4j.Logger
import java.lang.invoke.MethodHandles

class InitListener {
    companion object {
        @Suppress("UnstableApiUsage")
        val NAMESPACE: Namespace = Namespace.resolve()
        val LOGGER: Logger = NAMESPACE.logger

        var generatorBlock: Block? = null
        var furnaceBlock: Block? = null
        var ironCableBlock: Block? = null

        var energySlotIndex: Atlas.Sprite? = null

        init {
            EntrypointManager.registerLookup(MethodHandles.lookup())
        }
    }

    @EventListener
    private fun init(event: InitEvent) {
        LOGGER.info(NAMESPACE.toString())
    }

    @EventListener
    private fun blockInit(event: BlockRegistryEvent) {
        generatorBlock = GeneratorBlock(NAMESPACE.id("generator"), Material.METAL).setTranslationKey(NAMESPACE.id("generator"))
        furnaceBlock = Furnace(NAMESPACE.id("furnace"), Material.METAL).setTranslationKey(NAMESPACE.id("furnace"))
        ironCableBlock = IronCableBlock(NAMESPACE.id("iron_cable")).setTranslationKey(NAMESPACE.id("iron_cable"))
    }

    @EventListener
    private fun tileEntityInit(event: BlockEntityRegisterEvent) {
        event.register(GeneratorBlockEntity::class.java, NAMESPACE.id("generator").toString())
        event.register(FurnaceBlockEntity::class.java, NAMESPACE.id("furnace").toString())
        event.register(IronCableBlockEntity::class.java, NAMESPACE.id("iron_cable").toString())
    }

    @EventListener
    private fun screenInit(event: GuiHandlerRegistryEvent) {
        event.register(NAMESPACE.id("generator"), GuiHandler(ScreenFactoryNoMessage { player, inventory -> GeneratorScreen(player.inventory, inventory as GeneratorBlockEntity) }, { GeneratorBlockEntity() }))
        event.register(NAMESPACE.id("furnace"), GuiHandler(ScreenFactoryNoMessage { player, inventory -> FurnaceScreen(player.inventory, inventory as FurnaceBlockEntity) }, { FurnaceBlockEntity() }))
    }

    @EventListener
    private fun textureInit(event: TextureRegisterEvent) {
        energySlotIndex = Atlases.getGuiItems().addTexture(NAMESPACE.id("item/battery_slot"))
    }
}