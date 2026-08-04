package net.glasslauncher.mods.glasstech.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.glasslauncher.mods.glasstech.DamageSource;
import net.glasslauncher.mods.glasstech.GTArmorDamageHandler;
import net.glasslauncher.mods.glasstech.GlassTechPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements GlassTechPlayer {

    @Unique
    private boolean didJump;
    @Unique
    private boolean isHovering;

    @WrapOperation(method = "onLanding", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/Entity;I)Z"))
    private boolean stopFallDamage(LivingEntity instance, Entity entity, int damage, Operation<Boolean> original) {
        if (instance instanceof PlayerEntity player) {
            for (ItemStack itemStack : player.inventory.armor) {
                if (itemStack != null && itemStack.getItem() instanceof GTArmorDamageHandler handler && !handler.shouldDamage(player, itemStack, damage, DamageSource.FALLING)) {
                    return false;
                }
            }
        }

        return original.call(instance, entity, damage);
    }

    @WrapOperation(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/Entity;I)Z", ordinal = 0))
    private boolean stopCrushingDamage(LivingEntity instance, Entity entity, int damage, Operation<Boolean> original) {
        if (instance instanceof PlayerEntity player) {
            for (ItemStack itemStack : player.inventory.armor) {
                if (itemStack != null && itemStack.getItem() instanceof GTArmorDamageHandler handler && !handler.shouldDamage(player, itemStack, damage, DamageSource.CRUSHING)) {
                    return false;
                }
            }
        }

        return original.call(instance, entity, damage);
    }

    @WrapOperation(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/Entity;I)Z", ordinal = 1))
    private boolean stopDrowningDamage(LivingEntity instance, Entity entity, int damage, Operation<Boolean> original) {
        if (instance instanceof PlayerEntity player) {
            for (ItemStack itemStack : player.inventory.armor) {
                if (itemStack != null && itemStack.getItem() instanceof GTArmorDamageHandler handler && !handler.shouldDamage(player, itemStack, damage, DamageSource.DROWNING)) {
                    return false;
                }
            }
        }

        return original.call(instance, entity, damage);
    }

    @Inject(method = "tickMovement", at = @At(value = "HEAD"))
    private void resetJump(CallbackInfo ci) {
        didJump = false;
    }

    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;jump()V"))
    private void doingJump(CallbackInfo ci) {
        didJump = true;
    }

    @Override
    public boolean glasstech$didJump() {
        return didJump;
    }

    @Override
    public boolean glasstech$isHovering() {
        return isHovering;
    }

    @Override
    public void glasstech$setHovering(boolean hovering) {
        isHovering = hovering;
    }
}
