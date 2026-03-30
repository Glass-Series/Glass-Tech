package net.glasslauncher.mods.glasstech.blocks.cable;

import net.glasslauncher.mods.glasstech.GTWireMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.Identifier;
import net.teamterminus.machineessentials.energy.electric.api.WireProperties;
import net.teamterminus.machineessentials.energy.electric.template.ElectricBlock;
import net.teamterminus.machineessentials.energy.electric.template.ElectricWireBlock;
import net.teamterminus.machineessentials.network.NetworkComponentBlock;
import net.teamterminus.machineessentials.network.NetworkType;

import static net.glasslauncher.mods.glasstech.blocks.cable.IronCableBlockEntity.DIR_PROPS;

public class IronCableBlock extends ElectricWireBlock implements NetworkComponentBlock {

    public IronCableBlock(Identifier identifier) {
        super(identifier, Material.WOOL, new WireProperties(1, true, false, GTWireMaterial.IRON));
        resistance = 1f;
        hardness = 0.5f;
        setDefaultState(getDefaultState()
            .with(Properties.NORTH, false)
            .with(Properties.SOUTH, false)
            .with(Properties.EAST, false)
            .with(Properties.WEST, false)
            .with(Properties.UP, false)
            .with(Properties.DOWN, false)
        );
    }

    public BlockState getPlacementState(ItemPlacementContext context) {
        return updateModel(context.getWorld(), context.getBlockPos().x, context.getBlockPos().y, context.getBlockPos().z);
    }

    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.NORTH);
        builder.add(Properties.SOUTH);
        builder.add(Properties.EAST);
        builder.add(Properties.WEST);
        builder.add(Properties.UP);
        builder.add(Properties.DOWN);
        super.appendProperties(builder);
    }

    public BlockEntity createBlockEntity() {
        return new IronCableBlockEntity();
    }

    public NetworkType getType() {
        return NetworkType.ELECTRIC;
    }

    public boolean isFullCube() {
        return false;
    }

    public boolean isOpaque() {
        return false;
    }

    public void onBreak(World world, int x, int y, int z) {
        if (!FurnaceBlock.ignoreBlockRemoval) {
            super.onBreak(world, x, y, z);
        }
    }

    public void onPlaced(World world, int x, int y, int z) {
        if (!FurnaceBlock.ignoreBlockRemoval) {
            super.onPlaced(world, x, y, z);
        }
    }

    public void neighborUpdate(World world, int x, int y, int z, int id) {
        super.neighborUpdate(world, x, y, z, id);

        FurnaceBlock.ignoreBlockRemoval = true;
        world.setBlockStateWithNotify(x, y, z, updateModel(world, x, y, z));
        FurnaceBlock.ignoreBlockRemoval = false;
    }

    public BlockState updateModel(World world, int x, int y, int z) {
        var state = getDefaultState();
        for (var it : DIR_PROPS.entrySet()) {
            state = state.with(it.getKey(), world.getBlockState(x + it.getValue().getOffsetX(), y + it.getValue().getOffsetY(), z + it.getValue().getOffsetZ()).getBlock() instanceof ElectricBlock);
        }
        return state;
    }
}