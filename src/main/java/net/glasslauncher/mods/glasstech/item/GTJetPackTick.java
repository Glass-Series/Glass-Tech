package net.glasslauncher.mods.glasstech.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public interface GTJetPackTick extends GTArmorPlayerTick {
    @Override
    default void tick(PlayerEntity player, int armorSlot) {
        ItemStack jetPack = player.inventory.armor[armorSlot];
        if (jetPack.getStationNbt().getInt("energy") <= 0) {
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
                player.velocityY += getVerticalAcceleration();
                if (player.velocityY > 0) {
                    player.velocityY = 0;
                }
                if (!player.world.isRemote) {
                    jetPack.getStationNbt().putInt("energy", Math.max(0, (int) (jetPack.getStationNbt().getInt("energy") - getVerticalAcceleration() * 20)));
                }
            }
        }
        if (player.velocityY >= -0.5) {
            player.fallDistance = 0;
        }

        Vec3d vec3d = Vec3d.create(player.sidewaysSpeed > 0 ? getHorizontalAcceleration() / 1.2 : player.sidewaysSpeed < 0 ? -getHorizontalAcceleration() / 1.2 : 0, 0, player.forwardSpeed > 0 ? getHorizontalAcceleration() : player.forwardSpeed < 0 ? -getHorizontalAcceleration() / 1.5 : 0);
        vec3d.rotateY((float) Math.toRadians(-player.yaw % 360));
        player.velocityX += vec3d.x;
        player.velocityZ += vec3d.z;

        if (player.forwardSpeed > getMaxHorizontalVelocity()) {
            player.forwardSpeed = getMaxHorizontalVelocity();
        }

        if (!player.jumping) {
            return;
        }

        player.velocityY += getVerticalAcceleration();
        if (!player.world.isRemote) {
            jetPack.getStationNbt().putInt("energy", Math.max(0, (int) (jetPack.getStationNbt().getInt("energy") - getVerticalAcceleration() * 100)));
        }
        if (player.velocityY > getMaxVerticalVelocity()) {
            player.velocityY = getMaxVerticalVelocity();
        }
    }

    int getMaxHeight();

    float getVerticalAcceleration();
    float getMaxVerticalVelocity();

    float getHorizontalAcceleration();
    float getMaxHorizontalVelocity();
}
