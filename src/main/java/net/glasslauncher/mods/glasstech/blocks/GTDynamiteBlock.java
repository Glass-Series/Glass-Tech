package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.entity.GTExplosion;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateTorchBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class GTDynamiteBlock extends TemplateTorchBlock {
    public int linkedTexture;

    public GTDynamiteBlock(Identifier identifier) {
        super(identifier, 0);
        setSoundGroup(DIRT_SOUND_GROUP);
    }

    public static void explode(World world, int x, int y, int z) {
        GTExplosion explosion = new GTExplosion(world, null, x, y, z, 1);
        explosion.destroyItems = false;
        explosion.explode();
        explosion.playExplosionSound(true);
    }

    // Notch doesn't filter this shit in the renderer (despite it being done in the torch class) and so this whole idea falls apart
// And mixins can't fix this easily cause the actual torch rendering method has no metadata or world context
// This isn't worth the effort for a feature I know almost no one will use
//    @Override
//    public int getTexture(int side, int meta) {
//        // Torch code uses 3 bits, so we can use the 4th here
//        return (meta & (1 << 3)) != 0 ? textureId : linkedTexture;
//    }

//    @Override
//    public int getTextureId(BlockView blockView, int x, int y, int z, int side) {
//        return getTexture(side, blockView.getBlockMeta(x, y, z));
//    }


    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        super.neighborUpdate(world, x, y, z, id);
        if (world.getPowerLevel(x, y, z) != 0) {
            world.setBlock(x, y, z, 0);
            explode(world, x, y, z);
        }
    }

    @Override
    public void onDestroyedByExplosion(World world, int x, int y, int z) {
        explode(world, x, y, z);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 0;
    }

    @Override
    public void onBlockBreakStart(World world, int x, int y, int z, PlayerEntity player) {
        super.onBlockBreakStart(world, x, y, z, player);
        world.setBlockMeta(x, y, z, (world.getBlockMeta(x, y, z) | (1 << 3)));
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        super.onBreak(world, x, y, z);
        if ((world.getBlockMeta(x, y, z) & (1 << 3)) != 0) {
            dropStack(world, x, y, z, new ItemStack(this));
        }
    }
}
