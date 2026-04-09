package net.glasslauncher.mods.glasstech.events.init.recipes;

import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class SmeltingRecipes {
    public static void initRecipes() {
        simpleRegister(Item.IRON_INGOT, GlassTechItems.refinedIronIngot);
        simpleRegister(GlassTechBlocks.tinOreBlock, GlassTechItems.tinIngot);
        simpleRegister(GlassTechItems.tinDust, GlassTechItems.tinIngot);
        simpleRegister(GlassTechBlocks.copperOreBlock, GlassTechItems.copperIngot);
        simpleRegister(GlassTechItems.copperDust, GlassTechItems.copperIngot);
        simpleRegister(GlassTechItems.bronzeDust, GlassTechItems.bronzeIngot);
        simpleRegister(GlassTechItems.resin, GlassTechItems.rubber);
        simpleRegister(GlassTechItems.ironDust, Item.IRON_INGOT);
        simpleRegister(GlassTechItems.goldDust, Item.GOLD_INGOT);
        simpleRegister(GlassTechItems.hydratedCoalDust, GlassTechItems.coalDust);
    }

    private static void simpleRegister(Item item, Item item2) {
        SmeltingRegistry.addSmeltingRecipe(item.id, new ItemStack(item2));
    }

    private static void simpleRegister(Block item, Item item2) {
        SmeltingRegistry.addSmeltingRecipe(item.id, new ItemStack(item2));
    }
}
