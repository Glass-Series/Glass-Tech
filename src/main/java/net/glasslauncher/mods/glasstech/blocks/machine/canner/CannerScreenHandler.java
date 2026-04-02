package net.glasslauncher.mods.glasstech.blocks.machine.canner;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.minecraft.entity.player.PlayerInventory;

class CannerScreenHandler extends ConsumerScreenHandlerTemplate<CannerBlockEntity> {
    public CannerScreenHandler(PlayerInventory playerInventory, CannerBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        SlotLayout.createBasic(this, playerInventory.player, blockEntity);
    }
}
