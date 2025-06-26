plugins {
    // The Kotlin DSL plugin provides a convenient way to develop convention plugins.
    // Convention plugins are located in `src/main/kotlin`, with the file extension `.gradle.kts`,
    // and are applied in the project's `build.gradle.kts` files as required.
    `kotlin-dsl`
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
val jvmVersion = versionCatalog.findVersion("jvmToolchainVersion").get().toString()

// JVM version for convention plugins defined in buildSrc
kotlin {
    jvmToolchain(jvmVersion.toInt())
}

dependencies {
    implementation(libs.kotlinGradlePlugin)
}
