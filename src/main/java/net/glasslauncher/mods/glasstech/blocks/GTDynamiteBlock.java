package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.util.Identifier;

public class GTDynamiteBlock extends GTTemplateBlock {
    public GTDynamiteBlock(Identifier identifier) {
        super(identifier, Material.TNT, Block.DEFAULT_SOUND_GROUP);
    }
}
