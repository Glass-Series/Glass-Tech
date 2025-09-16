package net.glasslauncher.mods.glasstech.blocks.cable

import net.glasslauncher.mods.glasstech.GTWireMaterial
import net.glasslauncher.mods.glasstech.blocks.cable.IronCableBlockEntity.Companion.DIR_PROPS
import net.minecraft.block.Block
import net.minecraft.block.FurnaceBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.material.Material
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.modificationstation.stationapi.api.block.BlockState
import net.modificationstation.stationapi.api.item.ItemPlacementContext
import net.modificationstation.stationapi.api.state.StateManager
import net.modificationstation.stationapi.api.state.property.Properties
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity
import net.modificationstation.stationapi.api.util.Identifier
import net.teamterminus.machineessentials.energy.electric.api.VoltageTier
import net.teamterminus.machineessentials.energy.electric.api.WireMaterial
import net.teamterminus.machineessentials.energy.electric.api.WireProperties
import net.teamterminus.machineessentials.energy.electric.template.ElectricBlock
import net.teamterminus.machineessentials.energy.electric.template.ElectricWireBlock
import net.teamterminus.machineessentials.network.NetworkComponentBlock
import net.teamterminus.machineessentials.network.NetworkManager
import net.teamterminus.machineessentials.network.NetworkType

class IronCableBlock(identifier: Identifier) : ElectricWireBlock(identifier, Material.WOOL, WireProperties(1, true, false, GTWireMaterial.IRON)), NetworkComponentBlock {

    init {
        resistance = 1f
        hardness = 0.5f
        defaultState = defaultState
            .with(Properties.NORTH, false)
            .with(Properties.SOUTH, false)
            .with(Properties.EAST, false)
            .with(Properties.WEST, false)
            .with(Properties.UP, false)
            .with(Properties.DOWN, false)
    }

    override fun getPlacementState(context: ItemPlacementContext): BlockState {
        return updateModel(context.world, context.blockPos.x, context.blockPos.y, context.blockPos.z)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.NORTH)
        builder.add(Properties.SOUTH)
        builder.add(Properties.EAST)
        builder.add(Properties.WEST)
        builder.add(Properties.UP)
        builder.add(Properties.DOWN)
        super.appendProperties(builder)
    }

    override fun createBlockEntity(): BlockEntity {
        return IronCableBlockEntity()
    }

    override fun getType(): NetworkType {
        return NetworkType.ELECTRIC
    }

    override fun isFullCube(): Boolean {
        return false
    }

    override fun isOpaque(): Boolean {
        return false
    }

    override fun onBreak(world: World, x: Int, y: Int, z: Int) {
        if (!FurnaceBlock.ignoreBlockRemoval) {
            super.onBreak(world, x, y, z)
        }
    }

    override fun onPlaced(world: World, x: Int, y: Int, z: Int) {
        if (!FurnaceBlock.ignoreBlockRemoval) {
            super.onPlaced(world, x, y, z)
        }
    }

    override fun neighborUpdate(world: World, x: Int, y: Int, z: Int, id: Int) {
        super.neighborUpdate(world, x, y, z, id)

        FurnaceBlock.ignoreBlockRemoval = true
        world.setBlockStateWithNotify(x, y, z, updateModel(world, x, y, z))
        FurnaceBlock.ignoreBlockRemoval = false
    }

    fun updateModel(world: World, x: Int, y: Int, z: Int): BlockState {
        var state = defaultState
        DIR_PROPS.forEach {
            state = state.with(it.key, world.getBlockState(x + it.value.offsetX, y + it.value.offsetY, z + it.value.offsetZ).block is ElectricBlock)
        }
        return state
    }
}