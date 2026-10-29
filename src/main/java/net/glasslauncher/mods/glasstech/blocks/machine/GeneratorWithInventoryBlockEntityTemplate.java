package net.glasslauncher.mods.glasstech.blocks.machine;

import lombok.Getter;
import lombok.Setter;
import net.danygames2014.nyalib.capability.CapabilityHelper;
import net.danygames2014.nyalib.capability.item.energyhandler.EnergyStorageItemCapability;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;

public abstract class GeneratorWithInventoryBlockEntityTemplate extends GeneratorBlockEntityTemplate implements GTMachineInventory {
    // Inventory
    @Getter
    @Setter
    public ItemStack[] inventory;
    @Getter
    public int inventoryIndex;
    @Getter @Setter
    public int[] inputSlots = new int[0];
    @Getter
    public final HashMap<RecipeOutputType, int[]> outputs = blankOutputs();
    @Getter
    public final HashMap<SlotType, int[]> slots = blankInputs();

    @Override
    public void increaseInventoryIndex() {
        inventoryIndex++;
    }

    public GeneratorWithInventoryBlockEntityTemplate(int inventorySize, VoltageTier voltageTier) {
        super(voltageTier);
        inventory = new ItemStack[inventorySize];
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        for (int i = 0; i < inventory.length; i++) {
            ItemStack slot = inventory[i];
            if (slot != null) {
                NbtCompound item = new NbtCompound();
                slot.writeNbt(item);
                tag.put("item" + i, item);
            }
        }
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        for (int i = 0; i < inventory.length; i++) {
            if (tag.contains("item" + i)) {
                inventory[i] = new ItemStack(tag.getCompound("item" + i));
            }
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return player.getSquaredDistance(x + 0.5, y + 0.5, z + 0.5) <= 64;
    }

    @Override
    public void tick() {
        super.tick();
        if (world.isRemote) {
            return;
        }

        for (ItemStack stack : inventory) {
            if (stack == null) {
                continue;
            }
            // Energy Storage Item
            EnergyStorageItemCapability energyStorage = CapabilityHelper.getCapability(stack, EnergyStorageItemCapability.class);
            if (energyStorage != null && getEnergyStored() > 0 && energyStorage.getEnergyStored() < energyStorage.getEnergyCapacity() && VoltageTier.get(getMaxOutputVoltage(null)).maxVoltage >= energyStorage.getMaxEnergyInput()) {
                energyStorage.addEnergy(removeEnergy(Math.min(energyStorage.getEnergyCapacity() - energyStorage.getEnergyStored(), energyStorage.getMaxEnergyInput())));
            }
        }
    }
}
