package net.glasslauncher.gradleplugin.resourcegen

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class ResourceGenPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create("glassResourceGen", ResourceGenExtension::class.java, project)
        val baseDir = project.projectDir.resolve("src/main/generated")
        val templateDir = baseDir.resolve("templates")
        val outDir = baseDir.resolve("resources")

        outDir.deleteRecursively()
        outDir.mkdir()

        val genTask = project.tasks.register("genModResources") { task ->
            task.group = "resource gen"
            extension.patternTargets.get().targets.forEach { v ->
                var currentReplacer = 0

                val inPath = templateDir.resolve(v.resourcePath.replace(".", "/") + ".json")
                v.outputs.forEach {
                    val outPath = outDir.resolve(it.resourcePath.replace(".", "/")  + "_" + inPath.name)
                    var text = inPath.readText()
                    it.patterns.forEach { replacement ->
                        text = text.replace("$$currentReplacer$", replacement)
                        currentReplacer++
                    }
                    outPath.parentFile.mkdirs()
                    outPath.createNewFile()
                    outPath.writeText(text)
                }
            }
        }

        project.tasks.getByName("compileJava").dependsOn(genTask)
    }
}