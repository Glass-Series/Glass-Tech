package net.glasslauncher.mods.glasstech.recipe;

import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.danygames2014.nyalib.fluid.Fluid;
import net.modificationstation.stationapi.api.util.API;

public class GeothermalFuelRegistry {
    private static final Reference2IntMap<Fluid> ITEM_FUEL_TIME = new Reference2IntOpenHashMap<>();

    @API
    public static int getFuelTime(Fluid fluid) {
        if (fluid == null)
            return 0;

        return ITEM_FUEL_TIME.getOrDefault(fluid, 0);
    }

    /**
     * @param fuel the fluid entry for the fuel.
     * @param fuelTime the time in ticks 10mb of the fuel lasts for.
     */
    @API
    public static void addFuelItem(Fluid fuel, int fuelTime) {
        ITEM_FUEL_TIME.putIfAbsent(fuel, fuelTime);
    }
}
