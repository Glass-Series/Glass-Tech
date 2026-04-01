package net.glasslauncher.mods.glasstech.blocks.machine.macerator;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.glasslauncher.mods.glasstech.events.init.InitListener;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;

public class MaceratorBlock extends MachineBlockTemplate {

    public MaceratorBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new MaceratorBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        MaceratorBlockEntity maceratorBlockEntity = (MaceratorBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(
                player,
                InitListener.NAMESPACE.id("macerator"),
                maceratorBlockEntity,
                new MaceratorScreenHandler(player.inventory, maceratorBlockEntity)
        );
        return true;
    }
}
