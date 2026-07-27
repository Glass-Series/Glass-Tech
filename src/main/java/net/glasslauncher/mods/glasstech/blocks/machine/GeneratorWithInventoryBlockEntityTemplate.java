package net.glasslauncher.mods.glasstech.blocks.machine;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public abstract class GeneratorWithInventoryBlockEntityTemplate extends GeneratorBlockEntityTemplate implements Inventory {

    protected ItemStack[] slots;

    public GeneratorWithInventoryBlockEntityTemplate(int inventorySize, VoltageTier voltageTier) {
        super(voltageTier);
        slots = new ItemStack[inventorySize];
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
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
        for (int i = 0; i < slots.length; i++) {
            if (tag.contains("item" + i)) {
                slots[i] = new ItemStack(tag.getCompound("item" + i));
            }
        }
    }

    @Override
    public int size() {
        return slots.length;
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
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return player.getSquaredDistance(x + 0.5, y + 0.5, z + 0.5) <= 64;
    }
}
