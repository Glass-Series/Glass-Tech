package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateSaplingBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

import static net.glasslauncher.mods.glasstech.worldgen.Features.RUBBER_TREE;

public class RubberSaplingBlock extends TemplateSaplingBlock {
    public RubberSaplingBlock(Identifier identifier) {
        super(identifier, 0);
        setSoundGroup(DIRT_SOUND_GROUP);
    }

    @Override
    public void generate(World world, int x, int y, int z, Random random) {
        world.setBlock(x, y, z, 0);
        if (!RUBBER_TREE.generate(world, random, x, y, z)) {
            world.setBlock(x, y, z, id);
        }
    }
}
