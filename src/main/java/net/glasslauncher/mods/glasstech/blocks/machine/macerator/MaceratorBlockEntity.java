package net.glasslauncher.mods.glasstech.blocks.machine.macerator;

import net.glasslauncher.mods.glasstech.FuelValues;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.MaceratorRecipeRegistry;
import net.minecraft.item.ItemStack;

public class MaceratorBlockEntity extends RecipeBlockEntityTemplate<BasicMachineRecipe> {

    public MaceratorBlockEntity() {
        super(VoltageTier.LV, 200, 4);
        SlotLayout.createBasic(this);
        setEnergyCapacity((int) (FuelValues.COAL * 0.5));
        setMaxEnergyInput(24);
    }

    @Override
    public String getName() {
        return "Macerator";
    }

    @Override
    public BasicMachineRecipe fetchRecipe(ItemStack[] input) {
        return MaceratorRecipeRegistry.INSTANCE.get(input);
    }
}
