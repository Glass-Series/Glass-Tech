package net.glasslauncher.mods.glasstech.gui;

import net.danygames2014.nyalib.fluid.FluidBucket;
import net.glasslauncher.mods.glassguis.compat.StationAPICompat;
import net.glasslauncher.mods.glassguis.screen.widget.slot.GlassSlot;
import net.glasslauncher.mods.glasstech.events.init.GlassTechClient;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.SpriteAtlasTexture;

public class BucketSlot extends Slot implements GlassSlot {
    public BucketSlot(Inventory inventory, int index, int x, int y) {
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
        return stack.getItem() instanceof FluidBucket bucket && bucket.getFluid() == null;
    }

    @Override
    public boolean renderExtras() {

        SpriteAtlasTexture atlas = StationRenderAPI.getBakedModelManager().getAtlas(GlassTechClient.bucketSlotIndex.getSprite().getAtlasId());
        atlas.bindTexture();
        StationAPICompat.drawSprite(
                x,
                y,
                GlassTechClient.bucketSlotIndex.getWidth(),
                GlassTechClient.bucketSlotIndex.getHeight(),
                GlassTechClient.bucketSlotIndex.getSprite()
        );
        return true;
    }
}
