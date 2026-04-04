package net.glasslauncher.mods.glasstech.blocks.machine.canner;

import net.glasslauncher.mods.glasstech.FuelValues;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.CannerRecipeRegistry;
import net.minecraft.item.ItemStack;

public class CannerBlockEntity extends RecipeBlockEntityTemplate<BasicMachineRecipe> {

    public CannerBlockEntity() {
        super(VoltageTier.LV, 200, 3, 300);
        SlotLayout.createBasic(this);
    }

    @Override
    public String getName() {
        return "Canner";
    }

    @Override
    public BasicMachineRecipe fetchRecipe(ItemStack[] input) {
        return CannerRecipeRegistry.INSTANCE.get(input);
    }
}
