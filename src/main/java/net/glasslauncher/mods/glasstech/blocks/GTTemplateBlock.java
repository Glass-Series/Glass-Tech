package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.GTTextureInit;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class GTTemplateBlock extends TemplateBlock implements GTTextureInit {
    public final Identifier identifier;

    public GTTemplateBlock(Identifier identifier, Material material, BlockSoundGroup soundGroup) {
        super(identifier, material);
        this.identifier = identifier;
        setTranslationKey(identifier);
        setSoundGroup(soundGroup);
    }
}
