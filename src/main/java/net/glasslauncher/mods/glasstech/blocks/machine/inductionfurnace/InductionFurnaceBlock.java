package net.glasslauncher.mods.glasstech.blocks.machine.inductionfurnace;

import net.danygames2014.nyalib.block.DropInventoryOnBreak;
import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;

public class InductionFurnaceBlock extends MachineBlockTemplate implements DropInventoryOnBreak {

    public InductionFurnaceBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public BlockEntity createBlockEntity() {
        return new InductionFurnaceBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        InductionFurnaceBlockEntity inductionFurnaceBlockEntity = (InductionFurnaceBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(
                player,
                GlassTech.NAMESPACE.id("induction_furnace"),
                inductionFurnaceBlockEntity,
                new InductionFurnaceScreenHandler(player.inventory, inductionFurnaceBlockEntity)
        );
        return true;
    }
}