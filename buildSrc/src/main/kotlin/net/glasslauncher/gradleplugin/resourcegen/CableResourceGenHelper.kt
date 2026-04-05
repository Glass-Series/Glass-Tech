package net.glasslauncher.gradleplugin.resourcegen

class CableResourceGenHelper {
    companion object {
        fun create(name: String, type: String, texture: String = name): List<ResourceGenPatternTargetFile> {
            return listOf(
                ResourceGenPatternTargetFile(
                    "cable.cable",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.blockstates.", arrayOf(name, type), {outDir, resourcePath, inputPathName ->
                            outDir.resolve("${resourcePath.replace(".", "/")}$name.json")})
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_core",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.", arrayOf(texture, type))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_down",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.", arrayOf(texture, type))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_east",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.", arrayOf(texture, type))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_north",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.", arrayOf(texture, type))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_south",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.", arrayOf(texture, type))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_up",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.", arrayOf(texture, type))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable_west",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.", arrayOf(texture, type))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.item.cable",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.item.", arrayOf(texture, type), {outDir, resourcePath, inputPathName ->
                            outDir.resolve("${resourcePath.replace(".", "/")}$name.json")
                        })
                    )
                ),
            )
        }
    }
}