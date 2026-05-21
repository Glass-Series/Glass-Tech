package net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts;

import net.glasslauncher.mods.glasstech.blocks.machine.generator.DynamoComponent;
import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.api.util.math.Vec2f;

import static net.modificationstation.stationapi.api.state.property.Properties.HORIZONTAL_FACING;

public class WindSailsBlockEntity extends BlockEntity implements DynamoComponent {
    public float rot;
    public int ticks = 0;
    public boolean hasAir = false;
    public float brightness = 0;
    public Direction wheelDir;
    public float[] color = {1, 1, 1};
    public boolean showScan = false;

    @Override
    public void tick() {
        Direction dir = world.getBlockState(x, y, z).get(HORIZONTAL_FACING);
        if (wheelDir == null) {
            wheelDir = dir;
        }

        brightness = world.method_1782(x, y, z);

        if (ticks % 20 == 0) {
            hasAir = true;
            for (int x = 0; x < 15; x++) {
                for (int y = 0; y < 15; y++) {
                    if (x == 7 && y == 7) {
                        continue;
                    }
                    int blockId;
                    if (dir.getAxis() == Direction.Axis.Z) {
                        blockId = world.getBlockId((this.x - 7) + x, (this.y - 7) + y, this.z);
                        if (showScan) {
                            world.addParticle("smoke", (this.x - 7) + x, (this.y - 7) + y, this.z, 0, 0, 0);
                        }
                    } else {
                        blockId = world.getBlockId(this.x, (this.y - 7), (this.z - 7) + x);
                        if (showScan) {
                            world.addParticle("smoke", this.x, (this.y - 7), (this.z - 7) + x, 0, 0, 0);
                        }
                    }
                    if (blockId != 0) {
                        hasAir = false;
                        break;
                    }
                }
                if (!hasAir) {
                    break;
                }
            }
        }
        rot = ticks % 360;

        ticks++;
    }

    @Override
    public boolean isGenerating() {
        return hasAir;
    }

    @Override
    public int getOutput() {
        return 10;
    }

    @Override
    public boolean isConnected(Direction dynamoDirection) {
        return world.getBlockState(x, y, z).get(HORIZONTAL_FACING) == dynamoDirection && isGenerating();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        color[0] = nbt.getFloat("color1");
        color[1] = nbt.getFloat("color2");
        color[2] = nbt.getFloat("color3");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putFloat("color1", color[0]);
        nbt.putFloat("color2", color[1]);
        nbt.putFloat("color3", color[2]);
    }
}
