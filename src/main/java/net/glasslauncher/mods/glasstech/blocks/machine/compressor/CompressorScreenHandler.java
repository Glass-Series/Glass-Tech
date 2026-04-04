package net.glasslauncher.mods.glasstech.blocks.machine.compressor;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.minecraft.entity.player.PlayerInventory;

class CompressorScreenHandler extends ConsumerScreenHandlerTemplate<CompressorBlockEntity> {
    public CompressorScreenHandler(PlayerInventory playerInventory, CompressorBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        SlotLayout.createBasic(this, playerInventory.player, blockEntity);
    }
}
