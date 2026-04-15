package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.glasslauncher.mods.glasstech.blocks.GTTemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class GTExplosiveBlock extends GTTemplateBlock {
    public GTExplosiveBlock(Identifier identifier, Material material) {
        super(identifier, material, Block.DEFAULT_SOUND_GROUP);
    }
}
