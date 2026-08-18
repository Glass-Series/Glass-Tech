package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.blocks.GTProperties;
import net.glasslauncher.mods.glasstech.util.WorldHelper;
import net.glasslauncher.mods.glasstech.blocks.GTScaffoldBlock;
import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.glasslauncher.mods.glasstech.util.BlockWalker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.List;

public class CFSprayer extends TemplateItem {
    public static BlockWalker.WalkValidator SCAFFOLD_FILTER = ((world, x, y, z) -> world.getBlockState(x, y, z).getBlock() instanceof GTScaffoldBlock);

    public CFSprayer(Identifier identifier) {
        super(identifier);
        setMaxDamage(32);
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        int charges = stack.getMaxDamage() - stack.getDamage();
        if (charges < 1) {
            return false;
        }

        Direction direction = Direction.byId(side);
        BlockState state = world.getBlockState(x, y, z);

        int foamPlaced;

        if (state.getBlock() instanceof GTScaffoldBlock) {
            foamPlaced = sprayFoamInScaffold(world, x, y, z, direction, charges);
        }
        else {
            foamPlaced = sprayFoamPlane(world, x, y, z, direction, charges, user.isSneaking());
        }

        if (foamPlaced > 0) {
            charges -= foamPlaced;
            stack.setDamage(getMaxDamage() - charges);
        }
        return true;
    }

    public int sprayFoamInScaffold(World world, int x, int y, int z, Direction direction, int charges) {
        List<BlockPos> results = BlockWalker.walk(world, new BlockPos(x, y, z), BlockWalker.ADJACENT_SEARCH_OFFSETS, SCAFFOLD_FILTER, Math.min(32, charges));
        for (BlockPos pos : results) {
            BlockState state = world.getBlockState(pos);
            WorldHelper.breakBlockWithParticles(world, pos.x, pos.y, pos.z, state.getBlock().id);
            state.getBlock().dropStacks(world, x + direction.getOffsetX(), y + direction.getOffsetY(), z + direction.getOffsetZ(), 0);
            world.setBlock(pos.x, pos.y, pos.z, GlassTechBlocks.constructionFoamBlock.id);
        }
        return results.size();
    }

    public int sprayFoamPlane(World world, int x, int y, int z, Direction direction, int charges, boolean placeInWall) {
        Vec3i planeVector = new Vec3i(direction.getOffsetX() == 0 ? 3 : 1, direction.getOffsetY() == 0 ? 3 : 1, direction.getOffsetZ() == 0 ? 3 : 1);
        int foamPlaced = 0;

        for (int placeX = 0; placeX < planeVector.x; placeX++) {
            for (int placeY = 0; placeY < planeVector.y; placeY++) {
                for (int placeZ = 0; placeZ < planeVector.z; placeZ++) {
                    int placingX = x + placeX - (direction.getOffsetX() == 0 ? 1 : 0);
                    int placingY = y + placeY - (direction.getOffsetY() == 0 ? 1 : 0);
                    int placingZ = z + placeZ - (direction.getOffsetZ() == 0 ? 1 : 0);
                    if (!placeInWall) {
                        placingX += direction.getOffsetX();
                        placingY += direction.getOffsetY();
                        placingZ += direction.getOffsetZ();
                    }
                    BlockState state = world.getBlockState(placingX, placingY, placingZ);
                    if (state.isAir()) {
                        world.setBlock(placingX, placingY, placingZ, GlassTechBlocks.constructionFoamBlock.id);
                        foamPlaced++;
                        if (foamPlaced >= charges) {
                            break;
                        }
                    }
                    else if (state.contains(GTProperties.FOAM) && state.get(GTProperties.FOAM) == 0) {
                        world.setBlockState(placingX, placingY, placingZ, state.with(GTProperties.FOAM, 1));
                        foamPlaced++;
                        if (foamPlaced >= charges) {
                            break;
                        }
                    }
                }
            }
        }

        return foamPlaced;
    }
}
