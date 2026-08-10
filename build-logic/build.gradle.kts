plugins {
    // The Kotlin DSL plugin provides a convenient way to develop convention plugins.
    // Convention plugins are located in `src/main/kotlin`, with the file extension `.gradle.kts`,
    // and are applied in the project's `build.gradle.kts` files as required.
    `kotlin-dsl`
}

// JVM version for convention plugins defined in build-logic
kotlin {
    jvmToolchain(libs.versions.jvmToolchainVersion.get().toInt())
}

dependencies {
    // compileOnly because the Kotlin Gradle plugin is provided at runtime by the
    // root project (see the root build.gradle.kts). Using implementation would load
    // the plugin multiple times via build-logic's classloader.
    compileOnly(libs.kotlinGradlePlugin)
}
