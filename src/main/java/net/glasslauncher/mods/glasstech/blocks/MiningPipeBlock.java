package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.util.WorldHelper;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class MiningPipeBlock extends GTTemplateBlock {
    public MiningPipeBlock(Identifier identifier, Material material, BlockSoundGroup soundGroup) {
        super(identifier, material, soundGroup);
        setHardness(1);
        setResistance(5);
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        if (world.getBlockState(x, y, z).isAir()) {
            world.scheduleBlockUpdate(x, y, z, id, 2);
        }
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        WorldHelper.breakBlockWithParticles(world, x, y, z, id);
        dropStacks(world, x, y, z, 0);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        return getBoundingBox(world, x, y, z);
    }

    @Override
    public Box getBoundingBox(World world, int x, int y, int z) {
        float step = 1 / 16f;
        return Box.createCached(x + (step * 5), y, z + (step * 5), x + (step * 11), y + (step * 16), z + (step * 11));
    }

    @Override
    public HitResult raycast(World world, int x, int y, int z, Vec3d startPos, Vec3d endPos) {
        Box box = getBoundingBox(world, x, y, z);

        HitResult hitResult = box.raycast(startPos, endPos);

        if (hitResult == null) {
            return null;
        }

        hitResult.blockX = x;
        hitResult.blockY = y;
        hitResult.blockZ = z;

        return hitResult;
    }
}
