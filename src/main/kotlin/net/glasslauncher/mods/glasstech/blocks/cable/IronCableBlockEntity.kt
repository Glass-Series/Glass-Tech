package net.glasslauncher.mods.glasstech.blocks.cable

import net.minecraft.block.FurnaceBlock
import net.modificationstation.stationapi.api.block.BlockState
import net.modificationstation.stationapi.api.state.property.Properties
import net.modificationstation.stationapi.api.util.math.Direction
import net.teamterminus.machineessentials.energy.electric.template.ElectricWireBlockEntity
import net.teamterminus.machineessentials.network.Network

class IronCableBlockEntity : ElectricWireBlockEntity() {
    companion object {
        // Fucking beta directions
        val DIR_PROPS = mapOf(
            Properties.NORTH to Direction.NORTH.rotateYClockwise(),
            Properties.SOUTH to Direction.SOUTH.rotateYClockwise(),
            Properties.EAST to Direction.EAST.rotateYClockwise(),
            Properties.WEST to Direction.WEST.rotateYClockwise(),
            Properties.UP to Direction.UP,
            Properties.DOWN to Direction.DOWN
        )
    }

    override fun onOvercurrent(amps: Long) {

    }

    override fun onOvervoltage(voltage: Long) {

    }
}