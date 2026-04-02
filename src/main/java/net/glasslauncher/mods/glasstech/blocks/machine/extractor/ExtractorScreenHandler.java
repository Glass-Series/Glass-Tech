package net.glasslauncher.mods.glasstech.blocks.machine.extractor;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.minecraft.entity.player.PlayerInventory;

class ExtractorScreenHandler extends ConsumerScreenHandlerTemplate<ExtractorBlockEntity> {
    public ExtractorScreenHandler(PlayerInventory playerInventory, ExtractorBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        SlotLayout.createBasic(this, blockEntity);
    }
}
