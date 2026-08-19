package net.glasslauncher.mods.glasstech.packet;

import lombok.SneakyThrows;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts.WindSailsBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import net.modificationstation.stationapi.api.util.SideUtil;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;

public class WindSailsDataPacket extends TemplateManagedPacket<WindSailsDataPacket> implements GTPlayerGetter {
    public static final PacketType<WindSailsDataPacket> TYPE = PacketType.builder(true, true, WindSailsDataPacket::new).build();

    int x, y, z;
    float r, g, b;

    public WindSailsDataPacket() {
    }

    public WindSailsDataPacket(int x, int y, int z, float r, float g, float b) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public WindSailsDataPacket(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @SneakyThrows
    @Override
    public void write(TrackingOutputStream outputStream) {
        outputStream.writeInt(x);
        outputStream.writeInt(y);
        outputStream.writeInt(z);
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            outputStream.writeFloat(r);
            outputStream.writeFloat(g);
            outputStream.writeFloat(b);
        }
    }

    @SneakyThrows
    @Override
    public void read(DataInputStream stream) {
        x = stream.readInt();
        y = stream.readInt();
        z = stream.readInt();
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            r = stream.readFloat();
            g = stream.readFloat();
            b = stream.readFloat();
        }
    }

    @Override
    public void apply(NetworkHandler networkHandler) {
        //noinspection Convert2MethodRef method ref still tries to load the method, even if it's never called
        PlayerEntity player = SideUtil.get(() -> getPlayer(), () -> getPlayer(networkHandler));
        BlockEntity entity = player.world.getBlockEntity(this.x, this.y, this.z);
        if (!(entity instanceof WindSailsBlockEntity windSailsBlockEntity)) {
            return;
        }

        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            windSailsBlockEntity.red = r;
            windSailsBlockEntity.green = g;
            windSailsBlockEntity.blue = b;
            return;
        }

        PacketHelper.sendTo(player, new WindSailsDataPacket(this.x, this.y, this.z, windSailsBlockEntity.red, windSailsBlockEntity.green, windSailsBlockEntity.blue));
    }

    @Override
    public @NotNull PacketType<WindSailsDataPacket> getType() {
        return TYPE;
    }
}
