package io.github

import com.github.xpenatan.gdx.backends.teavm.config.AssetFileHandle
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuildConfiguration
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuilder
import org.teavm.tooling.TeaVMTargetType
import org.teavm.vm.TeaVMOptimizationLevel
import java.io.File

/** Builds the TeaVM/HTML application. */
object TeaVMBuilder {
    @JvmStatic
    fun main(arguments: Array<String>) {
        val teaBuildConfiguration = TeaBuildConfiguration().apply {
            assetsPath.add(AssetFileHandle("../assets"))
            webappPath = File("build/dist").canonicalPath
            // Register any extra classpath assets here:
            // additionalAssetsClasspathFiles += "..."
            htmlTitle = "GdxGame"
            htmlWidth = 800
            htmlHeight = 600
        }

        // Register any classes or packages that require reflection here like
        // - custom scene2d styles
        // - typing label effects
        // - freetype font
        // TeaReflectionSupplier.addReflectionClass(FreeTypeFontGenerator::class.java)

        val tool = TeaBuilder.config(teaBuildConfiguration)
        tool.mainClass = "io.github.TeaVMLauncherKt"
        tool.optimizationLevel = TeaVMOptimizationLevel.ADVANCED
        tool.setObfuscated(true)
        tool.targetType = TeaVMTargetType.JAVASCRIPT
//        tool.targetType = TeaVMTargetType.WEBASSEMBLY_GC
        TeaBuilder.build(tool)
    }
}