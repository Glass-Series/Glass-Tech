package net.glasslauncher.mods.glasstech.blocks.batbox.mesu;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.machine.PowerStorageBlockTemplate;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;

public class MESUBlock extends PowerStorageBlockTemplate {
    public MESUBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setTranslationKey(identifier);
    }

    @Override
    public BlockEntity createBlockEntity() {
        return new MESUBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        MESUBlockEntity mesuBlockEntity = (MESUBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(
                player,
                GlassTech.NAMESPACE.id("mesu"),
                mesuBlockEntity,
                new MESUScreenHandler(player.inventory, mesuBlockEntity)
        );
        return true;
    }
}
