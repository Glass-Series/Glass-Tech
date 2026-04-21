package net.glasslauncher.mods.glasstech.entity;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class GTNukesplosion extends GTExplosion {
    public GTNukesplosion(World world, Entity source, double x, double y, double z, float power) {
        super(world, source, x, y, z, power);
    }

    @Override
    public void breakBlock(int x, int y, int z, int id) {
        this.world.setBlock(x, y, z, 0);
        // NO REFUNDS
        // Ping me aggressively if someone solves items fucking up performance by just existing in numbers > 100
    }
}
