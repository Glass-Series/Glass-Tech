package net.glasslauncher.gradleplugin.resourcegen

class CableResourceGenHelper {
    companion object {
        fun create(name: String, type: String? = null): List<ResourceGenPatternTargetFile> {
            return listOf(
                ResourceGenPatternTargetFile(
                    "cable.cable${if (type != null) "_${type}" else ""}",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.blockstates.$name", arrayOf(name))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable${if (type != null) "_${type}" else ""}_core",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf("${if (type != null) "${type}_" else ""}$name"))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable${if (type != null) "_${type}" else ""}_down",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf("${if (type != null) "${type}_" else ""}$name"))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable${if (type != null) "_${type}" else ""}_east",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf("${if (type != null) "${type}_" else ""}$name"))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable${if (type != null) "_${type}" else ""}_north",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf("${if (type != null) "${type}_" else ""}$name"))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable${if (type != null) "_${type}" else ""}_south",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf("${if (type != null) "${type}_" else ""}$name"))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable${if (type != null) "_${type}" else ""}_up",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf("${if (type != null) "${type}_" else ""}$name"))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.cable${if (type != null) "_${type}" else ""}_west",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.block.cable.$name.$name", arrayOf("${if (type != null) "${type}_" else ""}$name"))
                    )
                ),
                ResourceGenPatternTargetFile(
                    "cable.item.cable${if (type != null) "_${type}" else ""}",
                    arrayOf(
                        ResourceGenPatternOutputFile("assets.glasstech.stationapi.models.item.$name", arrayOf("${if (type != null) "${type}_" else ""}$name"))
                    )
                ),
            )
        }
    }
}