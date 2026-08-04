package net.glasslauncher.mods.glasstech.item;

import net.danygames2014.nyalib.energy.EnergyStorageItem;
import net.glasslauncher.mods.glasstech.VoltageTier;

public interface GTEnergyStorageItem extends EnergyStorageItem {
    VoltageTier getVoltageTier();
}
