package net.glasslauncher.mods.glasstech.mixin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityRenderDispatcher.class)
public class WorldRendererMixin {

//    @Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;render(Lnet/minecraft/entity/Entity;F)V"))
//    private void e(Vec3d culler, Culler tickDelta, float par3, CallbackInfo ci, @Local Entity entity) {
//        System.out.println(entity);
//    }
}
