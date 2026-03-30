package net.glasslauncher.mods.glasstech.events.init;

import com.mojang.datafixers.util.Either;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.impl.recipe.StationShapedRecipe;

public class CraftingMachines {
    public static final Either<TagKey<Item>, ItemStack> I = Either.right(new ItemStack(Item.IRON_INGOT));
    public static final Either<TagKey<Item>, ItemStack> W = Either.right(new ItemStack(Block.PLANKS));
    public static final Either<TagKey<Item>, ItemStack> WB = Either.right(new ItemStack(Item.WATER_BUCKET));
    public static final Either<TagKey<Item>, ItemStack> BAT = Either.right(new ItemStack(Item.REDSTONE));
    public static final Either<TagKey<Item>, ItemStack> S = Either.right(new ItemStack(Block.STONE));
    public static final Either<TagKey<Item>, ItemStack> F = Either.right(new ItemStack(Block.FURNACE));

    @EventListener
    public static void registerRecipes(RecipeRegisterEvent event) {

        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {

            //noinspection unchecked :)
            CraftingRecipeManager.getInstance().getRecipes().add(
                    new StationShapedRecipe(3, 3, new Either[]{
                            W, WB, W,
                            I, BAT, I,
                            S, F, S
                    }
                    , new ItemStack(InitListener.generatorBlock))
            );
        }
    }
}
