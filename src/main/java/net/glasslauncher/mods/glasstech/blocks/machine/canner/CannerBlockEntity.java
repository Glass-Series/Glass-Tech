package net.glasslauncher.mods.glasstech.blocks.machine.canner;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotType;
import net.glasslauncher.mods.glasstech.events.init.recipes.CannerRecipes;
import net.glasslauncher.mods.glasstech.item.FuelCan;
import net.glasslauncher.mods.glasstech.item.FuelJetPack;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.CannerRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.ItemRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.item.ItemStack;

public class CannerBlockEntity extends RecipeBlockEntityTemplate<BasicMachineRecipe> {

    public CannerBlockEntity() {
        super(VoltageTier.LV, 600, 3);
        addInput();
        addInput();
        addOutput(RecipeOutputType.PRIMARY);
        addSlot(SlotType.FUEL);
    }

    @Override
    public String getName() {
        return "Canner";
    }

    @Override
    public BasicMachineRecipe fetchRecipe(ItemStack[] input) {
        if (input[0] != null && input[1] != null && input[0].getItem() instanceof FuelJetPack fuelJetPack && fuelJetPack.getEnergyStored(input[0]) != fuelJetPack.getEnergyCapacity(input[0]) && input[1].getItem() instanceof FuelCan fuelCan) {
            ItemStack output = new ItemStack(input[0].getItem());
            output.getStationNbt().putInt("energy", Math.min(fuelJetPack.getEnergyCapacity(input[0]), fuelJetPack.getEnergyStored(input[0]) + fuelCan.getFuel(input[1])));
            return new CannerRecipes.CannerRecipe(new RecipeInput[]{new StackRecipeInput(input[0]), new ItemRecipeInput(input[1].getItem())}, new RecipeOutput[]{new RecipeOutput(output)});
        }
        return CannerRecipeRegistry.INSTANCE.get(input);
    }
}
