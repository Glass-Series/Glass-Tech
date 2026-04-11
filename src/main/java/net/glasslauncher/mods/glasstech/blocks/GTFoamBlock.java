package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class GTFoamBlock extends GTTemplateBlock {
    public GTFoamBlock(Identifier identifier) {
        super(identifier, Material.WOOL, WOOL_SOUND_GROUP);
        setTickRandomly(true);
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        if (world.isRemote) {
            return;
        }

        if (world.getBrightness(x, y, z) * 6 >= random.nextInt(1000)) {
            world.setBlock(x, y, z, GlassTechBlocks.hardenedConstructionFoamBlock.id);
        }
    }

    @Override
    public int getTickRate() {
        return 500;
    }
}
