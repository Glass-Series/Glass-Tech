package net.glasslauncher.mods.glasstech.blocks.machine;

import net.danygames2014.nyalib.network.Network;
import net.danygames2014.nyalib.network.NetworkEdgeComponent;
import net.danygames2014.nyalib.network.NetworkType;
import net.glasslauncher.mods.glasstech.blocks.GTWrenchable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

public abstract class EnergySourceConsumerBlockTemplate extends TemplateBlockWithEntity implements NetworkEdgeComponent, GTWrenchable {
    public EnergySourceConsumerBlockTemplate(Identifier identifier, Material material) {
        super(identifier, material);
        setHardness(5f);
        setResistance(10f);
        setSoundGroup(METAL_SOUND_GROUP);
    }

    @Override
    public abstract BlockEntity createBlockEntity();

    @Override
    public NetworkType getNetworkType() {
        return NetworkType.ENERGY;
    }

    @Override
    public void onAddedToNet(World world, int x, int y, int z, Network network) {
        if (world.getBlockEntity(x, y, z) instanceof EnergySourceConsumerBlockEntityTemplate energySource) {
            energySource.addedToNet(world, x, y, z, network);
        }
    }

    @Override
    public void onRemovedFromNet(World world, int x, int y, int z, Network network) {
        if (world.getBlockEntity(x, y, z) instanceof EnergySourceConsumerBlockEntityTemplate energySource) {
            energySource.removedFromNet(world, x, y, z, network);
        }
    }

    @Override
    public void update(World world, int x, int y, int z, Network network) {
        if (world.getBlockEntity(x, y, z) instanceof EnergySourceConsumerBlockEntityTemplate energySource) {
            energySource.update(world, x, y, z, network);
        }
    }

    @Override
    public void dropStacksWrench(World world, int x, int y, int z, int i) {
        dropStacks(world, x, y, z, i);
    }
}
