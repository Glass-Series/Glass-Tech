package net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.minecraft.entity.player.PlayerInventory;

class ElectrolyzerScreenHandler extends ConsumerScreenHandlerTemplate<ElectrolyzerBlockEntity> {
    public ElectrolyzerScreenHandler(PlayerInventory playerInventory, ElectrolyzerBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        SlotLayout.createBasic(this, playerInventory.player, blockEntity);
    }
}
