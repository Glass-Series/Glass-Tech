package net.glasslauncher.mods.glasstech.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class NbtHelper {
    public static final String BLOCK_POS_KEY = "block_pos";

    public static BlockPos readBlockPos(NbtCompound tag) {
        int[] pos = tag.getIntArray(BLOCK_POS_KEY);
        if (pos.length != 3)
            return BlockPos.ORIGIN;
        return new BlockPos(pos[0], pos[1], pos[2]);
    }

    public static void writeBlockPos(NbtCompound tag, BlockPos pos) {
        tag.put(BLOCK_POS_KEY, new int[] {pos.getX(), pos.getY(), pos.getZ()});
    }
}
