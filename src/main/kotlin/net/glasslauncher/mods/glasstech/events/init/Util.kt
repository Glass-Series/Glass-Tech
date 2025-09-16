package net.glasslauncher.mods.glasstech.events.init

import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.entity.player.PlayerEntity
import net.modificationstation.stationapi.api.util.math.Direction
import kotlin.math.floor

fun PlayerEntity.placementFacing(): Direction {
    val direction = floor((this.yaw * 4.0f / 360.0f).toDouble() + 0.5).toInt() and 3
    return when (direction) {
        0 -> Direction.NORTH
        1 -> Direction.EAST
        2 -> Direction.SOUTH
        3 -> Direction.WEST
        else -> Direction.NORTH
    }
}
