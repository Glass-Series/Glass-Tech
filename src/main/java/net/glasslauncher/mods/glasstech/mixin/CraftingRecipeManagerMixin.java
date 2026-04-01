package net.glasslauncher.mods.glasstech.mixin;

import net.glasslauncher.mods.glasstech.events.init.InitListener;
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
        postRecipes("macerator");
    }

    @Unique
    private void postRecipes(String id) {
        StationAPI.EVENT_BUS.post(RecipeRegisterEvent.builder().recipeId(InitListener.NAMESPACE.id(id)).build());
    }
}
