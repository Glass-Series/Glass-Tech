package net.glasslauncher.mods.glasstech;

import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.minecraft.world.gen.feature.OreFeature;

public class Features {
    public static final OreFeature TIN_ORE = new OreFeature(GlassTechBlocks.tinOreBlock.id, 10);
    public static final OreFeature COPPER_ORE = new OreFeature(GlassTechBlocks.copperOreBlock.id, 10);
    public static final OreFeature URANIUM_ORE = new OreFeature(GlassTechBlocks.uraniumOreBlock.id, 8);
}
