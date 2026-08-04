package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.minecraft.item.ArmorItem;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class BronzeArmor extends TemplateArmorItem implements ArmorTextureProvider {
    public BronzeArmor(Identifier identifier, int j, int k, int slot) {
        super(identifier, j, k, slot);
    }

    @Override
    public Identifier getTexture(ArmorItem armor) {
        return GlassTech.NAMESPACE.id("bronze_armor");
    }
}
