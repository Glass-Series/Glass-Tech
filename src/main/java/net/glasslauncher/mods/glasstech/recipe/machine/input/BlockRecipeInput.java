package net.glasslauncher.mods.glasstech.recipe.machine.input;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

import java.util.List;

public class BlockRecipeInput extends RecipeInput {
    /**
     * The block required
     */
    public final Block block;
    /**
     * The block count required
     */
    public final int count;
    /**
     * The block meta required, -1 for any meta.
     */
    public final int meta;

    public BlockRecipeInput(Block block, int count, int meta) {
        this.block = block;
        this.count = count;
        this.meta = meta;
    }

    public BlockRecipeInput(Block block, int count) {
        this(block, count, -1);
    }

    public BlockRecipeInput(Block block) {
        this(block, 1);
    }

    @Override
    public boolean matches(ItemStack other) {
        // If block doesn't match, return false
        if (other.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock() != block) {
                return false;
            }
        }
        else {
            return false;
        }

        // If block count is lower than required, return false
        if (other.count < count) {
            return false;
        }

        // If meta is not -1 and block meta doesn't match, return false
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
        return List.of(new ItemStack(block, count, meta));
    }

    @Override
    public String toString() {
        return "BlockRecipeInput{" +
                "block=" + block +
                ", count=" + count +
                ", meta=" + meta +
                '}';
    }
}
