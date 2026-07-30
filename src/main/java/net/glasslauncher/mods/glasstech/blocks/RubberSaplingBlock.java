package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.glasslauncher.mods.glasstech.worldgen.RubberGen;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateSaplingBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class RubberSaplingBlock extends TemplateSaplingBlock {
    public static final RubberGen RUBBER_TREE_GENERATOR = new RubberGen(
            GlassTechBlocks.rubberLogBlock,
            GlassTechBlocks.rubberLeavesBlock,
            r -> 5 + r.nextInt(4),
            (r, layer) -> (layer > 1 ? 1 : 2) + layer / 2,
            r -> 3 + r.nextInt(2),
            r -> 0,
            r -> 1 + r.nextInt(2),
            (r, treeHeight) -> treeHeight - 2 + r.nextInt(2)
    );

    public RubberSaplingBlock(Identifier identifier) {
        super(identifier, 0);
        setSoundGroup(DIRT_SOUND_GROUP);
    }

    @Override
    public void generate(World world, int x, int y, int z, Random random) {
        world.setBlock(x, y, z, 0);
        if (!RUBBER_TREE_GENERATOR.generate(world, random, x, y, z)) {
            world.setBlock(x, y, z, id);
        }
    }
}
