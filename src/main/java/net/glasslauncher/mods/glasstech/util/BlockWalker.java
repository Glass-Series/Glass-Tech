package net.glasslauncher.mods.glasstech.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.ArrayList;

// Thanks dany, I have now stolen your code
public class BlockWalker {
    public static Vec3i[] ADJACENT_SEARCH_OFFSETS = new Vec3i[] {
            // Top and Bottom
            new Vec3i(0, 1, 0),
            new Vec3i(0, -1, 0),

            // Sides
            new Vec3i(1, 0, 0),
            new Vec3i(-1, 0, 0),
            new Vec3i(0, 0, 1),
            new Vec3i(0, 0, -1),
    };

    public static final Vec3i[] DIAGONAL_SEARCH_OFFSETS = new Vec3i[] {
            // Top and Bottom
            new Vec3i(0, 1, 0),
            new Vec3i(0, -1, 0),

            // Sides
            new Vec3i(1, 0, 0),
            new Vec3i(-1, 0, 0),
            new Vec3i(0, 0, 1),
            new Vec3i(0, 0, -1),

            // Diagonals
            new Vec3i(1, 0, 1),
            new Vec3i(1, 0, -1),
            new Vec3i(-1, 0, 1),
            new Vec3i(-1, 0, -1)
    };

    public static ArrayList<BlockPos> walk(World world, BlockPos start, Vec3i[] searchOffsets, WalkValidator validator) {
        return walk(world, start, searchOffsets, validator, 32);
    }

    public static ArrayList<BlockPos> walk(World world, BlockPos start, Vec3i[] searchOffsets, WalkValidator validator, int maxSearch) {
        // ArrayList for list of blocks yet to explore
        ArrayList<BlockPos> open = new ArrayList<>();
        // ArrayList for list of blocks that have been found
        ArrayList<BlockPos> closed = new ArrayList<>();

        // Add the starting position to explore
        open.add(start);

        // Go until open isn't empty
        while (!open.isEmpty()) {
            // Get the position to explore
            BlockPos pos = open.get(0);
            // Look at all of its sides
            for (Vec3i dir : searchOffsets) {
                // Get the side and see if there is a block on it. Then check if it doesnt already exist
                BlockPos side = new BlockPos(pos.x + dir.x, pos.y + dir.y, pos.z + dir.z);
                if (!closed.contains(side)) {
                    if (validator.test(world, side.x, side.y, side.z)) {
                        open.add(side);
                    }
                }
            }

            // Add the position to closed and remove it from open
            if (!closed.contains(pos)) {
                closed.add(pos);
            }
            if (closed.size() >= maxSearch) {
                return closed;
            }
            open.remove(pos);
        }

        return closed;
    }

    @FunctionalInterface
    public interface WalkValidator {
        boolean test(World world, int x, int y, int z);
    }
}
