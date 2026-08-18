package net.glasslauncher.mods.glasstech.mixin;

import net.glasslauncher.mods.glasstech.item.GTArmorPlayerTick;
import net.glasslauncher.mods.glasstech.item.GTCustomAttackDamage;
import net.glasslauncher.mods.glasstech.entity.GlassTechPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements GlassTechPlayer {
    @Shadow
    public PlayerInventory inventory;

    @Shadow
    public abstract ItemStack getHand();

    @Shadow
    public abstract boolean isFullyAsleep();

    @Unique
    private boolean holdingAbilityKey = false;

    @Inject(method = "tick", at = @At("HEAD"))
    private void tickArmor(CallbackInfo ci) {
        for (int i = 0; i < inventory.armor.length; i++) {
            ItemStack armor = inventory.armor[i];
            if (armor != null && armor.getItem() instanceof GTArmorPlayerTick armorPlayerTick) {
                armorPlayerTick.tick((PlayerEntity) (Object) this, i);
            }
        }
    }

    @Override
    public boolean glasstech$isHoldingAbilityKey() {
        return holdingAbilityKey;
    }

    @Override
    public void glasstech$setHoldingAbilityKey(boolean value) {
        holdingAbilityKey = value;
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getHand()Lnet/minecraft/item/ItemStack;"))
    private void dealtDamage(Entity par1, CallbackInfo ci) {
        ItemStack hand = getHand();
        if (hand != null && hand.getItem() instanceof GTCustomAttackDamage customAttackDamage) {
            customAttackDamage.dealtDamage(hand, par1, (Entity) (Object) this);
        }
    }
}
