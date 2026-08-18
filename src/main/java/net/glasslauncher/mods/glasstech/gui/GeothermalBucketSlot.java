package net.glasslauncher.mods.glasstech.gui;

import net.danygames2014.nyalib.fluid.FluidBucket;
import net.glasslauncher.mods.glasstech.recipe.GeothermalFuelRegistry;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class GeothermalBucketSlot extends FullBucketSlot {
    public GeothermalBucketSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() instanceof FluidBucket fluidBucket && GeothermalFuelRegistry.getFuelTime(fluidBucket.getFluid()) != 0;
    }
}
