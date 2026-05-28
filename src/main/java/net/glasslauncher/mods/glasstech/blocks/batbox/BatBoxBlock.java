package net.glasslauncher.mods.glasstech.blocks.batbox;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.machine.PowerStorageBlockTemplate;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;

public class BatBoxBlock extends PowerStorageBlockTemplate {
    public BatBoxBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setTranslationKey(identifier);
    }

    @Override
    public BlockEntity createBlockEntity() {
        return new BatBoxBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BatBoxBlockEntity batBoxBlockEntity = (BatBoxBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(
                player,
                GlassTech.NAMESPACE.id("battery_box"),
                batBoxBlockEntity,
                new BatBoxScreenHandler(player.inventory, batBoxBlockEntity)
        );
        return true;
    }
}
