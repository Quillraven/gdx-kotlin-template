package io.github

import com.github.xpenatan.gdx.teavm.backends.web.WebApplication
import com.github.xpenatan.gdx.teavm.backends.web.WebApplicationConfiguration
import com.github.xpenatan.gdx.teavm.backends.web.WebAssetPreloadListener

/** Launches the TeaVM/HTML application. */
fun main() {
    val config = WebApplicationConfiguration("canvas").apply {
        // If width and height are each greater than 0, then the app will use a fixed size.
        // If width and height are both 0, then the app will use all available space.
        // If width and height are both -1, then the app will fill the canvas size.
        width = 0
        height = 0
        showDownloadLogs = true
        // needed for freetype support
        preloadListener = WebAssetPreloadListener { assetLoader -> assetLoader.loadScript("freetype.js") }
    }
    WebApplication(GdxGame(), config)
}