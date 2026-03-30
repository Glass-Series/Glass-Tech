package net.glasslauncher.mods.glasstech;

public class GTWireMaterial {
    public static final GTWireMaterial IRON = new GTWireMaterial("iron_cable", -1, VoltageTier.LV.maxVoltage * 8, VoltageTier.LV, 4);
    public static final GTWireMaterial ALUMINIUM = new GTWireMaterial("aluminium_cable", -1, VoltageTier.MV.maxVoltage * 10, VoltageTier.LV, 3);
    public static final GTWireMaterial CARBON = new GTWireMaterial("carbon_cable", -1, VoltageTier.MV.maxVoltage * 16, VoltageTier.LV, 2);
    public static final GTWireMaterial GOLD = new GTWireMaterial("gold_cable", -1, VoltageTier.HV.maxVoltage * 7, VoltageTier.LV, 2);
    public static final GTWireMaterial COPPER = new GTWireMaterial("copper_cable", -1, VoltageTier.HV.maxVoltage * 8, VoltageTier.LV, 2);
    public static final GTWireMaterial SILVERED_COPPER = new GTWireMaterial("silvered_copper_cable", -1, VoltageTier.HV.maxVoltage * 8, VoltageTier.LV, 1);
    public static final GTWireMaterial SILVER = new GTWireMaterial("silver_cable", -1, VoltageTier.HV.maxVoltage * 12, VoltageTier.LV, 1);

    public final String translationKey;
    public final int color;
    public final int maxVoltage;
    public final VoltageTier voltageTier;
    public final int lossPerBlock;


    private GTWireMaterial(String translationKey, int color, int maxVoltage, VoltageTier voltageTier, int lossPerBlock) {
        this.translationKey = translationKey;
        this.color = color;
        this.maxVoltage = maxVoltage;
        this.voltageTier = voltageTier;
        this.lossPerBlock = lossPerBlock;
    }
}
