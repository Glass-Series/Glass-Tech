package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.danygames2014.nyalib.fluid.FluidRegistry;
import net.glasslauncher.mods.glasstech.GeothermalFuelRegistry;
import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

public class GeothermalFuels {
    public static void initFuels() {
        GeothermalFuelRegistry.addFuelItem(FluidRegistry.get(Block.LAVA.id), 10);
    }
}
