package net.glasslauncher.mods.glasstech;

import net.minecraft.entity.player.PlayerEntity;
import net.modificationstation.stationapi.api.util.math.Direction;

public class PlayerEntityUtil {

    public static Direction placementFacing(PlayerEntity player) {

        int direction = ((int) Math.floor((player.yaw * 4.0f / 360.0d) + 0.5)) & 3;
        return switch (direction) {
            case 1 -> Direction.EAST;
            case 2 -> Direction.SOUTH;
            case 3 -> Direction.WEST;
            default -> Direction.NORTH;
        };
    }
}
