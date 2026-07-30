package net.glasslauncher.mods.glasstech.entity;

import net.minecraft.entity.TntEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.modificationstation.stationapi.api.server.entity.EntitySpawnDataProvider;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.TriState;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

@HasTrackingParameters(trackingDistance = 40, updatePeriod = 40, sendVelocity = TriState.TRUE)
public class GTTntEntity extends TntEntity implements EntitySpawnDataProvider {
    public float power = 4;
    private boolean nukesplosion;

    public GTTntEntity(World world) {
        super(world);
        fuse = 40;
    }

    // Used in multiplayer.
    public GTTntEntity(World world, double x, double y, double z) {
        this(world);
        setPosition(x, y, z);
    }

    public GTTntEntity(World world, double x, double y, double z, int fuse, float power, boolean nukesplosion) {
        this(world);
        setPosition(x, y, z);
        this.fuse = fuse;
        this.power = power;
        this.nukesplosion = nukesplosion;
    }

    @Override
    protected void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        power = nbt.getFloat("power");
        nukesplosion = nbt.getBoolean("nukesplosion");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putFloat("power", power);
        nbt.putBoolean("nukesplosion", nukesplosion);
    }

    @Override
    public void explode() {
        Explosion explosion = nukesplosion ? new GTNukesplosion(world, this, x, y, z, power) : new GTExplosion(world, this, x, y, z, power);
        explosion.explode();
        explosion.playExplosionSound(true);
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return NAMESPACE.id("tnt");
    }
}
