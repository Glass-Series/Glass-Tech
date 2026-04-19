package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.glasslauncher.mods.glasstech.blocks.GTTemplateBlock;
import net.modificationstation.stationapi.api.template.block.TemplateLogBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class RubberLogBlock extends TemplateLogBlock {
    public RubberLogBlock(Identifier identifier) {
        super(identifier);
        setSoundGroup(Block.WOOD_SOUND_GROUP);
        setTranslationKey(identifier);
    }

    public int getTexture(int side, int meta) {
        if (side == 1 || side == 0) {
            return 21;
        }
        return textureId;
    }
}
