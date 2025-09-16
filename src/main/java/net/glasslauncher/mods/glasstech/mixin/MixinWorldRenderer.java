package net.glasslauncher.mods.glasstech.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {

    @Shadow
    private TextureManager textureManager;

    @Shadow private World world;

    @Shadow private Minecraft client;

    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void endSky(float f, CallbackInfo ci) {
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        //RenderHelper.disableLighting();
        GL11.glDepthMask(false);
        this.textureManager.bindTexture(this.textureManager.getTextureId("/assets/sltest/textures/skybox/sky6.png"));
        Tessellator var21 = Tessellator.INSTANCE;

        GL11.glPushMatrix();

//                this.textureManager.bindTexture(this.textureManager.getTextureId("/assets/sltest/textures/skybox/sky2.png"));
//                this.textureManager.bindTexture(this.textureManager.getTextureId(Atlases.getGuiItems().spritesheet));
//                ((CustomAtlasProvider) ItemListener.testItem).getAtlas().of(ItemListener.testItem.getTexturePosition(0)).bindAtlas();
//                Atlases.getStationJsonModels().bindAtlas();
        this.textureManager.bindTexture(StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).getGlId());
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);

        var21.startQuads();

        float light = world.dimension.lightLevelToLuminance[15 - world.ambientDarkness];
        var21.color(light, light, light);

        var21.vertex(-100.0D, -100.0D, -100.0D, 1.0D, 0.0D);
        var21.vertex(-100.0D, -100.0D, 100.0D, 0.0D, 0.0D);
        var21.vertex(100.0D, -100.0D, 100.0D, 0.0D, 1.0D);
        var21.vertex(100.0D, -100.0D, -100.0D, 1.0D, 1.0D);
        var21.draw();
        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_FOG);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDepthMask(true);
        ci.cancel();
    }
}
