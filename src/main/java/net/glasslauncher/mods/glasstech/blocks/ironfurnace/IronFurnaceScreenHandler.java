package net.glasslauncher.mods.glasstech.blocks.ironfurnace;

import net.glasslauncher.mods.glassguis.screen.AutoSyncingScreenHandler;
import net.glasslauncher.mods.glasstech.gui.BigOutputSlot;
import net.glasslauncher.mods.glasstech.gui.FuelSlot;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class IronFurnaceScreenHandler extends ScreenHandler implements AutoSyncingScreenHandler {

    private final IronFurnaceBlockEntity ironFurnaceBlockEntity;

    public IronFurnaceScreenHandler(PlayerInventory playerInventory, IronFurnaceBlockEntity ironFurnaceBlockEntity) {
        super();
        this.ironFurnaceBlockEntity = ironFurnaceBlockEntity;
        if (playerInventory != null) {
            glassguis_setupPlayerInventory(8, 167, playerInventory);
        }
        addSlot(new Slot(ironFurnaceBlockEntity, 0, 56, 17));
        addSlot(new FuelSlot(ironFurnaceBlockEntity, 1, 56, 53));
        addSlot(new BigOutputSlot(ironFurnaceBlockEntity, 2, 116, 35));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return ironFurnaceBlockEntity.canPlayerUse(player);
    }

    @Override
    public BlockEntity getBlockEntity() {
        return ironFurnaceBlockEntity;
    }
}
