package net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts;

import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.blocks.GTProperties;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.DynamoComponent;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.math.Direction;

import static net.modificationstation.stationapi.api.state.property.Properties.HORIZONTAL_FACING;

public class WindSailsBlockEntity extends BlockEntity implements DynamoComponent {
    public float rot;
    public int ticks = 0;
    public float brightness = 0;
    public int wheelDir = -1;
    @ServerSyncedField
    public float red = 1;
    @ServerSyncedField
    public float green = 1;
    @ServerSyncedField
    public float blue = 1;
    public boolean showScan = false;

    @Override
    public void tick() {
        BlockState state = world.getBlockState(x, y, z);
        if (!(state.getBlock() instanceof WindSailsBlock)) {
            return;
        }
        Direction dir = state.get(HORIZONTAL_FACING);
        if (wheelDir == -1) {
            wheelDir = dir.getId();
        }

        brightness = world.method_1782(x, y, z);

        if (!world.isRemote && ticks % 20 == 0) {
            boolean hasAir = true;
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
                        blockId = world.getBlockId(this.x, (this.y - 7) + y, (this.z - 7) + x);
                        if (showScan) {
                            world.addParticle("smoke", this.x, (this.y - 7) + y, (this.z - 7) + x, 0, 0, 0);
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
            if (state.get(GTProperties.HAS_AIR) != hasAir) {
                world.setBlockState(x, y, z, state.with(GTProperties.HAS_AIR, hasAir ? Boolean.TRUE : Boolean.FALSE));
            }
        }
        rot = ticks % 360;

        ticks++;
    }

    @Override
    public boolean isGenerating() {
        if (world == null) {
            return false;
        }
        return world.getBlockState(x, y, z).get(GTProperties.HAS_AIR);
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
        red = nbt.getFloat("color1");
        green = nbt.getFloat("color2");
        blue = nbt.getFloat("color3");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putFloat("color1", red);
        nbt.putFloat("color2", green);
        nbt.putFloat("color3", blue);
    }
}
