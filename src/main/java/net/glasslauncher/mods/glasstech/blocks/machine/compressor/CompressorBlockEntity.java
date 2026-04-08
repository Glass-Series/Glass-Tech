package net.glasslauncher.mods.glasstech.blocks.machine.compressor;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.CompressorRecipeRegistry;
import net.minecraft.item.ItemStack;

public class CompressorBlockEntity extends RecipeBlockEntityTemplate<BasicMachineRecipe> {

    public CompressorBlockEntity() {
        super(VoltageTier.LV, 200, 4, 300);
        SlotLayout.createBasic(this);
    }

    @Override
    public String getName() {
        return "Compressor";
    }

    @Override
    public BasicMachineRecipe fetchRecipe(ItemStack[] input) {
        return CompressorRecipeRegistry.INSTANCE.get(input);
    }
}
