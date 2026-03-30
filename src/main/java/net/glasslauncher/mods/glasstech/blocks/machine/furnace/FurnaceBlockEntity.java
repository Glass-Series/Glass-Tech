package net.glasslauncher.mods.glasstech.blocks.machine.furnace;

import net.glasslauncher.mods.glasstech.FuelValues;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerBlockEntityTemplate;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.recipe.SmeltingRecipeManager;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.property.Properties;

public class FurnaceBlockEntity extends ConsumerBlockEntityTemplate implements Inventory {
    protected int smeltTime = 6 * 20;

    protected int currentSmeltTime = -1;

    protected ItemStack[] slots = new ItemStack[2];

    public FurnaceBlockEntity() {
        super(VoltageTier.LV);
        setEnergyCapacity((int) (FuelValues.COAL * 0.5)); // .2 coal worth
        setMaxEnergyInput(24); // 2 generators
    }

    public boolean canSmelt() {
        return energy >= 8;
    }

    @Override
    public void tick() {
        super.tick();

        if (world.isRemote) {
            return;
        }

        BlockState state = world.getBlockState(x, y, z);
        if (this.canSmelt() && this.canAcceptRecipeOutput()) {
            ++this.currentSmeltTime;
            energy -= 4;
            if (state.contains(Properties.LIT) && !state.get(Properties.LIT)) {
                FurnaceBlock.ignoreBlockRemoval = true;
                world.setBlockStateWithNotify(x, y, z, state.with(Properties.LIT, true));
                FurnaceBlock.ignoreBlockRemoval = false;
            }
            if (this.currentSmeltTime == smeltTime) {
                this.currentSmeltTime = 0;
                this.craftRecipe();
                markDirty();
            }
        }
        else if (state.get(Properties.LIT) && !canAcceptRecipeOutput()) {
            FurnaceBlock.ignoreBlockRemoval = true;
            world.setBlockStateWithNotify(x, y, z, state.with(Properties.LIT, false));
            FurnaceBlock.ignoreBlockRemoval = false;
            this.currentSmeltTime = 0;
        }
        else {
            this.currentSmeltTime = 0;
        }
    }

    private boolean canAcceptRecipeOutput() {
        if (slots[0] == null) {
            return false;
        } else {
            ItemStack var1 = SmeltingRecipeManager.getInstance().craft(slots[0].itemId);
            if (var1 == null) {
                return false;
            }
            else if (slots[1] == null) {
                return true;
            }
            else if (!slots[1].isItemEqual(var1)) {
                return false;
            }
            else if (slots[1].count < getMaxCountPerStack() && slots[1].count < slots[1].getMaxCount()) {
                return true;
            } else {
                return slots[1].count < var1.getMaxCount();
            }
        }
    }

    void craftRecipe() {
        if (canAcceptRecipeOutput()) {
            ItemStack var1 = SmeltingRecipeManager.getInstance().craft(slots[0].itemId);
            if (slots[1] == null) {
                slots[1] = var1.copy();
            } else if (slots[1].itemId == var1.itemId) {
                ++slots[1].count;
            }

            --slots[0].count;
            if (slots[0].count <= 0) {
                slots[0] = null;
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.put("currentSmeltTime", new NbtInt(currentSmeltTime));
        for (int i = 0; i < slots.length; i++) {
            ItemStack slot = slots[i];
            if (slot != null) {
                NbtCompound item = new NbtCompound();
                slot.writeNbt(item);
                tag.put("item" + i, item);
            }
        }
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        currentSmeltTime = tag.getInt("currentSmeltTime");
        for (int i = 0; i < slots.length; i++) {
            if (tag.contains("item" + i)) {
                slots[i] = new ItemStack(tag.getCompound("item" + i));
            }
        }
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public ItemStack getStack(int slot) {
        return slots[slot];
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = getStack(slot);
        if (stack == null) {
            return null;
        }

        if (stack.count == amount) {
            slots[slot] = null;
            return stack;
        }
        stack.count -= amount;
        stack = stack.copy();
        stack.count = amount;
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        slots[slot] = stack;
    }

    @Override
    public String getName() {
        return "Generator";
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return player.getSquaredDistance(x + 0.5, y + 0.5, z + 0.5) <= 64;
    }
}
