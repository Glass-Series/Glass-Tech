package net.glasslauncher.mods.glasstech.compat.ami.drawable;

import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.glassguis.compat.StationAPICompat;
import net.glasslauncher.mods.glassguis.screen.widget.slot.GlassSlot;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.minecraft.client.Minecraft;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.NotNull;

public class ScreenDrawable<T extends MachineScreenTemplate<V>, V extends RecipeBlockEntityTemplate<?>> implements AMIDrawable {
    private final ScreenHandler fakeInventory;
    private final T screen;

    public ScreenDrawable(T screen) {
        this.screen = screen;
        screen.blockEntity.maxProgress = 100;
        fakeInventory = screen.container;
    }

    @Override
    public int getWidth() {
        return 84;
    }

    @Override
    public int getHeight() {
        return 58;
    }

    @Override
    public void draw(@NotNull Minecraft minecraft) {
        draw(minecraft, 0, 0);
    }

    @Override
    public void draw(@NotNull Minecraft minecraft, int xOffset, int yOffset) {
        xOffset -= 54;
        yOffset -= 15;
        for (int i = 0; i < fakeInventory.slots.size(); i++) {
            Slot slot = fakeInventory.getSlot(i);

            int slotX = slot.x + xOffset;
            int slotY = slot.y + yOffset;
            int backgroundWidth;
            int backgroundHeight;
            if (slot instanceof GlassSlot customSlot) {
                backgroundWidth = customSlot.getBackgroundWidth();
                backgroundHeight = customSlot.getBackgroundHeight();
            } else {
                backgroundWidth = 16;
                backgroundHeight = 16;
            }
            int slotBackgroundX = slotX - ((backgroundWidth - 16) / 2);
            int slotBackgroundY = slotY - ((backgroundHeight - 16) / 2);

            if (slot.getBackgroundTextureId() != -1 && ((slot instanceof GlassSlot && ((GlassSlot) slot).keepBackgroundTexture()) || !slot.hasStack())) {
                if (FabricLoader.getInstance().isModLoaded("stationapi")) {
                    StationAPICompat.drawSprite(slot, slotX, slotY, screen);
                }
            }

            screen.fill(slotBackgroundX - 1, slotBackgroundY - 1, slotBackgroundX + backgroundWidth + 1, slotBackgroundY + backgroundHeight + 1, screen.glassguis_getSlotBackground());
            screen.drawHorizontalLine(slotBackgroundX, slotBackgroundX + backgroundWidth - 1, slotBackgroundY - 1, screen.glassguis_getSlotRoundingDark());
            screen.drawVerticalLine(slotBackgroundX - 1, slotBackgroundY - 2, slotBackgroundY + backgroundHeight, screen.glassguis_getSlotRoundingDark());
            screen.drawHorizontalLine(slotBackgroundX, slotBackgroundX + backgroundWidth, slotBackgroundY + backgroundHeight, screen.glassguis_getSlotRoundingLight());
            screen.drawVerticalLine(slotBackgroundX + backgroundWidth, slotBackgroundY - 1, slotBackgroundY + backgroundHeight, screen.glassguis_getSlotRoundingLight());
        }
        screen.blockEntity.energy = screen.blockEntity.getEnergyCapacity() - Math.toIntExact(Minecraft.INSTANCE.world.getTime() % screen.blockEntity.getEnergyCapacity());
        screen.drawGauge(-xOffset - 8, -yOffset + 64, 0, 0);
        screen.drawArrow(-xOffset + 60, -yOffset + 86, (float) Minecraft.INSTANCE.world.getTime() % screen.blockEntity.maxProgress / screen.blockEntity.maxProgress);
    }
}
