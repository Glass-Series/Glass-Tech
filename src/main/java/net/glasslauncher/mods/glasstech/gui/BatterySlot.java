package net.glasslauncher.mods.glasstech.gui;

import net.glasslauncher.mods.glassguis.compat.StationAPICompat;
import net.glasslauncher.mods.glassguis.screen.widget.slot.GlassSlot;
import net.glasslauncher.mods.glasstech.events.init.ClientInitListener;
import net.minecraft.inventory.Inventory;
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
    public boolean renderExtras() {

        SpriteAtlasTexture atlas = StationRenderAPI.getBakedModelManager().getAtlas(ClientInitListener.energySlotIndex.getSprite().getAtlasId());
        atlas.bindTexture();
        StationAPICompat.drawSprite(
                x,
                y,
                ClientInitListener.energySlotIndex.getWidth(),
                ClientInitListener.energySlotIndex.getHeight(),
                ClientInitListener.energySlotIndex.getSprite()
        );
        return true;
    }
}
