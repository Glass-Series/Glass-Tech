package net.glasslauncher.gradleplugin.resourcegen

import java.util.function.Consumer

class ResourceGenPatternTargets private constructor() {
    val targets = ArrayList<ResourceGenPatternTargetFile>()

    companion object {
        fun create(runnable: Consumer<ArrayList<ResourceGenPatternTargetFile>>): ResourceGenPatternTargets {
            val ret = ResourceGenPatternTargets()
            runnable.accept(ret.targets)
            return ret
        }

        fun create(): ResourceGenPatternTargets = ResourceGenPatternTargets()
    }
}