package net.glasslauncher.mods.glasstech.blocks.machine.furnace;

import net.glasslauncher.mods.glassguis.DrawDirection;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.modificationstation.stationapi.api.util.Formatting;
import org.lwjgl.opengl.GL11;
import java.awt.Rectangle;
import java.util.List;

public class FurnaceScreen extends HandledScreen {
    protected final PlayerInventory playerInventory;
    protected final FurnaceBlockEntity furnaceBlockEntity;

    public static Rectangle euTooltipRect = new Rectangle(80, 10, 64, 18);

    public FurnaceScreen(PlayerInventory playerInventory, FurnaceBlockEntity furnaceBlockEntity) {
        super(new FurnaceScreenHandler(playerInventory, furnaceBlockEntity));
        this.playerInventory = playerInventory;
        this.furnaceBlockEntity = furnaceBlockEntity;
        glassguis_setName("Furnace");
    }

    @Override
    public void drawForeground() {
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        super.render(mouseX, mouseY, delta);

        glassguis_tooltip(this, List.of(((furnaceBlockEntity.getEnergyStored() < furnaceBlockEntity.getEnergyCapacity() / 5) ? Formatting.RED : Formatting.AQUA).toString() + furnaceBlockEntity.getEnergyStored() + Formatting.FORMATTING_CODE_PREFIX + "r/" + Formatting.AQUA + furnaceBlockEntity.getEnergyCapacity() + " EU"), euTooltipRect, mouseX, mouseY);
    }

    @Override
    public void drawBackground(float tickDelta) {
        glassguis_renderBackground(this);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_BLEND);
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/energy_background.png", 80, 10);
        glassguis_drawImagePercentage(this, "/assets/glasstech/stationapi/textures/gui/energy_bar.png", 82, 12, (((float) furnaceBlockEntity.getEnergyStored()) / furnaceBlockEntity.getEnergyCapacity()), DrawDirection.RIGHT);
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/energy_overlay.png", 80, 10);

        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/arrow_bg.png", 81, 41);
        glassguis_drawImagePercentage(this, "/assets/glasstech/stationapi/textures/gui/arrow_fg.png", 80, 40, furnaceBlockEntity.getCurrentSmeltTime() / (float) furnaceBlockEntity.getSmeltTime(), DrawDirection.RIGHT);



        glassguis_drawSlots(this);
        GL11.glDisable(GL11.GL_BLEND);

    }
}