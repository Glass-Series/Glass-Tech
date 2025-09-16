package net.glasslauncher.mods.glasstech.blocks.generator

import net.glasslauncher.mods.glasstech.events.init.InitListener
import net.glasslauncher.mods.glasstech.events.init.placementFacing
import net.minecraft.block.Block
import net.minecraft.block.FurnaceBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.material.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import net.modificationstation.stationapi.api.block.BlockState
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper
import net.modificationstation.stationapi.api.item.ItemPlacementContext
import net.modificationstation.stationapi.api.state.StateManager
import net.modificationstation.stationapi.api.state.property.Properties
import net.modificationstation.stationapi.api.util.Identifier
import net.modificationstation.stationapi.api.util.math.Direction
import net.teamterminus.machineessentials.energy.electric.template.ElectricBlock
import net.teamterminus.machineessentials.network.NetworkComponent
import net.teamterminus.machineessentials.network.NetworkComponentBlock
import net.teamterminus.machineessentials.network.NetworkType

open class GeneratorBlock(identifier: Identifier?, material: Material) : ElectricBlock(identifier, material) {

    init {
        setHardness(5f)
        setResistance(10f)
        setSoundGroup(METAL_SOUND_GROUP)
        defaultState = defaultState
            .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
            .with(Properties.LIT, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.LIT)
        super.appendProperties(builder)
    }

    override fun getPlacementState(context: ItemPlacementContext): BlockState {
        return defaultState
            .with(Properties.HORIZONTAL_FACING, context.player!!.placementFacing())
            .with(Properties.LIT, false)
    }

    override fun createBlockEntity(): BlockEntity {
        return GeneratorBlockEntity()
    }

    override fun onUse(world: World, x: Int, y: Int, z: Int, player: PlayerEntity): Boolean {
        val generatorBlockEntity = world.getBlockEntity(x, y, z) as GeneratorBlockEntity
        GuiHelper.openGUI(
            player,
            InitListener.NAMESPACE.id("generator"),
            generatorBlockEntity,
            GeneratorScreenHandler(player.inventory, generatorBlockEntity)
        )
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