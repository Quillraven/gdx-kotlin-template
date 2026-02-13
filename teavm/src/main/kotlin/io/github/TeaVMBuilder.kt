package io.github

import com.github.xpenatan.gdx.teavm.backends.shared.config.AssetFileHandle
import com.github.xpenatan.gdx.teavm.backends.shared.config.compiler.TeaCompiler
import com.github.xpenatan.gdx.teavm.backends.web.config.backend.WebBackend
import org.teavm.vm.TeaVMOptimizationLevel
import java.io.File


/** Builds the TeaVM/HTML application. */
fun main() {
    val webBackend = WebBackend()
        .setHtmlTitle("GdxGame")
        .setHtmlWidth(800)
        .setHtmlHeight(600)
        .setStartJettyAfterBuild(true)
        .setJettyPort(8080)
        // set 'webAssembly' to false for creating JAVASCRIPT target
        .setWebAssembly(true)

    TeaCompiler(webBackend)
        .addAssets(AssetFileHandle("../assets"))
        .setOptimizationLevel(TeaVMOptimizationLevel.ADVANCED)
        .setMainClass("io.github.TeaVMLauncherKt")
        .setObfuscated(true)
        // Register any classes or packages that require reflection here like
        // - custom scene2d styles
        // - typing label effects
        // - freetype font
        // .addReflectionClass(FreeTypeFontGenerator::class.java)
        .build(File("build/dist"))
}