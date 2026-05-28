package net.glasslauncher.mods.glasstech.events;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.glasslauncher.mods.alwaysmoreitems.api.event.AMITooltipEvent;
import net.glasslauncher.mods.alwaysmoreitems.gui.Tooltip;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.GTTooltipInfo;
import net.glasslauncher.mods.glasstech.blocks.machine.EnergySourceConsumerBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.EnergySourceConsumerBlockTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.glasslauncher.mods.glasstech.item.PainterItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.modificationstation.stationapi.api.client.event.gui.screen.container.TooltipBuildEvent;

import java.awt.*;

public class GTTooltip {
    private static final Object2IntMap<BlockWithEntity> MACHINE_TO_VOLTAGE_CACHE = new Object2IntOpenHashMap<>();
    private static final Object2IntMap<BlockWithEntity> MACHINE_TO_AMPS_CACHE = new Object2IntOpenHashMap<>();

    @EventListener
    public static void addToTooltips(AMITooltipEvent event) {
        if (event.itemStack == null) {
            return;
        }
        if (event.itemStack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof BlockWithEntity machineBlockTemplate && (blockItem.getBlock() instanceof MachineBlockTemplate || blockItem.getBlock() instanceof EnergySourceConsumerBlockTemplate)) {
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
                VoltageTier voltageTier = VoltageTier.get(voltage);
                event.tooltip.add(Tooltip.Line.create(new Tooltip.Text("Voltage Tier: "), new Tooltip.Text(voltageTier.name(), Tooltip.Alignment.TOP_LEFT, new Color(voltageTier.color))));
                event.tooltip.add("Max Amperage: " + amperage);
            }
        }
        else if (event.itemStack.getItem() instanceof PainterItem) {
            String color = event.itemStack.getStationNbt().getString("color");
            if (color.isEmpty()) {
                return;
            }
            event.tooltip.add(color);
        }
    }
}
