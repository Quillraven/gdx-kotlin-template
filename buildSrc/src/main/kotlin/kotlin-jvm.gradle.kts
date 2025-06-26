package buildsrc.convention

import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm")
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
val jvmVersion = versionCatalog.findVersion("jvmToolchainVersion").get().toString()

// JVM version defined for modules that use the kotlin-jvm convention plugin like core or lwjgl3
kotlin {
    jvmToolchain(jvmVersion.toInt())
}

tasks.withType<Test>().configureEach {
    // Configure all test Gradle tasks to use JUnitPlatform.
    useJUnitPlatform()

    // Log information about all test results, not only the failed ones.
    testLogging {
        events(
            TestLogEvent.FAILED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED
        )
    }
}
