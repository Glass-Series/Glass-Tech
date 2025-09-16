package net.glasslauncher.mods.glasstech.blocks.furnace

import net.glasslauncher.mods.glasstech.events.init.InitListener
import net.glasslauncher.mods.glasstech.events.init.placementFacing
import net.minecraft.block.Block
import net.minecraft.block.FurnaceBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.material.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Box
import net.minecraft.world.World
import net.modificationstation.stationapi.api.block.BlockState
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper
import net.modificationstation.stationapi.api.item.ItemPlacementContext
import net.modificationstation.stationapi.api.state.StateManager
import net.modificationstation.stationapi.api.state.property.Properties
import net.modificationstation.stationapi.api.util.Identifier
import net.modificationstation.stationapi.api.util.math.Direction
import net.teamterminus.machineessentials.energy.electric.template.ElectricBlock
import net.teamterminus.machineessentials.network.NetworkComponentBlock
import net.teamterminus.machineessentials.network.NetworkType

open class Furnace(identifier: Identifier, material: Material?) : ElectricBlock(identifier, material), NetworkComponentBlock {

    init {
        setHardness(5f)
        setResistance(10f)
        setSoundGroup(METAL_SOUND_GROUP)
        defaultState
            .with(Properties.LIT, false)
            .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.LIT)
        super.appendProperties(builder)
    }

    override fun getBoundingBox(world: World?, x: Int, y: Int, z: Int): Box {
        return super.getBoundingBox(world, x, y, z)
    }

    override fun getPlacementState(context: ItemPlacementContext): BlockState {
        return defaultState
            .with(Properties.HORIZONTAL_FACING, context.player!!.placementFacing())
            .with(Properties.LIT, false)
    }

    override fun createBlockEntity(): BlockEntity {
        return FurnaceBlockEntity()
    }

    override fun onUse(world: World, x: Int, y: Int, z: Int, player: PlayerEntity): Boolean {
        val furnaceBlockEntity = world.getBlockEntity(x, y, z) as FurnaceBlockEntity
        GuiHelper.openGUI(player, InitListener.NAMESPACE.id("furnace"), furnaceBlockEntity, FurnaceScreenHandler(player.inventory, furnaceBlockEntity))
        return true
    }

    override fun getType(): NetworkType {
        return NetworkType.ELECTRIC
    }

    override fun onBreak(world: World, x: Int, y: Int, z: Int) {
        if (!FurnaceBlock.ignoreBlockRemoval) {
            world.removeBlockEntity(x, y, z)
        }
    }

    override fun onPlaced(world: World, x: Int, y: Int, z: Int) {
        if (!FurnaceBlock.ignoreBlockRemoval) {
            world.setBlockEntity(x, y, z, createBlockEntity())
        }
    }
}