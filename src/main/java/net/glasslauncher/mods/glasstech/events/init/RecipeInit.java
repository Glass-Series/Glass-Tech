package net.glasslauncher.mods.glasstech.events.init;

import com.mojang.datafixers.util.Either;
import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.FoamColor;
import net.glasslauncher.mods.glasstech.events.init.recipes.*;
import net.glasslauncher.mods.glasstech.recipe.PainterRecipe;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.CraftingRecipeManager;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;

import java.util.List;

public class RecipeInit {

    @EventListener
    public static void registerRecipes(RecipeRegisterEvent event) {
        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            ToolRecipes.initRecipes();
            BlockRecipes.initRecipes();
            ItemRecipes.initRecipes();

            ItemStack painterStack = new ItemStack(GlassTechItems.painter);
            //noinspection unchecked
            List<Object> recipes = CraftingRecipeManager.getInstance().getRecipes();
            recipes.add(new PainterRecipe(painterStack, new Either[]{Either.right(new ItemStack(Item.DYE, 1, -1)), Either.right(new ItemStack(GlassTechItems.painter, 1, -1))}));
            recipes.add(new PainterRecipe(painterStack, new Either[]{Either.right(new ItemStack(Block.WOOL, 1, -1)), Either.right(new ItemStack(GlassTechItems.painter, 1, -1))}));
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
        ItemStack usedPainter = null;

        for (int i = 0; i < grid.size(); i++) {
            ItemStack itemUsed = grid.getStack(i);
            if (itemUsed != null && itemUsed.getItem() == GlassTechItems.painter) {
                usedPainter = itemUsed;
                break;
            }
        }

        if (usedPainter == null) {
            throw new RuntimeException("This should be impossible!");
        }

        for (int i = 0; i < grid.size(); i++) {
            ItemStack itemUsed = grid.getStack(i);
            if (itemUsed == null) {
                continue;
            }

            if (itemUsed.getItem() == Item.DYE) {
                int colorMeta = itemUsed.getDamage();
                NbtCompound nbt = output.getStationNbt();
                nbt.putString("color", FoamColor.DYE_TO_FOAM_COLOR[colorMeta].name());
                usedPainter.setDamage(Math.max(0, usedPainter.getDamage() - 8));
                addedDye = true;
            }
        }

        if (!addedDye) {
            output.setDamage(output.getMaxDamage() - 1);
        }
        else {
            output.setDamage(usedPainter.getDamage());
        }
    }
}
