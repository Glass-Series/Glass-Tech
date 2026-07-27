package net.glasslauncher.mods.glasstech.blocks.renderer;

import net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts.WindSailsBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.lwjgl.opengl.GL11;

// Hey so I'm about to do some *cursed* shit
public class WindSailsBlockEntityRenderer extends BlockEntityRenderer {
    // If you're going to replicate my madness, you need to pass this as the scale or it'll be WAY TOO BIG
    public static float NOTCH_MAGIC_SCALE_NUMBER = 0.0625F;

    public ModelPart[] windSailsParts;

    public WindSailsBlockEntityRenderer() {
        // Partly stolen from btw
        windSailsParts = new ModelPart[9];
        for (int i = 0; i < 4; i++) {
            windSailsParts[i] = new ModelPart(0, 0);
            windSailsParts[i].addCuboid(2.5F, -2F, -2F, 110, 4, 4);
            windSailsParts[i].setPivot(0.0F, 0.0F, 0.0F);
            windSailsParts[i].roll = (3.141593F * (float) (i - 4)) / 2.0F;
        }
        windSailsParts[8] = new ModelPart(0, 0);
        windSailsParts[8].addCuboid(-3, -3, -4, 6, 6, 12);
        windSailsParts[8].setPivot(0.0F, 0.0F, 0.0F);

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
        BlockState state = dispatcher.world.getBlockState(blockEntity.x, blockEntity.y, blockEntity.z);
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        GL11.glRotatef(sailsEntity.wheelDir.getAxis() == Direction.Axis.Z ? sailsEntity.wheelDir.getOpposite().asRotation() : sailsEntity.wheelDir.asRotation(), 0, 1, 0);
        if (sailsEntity.hasAir) {
            GL11.glRotatef(sailsEntity.rot + tickDelta, 0, 0, -1);
        }
        GL11.glColor4d(1, 1, 1, 1);
        GL11.glDisable(GL11.GL_LIGHTING);

        Minecraft.INSTANCE.textureManager.bindTexture(Minecraft.INSTANCE.textureManager.getTextureId("/assets/glasstech/stationapi/textures/block/wind_sails_wood.png"));
        float blockBrightness = sailsEntity.brightness * 0.8f; // x0.8 cause it looks better

        GL11.glColor3f(blockBrightness, blockBrightness, blockBrightness);
        for (int partIndex = 0; partIndex < 4; partIndex++) {
            windSailsParts[partIndex].render(NOTCH_MAGIC_SCALE_NUMBER);
        }
        windSailsParts[8].render(NOTCH_MAGIC_SCALE_NUMBER); // center bit

        Minecraft.INSTANCE.textureManager.bindTexture(Minecraft.INSTANCE.textureManager.getTextureId("/assets/glasstech/stationapi/textures/block/wind_sails.png"));
        float[] color = sailsEntity.color;
        GL11.glColor3f(blockBrightness * color[0], blockBrightness * color[1], blockBrightness * color[2]);
        for (int partIndex = 4; partIndex < 8; partIndex++) {
            windSailsParts[partIndex].render(NOTCH_MAGIC_SCALE_NUMBER);
        }

        GL11.glEnable(GL11.GL_LIGHTING);

        GL11.glPopMatrix();
    }
}
