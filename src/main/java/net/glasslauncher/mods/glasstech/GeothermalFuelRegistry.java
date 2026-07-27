package net.glasslauncher.mods.glasstech;

import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.*;
import lombok.val;
import net.danygames2014.nyalib.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.registry.*;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.util.API;

import java.util.Map;
import java.util.OptionalInt;
import java.util.function.ToIntFunction;

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
