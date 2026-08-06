package net.glasslauncher.mods.glasstech.item;

import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class Scrap extends TemplateItem implements GTMassAccelerant {

    public Scrap(Identifier identifier) {
        super(identifier);
    }

    @Override
    public int getAccelerantValue() {
        return 5000;
    }
}
