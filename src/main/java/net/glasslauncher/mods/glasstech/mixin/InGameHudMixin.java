package net.glasslauncher.mods.glasstech.mixin;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.glasslauncher.mods.glasstech.item.VoltMeterItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V", shift = At.Shift.AFTER))
    private void voltMeter(float tickDelta, boolean screenOpen, int mouseX, int mouseY, CallbackInfo ci) {
        if (Minecraft.INSTANCE.player.getHand() != null && Minecraft.INSTANCE.player.getHand().getItem().id == GlassTechItems.voltageMeter.id) {
            VoltMeterItem.renderHud();
        }
    }
}
