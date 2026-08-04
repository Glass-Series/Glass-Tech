package net.glasslauncher.mods.glasstech.events;

import net.glasslauncher.mods.glasstech.packet.C2SHoldingAbilityPacket;
import net.glasslauncher.mods.glasstech.packet.C2SJetpackModePacket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import org.lwjgl.input.Keyboard;

public class GTKeybinds {
    public static final KeyBinding ARMOR_ABILITY = new KeyBinding("key.glasstech.armor_ability", Keyboard.KEY_LCONTROL);
    public static final KeyBinding HOVER_MODE = new KeyBinding("key.glasstech.hover_mode", Keyboard.KEY_H);

    @EventListener
    public void registerKeys(KeyBindingRegisterEvent event) {
        event.register(ARMOR_ABILITY);
        event.register(HOVER_MODE);
    }

    @EventListener
    public void onKey(KeyStateChangedEvent event) {
        if (event.environment != KeyStateChangedEvent.Environment.IN_GAME) {
            return;
        }

        if (Keyboard.getEventKey() == ARMOR_ABILITY.code) {
            PacketHelper.send(new C2SHoldingAbilityPacket(Keyboard.getEventKeyState()));
            Minecraft.INSTANCE.player.glasstech$setHoldingAbilityKey(Keyboard.getEventKeyState());
        }
        else if (Keyboard.getEventKey() == HOVER_MODE.code && Keyboard.getEventKeyState()) {
            PacketHelper.send(new C2SJetpackModePacket());
            PlayerEntity player = Minecraft.INSTANCE.player;
            player.glasstech$setHovering(!player.glasstech$isHovering());
            player.sendMessage("Hovering: " + player.glasstech$isHovering());
        }
    }
}
