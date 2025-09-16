package net.glasslauncher.mods.glasstech.blocks.furnace

import net.glasslauncher.mods.glasstech.blocks.FuelValues.Companion.COAL_VALUE
import net.minecraft.block.FurnaceBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtInt
import net.minecraft.recipe.SmeltingRecipeManager
import net.minecraft.world.explosion.Explosion
import net.modificationstation.stationapi.api.state.property.Properties
import net.modificationstation.stationapi.api.util.math.Direction
import net.teamterminus.machineessentials.energy.electric.api.VoltageTier
import net.teamterminus.machineessentials.energy.electric.template.ElectricBlockEntity
import net.teamterminus.machineessentials.energy.electric.template.ElectricDeviceBlockEntity
import net.teamterminus.machineessentials.energy.electric.template.ElectricGeneratorBlockEntity
import net.teamterminus.machineessentials.network.NetworkManager

open class FurnaceBlockEntity : ElectricDeviceBlockEntity(), Inventory {
    protected val smeltTime = 6 * 20

    protected var currentSmeltTime = -1;

    protected var slots: Array<ItemStack?> = arrayOfNulls(2)

    override fun onOvervoltage(l: Long) {
        val dio = Explosion(world, null, x.toDouble(), y.toDouble(), z.toDouble(), 5F)
        dio.playExplosionSound(true)
        dio.explode()
    }

    init {
        capacity = (COAL_VALUE*0.5).toLong() // .2 coal worth
        maxVoltageIn = VoltageTier.LV.maxVoltage.toLong()
        maxAmpsIn = 1 // tl;dr 24 eu/t in
    }

    override fun canReceive(dir: Direction): Boolean {
        return true
    }

    fun canSmelt(): Boolean {
        return energy >= 8
    }

    override fun tick() {
        super.tick()

        if (world.isRemote) {
            return
        }

        val state = world.getBlockState(x, y, z)
        if (this.canSmelt() && this.canAcceptRecipeOutput()) {
            ++this.currentSmeltTime
            energy -= 4
            if (state.contains(Properties.LIT) && !state[Properties.LIT]) {
                FurnaceBlock.ignoreBlockRemoval = true
                world.setBlockStateWithNotify(x, y, z, state.with(Properties.LIT, true))
                FurnaceBlock.ignoreBlockRemoval = false
            }
            if (this.currentSmeltTime == smeltTime) {
                this.currentSmeltTime = 0
                this.craftRecipe()
                markDirty()
            }
        }
        else if (state[Properties.LIT] && !canAcceptRecipeOutput()) {
            FurnaceBlock.ignoreBlockRemoval = true
            world.setBlockStateWithNotify(x, y, z, state.with(Properties.LIT, false))
            FurnaceBlock.ignoreBlockRemoval = false
            this.currentSmeltTime = 0
        }
        else {
            this.currentSmeltTime = 0
        }
    }

    private fun canAcceptRecipeOutput(): Boolean {
        if (this.slots[0] == null) {
            return false
        } else {
            val var1 = SmeltingRecipeManager.getInstance().craft(this.slots[0]!!.item.id)
            return if (var1 == null) {
                false
            } else if (this.slots[1] == null) {
                true
            } else if (!this.slots[1]!!.isItemEqual(var1)) {
                false
            } else if (this.slots[1]!!.count < this.maxCountPerStack && this.slots[1]!!.count < this.slots[1]!!.maxCount
            ) {
                true
            } else {
                this.slots[1]!!.count < var1.maxCount
            }
        }
    }

    fun craftRecipe() {
        if (this.canAcceptRecipeOutput()) {
            val var1 = SmeltingRecipeManager.getInstance().craft(this.slots[0]!!.item.id)
            if (this.slots[1] == null) {
                this.slots[1] = var1.copy()
            } else if (this.slots[1]!!.itemId == var1.itemId) {
                ++this.slots[1]!!.count
            }

            --this.slots[0]!!.count
            if (this.slots[0]!!.count <= 0) {
                this.slots[0] = null
            }
        }
    }

    override fun writeNbt(tag: NbtCompound) {
        super.writeNbt(tag)
        tag.put("currentSmeltTime", NbtInt(currentSmeltTime))
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
        currentSmeltTime = tag.getInt("currentSmeltTime")
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
}
