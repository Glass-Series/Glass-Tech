package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
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
        simpleRegister(Item.IRON_INGOT, GlassTechItems.refinedIronIngot);
        simpleRegister(GlassTechBlocks.tinOreBlock, GlassTechItems.tinIngot);
        simpleRegister("c:dusts/tin", GlassTechItems.tinIngot);
        simpleRegister(GlassTechBlocks.copperOreBlock, GlassTechItems.copperIngot);
        simpleRegister("c:dusts/copper", GlassTechItems.copperIngot);
        simpleRegister("c:dusts/bronze", GlassTechItems.bronzeIngot);
        simpleRegister("c:resin", GlassTechItems.rubber);
        simpleRegister("c:dusts/iron", Item.IRON_INGOT);
        simpleRegister("c:dusts/gold", Item.GOLD_INGOT);
        simpleRegister(GlassTechItems.hydratedCoalDust, GlassTechItems.coalDust);
    }

    private static void simpleRegister(Item item, Item item2) {
        SmeltingRegistry.addSmeltingRecipe(item.id, new ItemStack(item2));
    }

    private static void simpleRegister(Block item, Item item2) {
        SmeltingRegistry.addSmeltingRecipe(item.id, new ItemStack(item2));
    }

    private static void simpleRegister(String tag, Item item2) {
        SmeltingRegistry.addSmeltingRecipe(TagKey.of(ItemRegistry.KEY, Identifier.of(tag)), new ItemStack(item2));
    }
}