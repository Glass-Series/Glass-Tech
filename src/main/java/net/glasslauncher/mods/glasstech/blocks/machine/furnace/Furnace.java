package net.glasslauncher.mods.glasstech.blocks.machine.furnace;

import net.danygames2014.nyalib.block.DropInventoryOnBreak;
import net.danygames2014.nyalib.energy.template.block.EnergyConsumerBlockTemplate;
import net.glasslauncher.mods.glasstech.PlayerEntityUtil;
import net.glasslauncher.mods.glasstech.events.init.InitListener;

import net.minecraft.block.Block;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

public class Furnace extends EnergyConsumerBlockTemplate implements DropInventoryOnBreak {

    public Furnace(Identifier identifier, Material material) {
        super(identifier, material);
        setHardness(5f);
        setResistance(10f);
        setSoundGroup(METAL_SOUND_GROUP);
        setDefaultState(getDefaultState()
            .with(Properties.LIT, false)
            .with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(Properties.LIT);
        super.appendProperties(builder);
    }

    @Override
    public Box getBoundingBox(World world, int x, int y, int z) {
        return super.getBoundingBox(world, x, y, z);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState()
            .with(Properties.HORIZONTAL_FACING, PlayerEntityUtil.placementFacing(context.getPlayer()))
            .with(Properties.LIT, false);
    }

    @Override
    public BlockEntity createBlockEntity() {
        return new FurnaceBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        FurnaceBlockEntity furnaceBlockEntity = (FurnaceBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(player, InitListener.NAMESPACE.id("furnace"), furnaceBlockEntity, new FurnaceScreenHandler(player.inventory, furnaceBlockEntity));
        return true;
    }
}