package net.glasslauncher.mods.glasstech.recipe.machine.output;

import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * A recipe output with a given chance to "drop"
 */
public class ChanceRecipeOutput extends RecipeOutput {
    public final int rarity;

    public ChanceRecipeOutput(ItemStack stack, RecipeOutputType type, int rarity) {
        super(stack, type);
        this.rarity = rarity;
    }

    public ChanceRecipeOutput(ItemStack stack, int rarity) {
        super(stack);
        this.rarity = rarity;
    }

    @Override
    public ItemStack getOutput(Random random) {
        if (random == null) {
            return super.getOutput(null);
        }
        
        if (random.nextInt(rarity) == 0) {
            return super.getOutput(random);
        }
        
        return null;
    }

    @Override
    public String toString() {
        return "RecipeOutput { stack=" + getStackString() + ", type=" + type + ", chance=" + rarity + " }";
    }
}
