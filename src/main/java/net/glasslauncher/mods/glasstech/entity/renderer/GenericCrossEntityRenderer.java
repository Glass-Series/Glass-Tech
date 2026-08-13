package net.glasslauncher.mods.glasstech.entity.renderer;

import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public abstract class GenericCrossEntityRenderer<T extends Entity> extends EntityRenderer {

    public abstract void bindTexture(T entity);

    @Override
    public void render(Entity arrowEntity, double x, double y, double z, float yaw, float pitch) {
        if (arrowEntity.prevYaw != 0.0F || arrowEntity.prevPitch != 0.0F) {
            //noinspection unchecked
            bindTexture((T) arrowEntity);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x, (float)y, (float)z);
            GL11.glRotatef(arrowEntity.prevYaw + (arrowEntity.yaw - arrowEntity.prevYaw) * pitch - 90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(arrowEntity.prevPitch + (arrowEntity.pitch - arrowEntity.prevPitch) * pitch, 0.0F, 0.0F, 1.0F);
            Tessellator var10 = Tessellator.INSTANCE;
            float var12 = 0.0F;
            float var13 = 0.5F;
            float var14 = (float)(0) / 32.0F;
            float var15 = (float)(5) / 32.0F;
            float var16 = 0.0F;
            float var17 = 0.15625F;
            float var18 = (float)(5) / 32.0F;
            float var19 = (float)(10) / 32.0F;
            float var20 = 0.05625F;
            GL11.glEnable(32826);

            GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(var20, var20, var20);
            GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
            GL11.glNormal3f(var20, 0.0F, 0.0F);
            var10.startQuads();
            var10.vertex(-7.0F, -2.0F, -2.0F, var16, var18);
            var10.vertex(-7.0F, -2.0F, 2.0F, var17, var18);
            var10.vertex(-7.0F, 2.0F, 2.0F, var17, var19);
            var10.vertex(-7.0F, 2.0F, -2.0F, var16, var19);
            var10.draw();
            GL11.glNormal3f(-var20, 0.0F, 0.0F);
            var10.startQuads();
            var10.vertex(-7.0F, 2.0F, -2.0F, var16, var18);
            var10.vertex(-7.0F, 2.0F, 2.0F, var17, var18);
            var10.vertex(-7.0F, -2.0F, 2.0F, var17, var19);
            var10.vertex(-7.0F, -2.0F, -2.0F, var16, var19);
            var10.draw();

            for(int var23 = 0; var23 < 4; ++var23) {
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glNormal3f(0.0F, 0.0F, var20);
                var10.startQuads();
                var10.vertex(-8.0F, -2.0F, 0.0F, var12, var14);
                var10.vertex(8.0F, -2.0F, 0.0F, var13, var14);
                var10.vertex(8.0F, 2.0F, 0.0F, var13, var15);
                var10.vertex(-8.0F, 2.0F, 0.0F, var12, var15);
                var10.draw();
            }

            GL11.glDisable(32826);
            GL11.glPopMatrix();
        }
    }
}
