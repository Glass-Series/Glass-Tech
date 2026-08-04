package net.glasslauncher.mods.glasstech.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.glasslauncher.mods.glasstech.GTCustomDamageHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements Inventory {

    @Shadow
    public PlayerEntity player;

    @Redirect(method = "getTotalArmorDurability", at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/entity/player/PlayerInventory;armor:[Lnet/minecraft/item/ItemStack;",
            args = "array=get",
            ordinal = 1,
            opcode = Opcodes.GETFIELD))
    private ItemStack e(ItemStack[] array, int index) {
        ItemStack stack = array[index];
        return stack == null || (stack.getItem() instanceof GTCustomDamageHandler gtCustomDamageHandler && !gtCustomDamageHandler.canAbsorbDamage(player, stack)) ? null : stack;
    }

    @WrapOperation(method = "getTotalArmorDurability", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxDamage()I"))
    private int adjustMaxDamage(ItemStack instance, Operation<Integer> original) {
        if (instance.getItem() instanceof GTCustomDamageHandler customDamageHandler) {
            return customDamageHandler.getMaxArmorDamage(player, instance);
        }
        return original.call(instance);
    }

    @WrapOperation(method = "getTotalArmorDurability", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getDamage2()I"))
    private int adjustCurrentDamage(ItemStack instance, Operation<Integer> original) {
        if (instance.getItem() instanceof GTCustomDamageHandler customDamageHandler) {
            return customDamageHandler.getCurrentArmorDamage(player, instance);
        }
        return original.call(instance);
    }
}
