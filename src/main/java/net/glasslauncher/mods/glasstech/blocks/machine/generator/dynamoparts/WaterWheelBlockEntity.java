package net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts;

import net.glasslauncher.mods.glasstech.blocks.machine.generator.DynamoComponent;
import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.Vec3d;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.api.util.math.Vec2f;

import static net.modificationstation.stationapi.api.state.property.Properties.HORIZONTAL_FACING;

public class WaterWheelBlockEntity extends BlockEntity implements DynamoComponent {
    public float lastRot;
    public float rot;
    public int ticks = 0;
    public boolean hasWater = false;
    public float brightness = 0;
    public Vec3d waterFlow;
    public Vec2f wheelDir;

    @Override
    public void tick() {
        lastRot = rot;
        hasWater = false;
        if (wheelDir == null) {
            Direction dir = world.getBlockState(x, y, z).get(HORIZONTAL_FACING);
            boolean northSouth = dir == Direction.NORTH || dir == Direction.SOUTH;
            wheelDir = new Vec2f(dir.getOffsetX() != 0 ? (northSouth ? -1 : 1) : 0, dir.getOffsetZ() != 0 ? (northSouth ? -1 : 1) : 0);
        }

        brightness = world.method_1782(x, y, z);

        Block potentialWater = Block.BLOCKS[world.getBlockId(x, y - 1, z)];
        if (potentialWater instanceof LiquidBlock liquidBlock) {
            waterFlow = liquidBlock.getFlow(world, x, y - 1, z);
            waterFlow = Vec3d.create((float) waterFlow.z * wheelDir.x, 0, (float) waterFlow.x * wheelDir.y);
            if (waterFlow.x != 0 || waterFlow.z != 0) {
                hasWater = true;
                rot = (rot + 1) % 360;
            }
        }

        ticks++;
    }

    @Override
    public boolean isGenerating() {
        return hasWater;
    }

    @Override
    public int getOutput() {
        int wheels = getConnectedWheels(1);
        int penalty = 0;
        for (int i = 0; i < wheels; i++) {
            penalty += i;
        }
        return (wheels * 4) - penalty; // Last wheel only provides 1 eu/t, with a total cap of 10eu/t
    }

    public int getConnectedWheels(int currentDepth) {
        if (currentDepth >= 4) {
            return currentDepth;
        }
        Direction scanDir = world.getBlockState(x, y, z).get(HORIZONTAL_FACING);
        if (world.getBlockEntity(x + scanDir.getOffsetX(), y, z + scanDir.getOffsetZ()) instanceof WaterWheelBlockEntity waterWheelBlockEntity && waterWheelBlockEntity.isConnected(scanDir)) {
            return waterWheelBlockEntity.getConnectedWheels(currentDepth + 1);
        }
        return currentDepth;
    }

    @Override
    public boolean isConnected(Direction dynamoDirection) {
        return world.getBlockState(x, y, z).get(HORIZONTAL_FACING) == dynamoDirection && isGenerating();
    }
}
