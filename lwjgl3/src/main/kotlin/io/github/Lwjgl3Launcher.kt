package io.github

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import org.lwjgl.glfw.GLFW

fun main() {
    // A fix on Linux for NVIDIA to avoid this warning: "Failed to load plugin 'libdecor-gtk.so': failed to init"
    fun waylandCheck() {
        val wlDisplay = System.getenv("WAYLAND_DISPLAY")
        val sessionType = System.getenv("XDG_SESSION_TYPE")

        if (wlDisplay != null || "wayland".equals(sessionType, ignoreCase = true)) {
            GLFW.glfwInitHint(GLFW.GLFW_PLATFORM, GLFW.GLFW_PLATFORM_X11)
        }
    }

    waylandCheck()
    Lwjgl3Application(GdxGame(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("GdxGame")
        setWindowedMode(1280, 720)
        setDecorated(true) // show/hide window title bar
        useVsync(true)
        setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1)
        setWindowIcon("logo-128.png", "logo-64.png", "logo-32.png")
    })
}