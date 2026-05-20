package net.glasslauncher.mods.glasstech.blocks.renderer;

import com.google.common.primitives.Ints;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts.WindSailsBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import org.lwjgl.opengl.GL11;

import java.nio.ByteOrder;

// Hey so I'm about to do some *cursed* shit
public class WindSailsBlockEntityRenderer extends BlockEntityRenderer {
    // If you're going to replicate my madness, you need to pass this as the scale or it'll be WAY TOO BIG
    public static float NOTCH_MAGIC_SCALE_NUMBER = 0.0625F;

    public ModelPart[] windSailsParts;

    public WindSailsBlockEntityRenderer() {
        // Partly stolen from btw
        windSailsParts = new ModelPart[8];
        for (int i = 0; i < 4; i++) {
            windSailsParts[i] = new ModelPart(0, 0);
            windSailsParts[i].addCuboid(2.5F, -2F, -2F, 110, 4, 4);
            windSailsParts[i].setPivot(0.0F, 0.0F, 0.0F);
            windSailsParts[i].roll = (3.141593F * (float) (i - 4)) / 2.0F;
        }

        for (int j = 4; j < 8; j++) {
            windSailsParts[j] = new ModelPart(0, 0);
            windSailsParts[j].addCuboid(7F, 1.75F, -1.0F, 105, 24, 1);
            windSailsParts[j].setPivot(0.0F, 0.0F, 0.0F);
            windSailsParts[j].pitch = 0.2617994F;
            windSailsParts[j].roll = (3.141593F * (float) j) / 2.0F;
        }
    }

    @Override
    public void render(BlockEntity blockEntity, double x, double y, double z, float tickDelta) {
        WindSailsBlockEntity sailsEntity = (WindSailsBlockEntity) blockEntity;
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        if (sailsEntity.hasAir) {
            GL11.glRotatef(sailsEntity.rot + tickDelta, sailsEntity.wheelDir.getOffsetX(), 0, sailsEntity.wheelDir.getOffsetZ());
        }
        GL11.glColor4d(1, 1, 1, 1);
        GL11.glDisable(GL11.GL_LIGHTING);

        Minecraft.INSTANCE.textureManager.bindTexture(Minecraft.INSTANCE.textureManager.getTextureId("/assets/glasstech/stationapi/textures/block/wind_sails_wood.png"));
        float blockBrightness = sailsEntity.brightness * 0.8f; // x0.8 cause it looks better

        for (int partIndex = 0; partIndex < 4; partIndex++) {
            GL11.glColor3f(blockBrightness, blockBrightness, blockBrightness);
            windSailsParts[partIndex].render(NOTCH_MAGIC_SCALE_NUMBER); // Scale isn't exact, but I don't care
        }

        Minecraft.INSTANCE.textureManager.bindTexture(Minecraft.INSTANCE.textureManager.getTextureId("/assets/glasstech/stationapi/textures/block/wind_sails.png"));
        for (int partIndex = 4; partIndex < 8; partIndex++) {
            int[] color = sailsEntity.color;
            GL11.glColor3f(blockBrightness * color[0], blockBrightness * color[1], blockBrightness * color[2]);
            windSailsParts[partIndex].render(NOTCH_MAGIC_SCALE_NUMBER);
        }

        GL11.glEnable(GL11.GL_LIGHTING);

        GL11.glPopMatrix();
    }
}
