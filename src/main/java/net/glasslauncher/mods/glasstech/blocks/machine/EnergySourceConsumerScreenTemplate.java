package net.glasslauncher.mods.glasstech.blocks.machine;

import net.glasslauncher.mods.glassguis.DrawDirection;
import net.glasslauncher.mods.glassguis.events.init.GlassGUIs;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.modificationstation.stationapi.api.util.Formatting;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;

import static net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout.GAUGE_LOCATION;

public abstract class EnergySourceConsumerScreenTemplate<T extends EnergySourceConsumerBlockEntityTemplate> extends HandledScreen {
    static Rectangle euTooltipRect;

    protected final PlayerInventory playerInventory;
    protected final T blockEntity;

    public EnergySourceConsumerScreenTemplate(ScreenHandler screenHandler, PlayerInventory playerInventory, T blockEntity) {
        super(screenHandler);
        this.playerInventory = playerInventory;
        this.blockEntity = blockEntity;
    }

    @Override
    public void drawForeground() {
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        super.render(mouseX, mouseY, delta);

        if (euTooltipRect != null) {
            glassguis_tooltip(List.of(((blockEntity.getEnergyStored() < blockEntity.getEnergyCapacity() / 5) ? Formatting.RED : Formatting.AQUA).toString() + blockEntity.getEnergyStored() + Formatting.FORMATTING_CODE_PREFIX + "r/" + Formatting.AQUA + blockEntity.getEnergyCapacity() + " EU"), euTooltipRect, mouseX, mouseY);
        }
    }

    @Override
    public void drawBackground(float tickDelta) {
        glassguis_renderBackground();

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_BLEND);

        glassguis_drawSlots();

        drawMachineForeground(tickDelta);

        GL11.glDisable(GL11.GL_BLEND);

    }

    public abstract void drawMachineForeground(float tickDelta);

    public void drawGauge() {
        drawGauge(GAUGE_LOCATION[0], GAUGE_LOCATION[1]);
    }

    public void drawGauge(int x, int y) {
        glassguis_drawImage("/assets/glasstech/stationapi/textures/gui/energy_background.png", x, y);
        glassguis_drawImagePercentage("/assets/glasstech/stationapi/textures/gui/energy_bar.png", x + 2, y + 2, (((float) blockEntity.getEnergyStored()) / blockEntity.getEnergyCapacity()), DrawDirection.UP);
        glassguis_drawImage("/assets/glasstech/stationapi/textures/gui/energy_overlay.png", x, y);
        int[] size = GlassGUIs.IMAGE_SIZE_CACHE.getIfPresent("/assets/glasstech/stationapi/textures/gui/energy_background.png");
        if (size != null && (euTooltipRect == null || (euTooltipRect.x == x && euTooltipRect.y == y))) {
            euTooltipRect = new Rectangle(x, y, size[0], size[1]);
        }
    }

    public void drawArrow(int x, int y, float progress) {
        glassguis_drawImage("/assets/glasstech/stationapi/textures/gui/arrow_bg.png", x + 1, y + 1);
        glassguis_drawImagePercentage("/assets/glasstech/stationapi/textures/gui/arrow_fg.png", x, y, progress, DrawDirection.RIGHT);
    }
}