package net.glasslauncher.mods.glasstech.item;

import net.minecraft.item.ArmorItem;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class CompositeChestplate extends TemplateArmorItem implements ArmorTextureProvider {
    private final Identifier identifier;

    public CompositeChestplate(Identifier identifier, int type, int textureIndex, int equipmentSlot) {
        super(identifier, type, textureIndex, equipmentSlot);
        this.identifier = identifier;
        setMaxDamage(256);
    }

    @Override
    public Identifier getTexture(ArmorItem armor) {
        return identifier;
    }
}
