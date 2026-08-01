package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class PowerStorageArmor extends PowerArmor {

    public PowerStorageArmor(Identifier identifier, int slot, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, slot, voltageTier, maxEnergy);
    }

    @Override
    public boolean canExtractEnergy(ItemStack stack) {
        return true;
    }
}
