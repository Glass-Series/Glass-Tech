package net.glasslauncher.mods.glasstech.blocks.generator

import net.glasslauncher.mods.glassguis.packet.s2c.ScreenHandlerPropertyUpdateLongS2CPacket
import net.glasslauncher.mods.glasstech.gui.BatterySlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerListener
import net.minecraft.screen.slot.Slot

class GeneratorScreenHandler(val playerInventory: PlayerInventory, val generatorBlockEntity: GeneratorBlockEntity) : ScreenHandler() {

    init {
        addSlot(Slot(generatorBlockEntity, 0, 10, 40))
        addSlot(BatterySlot(generatorBlockEntity, 1, 32, 40))
        glassguis_setupPlayerInventory(8, 167, playerInventory)
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return generatorBlockEntity.canPlayerUse(player)
    }

    override fun addListener(listener: ScreenHandlerListener) {
        super.addListener(listener)
        // Ah yes, cursed shit, my favourite
        val player = listener as ServerPlayerEntity
        player.networkHandler.sendPacket(ScreenHandlerPropertyUpdateLongS2CPacket(syncId, 0, generatorBlockEntity.energy)
        )
    }

    override fun glassguis_setProperty(syncID: Int, long: Long) {
        if (syncID == 0) {
            generatorBlockEntity.energy = long
        }
    }
}