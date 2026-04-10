package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateGlassBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class GTGlassBlock extends TemplateGlassBlock {
    public GTGlassBlock(Identifier identifier) {
        super(identifier, 0, Material.GLASS, true);
        setTranslationKey(identifier);
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 1;
    }
}
