package net.glasslauncher.mods.glasstech.entity;

import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class GTTntEntity extends TntEntity {
    public GTTntEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
        fuse = 40;
    }

    @Override
    public void explode() {
        Explosion explosion = new GTExplosion(world, this, x, y, z, 4);
        explosion.explode();
        explosion.playExplosionSound(true);
    }
}
