package net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;

public class ElectrolyzerBlock extends MachineBlockTemplate {

    public ElectrolyzerBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public MachineBlockEntityTemplate createBlockEntity() {
        return new ElectrolyzerBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        ElectrolyzerBlockEntity electrolyzerBlockEntity = (ElectrolyzerBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(
                player,
                GlassTech.NAMESPACE.id("electrolyzer"),
                electrolyzerBlockEntity,
                new ElectrolyzerScreenHandler(player.inventory, electrolyzerBlockEntity)
        );
        return true;
    }
}
