plugins {
    buildsrc.convention.`kotlin-jvm`
    application
}

dependencies {
    implementation(libs.gdxBackendLwjgl3)
    implementation(project(":core"))
    listOf(
        libs.gdxPlatform,
        libs.gdxBox2dPlatform,
        libs.gdxFreetypePlatform,
    ).forEach {
        runtimeOnly(it) {
            artifact { classifier = "natives-desktop" }
        }
    }
}

group = "io.github"
version = "1.0"

application {
    applicationName = "GdxLwjgl3"
    mainClass = "io.github.Lwjgl3LauncherKt"
}

tasks {
    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(files(sourceSets.main.get().output.classesDirs))
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

        manifest {
            attributes["Main-Class"] = application.mainClass
        }
    }
}
