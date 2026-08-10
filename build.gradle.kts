plugins {
    // Load the Kotlin plugin once in the common parent project so that subprojects
    // applying it via the kotlin-jvm convention plugin share the same classloader.
    alias(libs.plugins.kotlinJvm) apply false
}
