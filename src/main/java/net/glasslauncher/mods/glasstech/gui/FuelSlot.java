package net.glasslauncher.mods.glasstech.gui;

import net.glasslauncher.mods.glassguis.compat.StationAPICompat;
import net.glasslauncher.mods.glassguis.screen.widget.slot.GlassSlot;
import net.glasslauncher.mods.glasstech.events.init.ClientInitListener;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.SpriteAtlasTexture;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;

public class FuelSlot extends Slot implements GlassSlot {
    public FuelSlot(Inventory inventory, int index, int x, int y) {
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
        return FuelRegistry.getFuelTime(stack) > 0;
    }

    @Override
    public boolean renderExtras() {

        SpriteAtlasTexture atlas = StationRenderAPI.getBakedModelManager().getAtlas(ClientInitListener.fuelSlotIndex.getSprite().getAtlasId());
        atlas.bindTexture();
        StationAPICompat.drawSprite(
                x,
                y,
                ClientInitListener.fuelSlotIndex.getWidth(),
                ClientInitListener.fuelSlotIndex.getHeight(),
                ClientInitListener.fuelSlotIndex.getSprite()
        );
        return true;
    }
}
