package net.glasslauncher.mods.glasstech.blocks.ironfurnace;

import net.danygames2014.nyalib.NyaLib;
import net.danygames2014.nyalib.item.HasSmeltingReturnStack;
import net.danygames2014.nyalib.item.block.ItemHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class IronFurnaceBlockEntity extends BlockEntity implements Inventory, ItemHandler {
    protected final ItemStack[] inventory = new ItemStack[3];

    protected final int initialCookTime = 160;
    protected int cookTime;
    protected int fuelTime;
    protected int burnTime;


    public float getCookTimeDelta() {
        return cookTime / (float) initialCookTime;
    }

    public float getFuelTimeDelta() {
        return fuelTime > 0 ? burnTime / (float) fuelTime : 0;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public void tick() {
        boolean wasBurning = isBurning();
        boolean didSomething = false;
        if (wasBurning) {
            --this.burnTime;
        }

        if (!this.world.isRemote) {
            if (!wasBurning && this.canAcceptRecipeOutput()) {
                this.fuelTime = this.burnTime = this.getFuelTime(this.inventory[1]);
                if (isBurning()) {
                    didSomething = true;
                    if (this.inventory[1] != null) {
                        --this.inventory[1].count;
                        if (this.inventory[1].count == 0) {
                            this.inventory[1] = null;
                        }
                    }
                }
            }

            if (this.isBurning() && this.canAcceptRecipeOutput()) {
                ++this.cookTime;
                if (this.cookTime == initialCookTime) {
                    this.cookTime = 0;
                    this.craftRecipe();
                    didSomething = true;
                }
            } else {
                this.cookTime = 0;
            }

            if (wasBurning != isBurning()) {
                didSomething = true;
                BlockState state = world.getBlockState(x, y, z);
                state = state.with(Properties.LIT, isBurning());
                world.setBlockState(x, y, z, state);
            }
        }

        if (didSomething) {
            this.markDirty();
        }

    }

    private boolean canAcceptRecipeOutput() {
        if (this.inventory[0] == null) {
            return false;
        } else {
            ItemStack output = SmeltingRegistry.getResultFor(this.inventory[0]);
            if (output == null) {
                return false;
            } else if (this.inventory[2] == null) {
                return true;
            } else if (!this.inventory[2].isItemEqual(output)) {
                return false;
            } else if (this.inventory[2].count + output.count - 1 < this.getMaxCountPerStack() && this.inventory[2].count + output.count - 1 < this.inventory[2].getMaxCount()) {
                return true;
            } else {
                return this.inventory[2].count + output.count - 1 < output.getMaxCount();
            }
        }
    }

    public void craftRecipe() {
        if (this.canAcceptRecipeOutput()) {
            ItemStack output = SmeltingRegistry.getResultFor(this.inventory[0]);
            if (this.inventory[2] == null) {
                this.inventory[2] = output.copy();
            } else if (this.inventory[2].itemId == output.itemId) {
                this.inventory[2].count += output.count;
            }

            if (output.getItem() instanceof HasSmeltingReturnStack hasSmeltReturnItem) {
                ItemStack returnStack = hasSmeltReturnItem.getSmeltingReturnStack(output);

                if (output != returnStack) {
                    this.inventory[0] = returnStack;
                    return;
                }
            }

            --this.inventory[0].count;
            if (this.inventory[0].count <= 0) {
                this.inventory[0] = null;
            }

        }
    }

    private int getFuelTime(ItemStack itemStack) {
        return FuelRegistry.getFuelTime(itemStack);
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        NbtList var2 = nbt.getList("Items");

        for(int var3 = 0; var3 < var2.size(); ++var3) {
            NbtCompound var4 = (NbtCompound)var2.get(var3);
            byte slot = var4.getByte("Slot");
            if (slot >= 0 && slot < this.inventory.length) {
                this.inventory[slot] = new ItemStack(var4);
            }
        }

        this.burnTime = nbt.getShort("BurnTime");
        this.cookTime = nbt.getShort("CookTime");
        this.fuelTime = nbt.getShort("FuelTime");
    }

    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putShort("BurnTime", (short)this.burnTime);
        nbt.putShort("CookTime", (short)this.cookTime);
        nbt.putShort("FuelTime", (short)this.fuelTime);
        NbtList var2 = new NbtList();

        for(int slot = 0; slot < this.inventory.length; ++slot) {
            if (this.inventory[slot] != null) {
                NbtCompound var4 = new NbtCompound();
                var4.putByte("Slot", (byte)slot);
                this.inventory[slot].writeNbt(var4);
                var2.add(var4);
            }
        }

        nbt.put("Items", var2);
    }

    @Override
    public int size() {
        return inventory.length;
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (inventory[slot] == null) {
            return null;
        }
        if (this.inventory[slot].count <= amount) {
            ItemStack foundStack = this.inventory[slot];
            this.inventory[slot] = null;
            return foundStack;
        }

        ItemStack splitStack = this.inventory[slot].split(amount);
        if (this.inventory[slot].count == 0) {
            this.inventory[slot] = null;
        }

        return splitStack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory[slot] = stack;
    }

    @Override
    public String getName() {
        return "Iron Furnace";
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.x, this.y, this.z) != this) {
            return false;
        } else {
            return !(player.getSquaredDistance(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D) > 64.0D);
        }
    }

    // Slots
    // 0 - Input
    // 1 - Fuel
    // 2 - Output

    // Sides
    // Top - Input Insert / Input Extract
    // Sides - Fuel Insert / Output Extract
    // Bottom - Fuel Insert / Output Extract

    @Override
    public boolean canExtractItem(@Nullable Direction side) {
        return true;
    }

    @Override
    public ItemStack extractItem(int amount, @Nullable Direction side) {
        if (!canExtractItem(side)) {
            return null;
        }

        if (!NyaLib.ITEM_CONFIG.simplifiedFurnaceHandling && side != null) {
            switch (side) {
                // Extract from UP -> Input Slot
                case UP -> {
                    if (getItem(0, side) != null) {
                        return extractItem(0, amount, side);
                    }
                }

                // Extract from any other side -> Output Slot
                case DOWN, NORTH, SOUTH, EAST, WEST -> {
                    if (getItem(2, side) != null) {
                        return extractItem(2, amount, side);
                    }
                }
            }
        }

        // If direction is null, keep super behavior
        return ItemHandler.super.extractItem(amount, side);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, @Nullable Direction side) {
        if (slot >= 0 && slot < inventory.length) {
            return this.removeStack(slot, amount);
        }

        return null;
    }

    @Override
    public ItemStack extractItem(Item item, int meta, int amount, @Nullable Direction side) {
        if (!NyaLib.ITEM_CONFIG.simplifiedFurnaceHandling && side != null) {
            switch (side) {
                // Extract from UP -> Input Slot
                case UP -> {
                    if (getItem(0, side) != null && getItem(0, side).itemId == item.id && (meta == -1 || getItem(0, side).getDamage() == meta)) {
                        return extractItem(0, amount, side);
                    }
                }

                // Extract from any other side -> Output Slot
                case DOWN, NORTH, SOUTH, EAST, WEST -> {
                    if (getItem(2, side) != null && getItem(2, side).itemId == item.id && (meta == -1 || getItem(2, side).getDamage() == meta)) {
                        return extractItem(2, amount, side);
                    }
                }
            }

            return null;
        }

        // If direction is null, keep super behavior
        return ItemHandler.super.extractItem(item, amount, side);
    }

    @Override
    public boolean canInsertItem(@Nullable Direction side) {
        return true;
    }

    @Override
    public ItemStack insertItem(ItemStack stack, int slot, @Nullable Direction side) {
        // Only allow fuels into the fuel slot
        if (slot == 1 && FuelRegistry.getFuelTime(stack) <= 0) {
            return stack;
        }

        ItemStack slotStack;

        slotStack = this.getStack(slot);

        if (slotStack == null) {
            this.setStack(slot, stack);
            return null;
        }

        if (slotStack.isItemEqual(stack)) {
            int addedCount = Math.min(slotStack.getItem().getMaxCount() - slotStack.count, stack.count);

            slotStack.count += addedCount;

            if (addedCount >= stack.count) {
                return null;
            } else {
                return new ItemStack(stack.getItem(), stack.count - addedCount, stack.getDamage());
            }
        }

        return stack;
    }

    @Override
    public ItemStack insertItem(ItemStack stack, @Nullable Direction side) {
        ItemStack insertedStack = stack.copy();

        if (!NyaLib.ITEM_CONFIG.simplifiedFurnaceHandling && side != null) {
            switch (side) {
                case UP -> {
                    // Insert into input
                    insertedStack = insertItem(insertedStack, 0, side);
                }

                case NORTH, SOUTH, EAST, WEST -> {
                    if (FuelRegistry.getFuelTime(insertedStack) >= 0) {
                        // If the item has a fuel value, insert into fuel slot
                        insertedStack = insertItem(insertedStack, 1, side);
                    } else {
                        // If the item does not have fuel value, insert into input
                        insertedStack = insertItem(insertedStack, 0, side);
                    }
                }

                case DOWN -> {
                    // Insert into fuel
                    insertedStack = insertItem(insertedStack, 1, side);
                }
            }
        } else {
            // If direction is not specified, use default behavior
            for (int i = 0; i < this.getItemSlots(side); ++i) {
                insertedStack = insertItem(insertedStack, i, side);
                if (insertedStack == null) {
                    return null;
                }
            }
        }

        return insertedStack;
    }

    @Override
    public ItemStack getItem(int slot, @Nullable Direction side) {
        return this.getStack(slot);
    }

    @Override
    public boolean setItem(ItemStack stack, int slot, @Nullable Direction side) {
        setStack(slot, stack);
        return true;
    }

    @Override
    public ItemStack[] getInventory(@Nullable Direction side) {
        return this.inventory;
    }

    @Override
    public int getItemSlots(Direction side) {
        return this.size();
    }

    @Override
    public boolean canConnectItem(Direction side) {
        return true;
    }
}
