package net.glasslauncher.mods.glasstech.blocks.machine.pump;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.minecraft.entity.player.PlayerInventory;

class PumpScreenHandler extends ConsumerScreenHandlerTemplate<PumpBlockEntity> {
    public PumpScreenHandler(PlayerInventory playerInventory, PumpBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
    }

    @Override
    protected void initSlots() {
        SlotLayout.createFluid(this, blockEntity);
    }
}
