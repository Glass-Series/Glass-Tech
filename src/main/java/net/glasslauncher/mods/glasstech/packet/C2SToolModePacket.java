package net.glasslauncher.mods.glasstech.packet;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.glasstech.item.GTItemWithModes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;

public class C2SToolModePacket extends TemplateManagedPacket<C2SToolModePacket> implements GTPlayerGetter {
    public static final PacketType<C2SToolModePacket> TYPE = PacketType.builder(false, true, C2SToolModePacket::new).build();

    @Override
    public void write(TrackingOutputStream outputStream) {
    }

    @Override
    public void read(DataInputStream stream) {
    }

    @Override
    public void apply(NetworkHandler networkHandler) {
        PlayerEntity player;
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            player = getPlayer();
        }
        else {
            player = getPlayer(networkHandler);
        }
        if (player != null && player.getHand() != null && player.getHand().getItem() instanceof GTItemWithModes modesItem) {
            modesItem.cycleMode(player, player.getHand());
        }
    }

    @Override
    public @NotNull PacketType<C2SToolModePacket> getType() {
        return TYPE;
    }
}
