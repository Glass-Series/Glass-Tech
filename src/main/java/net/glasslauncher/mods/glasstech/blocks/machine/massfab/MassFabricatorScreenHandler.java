package net.glasslauncher.mods.glasstech.blocks.machine.massfab;

import net.glasslauncher.mods.glasstech.blocks.machine.ConsumerScreenHandlerTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.minecraft.entity.player.PlayerInventory;

class MassFabricatorScreenHandler extends ConsumerScreenHandlerTemplate<MassFabricatorBlockEntity> {
    public MassFabricatorScreenHandler(PlayerInventory playerInventory, MassFabricatorBlockEntity blockEntity) {
        super(playerInventory, blockEntity, true);
        SlotLayout.createBasic(this, blockEntity);
    }
}
