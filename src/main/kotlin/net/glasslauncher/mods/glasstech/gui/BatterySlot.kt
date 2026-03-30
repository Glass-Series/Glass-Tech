package net.glasslauncher.mods.glasstech.gui

import net.glasslauncher.mods.glassguis.compat.StationAPICompat
import net.glasslauncher.mods.glassguis.screen.widget.slot.BigHitboxSlot
import net.glasslauncher.mods.glasstech.events.init.InitListener
import net.minecraft.inventory.Inventory
import net.modificationstation.stationapi.api.client.StationRenderAPI

class BatterySlot(inventory: Inventory, index: Int, x: Int, y: Int) : BigHitboxSlot(inventory, index, x, y) {

    override fun renderExtras(): Boolean {

        val atlas = StationRenderAPI.getBakedModelManager().getAtlas(InitListener.energySlotIndex!!.sprite.atlasId)
        atlas.bindTexture()
        StationAPICompat.drawSprite(
            x,
            y,
            InitListener.energySlotIndex!!.width,
            InitListener.energySlotIndex!!.height,
            InitListener.energySlotIndex!!.sprite
        )
        return true
    }
}