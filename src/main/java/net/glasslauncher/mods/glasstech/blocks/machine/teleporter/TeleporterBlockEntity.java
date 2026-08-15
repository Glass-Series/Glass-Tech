package net.glasslauncher.mods.glasstech.blocks.machine.teleporter;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;

public class TeleporterBlockEntity extends MachineBlockEntityTemplate {

    public boolean targetSet;
    public int targetX;
    public int targetY;
    public int targetZ;
    protected boolean teleportedThisPulse;

    public TeleporterBlockEntity() {
        super(VoltageTier.HV, 0, VoltageTier.HV.maxVoltage * 2);
        inventory = new ItemStack[0];
    }

    @Override
    public String getName() {
        return "Tesla Coil";
    }

    @Override
    public void processTick() {
        if (!canProcess() || !targetSet) {
            return;
        }

        int powerLevel = world.getPowerLevel(x, y, z);

        if (teleportedThisPulse && powerLevel == 0) {
            teleportedThisPulse = false;
        }

        if (teleportedThisPulse || powerLevel == 0) {
            return;
        }

        if (world.getBlockId(targetX, targetY, targetZ) != GlassTechBlocks.teleporterBlock.id) {
            return;
        }

        List<Entity> entities = world.getEntities(null, Box.create(x, y + 1, z, x + 1, y + 2, z + 1));
        if (entities.isEmpty()) {
            return;
        }

        teleportedThisPulse = true;
        for (Entity entity : new ArrayList<>(entities)) {
            if (entity instanceof ItemEntity item && item.stack.getItem() == Item.BREAD && random.nextInt(100) == 0) {
                item.stack = new ItemStack(Item.SLIMEBALL, item.stack.count);
            }
            entity.setPosition(targetX + 0.5, targetY + 1 + entity.standingEyeHeight, targetZ + 0.5);
            // Stops the entity from visually shmoving at mach 7 towards where they teleported
            entity.lastTickX = targetX;
            entity.lastTickY = targetY;
            entity.lastTickZ = targetZ;
        }

        setEnergy(0);
    }

    @Override
    public boolean canProcess() {
        return getEnergyStored() == getEnergyCapacity();
    }
}
