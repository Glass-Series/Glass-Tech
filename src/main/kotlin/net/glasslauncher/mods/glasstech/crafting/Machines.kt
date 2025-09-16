package net.glasslauncher.mods.glasstech.crafting

import com.mojang.datafixers.util.Either
import net.glasslauncher.mods.alwaysmoreitems.plugins.vanilla.crafting.ShapedRecipesHandler
import net.glasslauncher.mods.glasstech.events.init.InitListener
import net.mine_diver.unsafeevents.listener.EventListener
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.recipe.CraftingRecipeManager
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent
import net.modificationstation.stationapi.api.tag.TagKey
import net.modificationstation.stationapi.api.util.Identifier
import net.modificationstation.stationapi.impl.recipe.StationShapedRecipe

class Machines {
    companion object {
        val I = eitherRight(ItemStack(Item.IRON_INGOT))
        val W = eitherRight(ItemStack(Block.PLANKS))
        val WB = eitherRight(ItemStack(Item.WATER_BUCKET))
        val BAT = eitherRight(ItemStack(Item.REDSTONE))
        val S = eitherRight(ItemStack(Block.STONE))
        val F = eitherRight(ItemStack(Block.FURNACE))

        fun eitherRight(itemStack: ItemStack): Either<TagKey<Item>, ItemStack> {
            return Either.right(itemStack)
        }

        fun add(recipe: StationShapedRecipe) {
            CraftingRecipeManager.getInstance().recipes.add(recipe)
        }
    }

    @EventListener
    fun registerRecipes(event: RecipeRegisterEvent) {

        if (event.recipeId.equals("shaped")) {
            add(
                StationShapedRecipe(3, 3, arrayOf(
                    W, WB, W,
                    I, BAT, I,
                    S, F, S
            ), ItemStack(InitListener.generatorBlock))
            )
        }
    }
}