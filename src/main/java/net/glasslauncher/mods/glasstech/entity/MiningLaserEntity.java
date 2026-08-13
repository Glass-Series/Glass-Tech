package net.glasslauncher.mods.glasstech.entity;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.entity.HasOwner;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.StationSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

@HasTrackingParameters(trackingDistance = 40, updatePeriod = 40)
public class MiningLaserEntity extends Entity implements StationSpawnDataProvider, HasOwner {
    public static final Identifier HANDLER_ID = GlassTech.NAMESPACE.id("mining_laser");

    protected Entity owner;
    protected int range;
    protected boolean explode;

    protected double startX;
    protected double startY;
    protected double startZ;

    public MiningLaserEntity(World world) {
        super(world);
        setBoundingBoxSpacing(0.2f, 0.2f);
    }

    public MiningLaserEntity(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x, y, z);
    }

    public MiningLaserEntity(World world, PlayerEntity owner, int range, boolean explode) {
        this(world);
        this.x = owner.x - MathHelper.cos(owner.yaw / 180.0F * (float) Math.PI) * 0.16F;
        this.y = owner.y - 0.1F;
        this.z = owner.z - MathHelper.sin(owner.yaw / 180.0F * (float) Math.PI) * 0.16F;
        this.setPositionAndAngles(x, y, z, -owner.yaw, -owner.pitch);
        this.prevPitch = this.pitch;
        this.prevYaw = this.yaw;
        this.owner = owner;
        this.range = range;
        this.explode = explode;
        Vec3d look = owner.getLookVector(1);
        this.velocityX = look.x;
        this.velocityY = look.y;
        this.velocityZ = look.z;
        startX = x;
        startY = y;
        startZ = z;
    }

    @Override
    public void tick() {
        super.tick();

        setPosition(x + velocityX, y + velocityY, z + velocityZ);

        double distFromSpawn = getDistance(startX, startY, startZ);
        if (distFromSpawn > range) {
            markDead();
            return;
        }

        HitResult result = world.raycast(Vec3d.create(x, y, z), Vec3d.create(x + velocityX, y + velocityY, z + velocityZ), false, true);
        if (result == null) {
            return;
        }
        if (explode) {
            markDead();
            return;
        }
        Block block = world.getBlockState(result.blockX, result.blockY, result.blockZ).getBlock();
        if (block.material.isHandHarvestable() || Item.IRON_PICKAXE.isSuitableFor(block) || Item.IRON_SHOVEL.isSuitableFor(block) || Item.IRON_AXE.isSuitableFor(block) || Item.IRON_HOE.isSuitableFor(block) || Item.SHEARS.isSuitableFor(block)) {
            block.dropStacks(world, result.blockX, result.blockY, result.blockZ, world.getBlockMeta(result.blockX, result.blockY, result.blockZ));
            WorldUtil.breakBlockWithParticles(world, result.blockX, result.blockY, result.blockZ, block.id);
        }
    }

    @Override
    public void onCollision(Entity otherEntity) {
        if (owner != null && otherEntity == owner && age < 5) {
            return;
        }
        otherEntity.damage(this, 4);
        markDead();
    }

    @Override
    public void markDead() {
        super.markDead();
        if (explode) {
            world.createExplosion(this, x, y, z, 2f);
        }
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readNbt(NbtCompound nbt) {
        startX = nbt.getDouble("startX");
        startY = nbt.getDouble("startY");
        startZ = nbt.getDouble("startZ");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putDouble("startX", startX);
        nbt.putDouble("startY", startY);
        nbt.putDouble("startZ", startZ);
    }

    @Override
    public Entity getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Entity entityBase) {
        owner = entityBase;
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return HANDLER_ID;
    }

    @Override
    public Packet getSpawnData() {
        return null;
    }

    @Override
    public boolean isPushable() {
        return true;
    }
}
