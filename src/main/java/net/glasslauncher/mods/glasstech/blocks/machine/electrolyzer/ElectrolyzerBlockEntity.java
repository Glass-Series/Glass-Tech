package net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.PowerStorageBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.recipe.machine.ElectrolyzerMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.ElectrolyzerRecipeRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.math.Direction;

public class ElectrolyzerBlockEntity extends RecipeBlockEntityTemplate<ElectrolyzerMachineRecipe> {

    public ElectrolyzerBlockEntity() {
        super(VoltageTier.MV, 200, 10, VoltageTier.MV.maxVoltage);
        SlotLayout.createBasic(this);
    }

    @Override
    public String getName() {
        return "Electrolyzer";
    }

    @Override
    public boolean craft(int[] inputIndexes, boolean simulate) {
        if (inputIndexes == null) {
            inputIndexes = getInputIndexes();
        }
        ItemStack[] inputs = getInputs(inputIndexes);
        ElectrolyzerMachineRecipe recipe = fetchRecipe(inputs);
        if (recipe == null) {
            return false;
        }

        int energyDiff = recipe.energyDifference;
        PowerStorageBlockEntityTemplate powerStorage = null;
        for (Direction dir : Direction.values()) {
            BlockEntity entity = world.getBlockEntity(x + dir.getOffsetX(), y + dir.getOffsetY(), z + dir.getOffsetZ());
            if (entity instanceof PowerStorageBlockEntityTemplate) {
                powerStorage = (PowerStorageBlockEntityTemplate) entity;
            }
        }
        if (powerStorage == null) {
            return false;
        }
        if (energyDiff > 0) {
            if (powerStorage.getEnergyCapacity() - powerStorage.getEnergyStored() < energyDiff) {
                return false;
            }
            if (!simulate) {
                powerStorage.addEnergy(energyDiff);
            }
        }
        else if (energyDiff < 0) {
            if (powerStorage.getEnergyStored() < -energyDiff) {
                return false;
            }
            if (!simulate) {
                powerStorage.removeEnergy(-energyDiff);
            }
        }

        return super.craft(inputIndexes, simulate);
    }

    @Override
    public ElectrolyzerMachineRecipe fetchRecipe(ItemStack[] input) {
        return ElectrolyzerRecipeRegistry.INSTANCE.get(input);
    }
}
