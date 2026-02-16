package io.github

import com.github.xpenatan.gdx.teavm.backends.shared.config.AssetFileHandle
import com.github.xpenatan.gdx.teavm.backends.shared.config.compiler.TeaCompiler
import com.github.xpenatan.gdx.teavm.backends.web.config.backend.WebBackend
import org.teavm.tooling.TeaVMSourceFilePolicy
import org.teavm.tooling.sources.DirectorySourceFileProvider
import org.teavm.vm.TeaVMOptimizationLevel
import java.io.File

/** Builds the TeaVM/HTML application. */
fun main(args: Array<String>) {
    val debug = "debug" in args
    val startJetty = "startJetty" in args

    println("Building TeaVM with 'debug'=$debug and 'startJetty'=$startJetty")

    val webBackend = WebBackend()
        .setHtmlTitle("GdxGame")
        .setHtmlWidth(800)
        .setHtmlHeight(600)
        .setStartJettyAfterBuild(startJetty)
        .setJettyPort(8080)
        // set 'webAssembly' to false for creating JAVASCRIPT target.
        // JAVASCRIPT target makes it also easier to debug with breakpoints in the browser.
        .setWebAssembly(true)

    TeaCompiler(webBackend)
        .addAssets(AssetFileHandle("../assets"))
        .setOptimizationLevel(if (debug) TeaVMOptimizationLevel.SIMPLE else TeaVMOptimizationLevel.ADVANCED)
        .setMainClass("io.github.TeaVMLauncherKt")
        .setObfuscated(!debug)
        .setDebugInformationGenerated(debug)
        .setSourceMapsFileGenerated(debug)
        .setSourceFilePolicy(if (debug) TeaVMSourceFilePolicy.COPY else TeaVMSourceFilePolicy.DO_NOTHING)
        .addSourceFileProvider(DirectorySourceFileProvider(File("../core/src/main/kotlin")))
        // Register any classes or packages that require reflection here like
        // - custom scene2d styles
        // - typing label effects
        // - freetype font
        // .addReflectionClass(FreeTypeFontGenerator::class.java)
        .build(File("build/dist"))
}