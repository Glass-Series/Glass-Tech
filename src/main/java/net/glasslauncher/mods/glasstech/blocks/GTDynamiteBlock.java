package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.entity.GTExplosion;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.util.Identifier;

public class GTDynamiteBlock extends GTTemplateBlock {
    public GTDynamiteBlock(Identifier identifier) {
        super(identifier, Material.TNT, Block.DEFAULT_SOUND_GROUP);
    }

    public static void explode(World world, int x, int y, int z) {
        Explosion explosion = new GTExplosion(world, null, x, y, z, 1);
        explosion.explode();
        explosion.playExplosionSound(true);
    }
}
