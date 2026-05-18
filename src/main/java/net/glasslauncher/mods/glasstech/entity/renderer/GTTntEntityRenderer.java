package net.glasslauncher.mods.glasstech.entity.renderer;

import net.glasslauncher.mods.glasstech.entity.GTTntEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class GTTntEntityRenderer extends EntityRenderer {
    protected final BlockRenderManager blockRenderManager = new BlockRenderManager();
    protected final Block block;

    public GTTntEntityRenderer(Block block) {
        this.block = block;
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(Entity entity, double x, double y, double z, float yaw, float pitch) {
        GTTntEntity tntEntity = (GTTntEntity) entity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        if ((float)tntEntity.fuse - yaw + 1.0F < 10.0F) {
            float var10 = 1.0F - ((float)tntEntity.fuse - yaw + 1.0F) / 10.0F;
            if (var10 < 0.0F) {
                var10 = 0.0F;
            }

            if (var10 > 1.0F) {
                var10 = 1.0F;
            }

            var10 *= var10;
            var10 *= var10;
            float var11 = 1.0F + var10 * 0.3F;
            GL11.glScalef(var11, var11, var11);
        }

        float var14 = (1.0F - ((float)tntEntity.fuse - yaw + 1.0F) / 100.0F) * 0.8F;
        this.bindTexture("/terrain.png");
        this.blockRenderManager.render(block, 0, tntEntity.getBrightnessAtEyes(yaw));
        if (tntEntity.fuse / 5 % 2 == 0) {
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 772);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var14);
            this.blockRenderManager.render(block, 0, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
        }

        GL11.glPopMatrix();
    }
}
