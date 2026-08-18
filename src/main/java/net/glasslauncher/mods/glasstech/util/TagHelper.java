package net.glasslauncher.mods.glasstech.util;

import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.util.Identifier;

public class TagHelper {

    public static TagKey<Item> tagKey(String name) {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:" + name));
    }
}
