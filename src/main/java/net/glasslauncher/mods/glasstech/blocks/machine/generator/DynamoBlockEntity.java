package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glassguis.screen.ServerSyncedField;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorWithInventoryBlockEntityTemplate;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.math.Direction;

import static net.modificationstation.stationapi.api.state.property.Properties.HORIZONTAL_FACING;

public class DynamoBlockEntity extends GeneratorWithInventoryBlockEntityTemplate implements Inventory {
    @ServerSyncedField
    protected int generating;

    public DynamoBlockEntity() {
        super(1, VoltageTier.LV);
        setEnergyCapacity(4000);
    }

    @Override
    public void tick() {
        super.tick();
        if (world.isRemote) {
            return;
        }
        generating = 0;
        BlockState state = world.getBlockState(x, y, z);
        Direction looking = state.get(HORIZONTAL_FACING);
        if (world.getBlockEntity(x + looking.getOffsetX(), y, z + looking.getOffsetZ()) instanceof DynamoComponent dynamoComponent && dynamoComponent.isConnected(looking) && dynamoComponent.isGenerating()) {
            generating = dynamoComponent.getOutput();
            energy += generating; // in eu/t
            if (state.contains(Properties.LIT) && !state.get(Properties.LIT)) {
                FurnaceBlock.ignoreBlockRemoval = true;
                world.setBlockStateWithNotify(x, y, z, state.with(Properties.LIT, true));
                FurnaceBlock.ignoreBlockRemoval = false;
            }
        }
        if (energy > getEnergyCapacity()) {
            energy = getEnergyCapacity();
        }

        markDirty();
    }

    @Override
    public String getName() {
        return "Dynamo";
    }

    @Override
    public int getGeneratingCurrent() {
        return generating;
    }
}
