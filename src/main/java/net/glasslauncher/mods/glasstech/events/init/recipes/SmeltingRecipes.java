package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.util.Identifier;

public class SmeltingRecipes {
    public static void initRecipes() {
        simpleRegister("ingots/iron", GlassTechItems.refinedIronIngot);
        simpleRegister("ores/tin", GlassTechItems.tinIngot);
        simpleRegister("dusts/tin", GlassTechItems.tinIngot);
        simpleRegister("ores/copper", GlassTechItems.copperIngot);
        simpleRegister("dusts/copper", GlassTechItems.copperIngot);
        simpleRegister("dusts/bronze", GlassTechItems.bronzeIngot);
        simpleRegister("resins", GlassTechItems.rubber);
        simpleRegister("dusts/iron", Item.IRON_INGOT);
        simpleRegister("dusts/gold", Item.GOLD_INGOT);
        simpleRegister(GlassTechItems.hydratedCoalDust, GlassTechItems.coalDust);
    }

    private static void simpleRegister(Item item, Item item2) {
        SmeltingRegistry.addSmeltingRecipe(item.id, new ItemStack(item2));
    }

    private static void simpleRegister(Block item, Item item2) {
        SmeltingRegistry.addSmeltingRecipe(item.id, new ItemStack(item2));
    }

    private static void simpleRegister(String tag, Item item2) {
        SmeltingRegistry.addSmeltingRecipe(TagKey.of(ItemRegistry.KEY, Identifier.of("c:" + tag)), new ItemStack(item2));
    }
}