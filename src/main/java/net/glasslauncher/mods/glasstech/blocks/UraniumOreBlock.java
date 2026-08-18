package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class UraniumOreBlock extends GTTemplateBlock {
    public UraniumOreBlock(Identifier identifier, Material material, BlockSoundGroup soundGroup) {
        super(identifier, material, soundGroup);
        setHardness(3);
        setResistance(5);
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return GlassTechItems.uranium.id;
    }
}
