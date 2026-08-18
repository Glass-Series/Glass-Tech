package net.glasslauncher.mods.glasstech.blocks;

import net.danygames2014.nyalib.energy.EnergyConductor;
import net.danygames2014.nyalib.network.*;
import net.danygames2014.nyalib.network.energy.EnergyNetwork;
import net.danygames2014.nyalib.particle.ParticleHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.block.States;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.BooleanProperty;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

import static net.glasslauncher.mods.glasstech.blocks.GTProperties.FOAM;
import static net.glasslauncher.mods.glasstech.blocks.GTProperties.FOAM_COLOR;

public class TemplateCableBlock extends TemplateBlock implements NetworkNodeComponent, EnergyConductor {
    public static final float PIXEL_SIZE = 1f / 16;
    // Fucking beta directions
    public static final Map<BooleanProperty, Direction> DIR_PROPS = Map.of(
            Properties.NORTH, Direction.NORTH.rotateYClockwise(),
            Properties.SOUTH, Direction.SOUTH.rotateYClockwise(),
            Properties.EAST, Direction.EAST.rotateYClockwise(),
            Properties.WEST, Direction.WEST.rotateYClockwise(),
            Properties.UP, Direction.UP,
            Properties.DOWN, Direction.DOWN
    );

    private static boolean ignoreSneaking = false;


    public final float size;
    public final WireMaterial wireMaterial;

    public TemplateCableBlock(Identifier identifier, WireMaterial wireMaterial, float size) {
        super(identifier, Material.WOOL);
        this.wireMaterial = wireMaterial;
        this.size = size;
        resistance = 1f;
        hardness = 0.5f;
        setSoundGroup(wireMaterial.soundGroup);
        setTranslationKey(identifier);
        setDefaultState(getDefaultState()
            .with(Properties.NORTH, false)
            .with(Properties.SOUTH, false)
            .with(Properties.EAST, false)
            .with(Properties.WEST, false)
            .with(Properties.UP, false)
            .with(Properties.DOWN, false)
            .with(FOAM_COLOR, FoamColor.DEFAULT)
            .with(FOAM, 0)
        );
        setTickRandomly(true); // Maybe see if there's a better way of doing this.
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return updateModel(context.getWorld(), context.getBlockPos().x, context.getBlockPos().y, context.getBlockPos().z);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.NORTH);
        builder.add(Properties.SOUTH);
        builder.add(Properties.EAST);
        builder.add(Properties.WEST);
        builder.add(Properties.UP);
        builder.add(Properties.DOWN);
        builder.add(FOAM_COLOR);
        builder.add(FOAM);
        super.appendProperties(builder);
    }

    @Override
    public NetworkType getNetworkType() {
        return NetworkType.ENERGY;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        if (!FurnaceBlock.ignoreBlockRemoval) {
            super.onBreak(world, x, y, z);
        }
    }

    @Override
    public void onPlaced(World world, int x, int y, int z) {
        if (!FurnaceBlock.ignoreBlockRemoval) {
            super.onPlaced(world, x, y, z);
        }
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        super.neighborUpdate(world, x, y, z, id);

        FurnaceBlock.ignoreBlockRemoval = true;
        world.setBlockState(x, y, z, updateModel(world, x, y, z));
        FurnaceBlock.ignoreBlockRemoval = false;
    }

    public BlockState updateModel(World world, int x, int y, int z) {
        var state = world.getBlockState(x, y, z);
        if (!(state.getBlock() instanceof TemplateCableBlock)) {
            return getDefaultState(); // Probably placing or breaking a block, if I return the wrong state shit breaks
        }
        for (Map.Entry<BooleanProperty, Direction> it : DIR_PROPS.entrySet()) {
            state = state.with(it.getKey(), canConnectTo(world, x, y, z, null, it.getValue()));
        }
        return state;
    }

    @Override
    public boolean canConnectTo(World world, int x, int y, int z, @Nullable Network network, Direction dir) {
        BlockState other = world.getBlockState(x + dir.getOffsetX(), y + dir.getOffsetY(), z + dir.getOffsetZ());
        if (other.getBlock() instanceof NetworkComponent component) {
            return component.getNetworkTypes().contains(net.danygames2014.nyalib.network.NetworkType.ENERGY);
        }
        return false;
    }

    @Override
    public Box getBoundingBox(World world, int x, int y, int z) {
        BlockState state = world.getBlockState(x, y, z);
        if (!state.contains(FOAM)) {
            return null;
        }
        if (state.get(FOAM) != 0 || (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT) && !ignoreSneaking && Minecraft.INSTANCE.player.isSneaking())) {
            return super.getBoundingBox(world, x, y, z);
        }

        if (state.getBlock() != this) {
            return null;
        }

        float center = (1f / 16) * 8;
        float maxC = center + (size / 2);
        float minC = center - (size / 2);

        float minX = minC;
        float minY = minC;
        float minZ = minC;

        float maxX = maxC;
        float maxY = maxC;
        float maxZ = maxC;

        if (state.get(Properties.UP)) {
            maxY = 1.0F;
        }

        if (state.get(Properties.DOWN)) {
            minY = 0.0F;
        }

        if (state.get(Properties.EAST)) {
            maxZ = 1.0F;
        }

        if (state.get(Properties.WEST)) {
            minZ = 0.0F;
        }

        if (state.get(Properties.SOUTH)) {
            minX = 0.0F;
        }

        if (state.get(Properties.NORTH)) {
            maxX = 1.0F;
        }

        return Box.createCached(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
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

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        ignoreSneaking = true;
        Box box = getBoundingBox(world, x, y, z);
        ignoreSneaking = false;
        return box;
    }

    // Energy Conductor
    @Override
    public void onBreakdownVoltage(World world, NetworkComponentEntry networkComponentEntry, int voltage) {

    }

    @Override
    public void onBreakdownPower(World world, NetworkComponentEntry networkComponentEntry, int voltage, int power) {
        Vec3i pos = networkComponentEntry.pos();
        for (int particle = 0; particle < 4; particle++) {
            ParticleHelper.addParticle(world, "smoke", pos.x + 0.5D + (world.random.nextDouble() - 0.5D), pos.y + 0.5D, pos.z + 0.5D + (world.random.nextDouble() - 0.5D));
        }
        world.setBlockState(pos.x, pos.y, pos.z, States.AIR.get());
    }

    @Override
    public int getBreakdownVoltage(World world, NetworkComponentEntry networkComponentEntry) {
        return wireMaterial.voltageTier.maxVoltage;
    }

    @Override
    public int getBreakdownPower(World world, NetworkComponentEntry networkComponentEntry) {
        return wireMaterial.getMaxPower();
    }

    @Override
    public double getEnergyLossPerBlock() {
        return wireMaterial.lossPerBlock;
    }

    @Override
    public void onEntityCollision(World world, int x, int y, int z, Entity entity) {
        if (world.isRemote || !wireMaterial.canShock) {
            return;
        }
        EnergyNetwork network = (EnergyNetwork) NetworkManager.getAt(world.dimension, x, y, z, NetworkType.ENERGY.getIdentifier());
        if (network == null) {
            return;
        }

        entity.damage(null, network.getFlowEntry(x, y, z).energyFlow);
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        BlockState state = world.getBlockState(x, y, z);
        if (world.isRemote || state.get(FOAM) != 1) {
            return;
        }

        if (world.getBrightness(x, y, z) * 6 >= random.nextInt(500)) {
            world.setBlockState(x, y, z, state.with(FOAM, 2));
        }
    }
}