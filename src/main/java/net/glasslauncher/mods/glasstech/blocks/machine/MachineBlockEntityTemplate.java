package net.glasslauncher.mods.glasstech.blocks.machine;

import lombok.Getter;
import lombok.Setter;
import net.danygames2014.nyalib.capability.CapabilityHelper;
import net.danygames2014.nyalib.capability.item.energyhandler.EnergyStorageItemCapability;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.GTTooltipInfo;
import net.glasslauncher.mods.glasstech.item.SingleUsePowerCapability;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.property.Properties;

import java.util.HashMap;
import java.util.Random;

// Holy shit this is a giga monolithic class
public abstract class MachineBlockEntityTemplate extends ConsumerBlockEntityTemplate implements GTMachineInventory, GTTooltipInfo {
    // Properties
    public int energyConsumption;

    // Random
    public Random random;

    // Lit State
    public boolean lit = false;

    // Inventory
    @Getter @Setter
    public ItemStack[] inventory = new ItemStack[0];
    @Getter
    public int inventoryIndex;
    @Getter @Setter
    public int[] inputSlots = new int[0];
    @Getter
    public final HashMap<RecipeOutputType, int[]>outputs = blankOutputs();
    @Getter
    public final HashMap<SlotType, int[]> slots = blankInputs();

    @Override
    public void increaseInventoryIndex() {
        inventoryIndex++;
    }

    public MachineBlockEntityTemplate(VoltageTier tier, int energyConsumption, int energyCapacity) {
        super(tier);

        // Properties
        this.energyConsumption = energyConsumption;
        setEnergyCapacity(energyCapacity);

        // Random
        this.random = new Random();
    }

    @Override
    public void tick() {
        super.tick();

        consumeFuel();

        if (!world.isRemote) {
            processTick();

            // Update lit state
            BlockState blockState = world.getBlockState(this.x, this.y, this.z);
            if (blockState.contains(Properties.LIT)) {
                if (!lit && blockState.get(Properties.LIT)) {
                    world.setBlockState(this.x, this.y, this.z, blockState.with(Properties.LIT, false));
                } else if (lit && !blockState.get(Properties.LIT)) {
                    world.setBlockState(this.x, this.y, this.z, blockState.with(Properties.LIT, true));
                }
            }
        }
    }

    public abstract void processTick();

    public void consumeFuel() {
        if (energy == getEnergyCapacity()) {
            return;
        }

        ItemStack[] fuelSlots = getSlots(SlotType.FUEL);
        for (int i = 0; i < fuelSlots.length; i++) {
            ItemStack fuelStack = fuelSlots[i];

            if (fuelStack == null) {
                continue;
            }

            // Single use energy item
            SingleUsePowerCapability singleUsePowerCapability = CapabilityHelper.getCapability(fuelStack, SingleUsePowerCapability.IDENTIFIER);
            if (singleUsePowerCapability != null) {
                int powerProvided = singleUsePowerCapability.getPowerProvided();
                if (powerProvided < 1) {
                    return;
                }

                if (energy + powerProvided <= getEnergyCapacity()) {
                    fuelStack.count--;
                    energy += powerProvided;
                }

                if (fuelStack.count <= 0) {
                    setSlot(SlotType.FUEL, i, null);
                }
                return;
            }

            // discharging item
            EnergyStorageItemCapability energyStorage = CapabilityHelper.getCapability(fuelStack, EnergyStorageItemCapability.class);
            if (energyStorage != null && energyStorage.getEnergyStored() > 0) {
                addEnergy(energyStorage.extractEnergy(Math.min(getRemainingCapacity(), Math.min(energyStorage.getMaxEnergyOutput(), getMaxEnergyInput(null)))));
            }
        }
    }

    public abstract boolean canProcess();

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.x, this.y, this.z) != this) {
            return false;
        } else {
            return !(player.getSquaredDistance(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D) > 64.0D);
        }
    }

    // NBT
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        // Inventory
        NbtList itemsNbt = nbt.getList("Items");

        for (int slot = 0; slot < itemsNbt.size(); slot++) {
            NbtCompound stackNbt = (NbtCompound) itemsNbt.get(slot);
            byte slotIndex = stackNbt.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length) {
                inventory[slotIndex] = new ItemStack(stackNbt);
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        // Inventory
        // Input
        NbtList itemsNbt = new NbtList();

        for (int slot = 0; slot < inventory.length; slot++) {
            if (inventory[slot] == null) {
                continue;
            }

            NbtCompound stackNbt = new NbtCompound();
            stackNbt.putByte("Slot", (byte) slot);
            inventory[slot].writeNbt(stackNbt);
            itemsNbt.add(stackNbt);
        }

        nbt.put("Items", itemsNbt);
    }
}
