package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.entity.GTExplosion;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
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
        Explosion explosion = new GTExplosion(world, null, x, y, z, 1);
        explosion.explode();
        explosion.playExplosionSound(true);
    }

    @Override
    public void onDestroyedByExplosion(World world, int x, int y, int z) {
        if (!world.isRemote) {
            explode(world, x, y, z);
        }
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
        if (world.getPowerLevel(x, y, z) != 0) {
            explode(world, x, y, z);
        }
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    }
}
