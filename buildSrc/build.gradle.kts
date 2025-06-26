plugins {
    // The Kotlin DSL plugin provides a convenient way to develop convention plugins.
    // Convention plugins are located in `src/main/kotlin`, with the file extension `.gradle.kts`,
    // and are applied in the project's `build.gradle.kts` files as required.
    `kotlin-dsl`
}

// JVM version for convention plugins defined in buildSrc
kotlin {
    jvmToolchain(libs.versions.jvmToolchainVersion.get().toInt())
}

dependencies {
    implementation(libs.kotlinGradlePlugin)
}
