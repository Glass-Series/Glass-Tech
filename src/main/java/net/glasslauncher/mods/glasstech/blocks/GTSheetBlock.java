package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

public class GTSheetBlock extends GTTemplateBlock {
    private final Type sheetType;

    public GTSheetBlock(Identifier identifier, Type sheetType) {
        super(identifier, Material.WOOL, Block.WOOL_SOUND_GROUP);
        this.sheetType = sheetType;
        setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public Box getCollisionShape(World world, int x, int y, int z) {
        int var5 = world.getBlockMeta(x, y, z) & 7;
        return var5 >= 3 ? Box.createCached((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (float)y + 0.5F, (double)z + this.maxZ) : null;
    }

    public boolean isOpaque() {
        return false;
    }

    public boolean isFullCube() {
        return false;
    }

    public void updateBoundingBox(BlockView blockView, int x, int y, int z) {
        int var5 = blockView.getBlockMeta(x, y, z) & 7;
        float var6 = (float)(2 * (1 + var5)) / 16.0F;
        setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, var6, 1.0F);
    }

    @Override
    public boolean isSolidFace(BlockView blockView, int x, int y, int z, int face) {
        return face == Direction.DOWN.getId();
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        if (!canPlaceAt(world, x, y, z)) {
            dropStacks(world, x, y, z, world.getBlockMeta(x, y, z));
            world.setBlock(x, y, z, 0);
        }
    }

    public boolean canPlaceAt(World world, int x, int y, int z) {
        int blockId = world.getBlockId(x, y - 1, z);
        return blockId != 0 && Block.BLOCKS[blockId].isOpaque() && world.getMaterial(x, y - 1, z).blocksMovement();
    }

    @Override
    public void onEntityCollision(World world, int x, int y, int z, Entity entity) {
        switch (sheetType) {
            case BOUNCY:
                if (entity.velocityY > 0) {
                    entity.velocityY += 0.1;
                }
                break;
            case STICKY:
                entity.velocityX *= 0.7;
                entity.velocityY *= 0.7;
                entity.velocityZ *= 0.7;
        }
    }

    public enum Type {
        BOUNCY,
        STICKY,
        NORMAL;
    }
}
