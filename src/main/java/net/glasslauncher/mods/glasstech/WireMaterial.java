package net.glasslauncher.mods.glasstech;

public class WireMaterial {
    public static final WireMaterial IRON = new WireMaterial("iron_cable", VoltageTier.LV.maxVoltage * 8, VoltageTier.LV, 4);
    public static final WireMaterial ALUMINIUM = new WireMaterial("aluminium_cable", VoltageTier.MV.maxVoltage * 10, VoltageTier.LV, 3);
    public static final WireMaterial CARBON = new WireMaterial("carbon_cable", VoltageTier.MV.maxVoltage * 16, VoltageTier.LV, 2);
    public static final WireMaterial GOLD = new WireMaterial("gold_cable", VoltageTier.HV.maxVoltage * 7, VoltageTier.LV, 2);
    public static final WireMaterial COPPER = new WireMaterial("copper_cable", VoltageTier.HV.maxVoltage * 8, VoltageTier.LV, 2);
    public static final WireMaterial SILVERED_COPPER = new WireMaterial("silvered_copper_cable", VoltageTier.HV.maxVoltage * 8, VoltageTier.LV, 1);
    public static final WireMaterial SILVER = new WireMaterial("silver_cable", VoltageTier.HV.maxVoltage * 12, VoltageTier.LV, 1);

    public final String translationKey;
    public final int maxVoltage;
    public final VoltageTier voltageTier;
    public final float lossPerBlock;


    private WireMaterial(String translationKey, int maxVoltage, VoltageTier voltageTier, float lossPerBlock) {
        this.translationKey = translationKey;
        this.maxVoltage = maxVoltage;
        this.voltageTier = voltageTier;
        this.lossPerBlock = lossPerBlock;
    }
}
