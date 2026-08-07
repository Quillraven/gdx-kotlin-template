pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://central.sonatype.com/repository/maven-snapshots/")
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        // LibGDX + Fleks Snapshots
        maven {
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
            mavenContent { snapshotsOnly() }
        }
        // LibKTX Snapshots
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
            mavenContent { snapshotsOnly() }
        }
        // GDX TeaVM
        maven { url = uri("https://jitpack.io") }
    }
}

plugins {
    // Use the Foojay Toolchains plugin to automatically download JDKs required by subprojects.
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":core")
include(":lwjgl3")
include(":teavm")

rootProject.name = "gdx-template"
