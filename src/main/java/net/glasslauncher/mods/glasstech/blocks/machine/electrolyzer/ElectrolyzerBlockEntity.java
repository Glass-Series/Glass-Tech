package net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer;

import net.glasslauncher.mods.glasstech.FuelValues;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.ElectrolyzerRecipeRegistry;
import net.minecraft.item.ItemStack;

public class ElectrolyzerBlockEntity extends RecipeBlockEntityTemplate<BasicMachineRecipe> {

    public ElectrolyzerBlockEntity() {
        super(VoltageTier.LV, 150000, 10, 15000);
        SlotLayout.createBasic(this);
    }

    @Override
    public String getName() {
        return "Electrolyzer";
    }

    @Override
    public BasicMachineRecipe fetchRecipe(ItemStack[] input) {
        return ElectrolyzerRecipeRegistry.INSTANCE.get(input);
    }
}
