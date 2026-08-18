package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.block.States;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.registry.tag.BlockTags;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.Random;

import static net.glasslauncher.mods.glasstech.blocks.GTProperties.LEAVES_DISTANCE;
import static net.modificationstation.stationapi.api.state.property.Properties.PERSISTENT;


public class LeavesBlockTemplate extends TemplateBlock {
    public int maxDistance = 7;
    public int fastTextureId = 1;

    public LeavesBlockTemplate(Identifier identifier) {
        super(identifier, Material.LEAVES);
        setSoundGroup(DIRT_SOUND_GROUP);
        setTickRandomly(true);
        setDefaultState(getDefaultState().with(LEAVES_DISTANCE, 0).with(PERSISTENT, false));
        setTranslationKey(identifier);
        setHardness(0.2F);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(PERSISTENT, true);
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, LivingEntity placer) {
        updateDecay(world, x, y, z, false);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LEAVES_DISTANCE);
        builder.add(PERSISTENT);
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        super.neighborUpdate(world, x, y, z, id);
        if (!world.isRemote) {
            updateDecay(world, x, y, z, false);
        }
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        super.onTick(world, x, y, z, random);
        if (!world.isRemote) {
            updateDecay(world, x, y, z, true);
        }
    }

    public void updateDecay(World world, int x, int y, int z, boolean decay) {
//        world.addParticle("flame", x + 0.5D, y + 0.5D, z + 0.5D, 0.0D, 0.0D, 0.0D);

        BlockState state = world.getBlockState(x, y, z);

        if (state.get(PERSISTENT)) {
            return;
        }

        int distance = maxDistance + 1;

        for (Direction direction : Direction.values()) {
            distance = Math.min(distance, getDistance(world, x + direction.getOffsetX(), y + direction.getOffsetY(), z + direction.getOffsetZ()) + 1);
            if (distance == 1) {
                break;
            }
        }

        if (decay && distance > this.maxDistance) {
            decay(world, x, y, z);
            return;
        }

        int prevDistance = state.get(LEAVES_DISTANCE);
        if (distance != prevDistance) {
            world.setBlockStateWithoutNotifyingNeighbors(x, y, z, state.with(LEAVES_DISTANCE, distance));
            for (Direction side : Direction.values()) {
                if (world.getBlockState(x + side.getOffsetX(), y + side.getOffsetY(), z + side.getOffsetZ()).contains(LEAVES_DISTANCE)) {
                    updateDecay(world, x + side.getOffsetX(), y + side.getOffsetY(), z + side.getOffsetZ(), decay);
                }
            }
        }

    }

    public int getDistance(World world, int x, int y, int z) {
        BlockState state = world.getBlockState(x, y, z);
        if (state.isIn(BlockTags.LOGS)) {
            return 0;
        } else if (state.contains(LEAVES_DISTANCE)) {
            return state.get(LEAVES_DISTANCE);
        }
        return maxDistance;
    }

    public void decay(World world, int x, int y, int z) {
        int meta = world.getBlockMeta(x, y, z);
        world.setBlockState(x, y, z, States.AIR.get());
        this.dropStacks(world, x, y, z, meta);
    }

    @Override
    public boolean isOpaque() {
        return Block.LEAVES.isOpaque();
    }

    @Override
    public boolean isSideVisible(BlockView blockView, int x, int y, int z, int side) {
        int foundBlockId = blockView.getBlockId(x, y, z);
        if (Block.LEAVES.isOpaque() && foundBlockId == this.id) {
            return false;
        }

        if (side == 0 && this.minY > 0.0) {
            return true;
        } else if (side == 1 && this.maxY < 1.0) {
            return true;
        } else if (side == 2 && this.minZ > 0.0) {
            return true;
        } else if (side == 3 && this.maxZ < 1.0) {
            return true;
        } else if (side == 4 && this.minX > 0.0) {
            return true;
        } else {
            return side == 5 && this.maxX < 1.0 || !blockView.method_1783(x, y, z);
        }
    }

    @Override
    public int getTextureId(BlockView blockView, int x, int y, int z, int side) {
        return Block.LEAVES.isOpaque() ? fastTextureId : textureId;
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
    }


}
