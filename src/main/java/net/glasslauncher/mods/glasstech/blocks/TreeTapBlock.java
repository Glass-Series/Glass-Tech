package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.util.WorldHelper;
import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.Random;

public class TreeTapBlock extends TemplateBlock {
    public TreeTapBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setDefaultState(getDefaultState().with(GTProperties.RESIN, 0));
        setSoundGroup(WOOD_SOUND_GROUP);
        setTickRandomly(true);
        setHardness(0.8f);
        setResistance(2);
    }

    @Override
    public boolean canPlaceAt(World world, int x, int y, int z, int side) {
        Direction direction = Direction.byId(side).getOpposite();
        return super.canPlaceAt(world, x, y, z, side) && scanTree(world, x + direction.getOffsetX(), y, z + direction.getOffsetZ());
    }

    public boolean scanTree(World world, int x, int y, int z) {
        if (!(world.getBlockState(x, y, z).getBlock() instanceof RubberLogBlock)) {
            return false;
        }
        if (!(world.getBlockState(x, y - 1, z).getBlock() instanceof RubberLogBlock)) {
            return false;
        }
        if (world.getBlockState(x, y - 2, z).getBlock() instanceof RubberLogBlock) {
            return false;
        }
        int finalY = y;
        if (Direction.Type.HORIZONTAL.stream().anyMatch(d -> world.getBlockState(x + d.getOffsetX(), finalY, z + d.getOffsetZ()).getBlock() instanceof TreeTapBlock)) {
            return false;
        }
        y++;
        while (y < world.getTopY(x, z)) {
            BlockState potentialLeaves = world.getBlockState(x, y, z);
            if (potentialLeaves.getBlock() instanceof RubberLeavesBlock) {
                return !potentialLeaves.get(Properties.PERSISTENT);
            }
            if (!(world.getBlockState(x, y, z).getBlock() instanceof RubberLogBlock)) {
                return false;
            }
            y++;
        }

        return false;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(Properties.HORIZONTAL_FACING, context.getSide().getOpposite());
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(GTProperties.RESIN);
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        Direction direction = world.getBlockState(x, y, z).get(Properties.HORIZONTAL_FACING);
        if (!(world.getBlockState(x + direction.getOffsetX(), y, z + direction.getOffsetZ()).getBlock() instanceof RubberLogBlock)) {
            WorldHelper.breakBlockWithParticles(world, x, y, z, id);
            dropStacks(world, x, y, z, world.getBlockMeta(x, y, z));
            return;
        }

        int meta = world.getBlockMeta(x, y, z);
        if (meta < 3 && random.nextInt(50) == 0) { // approx 30 minutes for a tap to fill on average during testing
            meta++;
            world.setBlockState(x, y, z, world.getBlockState(x, y, z).with(GTProperties.RESIN, meta));
            world.setBlockMeta(x, y, z, meta); // Can't get blockstate when block is broken :upside_down:
        }
    }

    @Override
    public HitResult raycast(World world, int x, int y, int z, Vec3d startPos, Vec3d endPos) {
        Box box = getBoundingBoxCommon(world, x, y, z);

        HitResult hitResult = box.raycast(startPos, endPos);

        if (hitResult == null) {
            return null;
        }

        hitResult.blockX = x;
        hitResult.blockY = y;
        hitResult.blockZ = z;

        return hitResult;
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        return getBoundingBoxCommon(world, x, y, z);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        int meta = world.getBlockMeta(x, y, z);
        if (meta > 0) {
            world.setBlockMeta(x, y, z, 0);
            world.setBlockState(x, y, z, world.getBlockState(x, y, z).with(GTProperties.RESIN, 0));
            dropStack(world, x, y, z, new ItemStack(GlassTechItems.resin, meta));
            return true;
        }
        return false;
    }

    @Override
    public Box getBoundingBox(World world, int x, int y, int z) {
        return getBoundingBoxCommon(world, x, y, z);
    }

    public Box getBoundingBoxCommon(World world, int x, int y, int z) {
        if (world.getBlockState(x, y, z).getBlock() != this) {
            return Box.createCached((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
        }
        Direction d = world.getBlockState(x, y, z).get(Properties.HORIZONTAL_FACING);
        double xPos = 0.35 + (d.getOffsetX() * 0.35);
        double zPos = 0.35 + (d.getOffsetZ() * 0.35);
        double yPos = 0.4;
        return Box.createCached(
                x + xPos,
                y + yPos,
                z + zPos,
                x + xPos + 0.3,
                y + yPos + 0.4,
                z + zPos + 0.3
        );
    }

    @Override
    public void dropStacks(World world, int x, int y, int z, int meta, float luck) {
        dropStack(world, x, y, z, new ItemStack(this));
        if (meta > 0) {
            dropStack(world, x, y, z, new ItemStack(GlassTechItems.resin, meta));
        }
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        super.neighborUpdate(world, x, y, z, id);
        onTick(world, x, y, z, world.random);
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
}
