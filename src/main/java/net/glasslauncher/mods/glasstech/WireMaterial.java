package net.glasslauncher.mods.glasstech;

public class WireMaterial {
    public static final WireMaterial TIN = new WireMaterial(VoltageTier.LV, 1, 0.025f, false);
    public static final WireMaterial COPPER = new WireMaterial(VoltageTier.MV, 1, 0.3f, true);
    public static final WireMaterial COPPER_INSULATED = new WireMaterial(VoltageTier.MV, 1, 0.2f, false);
    public static final WireMaterial GOLD = new WireMaterial(VoltageTier.HV, 1, 0.5f, true);
    public static final WireMaterial GOLD_INSULATED = new WireMaterial(VoltageTier.HV, 1, 0.45f, false);
    public static final WireMaterial GOLD_INSULATED_X2 = new WireMaterial(VoltageTier.HV, 1, 0.4f, false);
    public static final WireMaterial REFINED_IRON = new WireMaterial(VoltageTier.EV, 1, 1f, true);
    public static final WireMaterial REFINED_IRON_INSULATED = new WireMaterial(VoltageTier.EV, 1, 0.95f, false);
    public static final WireMaterial REFINED_IRON_INSULATED_X2 = new WireMaterial(VoltageTier.EV, 1, 0.9f, false);
    public static final WireMaterial REFINED_IRON_INSULATED_X4 = new WireMaterial(VoltageTier.EV, 1, 0.8f, false);
    public static final WireMaterial GLASS_FIBRE = new WireMaterial(VoltageTier.IV, 1, 0.025f, false);

    public final int amperage;
    public final VoltageTier voltageTier;
    public final float lossPerBlock;
    public final boolean canShock;

    private WireMaterial(VoltageTier voltageTier, int amperage, float lossPerBlock, boolean canShock) {
        this.amperage = amperage;
        this.voltageTier = voltageTier;
        this.lossPerBlock = lossPerBlock;
        this.canShock = canShock;
    }

    public int getMaxPower() {
        return amperage * voltageTier.maxVoltage;
    }
}
