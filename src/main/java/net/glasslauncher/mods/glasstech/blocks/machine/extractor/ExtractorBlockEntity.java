package net.glasslauncher.mods.glasstech.blocks.machine.extractor;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.ExtractorRecipeRegistry;
import net.minecraft.item.ItemStack;

public class ExtractorBlockEntity extends RecipeBlockEntityTemplate<BasicMachineRecipe> {

    public ExtractorBlockEntity() {
        super(VoltageTier.LV, 300, 2);
        SlotLayout.createBasic(this);
    }

    @Override
    public String getName() {
        return "Extractor";
    }

    @Override
    public BasicMachineRecipe fetchRecipe(ItemStack[] input) {
        return ExtractorRecipeRegistry.INSTANCE.get(input);
    }
}
