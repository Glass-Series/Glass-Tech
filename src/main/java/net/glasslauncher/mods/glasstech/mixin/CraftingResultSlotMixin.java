package net.glasslauncher.mods.glasstech.mixin;

import net.glasslauncher.mods.glasstech.events.init.RecipeInit;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingResultSlot.class)
public class CraftingResultSlotMixin {

    @Shadow
    @Final
    private Inventory input;

    @Inject(
            method = "onTakeItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;onCraft(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void stationapi_onTake(ItemStack stack, CallbackInfo ci) {
        RecipeInit.handlePainter(stack, input);
    }
}
