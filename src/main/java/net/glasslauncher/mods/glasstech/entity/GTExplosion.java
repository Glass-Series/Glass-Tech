package net.glasslauncher.mods.glasstech.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.ArrayList;
import java.util.List;

public class GTExplosion extends Explosion {
    public boolean destroyItems = true;

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
                breakBlock(blockPos.x, blockPos.y, blockPos.z, blockId);
            }
        }
    }

    public void breakBlock(int x, int y, int z, int id) {
        Block.BLOCKS[id].dropStacks(this.world, x, y, z, this.world.getBlockMeta(x, y, z));
        this.world.setBlock(x, y, z, 0);
        Block.BLOCKS[id].onDestroyedByExplosion(this.world, x, y, z);
    }

    @Override
    public void explode() {
        float var1 = this.power;
        byte var2 = 16;

        for (int var3 = 0; var3 < var2; var3++) {
            for (int var4 = 0; var4 < var2; var4++) {
                for (int var5 = 0; var5 < var2; var5++) {
                    if (var3 == 0 || var3 == var2 - 1 || var4 == 0 || var4 == var2 - 1 || var5 == 0 || var5 == var2 - 1) {
                        double var6 = var3 / (var2 - 1.0F) * 2.0F - 1.0F;
                        double var8 = var4 / (var2 - 1.0F) * 2.0F - 1.0F;
                        double var10 = var5 / (var2 - 1.0F) * 2.0F - 1.0F;
                        double var12 = Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
                        var6 /= var12;
                        var8 /= var12;
                        var10 /= var12;
                        float var14 = this.power * (0.7F + this.world.random.nextFloat() * 0.6F);
                        double var15 = this.x;
                        double var17 = this.y;
                        double var19 = this.z;

                        for (float var21 = 0.3F; var14 > 0.0F; var14 -= var21 * 0.75F) {
                            int var22 = MathHelper.floor(var15);
                            int var23 = MathHelper.floor(var17);
                            int var24 = MathHelper.floor(var19);
                            int var25 = this.world.getBlockId(var22, var23, var24);
                            if (var25 > 0) {
                                var14 -= (Block.BLOCKS[var25].getBlastResistance(this.source) + 0.3F) * var21;
                            }

                            if (var14 > 0.0F) {
                                this.damagedBlocks.add(new BlockPos(var22, var23, var24));
                            }

                            var15 += var6 * var21;
                            var17 += var8 * var21;
                            var19 += var10 * var21;
                        }
                    }
                }
            }
        }

        this.power *= 2.0F;
        int var29 = MathHelper.floor(this.x - this.power - 1.0);
        int var30 = MathHelper.floor(this.x + this.power + 1.0);
        int var31 = MathHelper.floor(this.y - this.power - 1.0);
        int var33 = MathHelper.floor(this.y + this.power + 1.0);
        int var7 = MathHelper.floor(this.z - this.power - 1.0);
        int var35 = MathHelper.floor(this.z + this.power + 1.0);
        List var9 = this.world.getEntities(this.source, Box.createCached(var29, var31, var7, var30, var33, var35));
        Vec3d var37 = Vec3d.createCached(this.x, this.y, this.z);

        for (int var11 = 0; var11 < var9.size(); var11++) {
            Entity var39 = (Entity)var9.get(var11);
            double var13 = var39.getDistance(this.x, this.y, this.z) / this.power;
            if (var13 <= 1.0) {
                double var43 = var39.x - this.x;
                double var46 = var39.y - this.y;
                double var49 = var39.z - this.z;
                double var51 = MathHelper.sqrt(var43 * var43 + var46 * var46 + var49 * var49);
                var43 /= var51;
                var46 /= var51;
                var49 /= var51;
                double var52 = this.world.getVisibilityRatio(var37, var39.boundingBox);
                double var53 = (1.0 - var13) * var52;
                if (destroyItems || !(var39 instanceof ItemEntity)) {
                    var39.damage(this.source, (int) ((var53 * var53 + var53) / 2.0 * 8.0 * this.power + 1.0));
                }
                var39.velocityX += var43 * var53;
                var39.velocityY += var46 * var53;
                var39.velocityZ += var49 * var53;
            }
        }

        this.power = var1;
        ArrayList var38 = new ArrayList();
        var38.addAll(this.damagedBlocks);
        if (this.fire) {
            for (int var40 = var38.size() - 1; var40 >= 0; var40--) {
                BlockPos var41 = (BlockPos)var38.get(var40);
                int var42 = var41.x;
                int var45 = var41.y;
                int var16 = var41.z;
                int var48 = this.world.getBlockId(var42, var45, var16);
                int var18 = this.world.getBlockId(var42, var45 - 1, var16);
                if (var48 == 0 && Block.BLOCKS_OPAQUE[var18] && this.random.nextInt(3) == 0) {
                    this.world.setBlock(var42, var45, var16, Block.FIRE.id);
                }
            }
        }
    }
}
