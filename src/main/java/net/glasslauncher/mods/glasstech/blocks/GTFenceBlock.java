package net.glasslauncher.mods.glasstech.blocks;

import net.danygames2014.nyalib.block.FenceBlockTemplate;
import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.glasslauncher.mods.glasstech.blocks.GTTemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

public class GTFenceBlock extends FenceBlockTemplate {
    public GTFenceBlock(Identifier identifier) {
        super(identifier, GlassTechBlocks.machineBlock, NAMESPACE.id("block/mv"));
        setSoundGroup(Block.METAL_SOUND_GROUP);
        setTranslationKey(identifier);
    }
}
