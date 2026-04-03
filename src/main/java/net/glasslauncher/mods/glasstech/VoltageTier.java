package net.glasslauncher.mods.glasstech;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.resource.language.TranslationStorage;

/**
 * Voltage tiers from ULV (24V) to IV (22kV+), with their colors and names.
 */
public enum VoltageTier {
    ULV("voltage.ulv.name", 8, 0xFF5555),
    LV("voltage.lv.name", 32, 0x778899),
    MV("voltage.mv.name", 128, 0xFF8C00),
    HV("voltage.hv.name", 512, 0xFFD700),
    EV("voltage.ev.name", 2048, 0x696969),
    IV("voltage.iv.name", 8192, 0x4169E1),
    LuV("voltage.luv.name", 32768, 0xFF00FF),
    ZPM("voltage.zpm.name", 131072, 0x00CED1),
    UV("voltage.uv.name", 524288, 0x008000),
    UHV("voltage.uhv.name", 2097152, 0x8B0000),
    UEV("voltage.uev.name", 8388608, 0x8B008B),
    UIV("voltage.uiv.name", 33554432, 0x0000CD),
    UMV("voltage.umv.name", 134217728, 0xDC143C),
    UXV("voltage.uxv.name", 536870912, 0xFF4500),
    MAX("voltage.max.name", Integer.MAX_VALUE, 0xBDB76B),
    ;


    public final String translationKey;
    public final int maxVoltage;
    public final int color;

    // cope mine diver
    private static final Int2ObjectOpenHashMap<VoltageTier> CACHE = new Int2ObjectOpenHashMap<>();

    VoltageTier(String translationKey, int maxVoltage, int color) {
        this.translationKey = translationKey;
        this.maxVoltage = maxVoltage;
        this.color = color;
    }

    public String getName() {
        return TranslationStorage.getInstance().getClientTranslation(translationKey);
    }

    public static VoltageTier get(int voltage) {
        return CACHE.computeIfAbsent(voltage, VoltageTier::internalGet);
    }

    private static VoltageTier internalGet(int voltage) {
        VoltageTier usableTier = ULV;
        for (VoltageTier tier : VoltageTier.values()) {
            if (voltage <= tier.maxVoltage) {
                usableTier = tier;
            }
            else {
                return usableTier;
            }
        }
        return usableTier;
    }
}