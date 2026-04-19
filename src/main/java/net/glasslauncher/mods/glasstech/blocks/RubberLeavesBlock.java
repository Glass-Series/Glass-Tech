package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.util.Identifier;

public class RubberLeavesBlock extends GTTemplateBlock {
    public RubberLeavesBlock(Identifier identifier) {
        super(identifier, Material.LEAVES, Block.WOOD_SOUND_GROUP);
    }
}
