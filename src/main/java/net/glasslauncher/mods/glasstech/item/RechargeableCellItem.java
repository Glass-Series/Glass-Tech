package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class RechargeableCellItem extends ChangingSpritePowerItem {
    public RechargeableCellItem(Identifier identifier, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, voltageTier, maxEnergy);
    }

    @Override
    public boolean canExtractEnergy(ItemStack stack) {
        return true;
    }
}
