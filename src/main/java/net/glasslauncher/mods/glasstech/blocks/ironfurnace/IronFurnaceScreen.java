package net.glasslauncher.mods.glasstech.blocks.ironfurnace;

import net.glasslauncher.mods.glassguis.DrawDirection;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class IronFurnaceScreen extends HandledScreen {
    private final PlayerInventory playerInventory;
    private final IronFurnaceBlockEntity blockEntity;

    public IronFurnaceScreen(PlayerInventory inventory, IronFurnaceBlockEntity ironFurnaceBlockEntity) {
        super(new IronFurnaceScreenHandler(inventory, ironFurnaceBlockEntity));
        this.playerInventory = inventory;
        this.blockEntity = ironFurnaceBlockEntity;
        glassguis_setName(ironFurnaceBlockEntity.getName());
    }

    @Override
    protected void drawBackground(float tickDelta) {
        glassguis_renderBackground(this);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_BLEND);

        glassguis_drawSlots(this);

        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/flame_bg.png", 57, 37);
        glassguis_drawImagePercentage(this, "/assets/glasstech/stationapi/textures/gui/flame_fg.png", 56, 36, blockEntity.getFuelTimeDelta(), DrawDirection.UP);

        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/arrow_bg.png", 80, 35);
        glassguis_drawImagePercentage(this, "/assets/glasstech/stationapi/textures/gui/arrow_fg.png", 79, 34, blockEntity.getCookTimeDelta(), DrawDirection.RIGHT);

        GL11.glDisable(GL11.GL_BLEND);
    }
}
