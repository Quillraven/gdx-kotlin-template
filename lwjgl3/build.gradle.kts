plugins {
    id("buildsrc.convention.kotlin-jvm")
    application
}

dependencies {
    implementation(libs.bundles.gdxLwjgl3Bundle)
    implementation(libs.gdxPlatform) {
        artifact {
            classifier = "natives-desktop"
        }
    }
    implementation(project(":core"))
}

group = "io.github"
version = "1.0"

sourceSets {
    main {
        // assets folder contains the resources of the game
        resources.srcDir(rootProject.files("assets"))
    }
}

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
