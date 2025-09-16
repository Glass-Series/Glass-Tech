package net.glasslauncher.mods.glasstech

import net.teamterminus.machineessentials.energy.electric.api.VoltageTier
import net.teamterminus.machineessentials.energy.electric.api.WireMaterial

class GTWireMaterial {
    companion object {
        val IRON = WireMaterial("iron_cable", -1, VoltageTier.LV.maxVoltage * 8, VoltageTier.LV, 4, 1000)
        val ALUMINIUM = WireMaterial("aluminium_cable", -1, VoltageTier.MV.maxVoltage * 10, VoltageTier.LV, 3, 1000)
        val CARBON = WireMaterial("carbon_cable", -1, VoltageTier.MV.maxVoltage * 16, VoltageTier.LV, 2, 1000)
        val GOLD = WireMaterial("gold_cable", -1, VoltageTier.HV.maxVoltage * 7, VoltageTier.LV, 2, 1000)
        val COPPER = WireMaterial("copper_cable", -1, VoltageTier.HV.maxVoltage * 8, VoltageTier.LV, 2, 1000)
        val SILVERED_COPPER = WireMaterial("silvered_copper_cable", -1, VoltageTier.HV.maxVoltage * 8, VoltageTier.LV, 1, 1000)
        val SILVER = WireMaterial("silver_cable", -1, VoltageTier.HV.maxVoltage * 12, VoltageTier.LV, 1, 1000)
    }
}