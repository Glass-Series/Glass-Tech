package net.glasslauncher.mods.glasstech.gui;

import net.glasslauncher.mods.glassguis.compat.StationAPICompat;
import net.glasslauncher.mods.glassguis.screen.widget.slot.BigSlot;
import net.glasslauncher.mods.glasstech.events.init.ClientInitListener;
import net.glasslauncher.mods.glasstech.events.init.InitListener;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.SpriteAtlasTexture;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;

public class BatterySlot extends BigSlot {
    public BatterySlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
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
