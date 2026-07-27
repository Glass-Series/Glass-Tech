package net.glasslauncher.mods.glasstech.mixin;

import net.glasslauncher.mods.glasstech.MachineRecipeIdentifier;
import net.minecraft.recipe.CraftingRecipeManager;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingRecipeManager.class)
public class CraftingRecipeManagerMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void initMachineRegistries(CallbackInfo ci) {
        postRecipes(MachineRecipeIdentifier.MACERATOR);
        postRecipes(MachineRecipeIdentifier.COMPRESSOR);
        postRecipes(MachineRecipeIdentifier.CANNER);
        postRecipes(MachineRecipeIdentifier.ELECTROLYZER);
        postRecipes(MachineRecipeIdentifier.EXTRACTOR);
        postRecipes(MachineRecipeIdentifier.GEOTHERMAL);
    }

    @Unique
    private void postRecipes(MachineRecipeIdentifier id) {
        StationAPI.EVENT_BUS.post(RecipeRegisterEvent.builder().recipeId(id.identifier).build());
    }
}
