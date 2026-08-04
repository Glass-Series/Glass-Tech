package net.glasslauncher.mods.glasstech.blocks.machine.miner;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.minecraft.entity.player.PlayerInventory;

class MinerScreenHandler extends ConsumerScreenHandlerTemplate<MinerBlockEntity> {
    public MinerScreenHandler(PlayerInventory playerInventory, MinerBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        SlotLayout.createBasic(this, blockEntity);
    }
}
