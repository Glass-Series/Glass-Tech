package net.glasslauncher.mods.glasstech.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.ArrayList;
import java.util.List;

public class GTExplosion extends Explosion {
    public GTExplosion(World world, Entity source, double x, double y, double z, float power) {
        super(world, source, x, y, z, power);
    }

    @Override
    public void playExplosionSound(boolean addParticles) {
        this.world.playSound(this.x, this.y, this.z, "random.explode", 4.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F);
        //noinspection rawtypes,unchecked
        List<?> damagedBlocks = (List<?>) new ArrayList<>(this.damagedBlocks);

        for(int var3 = damagedBlocks.size() - 1; var3 >= 0; --var3) {
            BlockPos blockPos = (BlockPos)damagedBlocks.get(var3);
            int blockId = this.world.getBlockId(blockPos.x, blockPos.y, blockPos.z);
            if (addParticles) {
                double particlePosX = (float)x + this.world.random.nextFloat();
                double particlePosY = (float)y + this.world.random.nextFloat();
                double particlePosZ = (float)z + this.world.random.nextFloat();
                double particleVelX = particlePosX - this.x;
                double particleVelY = particlePosY - this.y;
                double particleVelZ = particlePosZ - this.z;
                double uhWhat = MathHelper.sqrt(particleVelX * particleVelX + particleVelY * particleVelY + particleVelZ * particleVelZ);
                particleVelX /= uhWhat;
                particleVelY /= uhWhat;
                particleVelZ /= uhWhat;
                double particleYeetMultiplier = (double)0.5F / (uhWhat / (double)this.power + 0.1);
                particleYeetMultiplier *= this.world.random.nextFloat() * this.world.random.nextFloat() + 0.3F;
                particleVelX *= particleYeetMultiplier;
                particleVelY *= particleYeetMultiplier;
                particleVelZ *= particleYeetMultiplier;
                this.world.addParticle("explode", (particlePosX + this.x) / 2.0D, (particlePosY + this.y) / 2.0D, (particlePosZ + this.z) / 2.0D, particleVelX, particleVelY, particleVelZ);
                this.world.addParticle("smoke", particlePosX, particlePosY, particlePosZ, particleVelX, particleVelY, particleVelZ);
            }

            if (blockId > 0) {
                dropStacks(blockPos.x, blockPos.y, blockPos.z, blockId);
            }
        }
    }

    public void dropStacks(int x, int y, int z, int id) {
        Block.BLOCKS[id].dropStacks(this.world, x, y, z, this.world.getBlockMeta(x, y, z));
        this.world.setBlock(x, y, z, 0);
        Block.BLOCKS[id].onDestroyedByExplosion(this.world, x, y, z);
    }
}
