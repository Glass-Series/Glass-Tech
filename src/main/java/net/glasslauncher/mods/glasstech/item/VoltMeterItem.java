package net.glasslauncher.mods.glasstech.item;

import net.danygames2014.nyalib.energy.EnergyConductor;
import net.danygames2014.nyalib.energy.EnergyConsumer;
import net.danygames2014.nyalib.energy.EnergySource;
import net.danygames2014.nyalib.energy.EnergyStorage;
import net.danygames2014.nyalib.network.Network;
import net.danygames2014.nyalib.network.NetworkManager;
import net.danygames2014.nyalib.network.NetworkType;
import net.danygames2014.nyalib.network.energy.EnergyNetwork;
import net.glasslauncher.mods.alwaysmoreitems.gui.Tooltip;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.GeneratorBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.PowerStorageBlockEntityTemplate;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.hit.HitResult;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Formatting;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VoltMeterItem extends TemplateItem {
    public static final VoltMeterTooltip TOOLTIP = new VoltMeterTooltip();

    public VoltMeterItem(Identifier identifier) {
        super(identifier);
    }

    public static void renderHud() {
        HitResult hitResult = Minecraft.INSTANCE.crosshairTarget;
        if (hitResult == null) {
            return;
        }
        List<Object> tooltip = new ArrayList<>();
        Direction direction = Direction.byId(hitResult.side);
        BlockState state = Minecraft.INSTANCE.world.getBlockState(hitResult.blockX, hitResult.blockY, hitResult.blockZ);
        if (Minecraft.INSTANCE.player.isSneaking()) {
            direction = null;
            tooltip.add(state.getBlock().getTranslatedName() + Formatting.GRAY + " (all faces)");
        }
        else {
            tooltip.add(state.getBlock().getTranslatedName() + Formatting.GRAY + " (" + direction.getName() + " face)");
        }
        BlockEntity blockEntity = Minecraft.INSTANCE.world.getBlockEntity(hitResult.blockX, hitResult.blockY, hitResult.blockZ);

        if (blockEntity instanceof EnergyStorage storage) {
            tooltip.add(((storage.getEnergyStored() < storage.getEnergyCapacity() / 5) ? Formatting.RED : Formatting.AQUA).toString() + storage.getEnergyStored() + Formatting.FORMATTING_CODE_PREFIX + "r/" + Formatting.AQUA + storage.getEnergyCapacity() + " EU");
        }

        if (blockEntity instanceof EnergyConsumer consumer) {
            Tooltip.Divider divider = new Tooltip.Divider(null, 0, 0);
            divider.setColor(Color.GRAY);
            tooltip.add(Tooltip.Line.create(new Tooltip.Text("Input", Tooltip.Alignment.TOP_LEFT, Color.GRAY), divider));
            if (!consumer.canConnectEnergy(direction)) {
                tooltip.add(Formatting.RED + "Can't connect");
            }
            else if (!consumer.canReceiveEnergy(direction)) {
                tooltip.add(Formatting.RED + "Can't recieve");
            }
            else {
                int input = consumer.getMaxInputVoltage(direction);
                VoltageTier voltageTier = VoltageTier.get(input);
                tooltip.add("Max Voltage: " + Formatting.AQUA + input + "eu/t");
                tooltip.add(Tooltip.Line.create(new Tooltip.Text("Voltage Tier: "), new Tooltip.Text(voltageTier.name(), Tooltip.Alignment.TOP_LEFT, new Color(voltageTier.color))));
                if (consumer instanceof MachineBlockEntityTemplate machineBlockEntityTemplate) {
                    tooltip.add("Max Amps: " + machineBlockEntityTemplate.getMaxInputAmps());
                }
                else if (consumer instanceof PowerStorageBlockEntityTemplate powerStorageBlockEntityTemplate) {
                    tooltip.add("Max Amps: " + powerStorageBlockEntityTemplate.getAmps());
                }
            }
        }

        if (blockEntity instanceof EnergySource source) {
            Tooltip.Divider divider = new Tooltip.Divider(null, 0, 0);
            divider.setColor(Color.GRAY);
            tooltip.add(Tooltip.Line.create(new Tooltip.Text("Output", Tooltip.Alignment.TOP_LEFT, Color.GRAY), divider));
            if (!source.canConnectEnergy(direction)) {
                tooltip.add(Formatting.RED + "Can't connect");
            }
            else if (!source.canExtractEnergy(direction)) {
                tooltip.add(Formatting.RED + "Can't extract");
            }
            else {
                int input = source.getMaxOutputVoltage(direction);
                VoltageTier voltageTier = VoltageTier.get(input);
                tooltip.add("Max Voltage: " + Formatting.AQUA + input + "eu/t");
                tooltip.add(Tooltip.Line.create(new Tooltip.Text("Voltage Tier: "), new Tooltip.Text(voltageTier.name(), Tooltip.Alignment.TOP_LEFT, new Color(voltageTier.color))));
                if (source instanceof PowerStorageBlockEntityTemplate powerStorageBlockEntityTemplate) {
                    tooltip.add("Max Amps: " + powerStorageBlockEntityTemplate.getAmps());
                }
                if (source instanceof GeneratorBlockEntityTemplate powerStorageBlockEntityTemplate) {
                    tooltip.add("Max Amps: " + powerStorageBlockEntityTemplate.getMaxOutputAmps());
                    tooltip.add("Generating: " + Formatting.AQUA + powerStorageBlockEntityTemplate.getGeneratingCurrent() + "eu/t");
                }
            }
        }

        if (state.getBlock() instanceof EnergyConductor energyConductor) {
            Tooltip.Divider divider = new Tooltip.Divider(null, 0, 0);
            divider.setColor(Color.GRAY);
            tooltip.add(Tooltip.Line.create(new Tooltip.Text("Energy Flow", Tooltip.Alignment.TOP_LEFT, Color.GRAY), divider));
            Network network = NetworkManager.getAt(Minecraft.INSTANCE.world.dimension, hitResult.blockX, hitResult.blockY, hitResult.blockZ, NetworkType.ENERGY.getIdentifier());
            if (network instanceof EnergyNetwork energyNetwork) {
                EnergyNetwork.EnergyFlowEntry energyFlowEntry = energyNetwork.getFlowEntry(hitResult.blockX, hitResult.blockY, hitResult.blockZ);
                if (energyFlowEntry != null) {
                    tooltip.add((Formatting.AQUA).toString() + energyFlowEntry.energyFlow + Formatting.WHITE + "/" + Formatting.AQUA + energyConductor.getBreakdownPower(Minecraft.INSTANCE.world, energyNetwork.getEntry(hitResult.blockX, hitResult.blockY, hitResult.blockZ)) + "eu/t");
                }
            }
        }

        if (tooltip.size() == 1) {
            return;
        }

        VoltMeterItem.TOOLTIP.setTooltip(tooltip, 0, 0);
        VoltMeterItem.TOOLTIP.setCursor(-12, -5 - (TOOLTIP.getHeight(false) * 2));
        VoltMeterItem.TOOLTIP.render();
        VoltMeterItem.TOOLTIP.clear();
        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
    }

    public static class VoltMeterTooltip extends Tooltip {

        @Override
        public void commonInit() {
            containerScreen = null;
            screenWidth = screenHeight = 0;
            setupTooltip();
        }

        @Override
        public boolean isFlipped() {
            return false;
        }

        @Override
        public void onScreenIsNull() {}

        public void setCursor(int cursorX, int cursorY) {
            this.cursorX = cursorX;
            this.cursorY = cursorY;
        }

    }
}
