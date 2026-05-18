package net.glasslauncher.mods.glasstech.blocks;

import net.glasslauncher.mods.glasstech.entity.GTTntEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class GTExplosiveBlock extends GTTemplateBlock {
    public final int fuse;
    public final float power;
    public final boolean nukesplosion;

    public GTExplosiveBlock(Identifier identifier, Material material, int fuse, float power) {
        this(identifier, material, fuse, power, false);
    }

    public GTExplosiveBlock(Identifier identifier, Material material, int fuse, float power, boolean nukesplosion) {
        super(identifier, material, Block.DIRT_SOUND_GROUP);
        this.fuse = fuse;
        this.power = power;
        this.nukesplosion = nukesplosion;
    }

    @Override
    public void onPlaced(World world, int x, int y, int z) {
        super.onPlaced(world, x, y, z);
        if (world.isEmittingRedstonePower(x, y, z)) {
            this.onMetadataChange(world, x, y, z, 1);
            world.setBlock(x, y, z, 0);
        }
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        if (id > 0 && Block.BLOCKS[id].canEmitRedstonePower() && world.isEmittingRedstonePower(x, y, z)) {
            this.onMetadataChange(world, x, y, z, 1);
            world.setBlock(x, y, z, 0);
        }

    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 0;
    }

    @Override
    public void onDestroyedByExplosion(World world, int x, int y, int z) {
        if (!world.isRemote) {
            TntEntity tntEntity = createTntEntity(world, x, y, z);
            tntEntity.fuse = world.random.nextInt(tntEntity.fuse / 4) + tntEntity.fuse / 8;
            world.spawnEntity(tntEntity);
        }
    }

    @Override
    public void onMetadataChange(World world, int x, int y, int z, int meta) {
        if (!world.isRemote) {
            if ((meta & 1) == 0) {
                this.dropStack(world, x, y, z, new ItemStack(Block.TNT.id, 1, 0));
            } else {
                TntEntity tntEntity = createTntEntity(world, x, y, z);
                world.spawnEntity(tntEntity);
                world.playSound(tntEntity, "random.fuse", 1.0F, 1.0F);
            }
        }
    }

    public TntEntity createTntEntity(World world, int x, int y, int z) {
        return new GTTntEntity(world, (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
    }

    @Override
    public void onBlockBreakStart(World world, int x, int y, int z, PlayerEntity player) {
        if (player.getHand() != null && player.getHand().itemId == Item.FLINT_AND_STEEL.id) {
            world.setBlockMetaWithoutNotifyingNeighbors(x, y, z, 1);
        }

        super.onBlockBreakStart(world, x, y, z, player);
    }
}
