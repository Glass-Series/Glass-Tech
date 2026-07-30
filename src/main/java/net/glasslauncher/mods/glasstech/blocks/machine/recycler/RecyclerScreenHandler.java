package net.glasslauncher.mods.glasstech.blocks.machine.recycler;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.minecraft.entity.player.PlayerInventory;

class RecyclerScreenHandler extends ConsumerScreenHandlerTemplate<RecyclerBlockEntity> {
    public RecyclerScreenHandler(PlayerInventory playerInventory, RecyclerBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        SlotLayout.createBasic(this, playerInventory.player, blockEntity);
    }
}
