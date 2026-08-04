package net.glasslauncher.mods.glasstech.packet;

import lombok.SneakyThrows;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.NetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;

public class C2SJetpackModePacket extends TemplateManagedPacket<C2SJetpackModePacket> {
    public static final PacketType<C2SJetpackModePacket> TYPE = PacketType.builder(false, true, C2SJetpackModePacket::new).build();

    public C2SJetpackModePacket() {
    }

    @SneakyThrows
    @Override
    public void write(TrackingOutputStream outputStream) {}

    @SneakyThrows
    @Override
    public void read(DataInputStream stream) {}

    @SneakyThrows
    @Override
    public void apply(NetworkHandler networkHandler) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            ((ServerPlayNetworkHandler) networkHandler).player.glasstech$setHovering(((ServerPlayNetworkHandler) networkHandler).player.glasstech$isHovering());
        }
    }

    @Override
    public @NotNull PacketType<C2SJetpackModePacket> getType() {
        return TYPE;
    }
}
