package net.glasslauncher.mods.glasstech.worldgen;

import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.minecraft.world.gen.feature.OreFeature;

public class Features {
    public static final OreFeature TIN_ORE = new OreFeature(GlassTechBlocks.tinOreBlock.id, 10);
    public static final OreFeature COPPER_ORE = new OreFeature(GlassTechBlocks.copperOreBlock.id, 10);
    public static final OreFeature URANIUM_ORE = new OreFeature(GlassTechBlocks.uraniumOreBlock.id, 8);

    public static final RubberGen RUBBER_TREE = new RubberGen(
            GlassTechBlocks.rubberLogBlock,
            GlassTechBlocks.rubberLeavesBlock,
            r -> 5 + r.nextInt(4),
            (r, layer) -> (layer > 1 ? 1 : 2) + layer / 2,
            r -> 3 + r.nextInt(2),
            r -> 0,
            r -> 1 + r.nextInt(2),
            (r, treeHeight) -> treeHeight - 2 + r.nextInt(2)
    );
}
