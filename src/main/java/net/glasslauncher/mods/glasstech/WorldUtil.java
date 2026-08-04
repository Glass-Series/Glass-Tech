package net.glasslauncher.mods.glasstech;

import net.minecraft.world.World;

public class WorldUtil {

    public static void breakBlockWithParticles(World world, int x, int y, int z, int id) {
        world.worldEvent(null, 2001, x, y, z, id + (world.getBlockMeta(x, y, z) << 28));
        world.setBlock(x, y, z, 0);
    }
}
