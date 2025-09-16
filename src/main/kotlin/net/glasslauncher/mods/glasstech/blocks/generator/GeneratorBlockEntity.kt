package net.glasslauncher.mods.glasstech.blocks.generator

import net.glasslauncher.mods.glasstech.blocks.furnace.FurnaceBlockEntity
import net.minecraft.block.FurnaceBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtInt
import net.modificationstation.stationapi.api.block.BlockState
import net.modificationstation.stationapi.api.recipe.FuelRegistry
import net.modificationstation.stationapi.api.state.property.Properties
import net.teamterminus.machineessentials.energy.electric.template.ElectricGeneratorBlockEntity

open class GeneratorBlockEntity : ElectricGeneratorBlockEntity(), Inventory {
    protected var fuelTicks = 0

    protected var slots: Array<ItemStack?> = arrayOfNulls(3)

    override fun onOvervoltage(l: Long) {
    }

    init {
        capacity = 19200*4 // 4 coal worth
        maxVoltageOut = 12
        maxAmpsOut = 1 // tl;dr 12 eu/t out
    }

    override fun tick() {
        super.tick()
        if (slots[0] != null && fuelTicks < 1 && energy < capacity) {
            val fuelTime = FuelRegistry.getFuelTime(slots[0])
            if (fuelTime < 1) {
                return
            }
            fuelTicks = fuelTime
            slots[0]!!.count--
            if (slots[0]!!.count < 1) {
                slots[0] = null
            }
        }
        val state = world.getBlockState(x, y, z)
        if (fuelTicks > 0) {
            fuelTicks--
            energy += 8 // 8 eu/t
            if (state.contains(Properties.LIT) && !state[Properties.LIT]) {
                FurnaceBlock.ignoreBlockRemoval = true
                world.setBlockStateWithNotify(x, y, z, state.with(Properties.LIT, true))
                FurnaceBlock.ignoreBlockRemoval = false
            }
        }
        else if (state[Properties.LIT]) {
            FurnaceBlock.ignoreBlockRemoval = true
            world.setBlockStateWithNotify(x, y, z, state.with(Properties.LIT, false))
            FurnaceBlock.ignoreBlockRemoval = false
        }
        if (energy > capacity) {
            energy = capacity
        }

        markDirty()
    }

    override fun writeNbt(tag: NbtCompound) {
        super.writeNbt(tag)
        tag.put("fuelTicks", NbtInt(fuelTicks))
        for (i in slots.indices) {
            if (slots[i] != null) {
                val item = NbtCompound()
                slots[i]!!.writeNbt(item)
                tag.put("item$i", item)
            }
        }
    }

    override fun readNbt(tag: NbtCompound) {
        super.readNbt(tag)
        fuelTicks = tag.getInt("fuelTicks")
        for (i in slots.indices) {
            if (tag.contains("item$i")) {
                slots[i] = ItemStack(tag.getCompound("item$i"))
            }
        }
    }

    override fun size(): Int {
        return 2
    }

    override fun getStack(slot: Int): ItemStack? {
        return slots[slot]
    }

    override fun removeStack(slot: Int, amount: Int): ItemStack? {
        var stack: ItemStack = getStack(slot) ?: return null

        if (stack.count == amount) {
            slots[slot] = null
            return stack
        }
        stack.count -= amount
        stack = stack.copy()
        stack.count = amount
        return stack
    }

    override fun setStack(slot: Int, stack: ItemStack) {
        slots[slot] = stack
    }

    override fun getName(): String {
        return "Generator"
    }

    override fun getMaxCountPerStack(): Int {
        return 64
    }

    override fun canPlayerUse(player: PlayerEntity): Boolean {
        return player.getSquaredDistance(x + 0.5, y + 0.5, z + 0.5) <= 64
    }

    override fun markRemoved() {
        super.markRemoved()
        println("got removed")
    }
}
