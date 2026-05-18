package net.glasslauncher.mods.glasstech.blocks.renderer;

import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.api.util.math.Vec2f;

import static net.modificationstation.stationapi.api.state.property.Properties.FACING;
import static net.modificationstation.stationapi.api.state.property.Properties.HORIZONTAL_FACING;

public class WaterWheelBlockEntity extends BlockEntity {
    public float rot;
    public int ticks = 0;
    public boolean hasWater = false;
    public float brightness = 0;
    public Vec3d waterFlow;
    public Vec2f wheelDir;

    @Override
    public void tick() {
        hasWater = false;
        waterFlow = null;
        if (wheelDir == null) {
            Direction dir = world.getBlockState(x, y, z).get(HORIZONTAL_FACING);
            boolean northSouth = dir == Direction.NORTH || dir == Direction.SOUTH;
            wheelDir = new Vec2f(dir.getOffsetX() != 0 ? (northSouth ? -1 : 1) : 0, dir.getOffsetZ() != 0 ? (northSouth ? -1 : 1) : 0);
        }

        brightness = world.method_1782(x, y, z);

        Block potentialWater = Block.BLOCKS[world.getBlockId(x, y - 1, z)];
        if (potentialWater instanceof LiquidBlock liquidBlock) {
            waterFlow = liquidBlock.getFlow(world, x, y - 1, z);
            waterFlow = Vec3d.createCached((float) waterFlow.z * wheelDir.x, 0, (float) waterFlow.x * wheelDir.y);
            if (waterFlow.x != 0 || waterFlow.z != 0) {
                hasWater = true;
            }
        }

        ticks++;
    }
}
