package net.glasslauncher.gradleplugin.resourcegen

class CableResourceGenHelper {
    companion object {
        fun create(name: String): List<ResourceGenPatternTargetFile> {
            return listOf(
                ResourceGenPatternTargetFile(
                    "cable.cable",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.blockstates.$name", arrayOf(name))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_core_core",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf(name))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_core_down",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf(name))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_core_east",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf(name))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_core_north",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf(name))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_core_south",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf(name))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_core_up",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf(name))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_core_west",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf(name))
                    )
                ),
            )
        }
    }
}