package net.glasslauncher.mods.glasstech.blocks.machine.macerator;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.minecraft.entity.player.PlayerInventory;

public class MaceratorScreenHandler extends ConsumerScreenHandlerTemplate<MaceratorBlockEntity> {
    public MaceratorScreenHandler(PlayerInventory playerInventory, MaceratorBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
    }

    @Override
    protected void initSlots() {
        SlotLayout.createBasic(this, blockEntity);
    }
}
