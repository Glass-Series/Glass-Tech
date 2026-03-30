package net.glasslauncher.mods.glasstech;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.resource.language.TranslationStorage;
import net.modificationstation.stationapi.api.util.Formatting;

import java.util.ArrayList;
import java.util.List;

/**
 * Voltage tiers from ULV (24V) to IV (22kV+), with their colors and names.
 */
public class VoltageTier {
    public static final List<VoltageTier> VOLTAGE_TIERS = new ArrayList<>();

    public static final VoltageTier ULV = new VoltageTier("voltage.ulv.name", 1, 24, Formatting.GRAY, 0x555555);
    public static final VoltageTier LV = new VoltageTier("voltage.lv.name", 25, 60, Formatting.RED, 0xFF5555);
    public static final VoltageTier SV = new VoltageTier("voltage.sv.name", 61, 160, Formatting.GOLD, 0xFFAA00);
    public static final VoltageTier MV = new VoltageTier("voltage.mv.name", 161, 280, Formatting.YELLOW, 0xFFFF55);
    public static final VoltageTier HV = new VoltageTier("voltage.hv.name", 281, 480, Formatting.GREEN, 0x55FF55);
    public static final VoltageTier VHV = new VoltageTier("voltage.vhv.name", 481, 1500, Formatting.AQUA, 0x8C0000);
    public static final VoltageTier EV = new VoltageTier("voltage.ev.name", 1501, 22000, Formatting.DARK_PURPLE, 0x8C0000);
    public static final VoltageTier IV = new VoltageTier("voltage.iv.name", 22001, Integer.MAX_VALUE, Formatting.LIGHT_PURPLE, 0xFF55FF);

    public final String translationKey;
    public final int minVoltage;
    public final int maxVoltage;
    public final String textColor;
    public final int color;

    // cope mine diver
    private static final Int2ObjectOpenHashMap<VoltageTier> cache = new Int2ObjectOpenHashMap<>();

    VoltageTier(String translationKey, int minVoltage, int maxVoltage, String textColor, int color) {
        this.translationKey = translationKey;
        this.minVoltage = minVoltage;
        this.maxVoltage = maxVoltage;
        this.textColor = textColor;
        this.color = color;
        VOLTAGE_TIERS.add(this);
    }

    VoltageTier(String translationKey, int minVoltage, int maxVoltage, Formatting textColor, int color) {
        this(translationKey, minVoltage, maxVoltage, textColor.toString(), color);
    }

    public String getName() {
        return TranslationStorage.getInstance().getClientTranslation(translationKey);
    }

    public static VoltageTier get(int voltage) {
        return cache.computeIfAbsent(voltage, VoltageTier::internalGet);
    }

    private static VoltageTier internalGet(int voltage) {
        for (VoltageTier tier : VoltageTier.VOLTAGE_TIERS) {
            if (voltage >= tier.minVoltage && voltage <= tier.maxVoltage) {
                return tier;
            }
        }
        return null;
    }
}