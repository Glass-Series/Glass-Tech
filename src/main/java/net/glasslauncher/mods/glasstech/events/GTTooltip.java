package net.glasslauncher.mods.glasstech.events;

import com.github.benmanes.caffeine.cache.Cache;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.BlockItem;
import net.modificationstation.stationapi.api.client.event.gui.screen.container.TooltipBuildEvent;

public class GTTooltip {
    private static final Object2IntMap<MachineBlockTemplate> MACHINE_TO_VOLTAGE_CACHE = new Object2IntOpenHashMap<>();
    private static final Object2IntMap<MachineBlockTemplate> MACHINE_TO_AMPS_CACHE = new Object2IntOpenHashMap<>();

    @EventListener
    public static void addToTooltips(TooltipBuildEvent event) {
        if (event.itemStack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof MachineBlockTemplate machineBlockTemplate) {
            int voltage = MACHINE_TO_VOLTAGE_CACHE.computeIfAbsent(machineBlockTemplate, k -> {
                MachineBlockEntityTemplate fakeBlockEntity = machineBlockTemplate.createBlockEntity();
                MACHINE_TO_AMPS_CACHE.put(machineBlockTemplate, fakeBlockEntity.getMaxInputAmps());
                return fakeBlockEntity.getMaxInputVoltage(null);
            });
            int amperage = MACHINE_TO_AMPS_CACHE.getInt(machineBlockTemplate);
            event.add("Max Voltage: " + voltage);
            event.add("Max Amperage: " + amperage);
        }
    }
}
