package net.glasslauncher.mods.glasstech;

import net.teamterminus.machineessentials.energy.electric.api.VoltageTier;
import net.teamterminus.machineessentials.energy.electric.api.WireMaterial;

public class GTWireMaterial {
    public static final WireMaterial IRON = new WireMaterial("iron_cable", -1, VoltageTier.LV.maxVoltage * 8, VoltageTier.LV, 4, 1000);
    public static final WireMaterial ALUMINIUM = new WireMaterial("aluminium_cable", -1, VoltageTier.MV.maxVoltage * 10, VoltageTier.LV, 3, 1000);
    public static final WireMaterial CARBON = new WireMaterial("carbon_cable", -1, VoltageTier.MV.maxVoltage * 16, VoltageTier.LV, 2, 1000);
    public static final WireMaterial GOLD = new WireMaterial("gold_cable", -1, VoltageTier.HV.maxVoltage * 7, VoltageTier.LV, 2, 1000);
    public static final WireMaterial COPPER = new WireMaterial("copper_cable", -1, VoltageTier.HV.maxVoltage * 8, VoltageTier.LV, 2, 1000);
    public static final WireMaterial SILVERED_COPPER = new WireMaterial("silvered_copper_cable", -1, VoltageTier.HV.maxVoltage * 8, VoltageTier.LV, 1, 1000);
    public static final WireMaterial SILVER = new WireMaterial("silver_cable", -1, VoltageTier.HV.maxVoltage * 12, VoltageTier.LV, 1, 1000);
}
