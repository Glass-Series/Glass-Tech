package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class ReactorCoreBlock extends TemplateBlock {
    public ReactorCoreBlock(Identifier identifier) {
        super(identifier, Material.METAL);
    }
}
