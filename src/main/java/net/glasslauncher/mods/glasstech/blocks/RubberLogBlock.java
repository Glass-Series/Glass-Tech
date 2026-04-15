package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.glasslauncher.mods.glasstech.blocks.GTTemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class RubberLogBlock extends GTTemplateBlock {
    public RubberLogBlock(Identifier identifier) {
        super(identifier, Material.WOOD, Block.WOOD_SOUND_GROUP);
    }
}
