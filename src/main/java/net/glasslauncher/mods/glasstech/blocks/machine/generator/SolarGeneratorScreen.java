package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorScreenTemplate;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class SolarGeneratorScreen extends GeneratorScreenTemplate<SolarGeneratorBlockEntity> {

    public SolarGeneratorScreen(PlayerInventory playerInventory, SolarGeneratorBlockEntity solarGeneratorBlockEntity) {
        super(new SolarGeneratorScreenHandler(playerInventory, solarGeneratorBlockEntity), playerInventory, solarGeneratorBlockEntity);
        glassguis_setName(solarGeneratorBlockEntity.getName());
    }

    @Override
    public void drawMachineForeground(float tickDelta, int mouseX, int mouseY) {
        drawGauge(mouseX, mouseY);
        int offsetX = ((width - backgroundWidth) / 2);
        int offsetY = ((height - backgroundHeight) / 2);

        fill(offsetX + 82, offsetY + 50, offsetX + 92, offsetY + 60, glassguis_getSlotBackground());
        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 1); // Removes the black from the image. Dunno why it's not just transparent.
        GL11.glColor3f(0.8f, 0.8f, 0.8f);
        if (blockEntity.hasSky) {
            glassguis_drawImage(this, blockEntity.hasSun ? "/terrain/sun.png" : "/terrain/moon.png", 71, 39);
        }
        GL11.glDisable(GL11.GL_BLEND);
    }
}