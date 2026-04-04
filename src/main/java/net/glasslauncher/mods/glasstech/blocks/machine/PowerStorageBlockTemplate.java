package net.glasslauncher.mods.glasstech.blocks.machine;

import net.danygames2014.nyalib.block.DropInventoryOnBreak;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

public abstract class PowerStorageBlockTemplate extends EnergySourceConsumerBlockTemplate implements DropInventoryOnBreak{

    public PowerStorageBlockTemplate(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(Properties.FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        Direction direction = context.getPlayerLookDirection(); // ughghhh
        if (direction.getAxis() != Direction.Axis.Y) {
            direction = direction.rotateYClockwise();
        }
        else {
            direction = direction.rotateClockwise(Direction.Axis.X).rotateClockwise(Direction.Axis.X);
        }
        return super.getDefaultState().with(Properties.FACING, direction);
    }
}
