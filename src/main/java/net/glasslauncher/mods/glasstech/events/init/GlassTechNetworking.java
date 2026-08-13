package net.glasslauncher.mods.glasstech.events.init;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.packet.C2SHoldingAbilityPacket;
import net.glasslauncher.mods.glasstech.packet.C2SJetpackModePacket;
import net.glasslauncher.mods.glasstech.packet.C2SToolModePacket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;

public class GlassTechNetworking {

    @EventListener
    public static void network(PacketRegisterEvent event) {
        event.register(GlassTech.NAMESPACE.id("holding_ability"), C2SHoldingAbilityPacket.TYPE);
        event.register(GlassTech.NAMESPACE.id("jetpack_mode"), C2SJetpackModePacket.TYPE);
        event.register(GlassTech.NAMESPACE.id("tool_mode"), C2SToolModePacket.TYPE);
    }
}
