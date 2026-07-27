package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.danygames2014.nyalib.fluid.FluidRegistry;
import net.glasslauncher.mods.glasstech.GeothermalFuelRegistry;
import net.minecraft.block.Block;

public class GeothermalFuels {
    public static void initFuels() {
        GeothermalFuelRegistry.addFuelItem(FluidRegistry.get(Block.LAVA.id), 10);
    }
}
