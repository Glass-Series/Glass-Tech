package net.glasslauncher.mods.glasstech.item;

import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.util.Identifier;

public class OreScannerRegistry {
    public static final Reference2IntMap<Block> INSTANCE = new Reference2IntOpenHashMap<>();

    public static void initDefaults() {
        TagKey<Block> oreTag = TagKey.of(BlockRegistry.KEY, Identifier.of("c:ores"));
        for (Block block : Block.BLOCKS) {
            if (BlockRegistry.INSTANCE.getEntry(block).isIn(oreTag)) {
                INSTANCE.put(block, 1); // (basic) awareness of other ores
            }
        }

        add(GlassTechBlocks.tinOreBlock, 2);
        add(GlassTechBlocks.copperOreBlock, 2);
        add(Block.GOLD_ORE, 3);
        add(Block.REDSTONE_ORE, 3);
        add(Block.IRON_ORE, 4);
        add(GlassTechBlocks.uraniumOreBlock, 4);
        add(Block.DIAMOND_ORE, 5);
    }

    public static void add(Block block, int value) {
        INSTANCE.put(block, value);
    }
}
