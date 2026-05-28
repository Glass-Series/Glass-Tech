package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.template.block.TemplateLogBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class RubberLogBlock extends TemplateLogBlock {
    public int endTextureId = 21;

    public RubberLogBlock(Identifier identifier) {
        super(identifier);
        setSoundGroup(Block.WOOD_SOUND_GROUP);
        setTranslationKey(identifier);
        setHardness(2.0F);
        setResistance(5.0F);
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side == 1 || side == 0) {
            return endTextureId;
        }
        return textureId;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return asItem().id;
    }
}
