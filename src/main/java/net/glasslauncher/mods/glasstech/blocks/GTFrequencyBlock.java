package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public interface GTFrequencyBlock {
    boolean setFrequency(World world, int x, int y, int z, int freqX, int freqY, int freqZ);
    Vec3i getFrequency(World world, int x, int y, int z);
}
