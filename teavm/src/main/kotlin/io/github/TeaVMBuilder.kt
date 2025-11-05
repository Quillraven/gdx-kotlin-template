package io.github

import com.github.xpenatan.gdx.backends.teavm.config.AssetFileHandle
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuildConfiguration
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuilder
import org.teavm.tooling.TeaVMTargetType
import org.teavm.tooling.TeaVMTool
import org.teavm.vm.TeaVMOptimizationLevel
import java.io.File

/** Builds the TeaVM/HTML application. */
fun main() {
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

    // teaBuildConfiguration.targetType = TeaTargetType.JAVASCRIPT
    teaBuildConfiguration.targetType = TeaVMTargetType.WEBASSEMBLY_GC
    TeaBuilder.config(teaBuildConfiguration)

    val tool = TeaVMTool()
    tool.mainClass = "io.github.TeaVMLauncherKt"
    tool.optimizationLevel = TeaVMOptimizationLevel.ADVANCED
    tool.setObfuscated(true)

    TeaBuilder.build(tool)
}
