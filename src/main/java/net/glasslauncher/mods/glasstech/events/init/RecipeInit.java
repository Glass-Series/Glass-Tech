package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.GTHardenedFoamBlockColorProvider;
import net.glasslauncher.mods.glasstech.events.init.recipes.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.event.container.slot.ItemUsedInCraftingEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class RecipeInit {

    @EventListener
    public static void registerRecipes(RecipeRegisterEvent event) {
        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            ToolRecipes.initRecipes();
            BlockRecipes.initRecipes();
            ItemRecipes.initRecipes();

            ItemStack painterStack = new ItemStack(GlassTechItems.painter);
            CraftingRegistry.addShapelessRecipe(painterStack, new ItemStack(Item.DYE, 1, -1), new ItemStack(GlassTechItems.painter, 1, -1));
            CraftingRegistry.addShapelessRecipe(painterStack, new ItemStack(Block.WOOL, 1, -1), new ItemStack(GlassTechItems.painter, 1, -1));
        }
        else if (event.recipeId.equals(GlassTech.NAMESPACE.id("macerator"))) {
            MaceratorRecipes.initRecipes();
        }
        else if (event.recipeId.equals(GlassTech.NAMESPACE.id("compressor"))) {
            CompressorRecipes.initRecipes();
        }
        else if (event.recipeId.equals(GlassTech.NAMESPACE.id("canner"))) {
            CannerRecipes.initRecipes();
        }
        else if (event.recipeId.equals(GlassTech.NAMESPACE.id("electrolyzer"))) {
            ElectrolyzerRecipes.initRecipes();
        }
        else if (event.recipeId.equals(GlassTech.NAMESPACE.id("extractor"))) {
            ExtractorRecipes.initRecipes();
        }
        else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.SMELTING.type())) {
            SmeltingRecipes.initRecipes();
        }
    }

    public static void handlePainter(ItemStack output, Inventory grid) {
        if (output.getItem() != GlassTechItems.painter) {
            return;
        }

        boolean addedDye = false;

        for (int i = 0; i < grid.size(); i++) {
            ItemStack itemUsed = grid.getStack(i);
            if (itemUsed == null) {
                continue;
            }

            if (itemUsed.getItem() == Item.DYE) {
                int colorMeta = itemUsed.getDamage();
                NbtCompound nbt = output.getStationNbt();
                nbt.putString("color", GTHardenedFoamBlockColorProvider.WOOL_TO_FOAM_COLOR[colorMeta].name());
                output.setDamage(Math.max(0, output.getDamage() - 8));
                addedDye = true;
            } else if ((itemUsed.getItem() instanceof BlockItem blockItem) && blockItem.getBlock() == Block.WOOL) {
                NbtCompound nbt = output.getStationNbt();
                nbt.putString("color", "");
            }
        }

        if (!addedDye) {
            output.setDamage(output.getMaxDamage() - 1);
        }
    }
}
