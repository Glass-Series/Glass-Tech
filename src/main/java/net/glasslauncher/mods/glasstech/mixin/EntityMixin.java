package net.glasslauncher.mods.glasstech.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.glasslauncher.mods.glasstech.entity.DamageSource;
import net.glasslauncher.mods.glasstech.item.GTArmorDamageHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

    @Shadow
    public World world;

    @WrapOperation(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isFireOrLavaInBox(Lnet/minecraft/util/math/Box;)Z"))
    private boolean stopLavaFireDamage(World instance, Box box, Operation<Boolean> original) {
        if ((Object) this instanceof PlayerEntity player) {
            boolean isLava = isLavaInBox(box);
            boolean isFire = isFireInBox(box);
            for (ItemStack itemStack : player.inventory.armor) {
                if (itemStack != null && itemStack.getItem() instanceof GTArmorDamageHandler handler) {
                    boolean shouldLava = isLava && handler.shouldDamage(player, itemStack, 1, DamageSource.LAVA);
                    boolean shouldFire = isFire && handler.shouldDamage(player, itemStack, 1, DamageSource.FIRE);
                    if (!shouldLava && !shouldFire) {
                        return false;
                    }
                }
            }
        }

        return original.call(instance, box);
    }

    @Inject(method = "setOnFire", at = @At("HEAD"), cancellable = true)
    private void stopIgniteDamage(CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity player) {
            for (ItemStack itemStack : player.inventory.armor) {
                if (itemStack != null && itemStack.getItem() instanceof GTArmorDamageHandler handler && !handler.shouldDamage(player, itemStack, 4, DamageSource.LAVA)) {
                    ci.cancel();
                }
            }
        }
    }

    @Unique
    private boolean isLavaInBox(Box box) {
        int var2 = MathHelper.floor(box.minX);
        int var3 = MathHelper.floor(box.maxX + 1.0);
        int var4 = MathHelper.floor(box.minY);
        int var5 = MathHelper.floor(box.maxY + 1.0);
        int var6 = MathHelper.floor(box.minZ);
        int var7 = MathHelper.floor(box.maxZ + 1.0);
        if (world.isRegionLoaded(var2, var4, var6, var3, var5, var7)) {
            for (int var8 = var2; var8 < var3; var8++) {
                for (int var9 = var4; var9 < var5; var9++) {
                    for (int var10 = var6; var10 < var7; var10++) {
                        int var11 = world.getBlockId(var8, var9, var10);
                        if (var11 == Block.FLOWING_LAVA.id || var11 == Block.LAVA.id) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Unique // Lazy copy paste go brrrr
    public boolean isFireInBox(Box box) {
        int var2 = MathHelper.floor(box.minX);
        int var3 = MathHelper.floor(box.maxX + 1.0);
        int var4 = MathHelper.floor(box.minY);
        int var5 = MathHelper.floor(box.maxY + 1.0);
        int var6 = MathHelper.floor(box.minZ);
        int var7 = MathHelper.floor(box.maxZ + 1.0);
        if (world.isRegionLoaded(var2, var4, var6, var3, var5, var7)) {
            for (int var8 = var2; var8 < var3; var8++) {
                for (int var9 = var4; var9 < var5; var9++) {
                    for (int var10 = var6; var10 < var7; var10++) {
                        int var11 = world.getBlockId(var8, var9, var10);
                        if (var11 == Block.FIRE.id) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
