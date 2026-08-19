package net.glasslauncher.mods.glasstech.item;

import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class SingleUseBatteryItem extends TemplateItem implements SingleUsePowerItem {
    private final int powerProvided;

    public SingleUseBatteryItem(Identifier identifier, int powerProvided) {
        super(identifier);
        this.powerProvided = powerProvided;
    }

    @Override
    public int getSingleUsePowerProvided() {
        return powerProvided;
    }
}
