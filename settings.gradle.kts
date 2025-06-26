dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        // LibGDX Snapshots
        maven { url = uri("https://central.sonatype.com/repository/maven-snapshots/") }
        // Fleks Snapshots
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/") }
        // LibKTX Snapshots
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    }
}

plugins {
    // Use the Foojay Toolchains plugin to automatically download JDKs required by subprojects.
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":core")
include(":lwjgl3")

rootProject.name = "gdx-template"