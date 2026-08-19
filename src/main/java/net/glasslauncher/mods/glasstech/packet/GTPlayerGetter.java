package net.glasslauncher.mods.glasstech.packet;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public interface GTPlayerGetter {

    @Environment(EnvType.CLIENT)
    default PlayerEntity getPlayer() {
        return Minecraft.INSTANCE.player;
    }

    @Environment(EnvType.SERVER)
    default PlayerEntity getPlayer(NetworkHandler networkHandler) {
        return ((ServerPlayNetworkHandler) networkHandler).player;
    }
}
