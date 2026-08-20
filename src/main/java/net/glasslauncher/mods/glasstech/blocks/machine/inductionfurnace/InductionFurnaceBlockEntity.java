package net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace;

import lombok.Getter;
import lombok.Setter;
import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotType;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.StackRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class InductionFurnaceBlockEntity extends RecipeBlockEntityTemplate<BasicMachineRecipe> implements Inventory {
    @Getter @Setter @ServerSyncedField
    private int maxHeat = 10000;
    @Getter @Setter @ServerSyncedField
    private int heat;

    public InductionFurnaceBlockEntity() {
        super(VoltageTier.LV, 4000, 5);
        addInput();
        addInput();
        addOutput(RecipeOutputType.PRIMARY);
        addOutput(RecipeOutputType.PRIMARY);
        addSlot(SlotType.FUEL);
    }

    @Override
    public boolean tryCraft(boolean simulate) {
        boolean canCraft = craft(new int[]{0}, simulate);
        boolean canCraftSecondary = craft(new int[]{1}, simulate);
        if (simulate && (canCraft || canCraftSecondary)) {
            return true;
        }
        if (canCraft != canCraftSecondary) {
            craft(new int[]{canCraft ? 0 : 1}, false);
            return true;
        }
        return canCraft;
    }

    @Override
    public void processTick() {
        super.processTick();
        boolean gettingRedstone = world.getPowerLevel(x, y, z) > 0;
        if (lit || (gettingRedstone && energyConsumption <= getEnergyStored())) {
            if (gettingRedstone) {
                lit = true;
                removeEnergy(energyConsumption);
            }
            heat++;
            if (heat > maxHeat) {
                heat = maxHeat;
            }
            progress += heat / 32;
            return;
        }

        heat -= 5;
        if (heat < 0) {
            heat = 0;
        }
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

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        heat = nbt.getInt("heat");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("heat", heat);
    }
}
