package net.glasslauncher.mods.glasstech.mixin;

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

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void hijackDamage(int damage, Entity entity, CallbackInfo ci) {
        if (getItem() instanceof GTCustomDamageHandler customDamageHandler) {
            customDamageHandler.onTakeDamage(entity, (ItemStack) (Object) this, damage);
            ci.cancel();
        }
    }
}
