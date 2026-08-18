package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import lombok.Getter;
import lombok.Setter;
import net.danygames2014.nyalib.fluid.FluidBucket;
import net.danygames2014.nyalib.fluid.FluidStack;
import net.danygames2014.nyalib.fluid.block.FluidHandler;
import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.recipe.GeothermalFuelRegistry;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorWithInventoryBlockEntityTemplate;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class ThermalGeneratorBlockEntity extends GeneratorWithInventoryBlockEntityTemplate implements Inventory, FluidHandler {
    @Getter @Setter @ServerSyncedField
    protected int initialFuelTicks;
    @Getter @Setter @ServerSyncedField
    protected int fuelTicks;
    @Getter @Setter
    protected int generationAmount = 20; // eu/t
    @Getter @Setter
    protected float fuelEfficiency = 1f;

    protected FluidStack currentFluid;
    protected int maxFluidAmount = 1000;

    public ThermalGeneratorBlockEntity() {
        super(3, VoltageTier.LV);
        setEnergyCapacity(4000);
    }

    @Override
    public void tick() {
        super.tick();
        if (world.isRemote) {
            return;
        }
        if ((currentFluid == null || currentFluid.amount <= maxFluidAmount - 1000) && slots[0] != null && slots[0].getItem() instanceof FluidBucket fluidBucket && (slots[1] == null || (slots[1].getItem() == fluidBucket.getEmptyBucketItem() && slots[1].count < fluidBucket.getEmptyBucketItem().getMaxCount()))) {
            if (currentFluid == null) {
                currentFluid = new FluidStack(fluidBucket.getFluid(), 1000);
            }
            else {
                currentFluid.amount += 1000;
            }
            slots[0].count -= 1;
            if (slots[0].count < 1) {
                slots[0] = null;
            }
            if (slots[1] == null) {
                slots[1] = new ItemStack(fluidBucket.getEmptyBucketItem());
            }
            else {
                slots[1].count++;
            }
        }
        if (currentFluid != null && currentFluid.amount >= 10 && fuelTicks < 1 && energy < getEnergyCapacity()) {
            int fuelTime = (int) (GeothermalFuelRegistry.getFuelTime(currentFluid.fluid) * fuelEfficiency);
            if (fuelTime < 1) {
                return;
            }
            initialFuelTicks = fuelTime;
            fuelTicks = fuelTime;
            currentFluid.amount -= 10;
            if (currentFluid.amount < 1) {
                currentFluid = null;
            }
        }
        BlockState state = world.getBlockState(x, y, z);
        if (fuelTicks > 0) {
            fuelTicks--;
            energy += generationAmount; // in eu/t
            if (state.contains(Properties.LIT) && !state.get(Properties.LIT)) {
                FurnaceBlock.ignoreBlockRemoval = true;
                world.setBlockState(x, y, z, state.with(Properties.LIT, true));
                FurnaceBlock.ignoreBlockRemoval = false;
            }
        }
        else if (state.get(Properties.LIT)) {
            FurnaceBlock.ignoreBlockRemoval = true;
            world.setBlockState(x, y, z, state.with(Properties.LIT, false));
            FurnaceBlock.ignoreBlockRemoval = false;
        }
        if (energy > getEnergyCapacity()) {
            energy = getEnergyCapacity();
        }

        markDirty();
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.put("fuelTicks", new NbtInt(fuelTicks));
        tag.put("initialFuelTicks", new NbtInt(initialFuelTicks));
        if (currentFluid == null || currentFluid.fluid == null) {
            return;
        }

        NbtCompound fluid = new NbtCompound();
        currentFluid.writeNbt(fluid);
        tag.put("fluid", fluid);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        fuelTicks = tag.getInt("fuelTicks");
        initialFuelTicks = tag.getInt("initialFuelTicks");
        if (!tag.contains("fluid")) {
            return;
        }
        currentFluid = new FluidStack(tag.getCompound("fluid"));
    }

    @Override
    public String getName() {
        return "Thermal Generator";
    }

    @Override
    public int getGeneratingCurrent() {
        return fuelTicks > 1 ? generationAmount : 0;
    }

    // Fluid handler stuff

    @Override
    public boolean canExtractFluid(@Nullable Direction direction) {
        return direction != Direction.DOWN;
    }

    @Override
    public FluidStack extractFluid(int slot, int amount, @Nullable Direction direction) {
        return null;
    }

    @Override
    public boolean canInsertFluid(@Nullable Direction direction) {
        return true;
    }

    @Override
    public FluidStack insertFluid(FluidStack stack, int slot, @Nullable Direction direction) {
        if (slot != 0) {
            return stack;
        }
        return insertFluid(stack, direction);
    }

    @Override
    public FluidStack insertFluid(FluidStack stack, @Nullable Direction direction) {
        if (GeothermalFuelRegistry.getFuelTime(stack.fluid) < 1) {
            return stack;
        }

        int amount;
        if (currentFluid == null) {
            amount = Math.min(stack.amount, 1000);
            currentFluid = new FluidStack(stack.fluid, amount);
        }
        else {
            amount = Math.min(stack.amount, maxFluidAmount - currentFluid.amount);
            currentFluid.amount += amount;
        }
        stack.amount -= amount;

        if (stack.amount < 1) {
            return null;
        }
        return stack;
    }

    @Override
    public FluidStack getFluid(int slot, @Nullable Direction direction) {
        return currentFluid;
    }

    @Override
    public boolean setFluid(int slot, FluidStack stack, @Nullable Direction direction) {
        stack.amount = Math.min(maxFluidAmount, stack.amount);
        currentFluid = stack;
        return true;
    }

    @Override
    public int getFluidSlots(@Nullable Direction direction) {
        return 1;
    }

    @Override
    public int getFluidCapacity(int slot, @Nullable Direction direction) {
        return maxFluidAmount;
    }

    @Override
    public FluidStack[] getFluids(@Nullable Direction direction) {
        return new FluidStack[]{currentFluid};
    }

    @Override
    public boolean canConnectFluid(Direction direction) {
        return true;
    }
}
