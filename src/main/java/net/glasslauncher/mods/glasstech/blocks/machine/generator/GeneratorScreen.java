package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.modificationstation.stationapi.api.util.Formatting;
import org.lwjgl.opengl.GL11;
import java.awt.Rectangle;
import java.util.List;

public class GeneratorScreen extends HandledScreen {
    static final Rectangle euTooltipRect = new Rectangle(80, 10, 64, 18);

    private final PlayerInventory playerInventory;
    private final GeneratorBlockEntity generatorBlockEntity;

    public GeneratorScreen(PlayerInventory playerInventory, GeneratorBlockEntity generatorBlockEntity) {
        super(new GeneratorScreenHandler(playerInventory, generatorBlockEntity));
        this.playerInventory = playerInventory;
        this.generatorBlockEntity = generatorBlockEntity;
        glassguis_setName("Generator");
    }

    @Override
    public void drawForeground() {
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        super.render(mouseX, mouseY, delta);

        glassguis_tooltip(this, List.of(((generatorBlockEntity.getEnergyStored() < generatorBlockEntity.getEnergyCapacity() / 5) ? Formatting.RED : Formatting.AQUA).toString() + generatorBlockEntity.getEnergyStored() + Formatting.FORMATTING_CODE_PREFIX + "r/" + Formatting.AQUA + generatorBlockEntity.getEnergyCapacity() + " EU"), euTooltipRect, mouseX, mouseY);
    }

    @Override
    public void drawBackground(float tickDelta) {
        glassguis_renderBackground(this);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_BLEND);
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/energy_background.png", 80, 10);
        glassguis_drawImagePercentage(this, "/assets/glasstech/stationapi/textures/gui/energy_bar.png", 82, 12, (((float) generatorBlockEntity.getEnergyStored()) / generatorBlockEntity.getEnergyCapacity()));
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/energy_overlay.png", 80, 10);

        glassguis_drawSlots(this);
        GL11.glDisable(GL11.GL_BLEND);

    }
}