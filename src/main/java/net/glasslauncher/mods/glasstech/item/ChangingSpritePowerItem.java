package net.glasslauncher.mods.glasstech.item;

import lombok.Setter;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public abstract class ChangingSpritePowerItem extends PowerItem {
    @Setter
    protected int chargedTexture;
    @Setter
    protected int almostChargedTexture;
    @Setter
    protected int halfChargedTexture;
    @Setter
    protected int almostDischargedTexture;
    @Setter
    protected int dischargedTexture;

    public ChangingSpritePowerItem(Identifier identifier, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, voltageTier, maxEnergy);
        setMaxCount(1);
    }

    @Override
    public int getTextureId(ItemStack itemStack) {
        float percentCharge = (float) getEnergyStored(itemStack) / getEnergyCapacity(itemStack);
        if (percentCharge > .95f) {
            return chargedTexture;
        }
        if (percentCharge > .70f) {
            return almostChargedTexture;
        }
        if (percentCharge > .30f) {
            return halfChargedTexture;
        }
        if (percentCharge > 0f) {
            return almostDischargedTexture;
        }
        return dischargedTexture;
    }
}
