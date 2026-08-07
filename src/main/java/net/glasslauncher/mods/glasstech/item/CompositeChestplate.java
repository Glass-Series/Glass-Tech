package net.glasslauncher.mods.glasstech.item;

import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class CompositeChestplate extends TemplateArmorItem {
    public CompositeChestplate(Identifier identifier, int type, int textureIndex, int equipmentSlot) {
        super(identifier, type, textureIndex, equipmentSlot);
        setMaxDamage(256);
    }
}
