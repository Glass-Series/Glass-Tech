package net.glasslauncher.mods.glasstech.blocks.machine.electricfurnace;

import net.danygames2014.nyalib.block.DropInventoryOnBreak;
import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;

public class ElectricFurnace extends MachineBlockTemplate implements DropInventoryOnBreak {

    public ElectricFurnace(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public MachineBlockEntityTemplate createBlockEntity() {
        return new ElectricFurnaceBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        ElectricFurnaceBlockEntity electricFurnaceBlockEntity = (ElectricFurnaceBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(
                player,
                GlassTech.NAMESPACE.id("electric_furnace"),
                electricFurnaceBlockEntity,
                new ElectricFurnaceScreenHandler(player.inventory, electricFurnaceBlockEntity)
        );
        return true;
    }
}