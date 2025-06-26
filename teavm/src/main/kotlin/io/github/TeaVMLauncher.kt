package io.github

import com.github.xpenatan.gdx.backends.teavm.TeaApplication
import com.github.xpenatan.gdx.backends.teavm.TeaApplicationConfiguration
import com.github.xpenatan.gdx.backends.teavm.TeaAssetPreloadListener

/** Launches the TeaVM/HTML application. */
fun main() {
    val config = TeaApplicationConfiguration("canvas").apply {
        // If width and height are each greater than 0, then the app will use a fixed size.
        // If width and height are both 0, then the app will use all available space.
        // If width and height are both -1, then the app will fill the canvas size.
        width = 0
        height = 0
        // needed for freetype support
        preloadListener = TeaAssetPreloadListener { assetLoader -> assetLoader.loadScript("freetype.js") }
    }
    TeaApplication(GdxGame(), config)
}