package net.glasslauncher.mods.glasstech.compat.ami;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.GuiItemStackGroup;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.compat.ami.drawable.ScreenDrawable;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.output.ChanceRecipeOutput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RangeRecipeOutput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutput;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BasicMachineCategory<T extends MachineScreenTemplate<V>, V extends RecipeBlockEntityTemplate<?>> implements RecipeCategory {
    private final AMIDrawable slotAutoDraw;

    private final String type;
    private final String name;

    public BasicMachineCategory(String type, String name, T screen) {
        this.type = type;
        this.name = name;
        slotAutoDraw = new ScreenDrawable<>(screen);
    }

    @Override
    public @NotNull String getUid() {
        return type;
    }

    @Override
    public @NotNull String getTitle() {
        return name;
    }

    @Override
    public @NotNull AMIDrawable getBackground() {
        return slotAutoDraw;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {

    }

    @Override
    public void drawAnimations(Minecraft minecraft) {

    }

    public final Object2ObjectOpenHashMap<ItemStack, RecipeOutput> slotToOutputMap = new Object2ObjectOpenHashMap<>();

    @Override
    public void setRecipe(@NotNull RecipeLayout recipeLayout, @NotNull RecipeWrapper recipeWrapper) {
        if (recipeWrapper instanceof AMIMachineRecipe maceratorRecipeWrapper) {
            BasicMachineRecipe recipe = maceratorRecipeWrapper.recipe;

            GuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

            guiItemStacks.init(0, true, 1, 1);
            guiItemStacks.set(0, recipe.inputs[0].getRepresentingStacks());

            int i = 0, pI = 0, sI = 0;
            for (var output : recipe.outputs) {
                i++;

                switch (output.type) {
                    case PRIMARY -> {
                        guiItemStacks.init(i, false, 61 + (pI * 22), 19);
                        pI++;
                    }
                    case SECONDARY, WASTE -> {
                        guiItemStacks.init(i, false, 61 + (sI * 22), 41);
                        sI++;
                    }
                }

                ItemStack stack = output.getOutput(null);
                guiItemStacks.set(i, stack);
                slotToOutputMap.put(stack, output);
            }

            guiItemStacks.addTooltipCallback(this::callMeMaybe);
        }
    }

    private void callMeMaybe(int slotIndex, boolean input, ItemStack ingredient, ArrayList<Object> tooltip) {
        if (!slotToOutputMap.containsKey(ingredient)) {
            return;
        }

        RecipeOutput output = slotToOutputMap.get(ingredient);
        if (output instanceof ChanceRecipeOutput chanceOutput) {
            tooltip.add(Formatting.GRAY + "Output Chance: " + (100 / chanceOutput.rarity) + "%");
        }

        if (output instanceof RangeRecipeOutput rangeOutput) {
            tooltip.add(Formatting.GRAY + "Output Range: " + rangeOutput.minCount + "-" + rangeOutput.maxCount);
        }
    }
}
