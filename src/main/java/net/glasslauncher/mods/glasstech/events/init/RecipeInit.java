package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.events.init.recipes.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;

public class RecipeInit {

    @EventListener
    public static void registerRecipes(RecipeRegisterEvent event) {
        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            ToolRecipes.initRecipes();
            BlockRecipes.initRecipes();
            ItemRecipes.initRecipes();
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
}
