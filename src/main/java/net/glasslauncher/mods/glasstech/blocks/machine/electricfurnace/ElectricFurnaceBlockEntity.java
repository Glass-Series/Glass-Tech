package net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.glasslauncher.mods.glasstech.FuelValues;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.input.IdRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.SmeltingRecipeManager;

public class ElectricFurnaceBlockEntity extends RecipeBlockEntityTemplate<BasicMachineRecipe> implements Inventory {
    public static Int2ObjectMap<BasicMachineRecipe> CACHE = new Int2ObjectOpenHashMap<>();

    public ElectricFurnaceBlockEntity() {
        super(VoltageTier.LV, 200, 4);
        SlotLayout.createBasic(this);
        setEnergyCapacity((int) (FuelValues.COAL * 0.5)); // .2 coal worth
        setMaxEnergyInput(24); // 2 generators
    }

    @Override
    public String getName() {
        return "Electric Furnace";
    }

    @Override
    public BasicMachineRecipe fetchRecipe(ItemStack[] input) {
        if (input[0] == null) {
            return null;
        }

        int id = input[0].itemId;
        if (input[0].getItem() instanceof BlockItem blockItem) {
            id = blockItem.getBlock().id;
        }

        ItemStack output = SmeltingRecipeManager.getInstance().craft(id);
        if (output == null) {
            return null;
        }

        return CACHE.computeIfAbsent(id, k -> new BasicMachineRecipe(new RecipeInput[]{new IdRecipeInput(k)}, new RecipeOutput[]{new RecipeOutput(output)}));
    }
}
