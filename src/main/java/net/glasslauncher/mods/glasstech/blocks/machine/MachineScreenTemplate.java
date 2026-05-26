package net.glasslauncher.mods.glasstech.blocks.machine;

import net.danygames2014.nyalib.fluid.FluidStack;
import net.danygames2014.nyalib.fluid.block.FluidHandler;
import net.glasslauncher.mods.alwaysmoreitems.gui.Tooltip;
import net.glasslauncher.mods.glassguis.DrawDirection;
import net.glasslauncher.mods.glassguis.Rectangle;
import net.glasslauncher.mods.glassguis.events.init.GlassGUIs;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.Tessellator;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.util.Formatting;
import org.lwjgl.opengl.GL11;

import java.util.List;

import static net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout.GAUGE_LOCATION;

public abstract class MachineScreenTemplate<T extends MachineBlockEntityTemplate> extends HandledScreen {
    protected final PlayerInventory playerInventory;
    protected final T blockEntity;

    public MachineScreenTemplate(ScreenHandler screenHandler, PlayerInventory playerInventory, T blockEntity) {
        super(screenHandler);
        this.playerInventory = playerInventory;
        this.blockEntity = blockEntity;
        glassguis_setName(blockEntity.getName());
    }

    @Override
    public void drawForeground() {
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        super.render(mouseX, mouseY, delta);

        drawMachineForeground(delta, mouseX, mouseY);
    }

    @Override
    public void drawBackground(float tickDelta) {
        glassguis_renderBackground(this);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_BLEND);

        glassguis_drawSlots(this);

        GL11.glDisable(GL11.GL_BLEND);

    }

    public abstract void drawMachineForeground(float tickDelta, int mouseX, int mouseY);

    public void drawGauge(int mouseX, int mouseY) {
        drawGauge(GAUGE_LOCATION[0], GAUGE_LOCATION[1], mouseX, mouseY);
    }

    public void drawGauge(int x, int y, int mouseX, int mouseY) {
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/energy_background.png", x, y);
        glassguis_drawImagePercentage(this, "/assets/glasstech/stationapi/textures/gui/energy_bar.png", x + 2, y + 2, (((float) blockEntity.getEnergyStored()) / blockEntity.getEnergyCapacity()), DrawDirection.UP);
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/energy_overlay.png", x, y);
        int[] size = GlassGUIs.IMAGE_SIZE_CACHE.getIfPresent("/assets/glasstech/stationapi/textures/gui/energy_background.png");

        x += ((width - backgroundWidth) / 2);
        y += ((height - backgroundHeight) / 2);
        if (size != null && Rectangle.of(x, y, size[0], size[1]).contains(mouseX, mouseY)) {
            Tooltip.INSTANCE.setTooltip(List.of(((blockEntity.getEnergyStored() < blockEntity.getEnergyCapacity() / 5) ? Formatting.RED : Formatting.AQUA).toString() + blockEntity.getEnergyStored() + Formatting.FORMATTING_CODE_PREFIX + "r/" + Formatting.AQUA + blockEntity.getEnergyCapacity() + " EU"), mouseX, mouseY);
        }
    }

    public void drawArrow(int x, int y, float progress) {
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/arrow_bg.png", x + 1, y + 1);
        glassguis_drawImagePercentage(this, "/assets/glasstech/stationapi/textures/gui/arrow_fg.png", x, y, progress, DrawDirection.RIGHT);
    }

    public void drawFluidGauge(int mouseX, int mouseY) {
        drawFluidGauge(GAUGE_LOCATION[0] + 16, GAUGE_LOCATION[1], 0, mouseX, mouseY);
    }

    public void drawFluidGauge(int x, int y, int slot, int mouseX, int mouseY) {
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/energy_background.png", x, y);

        FluidHandler fluidHandler = (FluidHandler) blockEntity;
        FluidStack stack = fluidHandler.getFluid(slot, null);

        if (stack != null && stack.fluid != null) {
            int offsetX = ((width - backgroundWidth) / 2) + x;
            int offsetY = ((height - backgroundHeight) / 2) + y;
            int gaugeHeight = (int) (60 * ((float) stack.amount / fluidHandler.getFluidCapacity(slot, null)));
            drawFluid(stack, stack.amount, offsetX + 2, offsetY + 2 + (60 - gaugeHeight), 12, gaugeHeight, 0);

            int[] size = GlassGUIs.IMAGE_SIZE_CACHE.getIfPresent("/assets/glasstech/stationapi/textures/gui/energy_background.png");
            if (size != null && Rectangle.of(offsetX, offsetY, size[0], size[1]).contains(mouseX, mouseY)) {
                Tooltip.INSTANCE.setTooltip(List.of(stack.fluid.getTranslatedName() + ": " + stack.amount + "mB"), mouseX, mouseY);
            }
        }

        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/energy_overlay.png", x, y);
    }

    public static void drawFluid(FluidStack fluidStack, int level, int x, int y, int width, int height, float zOffset){
        if(fluidStack == null || fluidStack.fluid == null) {
            return;
        }
        int textureId = fluidStack.fluid.getStillBlock().getTexture(0);
        Atlas.Sprite sprite = Atlases.getTerrain().getTexture(textureId);
        StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).bindTexture();

        int color = fluidStack.fluid.getColor();

        float red = (float) (color >> 16 & 255) / 255.0F;
        float green = (float) (color >> 8 & 255) / 255.0F;
        float blue = (float) (color & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, 1.0F);

        int fullX = width / 16;
        int fullY = height / 16;
        int lastX = width - fullX * 16;
        int lastY = height - fullY * 16;
        int fullLvl = (height - level) / 16;
        int lastLvl = (height - level) - fullLvl * 16;
        for(int i = 0; i < fullX; i++) {
            for(int j = 0; j < fullY; j++) {
                if(j >= fullLvl) {
                    drawCutSprite(sprite, x + i * 16, y + j * 16, 16, 16, j == fullLvl ? lastLvl : 0, zOffset);
                }
            }
        }
        for(int i = 0; i < fullX; i++) {
            drawCutSprite(sprite, x + i * 16, y + fullY * 16, 16, lastY, fullLvl == fullY ? lastLvl : 0, zOffset);
        }
        for(int i = 0; i < fullY; i++) {
            if(i >= fullLvl) {
                drawCutSprite(sprite, x + fullX * 16, y + i * 16, lastX, 16, i == fullLvl ? lastLvl : 0, zOffset);
            }
        }
        drawCutSprite(sprite, x + fullX * 16, y + fullY * 16, lastX, lastY, fullLvl == fullY ? lastLvl : 0, zOffset);
        GL11.glColor4f(1, 1, 1, 1);
    }

    public static void drawCutSprite(Atlas.Sprite sprite, int x, int y, int width, int height, int cut, float zOffset){
        Tessellator tess = Tessellator.INSTANCE;
        tess.startQuads();
        tess.vertex(x, y + height, zOffset, sprite.getStartU(), getInterpolatedV(sprite, height));
        tess.vertex(x + width, y + height, zOffset, getInterpolatedU(sprite, width), getInterpolatedV(sprite, height));
        tess.vertex(x + width, y + cut, zOffset, getInterpolatedU(sprite, width), getInterpolatedV(sprite, cut));
        tess.vertex(x, y + cut, zOffset, sprite.getStartU(), getInterpolatedV(sprite, cut));
        tess.draw();
    }

    public static double getInterpolatedU(Atlas.Sprite sprite, double delta){
        double var3 = sprite.getEndU() - sprite.getStartU();
        return sprite.getStartU() + var3 * (float)delta / 16.0F;
    }

    public static double getInterpolatedV(Atlas.Sprite sprite, double delta){
        double var3 = sprite.getEndV() - sprite.getStartV();
        return sprite.getStartV() + var3 * ((float)delta / 16.0F);
    }
}