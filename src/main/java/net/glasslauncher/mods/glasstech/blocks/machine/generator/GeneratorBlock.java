package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;

public class GeneratorBlock extends MachineBlockTemplate {

    public GeneratorBlock(Identifier identifier, Material material) {
        super(identifier, material);
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
            GlassTech.NAMESPACE.id("generator"),
            generatorBlockEntity,
            new GeneratorScreenHandler(player.inventory, generatorBlockEntity)
        );
        return true;
    }
}