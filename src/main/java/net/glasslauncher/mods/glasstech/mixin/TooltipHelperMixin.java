package net.glasslauncher.mods.glasstech.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.client.TooltipHelper;
import net.modificationstation.stationapi.api.client.event.gui.screen.container.TooltipBuildEvent;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(value = TooltipHelper.class, remap = false)
public class TooltipHelperMixin {

    @Inject(method = "getTooltipForItemStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;", shift = At.Shift.BEFORE), remap = false, cancellable = true)
    private static void makeBlocksWork(String originalTooltip, ItemStack itemStack, PlayerInventory playerInventory, HandledScreen container, CallbackInfoReturnable<ArrayList<String>> cir) {
        if (itemStack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof CustomTooltipProvider provider) {
            ArrayList<String> newTooltip = new ArrayList<>(Arrays.asList(provider.getTooltip(itemStack, originalTooltip)));

            StationAPI.EVENT_BUS.post(TooltipBuildEvent.builder()
                    .tooltip(newTooltip)
                    .inventory(playerInventory)
                    .container(container)
                    .itemStack(itemStack)
                    .build()
            );
            cir.setReturnValue(newTooltip);
        }
    }
}
