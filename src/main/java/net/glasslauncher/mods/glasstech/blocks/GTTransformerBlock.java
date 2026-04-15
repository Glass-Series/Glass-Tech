package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.glasslauncher.mods.glasstech.blocks.GTTemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class GTTransformerBlock extends GTTemplateBlock {
    public GTTransformerBlock(Identifier identifier, Material material, VoltageTier voltageTier) {
        super(identifier, material, Block.METAL_SOUND_GROUP);
    }
}
