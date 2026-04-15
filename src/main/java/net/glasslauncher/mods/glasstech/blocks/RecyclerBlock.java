package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.glasslauncher.mods.glasstech.blocks.GTTemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class RecyclerBlock extends GTTemplateBlock {
    public RecyclerBlock(Identifier identifier) {
        super(identifier, Material.METAL, Block.METAL_SOUND_GROUP);
    }
}
