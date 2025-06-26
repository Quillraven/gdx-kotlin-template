package io.github

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

fun main() {
    Lwjgl3Application(GdxGame(), Lwjgl3ApplicationConfiguration().apply {
        useVsync(true)
        setTitle("GdxGame")
        setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1)
        setWindowedMode(800, 600)
    })
}