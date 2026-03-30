package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.danygames2014.nyalib.block.DropInventoryOnBreak;
import net.danygames2014.nyalib.energy.template.block.EnergySourceBlockTemplate;
import net.glasslauncher.mods.glasstech.PlayerEntityUtil;
import net.glasslauncher.mods.glasstech.events.init.InitListener;
import net.minecraft.block.Block;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

public class GeneratorBlock extends EnergySourceBlockTemplate implements DropInventoryOnBreak {

    public GeneratorBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setHardness(5f);
        setResistance(10f);
        setSoundGroup(METAL_SOUND_GROUP);
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
            .with(Properties.HORIZONTAL_FACING, PlayerEntityUtil.placementFacing(context.getPlayer()))
            .with(Properties.LIT, false);
    }

    @Override
    public BlockEntity createBlockEntity() {
        return new GeneratorBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        GeneratorBlockEntity generatorBlockEntity = (GeneratorBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(
            player,
            InitListener.NAMESPACE.id("generator"),
            generatorBlockEntity,
            new GeneratorScreenHandler(player.inventory, generatorBlockEntity)
        );
        return true;
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        if (!FurnaceBlock.ignoreBlockRemoval) {
            world.removeBlockEntity(x, y, z);
        }
    }

    @Override
    public void onPlaced(World world, int x, int y, int z) {
        if (!FurnaceBlock.ignoreBlockRemoval) {
            world.setBlockEntity(x, y, z, createBlockEntity());
        }
    }
}