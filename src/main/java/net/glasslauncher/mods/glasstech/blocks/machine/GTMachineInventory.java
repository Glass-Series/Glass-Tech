package net.glasslauncher.mods.glasstech.blocks.machine;

import net.danygames2014.nyalib.block.DropInventoryOnBreak;
import net.danygames2014.nyalib.item.block.ItemHandler;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

// Pray for those who have to look at this bullshit inventory class, cause I'm sure as hell am at my breaking point, and I'm the one who made the fucking thing
public interface GTMachineInventory extends Inventory, DropInventoryOnBreak, ItemHandler {

    // Copy-paste minimal setup for implementing class.
//    @Getter @Setter
//    public ItemStack[] inventory = new ItemStack[0];
//    @Getter
//    public int inventoryIndex;
//    @Getter @Setter
//    public int[] inputSlots = new int[0];
//    @Getter
//    public final HashMap<RecipeOutputType, int[]> outputs = blankOutputs();
//    @Getter
//    public final HashMap<SlotType, int[]> slots = blankInputs();
//
//    @Override
//    public void increaseInventoryIndex() {
//        inventoryIndex++;
//    }

    // Getters
    ItemStack[] getInventory();
    void setInventory(ItemStack[] inventory);
    int getInventoryIndex();
    void increaseInventoryIndex();
    int[] getInputSlots();
    void setInputSlots(int[] inputs);
    HashMap<RecipeOutputType, int[]> getOutputs();
    HashMap<SlotType, int[]> getSlots();
    ScreenHandler getInventoryFilterHandler();

    // Generic Slot

    /**
     * Adds a new slot of the given type to the machine
     *
     * @param type The type of slot to add
     * @return The index of the slot added for this type
     */
    default int addSlot(SlotType type) {
        // Fetch the old array
        int[] oldArray = getSlots().get(type);

        // Create a new array that is larger by one
        int[] newArray = new int[oldArray.length + 1];

        // Copy values from old array into new one
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        newArray[newArray.length - 1] = getInventoryIndex();

        getSlots().put(type, newArray);
        increaseInventoryIndex();
        setInventory(new ItemStack[getInventoryIndex()]);
        return newArray[newArray.length - 1];
    }

    default ItemStack getSlot(SlotType type, int index) {
        int[] arr = getSlots().get(type);

        // If the index is too high for this type, return null
        if (index >= arr.length) {
            return null;
        }

        return getInventory()[arr[index]];
    }

    default void setSlot(SlotType type, int index, ItemStack stack) {
        int[] arr = getSlots().get(type);

        // If the index is too high for this type, return null
        if (index >= arr.length) {
            return;
        }

        getInventory()[arr[index]] = stack;
    }

    default ItemStack[] getSlots(SlotType type) {
        return getSlots(type, false);
    }

    default ItemStack[] getSlots(SlotType type, boolean copy) {
        ArrayList<ItemStack> out = new ArrayList<>();
        int[] arr = getSlots().get(type);

        for (int outputSlot : arr) {
            if (getInventory()[outputSlot] == null) {
                out.add(null);
                continue;
            }

            out.add(copy ? getInventory()[outputSlot].copy() : getInventory()[outputSlot]);
        }

        return out.toArray(new ItemStack[0]);
    }

    default boolean setSlots(SlotType type, ItemStack[] stacks) {
        int[] arr = getSlots().get(type);

        // If the lengths are not equal, return
        if (stacks.length != arr.length) {
            return false;
        }

        // If the lenths are equal, write the stacks array to the output
        for (int i = 0; i < arr.length; i++) {
            getInventory()[arr[i]] = stacks[i];
        }
        return true;
    }

    /**
     * Gets the inventory index of the given slot
     *
     * @param type  The type of the slot
     * @param index The index of the slot in that type
     * @return The inventory index of the given slot
     */
    default int getSlotInventoryIndex(SlotType type, int index) {
        int[] arr = getSlots().get(type);

        // If the index is too high for this type, return null
        if (index >= arr.length) {
            return -1;
        }

        return arr[index];
    }

    default int[] getSlotInventoryIndexes(SlotType type) {
        return getSlots().get(type);
    }

    // Input Slot

    /**
     * Adds a new input slot
     */
    default void addInput() {
        // Create a new array that is larger by one
        int[] newArray = new int[getInputSlots().length + 1];

        // Copy values from old array into new one
        System.arraycopy(getInputSlots(), 0, newArray, 0, getInputSlots().length);
        newArray[newArray.length - 1] = getInventoryIndex();

        setInputSlots(newArray);
        increaseInventoryIndex();
        setInventory(new ItemStack[getInventoryIndex()]);
    }

    /**
     * Gets the ItemStack in the specified input slot
     *
     * @param index The index of the input slot
     * @return The ItemStack in the slot or <code>null</code> if there is no stack or the index is too high
     */
    default ItemStack getInput(int index) {
        // If the index is too high, return null
        if (index >= getInputSlots().length) {
            return null;
        }

        return getInventory()[getInputSlots()[index]];
    }

    default void setInput(int index, ItemStack stack) {
        if (index >= getInputSlots().length) {
            return;
        }

        getInventory()[getInputSlots()[index]] = stack;
    }

    default ItemStack[] getInputs() {
        ArrayList<ItemStack> out = new ArrayList<>();
        for (int inputSlot : getInputSlots()) {
            out.add(getInventory()[inputSlot]);
        }

        return out.toArray(new ItemStack[0]);
    }

    default int getInputIndex(int index) {
        // If the index is too high, return null
        if (index >= getInputSlots().length) {
            return -1;
        }

        return getInputSlots()[index];
    }

    default int[] getInputIndexes() {
        return getInputSlots();
    }

    // Output Slot

    /**
     * Adds a new output slot of the specified type
     *
     * @param type The type of output to add
     */
    default void addOutput(RecipeOutputType type) {
        // Fetch the old array
        int[] oldArray = getOutputs().get(type);

        // Create a new array that is larger by one
        int[] newArray = new int[oldArray.length + 1];

        // Copy values from old array into new one
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        newArray[newArray.length - 1] = getInventoryIndex();

        getOutputs().put(type, newArray);
        increaseInventoryIndex();
        setInventory(new ItemStack[getInventoryIndex()]);
    }

    default ItemStack getOutput(RecipeOutputType type, int index) {
        int[] arr = getOutputs().get(type);

        // If the index is too high for this type, return null
        if (index >= arr.length) {
            return null;
        }

        return getInventory()[arr[index]];
    }

    default void setOutput(RecipeOutputType type, int index, ItemStack stack) {
        int[] arr = getOutputs().get(type);

        // If the index is too high for this type, return null
        if (index >= arr.length) {
            return;
        }

        getInventory()[arr[index]] = stack;
    }

    default ItemStack[] getOutputs(RecipeOutputType type, boolean copy) {
        ArrayList<ItemStack> out = new ArrayList<>();
        int[] arr = getOutputs().get(type);

        for (int outputSlot : arr) {
            if (getInventory()[outputSlot] == null) {
                out.add(null);
                continue;
            }

            out.add(copy ? getInventory()[outputSlot].copy() : getInventory()[outputSlot]);
        }

        return out.toArray(new ItemStack[0]);
    }

    default boolean setOutputs(RecipeOutputType type, ItemStack[] stacks) {
        int[] arr = getOutputs().get(type);

        // If the lengths are not equal, return
        if (stacks.length != arr.length) {
            return false;
        }

        // If the lenths are equal, write the stacks array to the output
        for (int i = 0; i < arr.length; i++) {
            getInventory()[arr[i]] = stacks[i];
        }
        return true;
    }

    default int getOutputIndex(RecipeOutputType type, int index) {
        int[] arr = getOutputs().get(type);

        // If the index is too high for this type, return null
        if (index >= arr.length) {
            return -1;
        }

        return arr[index];
    }

    default int[] getOutputIndexes(RecipeOutputType type) {
        return getOutputs().get(type);
    }

    // Inventory Interface
    @Override
    default int size() {
        return getInventory().length;
    }

    @Override
    default ItemStack getStack(int slot) {
        if (slot < 0 || slot >= getInventory().length) {
            return null;
        }

        return getInventory()[slot];
    }

    @Override
    default ItemStack removeStack(int slot, int amount) {
        if (slot < 0 || slot >= getInventory().length) {
            return null;
        }

        ItemStack stack = null;

        if (this.getInventory()[slot] != null) {
            if (this.getInventory()[slot].count <= amount) {
                stack = this.getInventory()[slot];
                this.getInventory()[slot] = null;
            } else {
                stack = this.getInventory()[slot].split(amount);
                if (this.getInventory()[slot].count == 0) {
                    this.getInventory()[slot] = null;
                }
            }
        }

        return stack;
    }

    @Override
    default void setStack(int slot, ItemStack stack) {
        if (slot < 0 || slot >= getInventory().length) {
            return;
        }

        this.getInventory()[slot] = stack;
        if (stack != null && stack.count > this.getMaxCountPerStack()) {
            stack.count = this.getMaxCountPerStack();
        }
    }

    @Override
    default int getMaxCountPerStack() {
        return 64;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    default boolean pushOutput(RecipeOutputType type, ItemStack output, boolean simulate) {
        if (output == null) {
            return true;
        }
        int[] slotIndexes = getOutputIndexes(type);
        ItemStack[] slots = getOutputs(type, simulate);

        // Existing stacks? Fill those first.
        for (ItemStack slot : slots) {
            if (slot == null) {
                continue;
            }
            if (slot.isItemEqual(output)) {
                int amountTransferred = Math.min(Math.min(getMaxCountPerStack(), slot.getMaxCount()), slot.count + output.count) - slot.count;
                output.count -= amountTransferred;
                slot.count += amountTransferred;
                if (output.count == 0) {
                    return true;
                }
            }
        }
        // Okay now just dump the item
        for (int i = 0; i < slots.length; i++) {
            ItemStack slot = slots[i];
            if (slot == null) {
                if (!simulate) {
                    getInventory()[slotIndexes[i]] = output;
                }
                return true;
            }
        }
        return false;
    }

    default ItemStack[] getInputs(int[] inputIndexes) {
        ArrayList<ItemStack> out = new ArrayList<>();
        for (int inputSlot : inputIndexes) {
            out.add(getInventory()[inputSlot]);
        }

        return out.toArray(new ItemStack[0]);
    }

    default boolean isInput(int slot) {
        for (int inputSlot : getInputIndexes()) {
            if (inputSlot == slot) {
                return true;
            }
        }
        return false;
    }

    default boolean isOutput(int slot) {
        for (RecipeOutputType outputType : RecipeOutputType.values()) {
            for (int outputSlot : getOutputIndexes(outputType)) {
                if (outputSlot == slot) {
                    return true;
                }
            }
        }
        return false;
    }

    // ItemHandler implementation

    @Override
    default boolean canConnectItem(Direction side) {
        return canExtractItem(side) || canInsertItem(side);
    }

    @Override
    default boolean canExtractItem(@Nullable Direction side) {
        for (RecipeOutputType outputType : RecipeOutputType.values()) {
            if (getOutputIndexes(outputType).length > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    default boolean canInsertItem(@Nullable Direction side) {
        for (int inputSlot : getInputIndexes()) {
            if (inputSlot >= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    default ItemStack extractItem(int slot, int amount, @Nullable Direction side) {
        if (slot < 0 || getOutputIndexes(RecipeOutputType.PRIMARY).length == 0) {
            return null;
        }
        ItemStack stack = getOutput(RecipeOutputType.PRIMARY, slot);
        Slot slotInstance = getInventoryFilterHandler() != null ? getInventoryFilterHandler().getSlot(getOutputIndexes(RecipeOutputType.PRIMARY)[slot]) : null;

        if (slotInstance != null) {
            return slotInstance.takeStack(amount); // Slot takes precedence
        }

        if (stack == null) {
            return null;
        }

        return removeStack(slot, amount);
    }

    @Override
    default ItemStack insertItem(ItemStack stack, int slot, @Nullable Direction side) {
        if (slot < 0 || slot >= getInputIndexes().length) {
            return stack;
        }
        if (stack == null || stack.count <= 0) {
            return null;
        }

        Slot slotInstance = getInventoryFilterHandler() != null ? getInventoryFilterHandler().getSlot(getInputIndex(slot)) : null;
        if (slotInstance != null && !slotInstance.canInsert(stack)) {
            return stack; // Slot said no, now scram
        }

        ItemStack current = getInput(slot);
        int maxStack = Math.min(Math.min(stack.getMaxCount(), getMaxCountPerStack()), stack.count);
        if (current == null) {
            if (stack.count <= getMaxCountPerStack()) {
                setInput(slot, stack);
                return null;
            }
            var newStack = stack.copy();
            newStack.count = maxStack;
            setInput(slot, newStack);
            stack.count -= maxStack;
            return stack;
        }
        if (!current.isItemEqual(stack)) {
            return stack;
        }
        int space = Math.min(current.getMaxCount(), getMaxCountPerStack()) - current.count;
        if (space <= 0) {
            return stack;
        }
        int toInsert = Math.min(space, stack.count);
        current.count += toInsert;
        stack.count -= toInsert;
        if (stack.count <= 0) {
            return null;
        }
        return stack;
    }

    @Override
    default ItemStack insertItem(ItemStack stack, @Nullable Direction side) {
        if (stack == null || stack.count <= 0) {
            return null;
        }
        for (int i = 0; i < getInputIndexes().length; i++) {
            stack = insertItem(stack, i, side);
            if (stack == null || stack.count <= 0) {
                return null;
            }
        }
        return stack;
    }

    @Override
    default ItemStack getItem(int slot, @Nullable Direction side) {
        if (slot < 0 || slot >= getOutputIndexes(RecipeOutputType.PRIMARY).length) {
            return null;
        }
        return getOutput(RecipeOutputType.PRIMARY, slot);
    }

    @Override
    default boolean setItem(ItemStack stack, int slot, @Nullable Direction side) {
        if (slot < 0 || slot >= getInputIndexes().length || stack.count > getMaxCountPerStack()) {
            return false;
        }
        setInput(slot, stack);
        return true;
    }

    @Override
    default int getItemSlots(@Nullable Direction side) {
        return getInventory().length;
    }

    @Override
    default ItemStack[] getInventory(@Nullable Direction side) {
        return getInventory();
    }

    default HashMap<SlotType, int[]> blankInputs() {
        HashMap<SlotType, int[]> slots = new HashMap<>();
        Iterator<SlotType> iterator = SlotType.iterator();
        while (iterator.hasNext()) {
            slots.put(iterator.next(), new int[0]);
        }
        return slots;
    }

    default HashMap<RecipeOutputType, int[]> blankOutputs() {
        return new HashMap<>() {{
            for (RecipeOutputType type : RecipeOutputType.values()) {
                put(type, new int[0]);
            }
        }};
    }
}
