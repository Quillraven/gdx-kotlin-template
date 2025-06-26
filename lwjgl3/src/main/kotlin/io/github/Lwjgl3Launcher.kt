package io.github

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

fun main() {
    Lwjgl3Application(GdxGame(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("GdxGame")
        setWindowedMode(800, 600)
        useVsync(true)
        setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1)
        setWindowIcon("logo-128.png", "logo-64.png", "logo-32.png")
    })
}