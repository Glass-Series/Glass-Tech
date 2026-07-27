package net.glasslauncher.mods.glasstech.blocks.machine.pump;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;

public class PumpBlock extends MachineBlockTemplate {

    public PumpBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public MachineBlockEntityTemplate createBlockEntity() {
        return new PumpBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        PumpBlockEntity pumpBlockEntity = (PumpBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(
                player,
                GlassTech.NAMESPACE.id("pump"),
                pumpBlockEntity,
                new PumpScreenHandler(player.inventory, pumpBlockEntity)
        );
        return true;
    }
}
