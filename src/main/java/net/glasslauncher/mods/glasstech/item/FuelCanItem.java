package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class FuelCanItem extends TemplateItem implements FuelCan {
    public FuelCanItem(Identifier identifier) {
        super(identifier);
    }

    @Override
    public int getFuel(ItemStack stack) {
        return VoltageTier.MV.maxVoltage * 160;
    }
}
