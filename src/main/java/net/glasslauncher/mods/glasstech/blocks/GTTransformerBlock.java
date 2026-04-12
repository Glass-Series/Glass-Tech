package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class GTTransformerBlock extends TemplateBlock {
    public GTTransformerBlock(Identifier identifier, Material material, VoltageTier voltageTier) {
        super(identifier, material);
    }
}
