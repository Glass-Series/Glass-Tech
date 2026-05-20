package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import lombok.Getter;
import lombok.Setter;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorBlockEntityTemplate;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.math.Direction;

import static net.modificationstation.stationapi.api.state.property.Properties.HORIZONTAL_FACING;

public class DynamoBlockEntity extends GeneratorBlockEntityTemplate implements Inventory {
    @Getter @Setter
    protected float fuelEfficiency = 0.25f;

    protected ItemStack[] slots = new ItemStack[2];

    public DynamoBlockEntity() {
        super(VoltageTier.LV);
        setEnergyCapacity(4000);
    }

    @Override
    public void tick() {
        super.tick();
        if (world.isRemote) {
            return;
        }
        BlockState state = world.getBlockState(x, y, z);
        Direction looking = state.get(HORIZONTAL_FACING);
        if (world.getBlockEntity(x + looking.getOffsetX(), y, z + looking.getOffsetZ()) instanceof DynamoComponent dynamoComponent && dynamoComponent.isConnected(looking) && dynamoComponent.isGenerating()) {
            energy += dynamoComponent.getOutput(); // in eu/t
            if (state.contains(Properties.LIT) && !state.get(Properties.LIT)) {
                FurnaceBlock.ignoreBlockRemoval = true;
                world.setBlockStateWithNotify(x, y, z, state.with(Properties.LIT, true));
                FurnaceBlock.ignoreBlockRemoval = false;
            }
        }
        if (energy > getEnergyCapacity()) {
            energy = getEnergyCapacity();
        }

        markDirty();
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
        return 1;
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
        return "Dynamo";
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
