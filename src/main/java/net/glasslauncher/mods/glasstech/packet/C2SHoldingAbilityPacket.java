package net.glasslauncher.mods.glasstech.packet;

import lombok.SneakyThrows;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.NetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;

public class C2SHoldingAbilityPacket extends TemplateManagedPacket<C2SHoldingAbilityPacket> {
    public static final PacketType<C2SHoldingAbilityPacket> TYPE = PacketType.builder(false, true, C2SHoldingAbilityPacket::new).build();

    private boolean keyPressed;

    @Environment(EnvType.CLIENT)
    public C2SHoldingAbilityPacket(boolean keyPressed) {
        this.keyPressed = keyPressed;
    }

    public C2SHoldingAbilityPacket() {
    }

    @SneakyThrows
    @Override
    public void write(TrackingOutputStream outputStream) {
        outputStream.writeBoolean(keyPressed);
    }

    @SneakyThrows
    @Override
    public void read(DataInputStream stream) {
        keyPressed = stream.readBoolean();
    }

    @SneakyThrows
    @Override
    public void apply(NetworkHandler networkHandler) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            ((ServerPlayNetworkHandler) networkHandler).player.glasstech$setHoldingAbilityKey(keyPressed);
        }
    }

    @Override
    public @NotNull PacketType<C2SHoldingAbilityPacket> getType() {
        return TYPE;
    }
}
