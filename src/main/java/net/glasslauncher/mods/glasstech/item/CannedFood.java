package net.glasslauncher.mods.glasstech.item;

import net.modificationstation.stationapi.api.template.item.TemplateFoodItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class CannedFood extends TemplateFoodItem {
    public CannedFood(Identifier identifier) {
        super(identifier, 2, false);
        setMaxCount(16);
    }
}
