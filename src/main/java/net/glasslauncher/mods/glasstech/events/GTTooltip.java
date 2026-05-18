package net.glasslauncher.mods.glasstech.events;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.glasslauncher.mods.glasstech.blocks.GTTooltipInfo;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.glasslauncher.mods.glasstech.item.PainterItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.modificationstation.stationapi.api.client.event.gui.screen.container.TooltipBuildEvent;

public class GTTooltip {
    private static final Object2IntMap<MachineBlockTemplate> MACHINE_TO_VOLTAGE_CACHE = new Object2IntOpenHashMap<>();
    private static final Object2IntMap<MachineBlockTemplate> MACHINE_TO_AMPS_CACHE = new Object2IntOpenHashMap<>();

    @EventListener
    public static void addToTooltips(TooltipBuildEvent event) {
        if (event.itemStack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof MachineBlockTemplate machineBlockTemplate) {
            int voltage = MACHINE_TO_VOLTAGE_CACHE.computeIfAbsent(machineBlockTemplate, k -> {
                BlockEntity fakeBlockEntity = machineBlockTemplate.createBlockEntity();
                if (fakeBlockEntity instanceof GTTooltipInfo gtTooltipInfo) {
                    MACHINE_TO_AMPS_CACHE.put(machineBlockTemplate, gtTooltipInfo.getMaxInputAmps());
                    return gtTooltipInfo.getMaxInputVoltage(null);
                }
                MACHINE_TO_AMPS_CACHE.put(machineBlockTemplate, 0);
                return 0;
            });
            int amperage = MACHINE_TO_AMPS_CACHE.getInt(machineBlockTemplate);
            if (amperage != 0 && voltage != 0) {
                event.add("Max Voltage: " + voltage);
                event.add("Max Amperage: " + amperage);
            }
        }
        else if (event.itemStack.getItem() instanceof PainterItem) {
            String color = event.itemStack.getStationNbt().getString("color");
            if (color.isEmpty()) {
                return;
            }
            event.add(color);
        }
    }
}
