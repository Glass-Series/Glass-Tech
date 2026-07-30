package net.glasslauncher.mods.glasstech.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.glasslauncher.mods.glasstech.GlassTechItemInWorldRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.impl.client.arsenic.renderer.render.ArsenicOverlayRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArsenicOverlayRenderer.class)
public class ArsenicOverlayRendererMixin {

    @WrapOperation(method = "renderVanilla(FFLnet/minecraft/entity/player/ClientPlayerEntity;Lnet/minecraft/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/modificationstation/stationapi/impl/client/arsenic/renderer/render/ArsenicOverlayRenderer;renderItem3D(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)V"))
    private void e(ArsenicOverlayRenderer instance, LivingEntity entity, ItemStack item, Operation<Void> original) {
        if (item.getItem() instanceof GlassTechItemInWorldRenderer renderer) {
            renderer.renderItemInHand(entity.getBrightnessAtEyes(0));
            return;
        }
        original.call(instance, entity, item);
    }

    @WrapOperation(method = "renderVanilla(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/modificationstation/stationapi/api/client/texture/SpriteAtlasTexture;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;render(Lnet/minecraft/block/Block;IF)V"))
    private void e1(BlockRenderManager instance, Block metadata, int brightness, float v, Operation<Void> original, @Local(argsOnly = true) ItemStack item) {
        if (item.getItem() instanceof GlassTechItemInWorldRenderer renderer) {
            renderer.renderItemInHand(v);
            return;
        }
        original.call(instance, metadata, brightness, v);
    }
}
