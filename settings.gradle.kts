pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven {
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
            mavenContent { snapshotsOnly() }
        }
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        // LibGDX, Fleks and LibKTX Snapshots
        maven {
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
            mavenContent { snapshotsOnly() }
        }
        // JitPack hosts TextraTypist and FreeTypist; restricted to their group so nothing else is looked up there
        exclusiveContent {
            forRepository { maven("https://jitpack.io") }
            filter { includeGroupAndSubgroups("com.github.tommyettinger") }
        }
    }
}

plugins {
    // Use the Foojay Toolchains plugin to automatically download JDKs required by subprojects.
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":core")
include(":lwjgl3")
include(":teavm")
include(":tool")

rootProject.name = "gdx-template"
