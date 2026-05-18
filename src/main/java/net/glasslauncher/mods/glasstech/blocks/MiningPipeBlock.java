package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.util.Identifier;

public class MiningPipeBlock extends GTTemplateBlock {
    public MiningPipeBlock(Identifier identifier, Material material, BlockSoundGroup soundGroup) {
        super(identifier, material, soundGroup);
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
}
