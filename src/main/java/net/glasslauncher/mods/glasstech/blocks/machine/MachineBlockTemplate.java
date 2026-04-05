package net.glasslauncher.mods.glasstech.blocks.machine;

import net.danygames2014.nyalib.block.DropInventoryOnBreak;
import net.danygames2014.nyalib.energy.template.block.EnergySourceBlockTemplate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

public abstract class MachineBlockTemplate extends EnergySourceBlockTemplate implements DropInventoryOnBreak {

    public MachineBlockTemplate(Identifier identifier, Material material) {
        super(identifier, material);
        setHardness(5f);
        setResistance(10f);
        setSoundGroup(METAL_SOUND_GROUP);
        setTranslationKey(identifier);
        setDefaultState(getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
                .with(Properties.LIT, false)
        );
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(Properties.LIT);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState()
                .with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite())
                .with(Properties.LIT, false);
    }
}
