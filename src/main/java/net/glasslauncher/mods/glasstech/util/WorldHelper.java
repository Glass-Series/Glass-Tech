package net.glasslauncher.mods.glasstech.util;

import net.minecraft.world.World;

public class WorldHelper {

    public static void breakBlockWithParticles(World world, int x, int y, int z, int id) {
        world.worldEvent(null, 2001, x, y, z, id + (world.getBlockMeta(x, y, z) << 28));
        world.setBlock(x, y, z, 0);
    }
}
