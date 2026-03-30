package net.glasslauncher.mods.glasstech.blocks.furnace

import net.glasslauncher.mods.glassguis.packet.s2c.ScreenHandlerPropertyUpdateLongS2CPacket
import net.glasslauncher.mods.glasstech.gui.BatterySlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerListener
import net.minecraft.screen.slot.Slot

class FurnaceScreenHandler(val playerInventory: PlayerInventory, val furnaceBlockEntity: FurnaceBlockEntity) : ScreenHandler() {

    init {
        addSlot(Slot(furnaceBlockEntity, 0, 10, 40))
        addSlot(BatterySlot(furnaceBlockEntity, 1, 32, 40))
        glassguis_setupPlayerInventory(8, 167, playerInventory)
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return furnaceBlockEntity.canPlayerUse(player)
    }

    override fun addListener(listener: ScreenHandlerListener) {
        super.addListener(listener)
        // Ah yes, cursed shit, my favourite
        val player = listener as ServerPlayerEntity
        player.networkHandler.sendPacket(ScreenHandlerPropertyUpdateLongS2CPacket(syncId, 0, furnaceBlockEntity.energy))
    }

    override fun glassguis_setProperty(syncID: Int, long: Long) {
        if (syncID == 0) {
            furnaceBlockEntity.energy = long
        }
    }
}