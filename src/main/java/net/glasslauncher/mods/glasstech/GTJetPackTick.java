package net.glasslauncher.mods.glasstech;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface GTJetPackTick extends GTArmorPlayerTick {
    @Override
    default void tick(PlayerEntity player, int armorSlot) {
        ItemStack jetPack = player.inventory.armor[armorSlot];
        if (jetPack.getStationNbt().getInt("energy") < 0) {
            return;
        }
        if (player.onGround) {
            return;
        }
        int heightLimit = ((GTJetPackTick) jetPack.getItem()).getMaxHeight();
        if (heightLimit < player.y) {
            return;
        }

        if (player.glasstech$isHovering()) {
            if (player.isSneaking()) {
                if (player.velocityY <= -0.3) {
                    player.velocityY = -0.3;
                }
            }
            else if (player.velocityY < 0) {
                player.velocityY += getVelocity();
                if (player.velocityY > 0) {
                    player.velocityY = 0;
                }
                if (!player.world.isRemote) {
                    jetPack.getStationNbt().putInt("energy", Math.max(0, (int) (jetPack.getStationNbt().getInt("energy") - getVelocity() * 20)));
                }
            }
        }
        if (player.velocityY >= -0.5) {
            player.fallDistance = 0;
        }
        if (!player.jumping) {
            return;
        }

        player.velocityY += getVelocity();
        if (!player.world.isRemote) {
            jetPack.getStationNbt().putInt("energy", Math.max(0, (int) (jetPack.getStationNbt().getInt("energy") - getVelocity() * 100)));
        }
        if (player.velocityY > getMaxVelocity()) {
            player.velocityY = getMaxVelocity();
        }
    }

    int getMaxHeight();

    float getVelocity();
    float getMaxVelocity();
}
