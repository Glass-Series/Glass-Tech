package net.glasslauncher.mods.glasstech.blocks.renderer;

import com.google.common.primitives.Ints;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts.WaterWheelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.util.math.BlockPos;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.render.model.BakedModel;
import net.modificationstation.stationapi.api.client.render.model.BakedQuad;
import net.modificationstation.stationapi.api.util.Util;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.lwjgl.opengl.GL11;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Random;

public class WaterWheelBlockEntityRenderer extends BlockEntityRenderer {

    private static final Direction[] DIRECTIONS = Util.make(() -> {
        Direction[] originalValues = Direction.values();
        return Arrays.copyOf(originalValues, originalValues.length + 1);
    });

    private static final Random RANDOM = new Random();

    @Override
    public void render(BlockEntity blockEntity, double x, double y, double z, float tickDelta) {
        WaterWheelBlockEntity wheelEntity = (WaterWheelBlockEntity) blockEntity;
        BlockPos pos = new BlockPos(blockEntity.x, blockEntity.y, blockEntity.z);
        BlockState state = dispatcher.world.getBlockState(pos);
        BakedModel bakedModel = StationRenderAPI.getBakedModelManager().getBlockModels().getModel(state);
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        if (wheelEntity.waterFlow != null) {
            GL11.glRotatef((wheelEntity.lastRot == wheelEntity.rot ? wheelEntity.rot : (wheelEntity.rot + tickDelta)) * 4, (float) wheelEntity.waterFlow.x, 0, (float) wheelEntity.waterFlow.z);
        }
        GL11.glTranslated(-0.5, -0.5, -0.5);
        GL11.glColor4d(1, 1, 1, 1);
        GL11.glDisable(GL11.GL_LIGHTING);

        Minecraft.INSTANCE.textureManager.bindTexture(Minecraft.INSTANCE.textureManager.getTextureId("/terrain.png")); // I'd bind it properly but I've no patience left for reverse engineering the atlas system
        float blockBrightness = wheelEntity.brightness * 0.8f; // x0.8 cause it looks better
        int modelBrightness = colorF2I(blockBrightness, blockBrightness, blockBrightness);
        Tessellator.INSTANCE.startQuads();
        for (Direction direction : DIRECTIONS) {
            for (BakedQuad quad : bakedModel.getQuads(state, direction, RANDOM)) {
                Tessellator.INSTANCE.quad(quad, 0, 0, 0, modelBrightness, modelBrightness, modelBrightness, modelBrightness, 0, 0, 0, false);
            }
        }
        Tessellator.INSTANCE.draw();
        GL11.glEnable(GL11.GL_LIGHTING);

        GL11.glPopMatrix();
    }

    private int colorF2I(float r, float g, float b) {
        final int ri = colorChannelF2I(r), gi = colorChannelF2I(g), bi = colorChannelF2I(b);
        return ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN ?
                (0xFF << 24) | (bi << 16) | (gi << 8) | ri :
                (ri << 24) | (gi << 16) | (bi << 8) | 0xFF;
    }

    private int colorChannelF2I(float colorChannel) {
        return Ints.constrainToRange((int) (colorChannel * 255), 0, 255);
    }
}
