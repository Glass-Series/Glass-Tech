package net.glasslauncher.mods.glasstech.events.init;

import com.mojang.datafixers.util.Either;
import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.MaceratorRecipeRegistry;
import net.glasslauncher.mods.glasstech.recipe.machine.input.BlockRecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.impl.recipe.StationShapedRecipe;

public class CraftingMachines {
    public static Either<TagKey<Item>, ItemStack> I;
    public static Either<TagKey<Item>, ItemStack> W;
    public static Either<TagKey<Item>, ItemStack> WB;
    public static Either<TagKey<Item>, ItemStack> BAT;
    public static Either<TagKey<Item>, ItemStack> S;
    public static Either<TagKey<Item>, ItemStack> F;

    @EventListener
    public static void registerRecipes(RecipeRegisterEvent event) {
        if (I == null) {
            I = Either.right(new ItemStack(Item.IRON_INGOT));
            W = Either.right(new ItemStack(Block.PLANKS));
            WB = Either.right(new ItemStack(Item.WATER_BUCKET));
            BAT = Either.right(new ItemStack(Item.REDSTONE));
            S = Either.right(new ItemStack(Block.STONE));
            F = Either.right(new ItemStack(Block.FURNACE));
        }

        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {

            //noinspection unchecked :)
            CraftingRecipeManager.getInstance().getRecipes().add(
                    new StationShapedRecipe(3, 3, new Either[]{
                            W, WB, W,
                            I, BAT, I,
                            S, F, S
                    }
                    , new ItemStack(GlassTechBlocks.generatorBlock))
            );
        }
        if (event.recipeId.equals(GlassTech.NAMESPACE.id("macerator"))) {
            MaceratorRecipeRegistry.INSTANCE.register(GlassTech.NAMESPACE.id("iron"), new BasicMachineRecipe(new RecipeInput[]{new BlockRecipeInput(Block.IRON_ORE)}, new RecipeOutput[]{new RecipeOutput(new ItemStack(Item.IRON_INGOT))}));
        }
    }
}
