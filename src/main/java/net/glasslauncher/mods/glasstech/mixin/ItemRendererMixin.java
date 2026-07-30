package net.glasslauncher.mods.glasstech.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.glasslauncher.mods.glasstech.GlassTechItemInWorldRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.entity.ItemEntity;
import net.modificationstation.stationapi.impl.client.arsenic.renderer.render.ArsenicItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArsenicItemRenderer.class)
public abstract class ItemRendererMixin {

    @WrapOperation(method = "renderVanilla", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;render(Lnet/minecraft/block/Block;IF)V"))
    private void ree(BlockRenderManager instance, Block metadata, int brightness, float v, Operation<Void> original, @Local(argsOnly = true) ItemEntity item) {
        if (item.stack.getItem() instanceof GlassTechItemInWorldRenderer renderer) {
            renderer.renderItemOnGround(v);
            return;
        }
        original.call(instance, metadata, brightness, v);
    }
}
