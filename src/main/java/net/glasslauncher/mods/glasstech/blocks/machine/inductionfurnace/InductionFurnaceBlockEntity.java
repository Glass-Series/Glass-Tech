package net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.FuelValues;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotType;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.input.IdRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.SmeltingRecipeManager;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class InductionFurnaceBlockEntity extends RecipeBlockEntityTemplate<BasicMachineRecipe> implements Inventory {
    @Getter @Setter @ServerSyncedField
    private int maxHeat = 10000;
    @Getter @Setter @ServerSyncedField
    private int heat;

    public InductionFurnaceBlockEntity() {
        super(VoltageTier.LV, 4000, 15, 10000);
        addInput();
        addInput();
        addOutput(RecipeOutputType.PRIMARY);
        addOutput(RecipeOutputType.PRIMARY);
        addSlot(SlotType.FUEL);
    }

    @Override
    public String getName() {
        return "Induction Furnace";
    }

    @Override
    public BasicMachineRecipe fetchRecipe(ItemStack[] input) {
        if (input[0] == null) {
            return null;
        }

        ItemStack output = SmeltingRegistry.getResultFor(input[0]);
        if (output == null) {
            return null;
        }

        ItemStack inputItem = input[0].copy();
        inputItem.count = 1;

        return new BasicMachineRecipe(new RecipeInput[]{new StackRecipeInput(inputItem)}, new RecipeOutput[]{new RecipeOutput(output)});
    }
}
