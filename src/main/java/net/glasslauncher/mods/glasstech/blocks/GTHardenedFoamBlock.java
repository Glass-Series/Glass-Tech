package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.util.Identifier;

public class GTHardenedFoamBlock extends GTTemplateBlock {

    public GTHardenedFoamBlock(Identifier identifier) {
        super(identifier, Material.STONE, STONE_SOUND_GROUP);
    }
}
