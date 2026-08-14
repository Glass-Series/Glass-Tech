package net.glasslauncher.mods.glasstech.entity;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.GTDynamiteBlock;
import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
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

import static net.glasslauncher.mods.glasstech.util.TimeConstants.SECONDS;

@HasTrackingParameters(trackingDistance = 40, updatePeriod = 40)
public class StickyDynamiteEntity extends Entity implements StationSpawnDataProvider, HasOwner {
    public static final Identifier HANDLER_ID = GlassTech.NAMESPACE.id("sticky_dynamite");

    protected Entity owner;

    protected int stuckX;
    protected int stuckY = Integer.MIN_VALUE;
    protected int stuckZ;
    protected boolean explode = true;
    private boolean beenStuck;

    @Override
    public boolean shouldRender(double distance) {
        return distance < 2048;
    }

    public StickyDynamiteEntity(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.25F, 0.25F);
        this.standingEyeHeight = 0.0F;
    }

    public StickyDynamiteEntity(World world, LivingEntity owner) {
        this(world);
        this.owner = owner;
        this.setPositionAndAnglesKeepPrevAngles(owner.x, owner.y + owner.getEyeHeight(), owner.z, owner.yaw, owner.pitch);
        this.x -= MathHelper.cos(this.yaw / 180.0F * (float) Math.PI) * 0.16F;
        this.y -= 0.1F;
        this.z -= MathHelper.sin(this.yaw / 180.0F * (float) Math.PI) * 0.16F;
        this.setPosition(this.x, this.y, this.z);
        float var3 = 0.02F;
        this.velocityX = -MathHelper.sin(this.yaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.pitch / 180.0F * (float) Math.PI) * var3;
        this.velocityZ = MathHelper.cos(this.yaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.pitch / 180.0F * (float) Math.PI) * var3;
        this.velocityY = -MathHelper.sin(this.pitch / 180.0F * (float) Math.PI) * var3;
        this.setVelocity(this.velocityX, this.velocityY, this.velocityZ, 1.5F, 1.0F);
    }

    public StickyDynamiteEntity(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x, y, z);
    }

    @Override
    public void tick() {
        super.tick();

        setPosition(x + velocityX, y + velocityY, z + velocityZ);
        if (isSubmergedInWater()) {
            world.playSound(this, "random.fizz", 0.2f, 1.0f);
            explode = false;
            world.spawnEntity(new ItemEntity(world, x, y, z, new ItemStack(GlassTechItems.stickyDynamite)));
            markDead();
        }
        world.addParticle("smoke", x, y, z, 0, 0.02, 0);

        if (age > 5 * SECONDS) {
            markDead();
            return;
        }

        if (beenStuck && world.getBlockId(stuckX, stuckY, stuckZ) != 0 && getDistance(stuckX + 0.5, stuckY + 0.5, stuckZ + 0.5) < 2) {
            return;
        }
        velocityY -= 0.06;
        HitResult result = world.raycast(Vec3d.create(x, y, z), Vec3d.create(x + velocityX, y + velocityY, z + velocityZ), false, true);
        if (result == null) {
            return;
        }
        stuckX = result.blockX;
        stuckY = result.blockY;
        stuckZ = result.blockZ;
        if (getDistance(stuckX + 0.5, stuckY + 0.5, stuckZ + 0.5) > 2) {
            return;
        }
        beenStuck = true;
        velocityX = 0;
        velocityY = 0;
        velocityZ = 0;
    }

    @Override
    public void markDead() {
        super.markDead();
        if (explode) {
            GTDynamiteBlock.explode(world, (int) Math.round(x), (int) Math.round(y), (int) Math.round(z));
        }
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readNbt(NbtCompound nbt) {}

    @Override
    protected void writeNbt(NbtCompound nbt) {}

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

    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        float var9 = MathHelper.sqrt(x * x + y * y + z * z);
        x /= var9;
        y /= var9;
        z /= var9;
        x += this.random.nextGaussian() * 0.0075F * divergence;
        y += this.random.nextGaussian() * 0.0075F * divergence;
        z += this.random.nextGaussian() * 0.0075F * divergence;
        x *= speed;
        y *= speed;
        z *= speed;
        this.velocityX = x;
        this.velocityY = y;
        this.velocityZ = z;
        float var10 = MathHelper.sqrt(x * x + z * z);
        this.prevYaw = this.yaw = (float)(Math.atan2(x, z) * 180.0 / (float) Math.PI);
        this.prevPitch = this.pitch = (float)(Math.atan2(y, var10) * 180.0 / (float) Math.PI);
    }
}
