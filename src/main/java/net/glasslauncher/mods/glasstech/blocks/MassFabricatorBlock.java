package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.util.Identifier;

public class MassFabricatorBlock extends GTTemplateBlock {
    public MassFabricatorBlock(Identifier identifier) {
        super(identifier, Material.METAL, Block.METAL_SOUND_GROUP);
    }
}
