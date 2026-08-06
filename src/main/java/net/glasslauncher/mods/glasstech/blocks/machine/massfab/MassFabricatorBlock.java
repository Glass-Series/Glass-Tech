package net.glasslauncher.mods.glasstech.blocks.machine.massfab;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;

public class MassFabricatorBlock extends MachineBlockTemplate {

    public MassFabricatorBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public MachineBlockEntityTemplate createBlockEntity() {
        return new MassFabricatorBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        MassFabricatorBlockEntity massFabricatorBlockEntity = (MassFabricatorBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(
                player,
                GlassTech.NAMESPACE.id("mass_fabricator"),
                massFabricatorBlockEntity,
                new MassFabricatorScreenHandler(player.inventory, massFabricatorBlockEntity)
        );
        return true;
    }
}
