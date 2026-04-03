package net.glasslauncher.mods.glasstech.recipe.machine.input;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

import java.util.List;

public class IdRecipeInput extends RecipeInput {
    /**
     * The item required
     */
    public final int id;
    /**
     * The item count required
     */
    public final int count;
    /**
     * The item meta required, -1 for any meta.
     */
    public final int meta;

    public IdRecipeInput(int id, int count, int meta) {
        this.id = id;
        this.count = count;
        this.meta = meta;
    }

    public IdRecipeInput(int id, int count) {
        this(id, count, -1);
    }

    public IdRecipeInput(int id) {
        this(id, 1);
    }

    @Override
    public boolean matches(ItemStack other) {
        // If item doesn't match, return false
        if (other.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock().id != id) {
                return false;
            }
        }
        else if (other.itemId != id) {
            return false;
        }

        // If item count is lower than required, return false
        if (other.count < count) {
            return false;
        }

        // If meta is not -1 and item meta doesn't match, return false
        if (meta != -1 && meta != other.getDamage()) {
            return false;
        }

        return true;
    }

    @Override
    public int getRequiredAmount() {
        return count;
    }

    @Override
    public List<ItemStack> getRepresentingStacks() {
        return List.of(new ItemStack(id, count, meta));
    }

    @Override
    public String toString() {
        return "IdRecipeInput{" +
                "id=" + id +
                ", count=" + count +
                ", meta=" + meta +
                '}';
    }
}
