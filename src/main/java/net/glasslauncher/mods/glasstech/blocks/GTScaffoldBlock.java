package net.glasslauncher.mods.glasstech.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.glasstech.util.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.lwjgl.input.Keyboard;

import java.util.Random;

import static net.glasslauncher.mods.glasstech.blocks.GTProperties.SCAFFOLD_DISTANCE;
import static net.modificationstation.stationapi.api.state.property.Properties.BOTTOM;

public class GTScaffoldBlock extends GTTemplateBlock {

    public final int maxDistance;

    public GTScaffoldBlock(Identifier identifier, Material material, BlockSoundGroup soundGroup, int maxDistance) {
        super(identifier, material, soundGroup);
        this.maxDistance = maxDistance;
        setDefaultState(getDefaultState().with(BOTTOM, false).with(SCAFFOLD_DISTANCE, maxDistance));
        hardness = 0.1f;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(SCAFFOLD_DISTANCE, BOTTOM);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockPos pos = context.getBlockPos();
        int dist = calculateDistance(context.getWorld(), pos.x, pos.y, pos.z);
        return getDefaultState()
                .with(SCAFFOLD_DISTANCE, dist)
                .with(BOTTOM, shouldBeBottom(context.getWorld(), pos.x, pos.y, pos.z, dist))
                ;
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        checkAndBreakIfUnsupported(world, x, y, z);
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, int direction) {
        checkAndBreakIfUnsupported(world, x, y, z);
    }

    public void checkAndBreakIfUnsupported(World world, int x, int y, int z) {

        BlockState state = world.getBlockState(x, y, z);

        int dist = calculateDistance(world, x, y, z);
        BlockState newState = state.with(SCAFFOLD_DISTANCE, dist).with(BOTTOM, shouldBeBottom(world, x, y, z, dist));
        if (dist >= maxDistance) {
            world.scheduleBlockUpdate(x, y, z, id, 1);
        }
        else if (state != newState) {
            world.setBlockState(x, y, z, newState);
        }
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        breakScaffold(world, x, y, z);
    }

    public int calculateDistance(World world, int x, int y, int z) {
        BlockState blockState = world.getBlockState(x, y - 1, z);
        int currentDistance = maxDistance;
        if (blockState.getBlock() instanceof GTScaffoldBlock) {
            currentDistance = blockState.get(SCAFFOLD_DISTANCE);
        } else if (!blockState.isAir() && blockState.getBlock().isSolidFace(world, x, y, z, Direction.UP.getId()) && blockState.getBlock().material.isSolid()) {
            return 0;
        }

        for (Direction direction : Direction.Type.HORIZONTAL) {
            BlockState blockState2 = world.getBlockState(x + direction.getOffsetX(), y, z + direction.getOffsetZ());
            if (blockState2.getBlock() instanceof GTScaffoldBlock) {
                currentDistance = Math.min(currentDistance, blockState2.get(SCAFFOLD_DISTANCE) + 1);
                if (currentDistance == 1) {
                    break;
                }
            }
        }

        return currentDistance;
    }

    private boolean shouldBeBottom(World world, int x, int y, int z, int distance) {
        return distance == 0 && !(world.getBlockState(x, y - 1, z).getBlock() instanceof GTScaffoldBlock);
    }

    public void breakScaffold(World world, int x, int y, int z) {
        WorldHelper.breakBlockWithParticles(world, x, y, z, id);
        dropStacks(world, x, y, z, 0);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        return super.getCollisionShape(world, x, y, z).expand(-0.01, 0, -0.01);
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public void onEntityCollision(World world, int x, int y, int z, Entity entity) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT && entity instanceof PlayerEntity player) {
            player.fallDistance = 0;
            if (player.velocityY < -0.15) {
                player.velocityY = -0.15;
            }
            if (Keyboard.isKeyDown(Minecraft.INSTANCE.options.forwardKey.code) || Keyboard.isKeyDown(Minecraft.INSTANCE.options.leftKey.code) || Keyboard.isKeyDown(Minecraft.INSTANCE.options.rightKey.code) || Keyboard.isKeyDown(Minecraft.INSTANCE.options.backKey.code)) {
                player.velocityY = 0.2;
            }
            else if (player.isSneaking()) {
                player.velocityY = 0;
            }
        }
    }
}
