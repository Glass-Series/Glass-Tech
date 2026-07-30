package net.glasslauncher.mods.glasstech.worldgen;

import net.glasslauncher.mods.glasstech.blocks.LeavesBlockTemplate;
import net.glasslauncher.mods.glasstech.util.RandomIntProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.util.math.Vec3i;

import java.util.Objects;
import java.util.Random;
import java.util.function.BiFunction;

public class RubberGen extends Feature {
    protected final Block trunk;
    protected final Block leaves;
    protected final RandomIntProvider treeHeightGetter; // random.nextInt(3) + 4
    protected final BiFunction<Random, Integer, Integer> leafRadiusShrinkGetter; // 1 - leafLayer / 2
    protected final RandomIntProvider leafLayerCountGetter; // 3
    protected final RandomIntProvider branchCountGetter;
    protected final RandomIntProvider branchLengthGetter;
    protected final BiFunction<Random, Integer, Integer> branchHeightGetter; // tree height given

    public RubberGen(Block trunk, Block leaves, RandomIntProvider treeHeightGetter, BiFunction<Random, Integer, Integer> leafRadiusShrinkGetter, RandomIntProvider leafLayerCountGetter, RandomIntProvider branchCountGetter, RandomIntProvider branchLengthGetter, BiFunction<Random, Integer, Integer> branchHeightGetter) {
        this.trunk = trunk;
        this.leaves = leaves;
        this.treeHeightGetter = treeHeightGetter;
        this.leafRadiusShrinkGetter = leafRadiusShrinkGetter;
        this.leafLayerCountGetter = leafLayerCountGetter;
        this.branchCountGetter = branchCountGetter;
        this.branchLengthGetter = branchLengthGetter;
        this.branchHeightGetter = branchHeightGetter;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int treeHeight = treeHeightGetter.provide(random);
        if (y + treeHeight + 1 > world.getTopY()) {
            return false;
        }

        int supportingBlockId = world.getBlockId(x, y - 1, z);
        if ((supportingBlockId != Block.DIRT.id && supportingBlockId != Block.GRASS_BLOCK.id) || y >= world.getTopY() - treeHeight - 1) {
            return false;
        }

        for(int checkingY = y; checkingY <= y + 1 + treeHeight; ++checkingY) {
            if (checkingY < world.getTopY()) {
                int foundBlockId = world.getBlockId(x, checkingY, z);
                if (foundBlockId != 0 && Objects.requireNonNull(BlockRegistry.INSTANCE.get(foundBlockId)).material == Material.LEAVES) {
                    return false;
                }
            } else {
                return false;
            }
        }

        world.setBlock(x, y - 1, z, Block.DIRT.id);

        for(int relativeY = 0; relativeY < treeHeight; ++relativeY) {
            if (isReplaceableByLogs(world.getBlockState(x, y + relativeY, z))) {
                world.setBlockWithoutNotifyingNeighbors(x, y + relativeY, z, trunk.id);
            }
        }

        generateLeaves(world, random, x, y + treeHeight, z);

        for (int branchIndex = 0; branchIndex < branchCountGetter.provide(random); branchIndex++) {
            int branchY = y + branchHeightGetter.apply(random, treeHeight);
            int currentX = x;
            int currentZ = z;
            int branchLength = branchLengthGetter.provide(random);
            for (int branchStep = 0; branchStep < branchLength; branchStep++) {
                boolean xOrZ = random.nextBoolean();
                boolean negativeOrPositive = random.nextBoolean();
                boolean isDiagonal = random.nextBoolean();

                Vec3i vector = new Vec3i(xOrZ ? negativeOrPositive ? -1 : 1 : 0, 0, xOrZ ? 0 : negativeOrPositive ? -1 : 1);
                if (isDiagonal) {
                    boolean diagonalNegativeOrPositive = random.nextBoolean();
                    vector = vector.add(xOrZ ? 0 : diagonalNegativeOrPositive ? -1 : 1, 0, xOrZ ? diagonalNegativeOrPositive ? -1 : 1 : 0);
                }

                currentX += vector.getX();
                currentZ += vector.getZ();

                boolean couldPlaceBranch = isReplaceableByLogs(world.getBlockState(currentX, branchY, currentZ));
                if (couldPlaceBranch) {
                    world.setBlockWithoutNotifyingNeighbors(currentX, branchY, currentZ, trunk.id);
                }
                if (branchStep + 1 == branchLength || !couldPlaceBranch) {
                    generateLeaves(world, random, currentX, branchY + 1, currentZ);
                }
                if (!couldPlaceBranch) {
                    break;
                }
            }
        }

        return true;
    }

    protected void generateLeaves(World world, Random random, int x, int y, int z) {
        for(int placingY = y - leafLayerCountGetter.provide(random); placingY <= y; ++placingY) {
            int leafLayer = placingY - y;
            int leafRadiusShrink = leafRadiusShrinkGetter.apply(random, leafLayer);

            for(int placingX = x - leafRadiusShrink; placingX <= x + leafRadiusShrink; ++placingX) {
                int mirroredX = placingX - x;

                for(int placingZ = z - leafRadiusShrink; placingZ <= z + leafRadiusShrink; ++placingZ) {
                    int mirroredZ = placingZ - z;
                    if ((Math.abs(mirroredX) != leafRadiusShrink || Math.abs(mirroredZ) != leafRadiusShrink || random.nextInt(2) != 0 && leafLayer != 0) && !Block.BLOCKS_OPAQUE[world.getBlockId(placingX, placingY, placingZ)]) {
                        world.setBlockWithoutNotifyingNeighbors(placingX, placingY, placingZ, leaves.id);
                    }
                }
            }
        }

        updateGeneratedLeaves(world, x, y, z);
    }

    public static boolean isReplaceableByLogs(BlockState state) {
        return state.isAir() || !state.isOpaque() || state.getMaterial() == Material.LEAVES;
    }

    public static void updateGeneratedLeaves(World world, int x, int y, int z) {
        Block block = world.getBlockState(x, y, z).getBlock();
        if (block instanceof LeavesBlockTemplate template) {
            template.updateDecay(world, x, y, z, false);
        }
    }
}
