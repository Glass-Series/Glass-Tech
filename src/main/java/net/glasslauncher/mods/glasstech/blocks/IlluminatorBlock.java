package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.GTMaterials;
import net.glasslauncher.mods.glasstech.GTProperties;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

public class IlluminatorBlock extends MachineBlockTemplate {
    public IlluminatorBlock(Identifier identifier) {
        super(identifier, GTMaterials.FRAGILE_MACHINE);
        setSoundGroup(Block.METAL_SOUND_GROUP);
        setLuminance(state -> state.get(Properties.LIT) ? 15 : 0);
        setHardness(0.3f);
        setDefaultState(getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
                .with(Properties.LIT, false)
                .with(GTProperties.PLACEMENT, 0)
        );
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState state = super.getPlacementState(context);
        state = state.with(GTProperties.PLACEMENT, switch (context.getSide()) {
            case DOWN -> 2;
            case UP -> 0;
            default -> 1;
        });
        if (state.get(GTProperties.PLACEMENT) == 1) {
            // Make it face using the placed on face instead
            state = state.with(Properties.HORIZONTAL_FACING, context.getSide());
        }
        return state;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(GTProperties.PLACEMENT);
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        return null;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public HitResult raycast(World world, int x, int y, int z, Vec3d startPos, Vec3d endPos) {
        Box box = getBoundingBox(world, x, y, z);

        HitResult hitResult = box.raycast(startPos, endPos);

        if (hitResult == null) {
            return null;
        }

        hitResult.blockX = x;
        hitResult.blockY = y;
        hitResult.blockZ = z;

        return hitResult;
    }

    // I hate this.
    @Override
    public Box getBoundingBox(World world, int x, int y, int z) {
        BlockState state = world.getBlockState(x, y, z);
        int placement = state.get(GTProperties.PLACEMENT);
        Direction facing = state.get(Properties.HORIZONTAL_FACING);

        float pixel = 1f / 16;
        float depth = 6 * pixel;
        float width = 14 * pixel;
        float height = 2 * pixel;

        float minY = 0;
        float maxY = 0;
        //noinspection EnhancedSwitchMigration Terrible idea, fuck off
        switch (placement) {
            case 0: // Floor
                minY = 0f;
                maxY = height;
                break;
            case 1: // Middle
                minY = pixel * 5;
                maxY = minY + depth;
                break;
            case 2: // Ceiling
                minY = 1 - height;
                maxY = 1;
                break;
        }

        float minX, maxX, minZ, maxZ;

        if (facing.getAxis() == Direction.Axis.Z) {
            minX = pixel * 1;
            maxX = minX + width;
            minZ = pixel * 5;
            maxZ = minZ + depth;
        }
        else {
            minX = pixel * 5;
            maxX = minX + depth;
            minZ = pixel * 1;
            maxZ = minZ + width;
        }

        if (placement == 1) {
            float xModifier = ((facing.getOffsetX() == 0 ? 1 : 5) * pixel) * facing.getOffsetX();
            float zModifier = ((facing.getOffsetZ() == 0 ? 1 : 5) * pixel) * facing.getOffsetZ();
            minX -= xModifier;
            minZ -= zModifier;
            maxX -= xModifier;
            maxZ -= zModifier;
            if (Direction.NORTH == facing) {
                minZ -= facing.getOffsetZ() * (depth - height);
            }
            if (Direction.WEST == facing) {
                minX -= facing.getOffsetX() * (depth - height);
            }
            if (Direction.SOUTH == facing) {
                maxZ -= facing.getOffsetZ() * (depth - height);
            }
            if (Direction.EAST == facing) {
                maxX -= facing.getOffsetX() * (depth - height);
            }
        }

        return Box.create(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
    }

    @Override
    public BlockEntity createBlockEntity() {
        return new IlluminatorBlockEntity();
    }

    public static class IlluminatorBlockEntity extends MachineBlockEntityTemplate {
        int powerTime = 0;

        public IlluminatorBlockEntity() {
            super(VoltageTier.LV, 1, 32);
            inventory = new ItemStack[0];
        }

        @Override
        public void processTick() {
            powerTime--;
            if (powerTime <= 0) {
                int powerSipped = removeEnergy(1);
                powerTime = powerSipped * 1200; // 1 minute
            }
            if (powerTime > 0) {
                lit = true;
            }
        }

        @Override
        public boolean canProcess() {
            return powerTime < 1;
        }

        @Override
        public String getName() {
            return "Illuminator";
        }

        @Override
        public void writeNbt(NbtCompound nbt) {
            super.writeNbt(nbt);
            nbt.putInt("power_time", powerTime);
        }

        @Override
        public void readNbt(NbtCompound nbt) {
            super.readNbt(nbt);
            powerTime = nbt.getInt("power_time");
        }
    }
}
