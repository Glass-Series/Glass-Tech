package net.glasslauncher.mods.glasstech.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.glasslauncher.mods.glasstech.GTCustomAttackDamage;
import net.glasslauncher.mods.glasstech.GTCustomDamageHandler;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Inject(method = "damage", at = @At("HEAD"))
    private void hijackDamage(int damage, Entity entity, CallbackInfo ci) {
        if (getItem() instanceof GTCustomDamageHandler customDamageHandler) {
            customDamageHandler.onTakeDamage(entity, (ItemStack) (Object) this, damage);
        }
    }

    @WrapOperation(method = "getAttackDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getAttackDamage(Lnet/minecraft/entity/Entity;)I"))
    private int hijackAttackDamage(Item instance, Entity entity, Operation<Integer> original) {
        if (instance instanceof GTCustomAttackDamage customAttackDamage) {
            return customAttackDamage.glasstech$getAttackDamage((ItemStack) (Object) this, entity);
        }
        return original.call(instance, entity);
    }
}
