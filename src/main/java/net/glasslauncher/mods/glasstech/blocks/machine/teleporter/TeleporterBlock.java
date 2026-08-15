package net.glasslauncher.mods.glasstech.blocks.machine.teleporter;

import net.glasslauncher.mods.glasstech.blocks.GTFrequencyBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class TeleporterBlock extends MachineBlockTemplate implements GTFrequencyBlock {

    public TeleporterBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public MachineBlockEntityTemplate createBlockEntity() {
        return new TeleporterBlockEntity();
    }

    @Override
    public boolean setFrequency(World world, int x, int y, int z, int freqX, int freqY, int freqZ) {
        TeleporterBlockEntity blockEntity = (TeleporterBlockEntity) world.getBlockEntity(x, y, z);
        blockEntity.targetSet = true;
        blockEntity.targetX = freqX;
        blockEntity.targetY = freqY;
        blockEntity.targetZ = freqZ;
        return true;
    }

    @Override
    public Vec3i getFrequency(World world, int x, int y, int z) {
        return new Vec3i(x, y, z);
    }
}
