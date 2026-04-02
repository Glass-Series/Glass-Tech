package net.glasslauncher.mods.glasstech.blocks.machine;

import net.glasslauncher.mods.glasstech.gui.BatterySlot;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;

public class SlotLayout {
    public static final int[] GAUGE_LOCATION = {6, 16};

    public static void createBasic(RecipeBlockEntityTemplate<?> blockEntity) {
        blockEntity.addInput();
        blockEntity.addOutput(RecipeOutputType.PRIMARY);
        blockEntity.addSlot(SlotType.FUEL);
    }

    public static void createBasic(ConsumerScreenHandlerTemplate<?> screenHandler, PlayerEntity player, Inventory inventory) {
        screenHandler.addSlot(new Slot(inventory, 0, 56, 17));
        screenHandler.addSlot(new FurnaceOutputSlot(player, inventory,1, 116, 35));
        screenHandler.addSlot(new BatterySlot(inventory, 2, 56, 53));
    }
}
