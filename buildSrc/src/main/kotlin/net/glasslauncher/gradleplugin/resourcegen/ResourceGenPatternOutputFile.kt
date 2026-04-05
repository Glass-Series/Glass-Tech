package net.glasslauncher.gradleplugin.resourcegen

import java.io.File

class ResourceGenPatternOutputFile(
    val resourcePath: String,
    val patterns: Array<String>,
    val processPath: Function3<File, String, String, File> = { outDir, resourcePath, inputPathName ->
        val resp = resourcePath.replace(".", "/");
        outDir.resolve(resp + (if (resp.endsWith("/")) "" else "_") + inputPathName)},
)