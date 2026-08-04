package net.glasslauncher.mods.glasstech.events;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.glasslauncher.mods.alwaysmoreitems.api.event.AMIItemTooltipEvent;
import net.glasslauncher.mods.alwaysmoreitems.gui.Tooltip;
import net.glasslauncher.mods.glasstech.GTItemOverlay;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.WireMaterial;
import net.glasslauncher.mods.glasstech.blocks.GTTooltipInfo;
import net.glasslauncher.mods.glasstech.blocks.TemplateCableBlock;
import net.glasslauncher.mods.glasstech.blocks.machine.EnergySourceConsumerBlockTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.glasslauncher.mods.glasstech.item.PainterItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.BlockItem;
import net.modificationstation.stationapi.api.client.event.render.item.ItemOverlayRenderEvent;
import net.modificationstation.stationapi.api.util.Formatting;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GTTooltip {
    private static final Object2IntMap<BlockWithEntity> MACHINE_TO_VOLTAGE_CACHE = new Object2IntOpenHashMap<>();
    private static final Object2IntMap<BlockWithEntity> MACHINE_TO_AMPS_CACHE = new Object2IntOpenHashMap<>();

    private static final ItemRenderer ITEM_RENDERER = new ItemRenderer();

    @EventListener
    public static void addToTooltips(AMIItemTooltipEvent event) {
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
        else if (event.itemStack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof TemplateCableBlock cableBlock) {
            WireMaterial wireMaterial = cableBlock.wireMaterial;
            event.tooltip.add(Tooltip.Line.create(new Tooltip.Text("Voltage Tier: "), new Tooltip.Text(wireMaterial.voltageTier.name(), Tooltip.Alignment.TOP_LEFT, new Color(wireMaterial.voltageTier.color))));
            event.tooltip.add("Max Amperage: " + wireMaterial.amperage);
            event.tooltip.add("Power Loss: " + wireMaterial.lossPerBlock + "eu/b");
        }
        else if (event.itemStack.getItem() instanceof PainterItem) {
            String color = event.itemStack.getStationNbt().getString("color");
            if (color.isEmpty()) {
                return;
            }
            event.tooltip.add(color);
        }
        else if (event.itemStack.getItem() instanceof GTItemOverlay gtItemOverlay && gtItemOverlay.addTooltip()) {
            event.tooltip.add(((gtItemOverlay.getEnergyStored(event.itemStack) < gtItemOverlay.getEnergyCapacity(event.itemStack) / 5) ? Formatting.RED : Formatting.AQUA).toString() + gtItemOverlay.getEnergyStored(event.itemStack) + Formatting.FORMATTING_CODE_PREFIX + "r/" + Formatting.AQUA + gtItemOverlay.getEnergyCapacity(event.itemStack) + " EU");
        }
    }

    @EventListener
    public void overlay(ItemOverlayRenderEvent event) {
        if (event.itemStack != null && event.itemStack.getItem() instanceof GTItemOverlay stackToOverlay) {
            double capacity = stackToOverlay.getEnergyCapacity(event.itemStack);
            double stored = stackToOverlay.getEnergyStored(event.itemStack);
            int barLength = (int) Math.round(((stored / capacity) * 13));
            int colorOffset = 255 - (int) Math.round(((stored / capacity) * 225));
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glDisable(3553);
            Tessellator var8 = Tessellator.INSTANCE;
            int barColour = stackToOverlay.getColor(colorOffset);
            int backgroundColor = (255 - colorOffset) / 4 << 16 | 16128;
            int barOffset = event.itemStack.isDamaged() ? 2 : 0;
            ITEM_RENDERER.fillRect(var8, event.itemX + 2, event.itemY + 13 - barOffset, 13, 2, 0);
            ITEM_RENDERER.fillRect(var8, event.itemX + 2, event.itemY + 13 - barOffset, 12, 1, backgroundColor);
            ITEM_RENDERER.fillRect(var8, event.itemX + 2, event.itemY + 13 - barOffset, barLength, 1, barColour);
            GL11.glEnable(3553);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
