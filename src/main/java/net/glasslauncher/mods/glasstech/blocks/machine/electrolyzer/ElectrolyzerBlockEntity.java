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
        super(VoltageTier.LV, 200, 4);
        SlotLayout.createBasic(this);
        setEnergyCapacity((int) (FuelValues.COAL * 0.5));
        setMaxEnergyInput(24);
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
