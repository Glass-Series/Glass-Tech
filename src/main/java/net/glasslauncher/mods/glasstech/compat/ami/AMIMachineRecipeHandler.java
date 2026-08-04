package net.glasslauncher.mods.glasstech.compat.ami;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

public class AMIMachineRecipeHandler <T extends BasicMachineRecipe> implements RecipeHandler<T> {
    private final String uid;
    private final Class<T> tClass;

    public AMIMachineRecipeHandler(String uid, Class<T> tClass) {
        this.uid = uid;
        this.tClass = tClass;
    }

    @Override
    @Nonnull
    public Class<T> getRecipeClass() {
        return tClass;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return uid;
    }

    @Override
    @Nonnull
    public RecipeWrapper getRecipeWrapper(@Nonnull T recipe) {
        return new AMIMachineRecipe(recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull T recipe) {
        return Arrays.stream(recipe.inputs).anyMatch(Objects::nonNull);
    }

}