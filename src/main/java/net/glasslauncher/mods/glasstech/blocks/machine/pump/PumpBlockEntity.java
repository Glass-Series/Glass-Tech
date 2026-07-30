package net.glasslauncher.mods.glasstech.blocks.machine.pump;

import net.danygames2014.nyalib.fluid.*;
import net.danygames2014.nyalib.fluid.block.FluidHandler;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.ProgressMachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class PumpBlockEntity extends ProgressMachineBlockEntityTemplate implements FluidHandler {
    protected FluidStack currentFluid;
    protected int maxFluidAmount = 4000;

    public PumpBlockEntity() {
        super(VoltageTier.LV, 200, 2, 300);
        SlotLayout.createFluid(this);
        autoResetProgress = false;
    }

    @Override
    public String getName() {
        return "Pump";
    }

    @Override
    public void tick() {
        super.tick();
        ItemStack stack = getInput(0);
        if (stack != null && stack.getItem() instanceof FluidBucket bucket && bucket.getFluid() == null && currentFluid != null && currentFluid.fluid != null && Fluids.BUCKET_SIZE <= currentFluid.amount) {
            Item fullBucket = bucket.getFullBucketItem(currentFluid.fluid);
            if (pushOutput(RecipeOutputType.PRIMARY, new ItemStack(fullBucket), false)) {
                currentFluid.amount -= Fluids.BUCKET_SIZE;
                stack.count--;
                if (stack.count <= 0) {
                    setInput(0, null);
                }
            }
        }
    }

    @Override
    public void craftRecipe() {
        BlockState state = world.getBlockState(x, y - 1, z);
        Fluid fluid = FluidRegistry.get(state.getBlock().id);
        if (fluid == null || (currentFluid != null && (fluid != currentFluid.fluid || currentFluid.amount + Fluids.BUCKET_SIZE > maxFluidAmount))) {
            return;
        }

        world.setBlock(x, y - 1, z, 0);
        if (currentFluid == null) {
            currentFluid = new FluidStack(fluid, Fluids.BUCKET_SIZE);
        }
        else {
            currentFluid.amount += Fluids.BUCKET_SIZE;
        }
        progress = 0;
    }

    @Override
    public boolean canProcess() {
        return (currentFluid == null || !(maxFluidAmount - Fluids.BUCKET_SIZE < currentFluid.amount)) && FluidRegistry.get(world.getBlockId(x, y - 1, z)) != null; // The fluid below is registered, go time
    }

    // Fluid handler stuff

    @Override
    public boolean canExtractFluid(@Nullable Direction direction) {
        return direction != Direction.DOWN;
    }

    public void validateContents() {
        if (currentFluid.amount > 0) {
            return;
        }
        currentFluid.amount = 0;
        currentFluid = null;
    }

    @Override
    public FluidStack extractFluid(int slot, int amount, @Nullable Direction direction) {
        if (slot != 0 || direction == Direction.DOWN || currentFluid.amount == 0) {
            return null;
        }
        FluidStack stack = new FluidStack(currentFluid.fluid, Math.min(amount, currentFluid.amount));
        currentFluid.amount -= stack.amount;
        validateContents();
        return stack;
    }

    @Override
    public boolean canInsertFluid(@Nullable Direction direction) {
        return false;
    }

    @Override
    public FluidStack insertFluid(FluidStack stack, int slot, @Nullable Direction direction) {
        return stack;
    }

    @Override
    public FluidStack insertFluid(FluidStack stack, @Nullable Direction direction) {
        return stack;
    }

    @Override
    public FluidStack getFluid(int slot, @Nullable Direction direction) {
        if (slot != 0 || direction == Direction.DOWN) {
            return null;
        }
        return currentFluid;
    }

    @Override
    public boolean setFluid(int slot, FluidStack stack, @Nullable Direction direction) {
        if (slot != 0 || direction == Direction.DOWN) {
            return false;
        }
        stack.amount = Math.min(maxFluidAmount, stack.amount);
        currentFluid = stack;
        return true;
    }

    @Override
    public int getFluidSlots(@Nullable Direction direction) {
        return direction == Direction.DOWN ? 0 : 1;
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
        return direction != Direction.DOWN;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (!nbt.contains("fluid")) {
            return;
        }
        currentFluid = new FluidStack(nbt.getCompound("fluid"));
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (currentFluid == null || currentFluid.fluid == null) {
            return;
        }

        NbtCompound fluid = new NbtCompound();
        currentFluid.writeNbt(fluid);
        nbt.put("fluid", fluid);
    }
}
