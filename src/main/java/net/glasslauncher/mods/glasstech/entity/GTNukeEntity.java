package net.glasslauncher.mods.glasstech.entity;

import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.TriState;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

@HasTrackingParameters(trackingDistance = 40, updatePeriod = 40, sendVelocity = TriState.TRUE)
public class GTNukeEntity extends GTTntEntity {

    public GTNukeEntity(World world) {
        super(world);
        fuse = 120;
        nukesplosion = true;
    }

    // Used in multiplayer.
    public GTNukeEntity(World world, double x, double y, double z) {
        this(world);
        setPosition(x, y, z);
    }

    public GTNukeEntity(World world, double x, double y, double z, int fuse, float power, boolean nukesplosion) {
        this(world);
        setPosition(x, y, z);
        this.fuse = fuse;
        this.power = power;
        this.nukesplosion = nukesplosion;
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return NAMESPACE.id("nuke");
    }
}
