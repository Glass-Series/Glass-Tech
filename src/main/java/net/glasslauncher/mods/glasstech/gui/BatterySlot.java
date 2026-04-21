package net.glasslauncher.mods.glasstech.gui;

import net.danygames2014.nyalib.NyaLib;
import net.danygames2014.nyalib.capability.CapabilityHelper;
import net.glasslauncher.mods.glassguis.compat.StationAPICompat;
import net.glasslauncher.mods.glassguis.screen.widget.slot.GlassSlot;
import net.glasslauncher.mods.glasstech.events.init.GlassTechClient;
import net.glasslauncher.mods.glasstech.item.SingleUsePowerCapability;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.SpriteAtlasTexture;

public class BatterySlot extends Slot implements GlassSlot {
    public BatterySlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return CapabilityHelper.getCapability(stack, SingleUsePowerCapability.IDENTIFIER) != null || CapabilityHelper.getCapability(stack, NyaLib.NAMESPACE.id("energy_storage")) != null;
    }

    @Override
    public boolean renderExtras() {

        SpriteAtlasTexture atlas = StationRenderAPI.getBakedModelManager().getAtlas(GlassTechClient.batterySlotIndex.getSprite().getAtlasId());
        atlas.bindTexture();
        StationAPICompat.drawSprite(
                x,
                y,
                GlassTechClient.batterySlotIndex.getWidth(),
                GlassTechClient.batterySlotIndex.getHeight(),
                GlassTechClient.batterySlotIndex.getSprite()
        );
        return true;
    }
}
