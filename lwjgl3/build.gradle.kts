import io.github.fourlastor.construo.Target

plugins {
    id("kotlin-jvm")
    application
    alias(libs.plugins.construoGradlePlugin)
}

dependencies {
    implementation(libs.gdxBackendLwjgl3)
    implementation(project(":core"))
    implementation(libs.gdxControllersDesktop)
    listOf(
        libs.gdxPlatform,
        libs.gdxBox2dPlatform,
        libs.gdxFreetypePlatform,
    ).forEach {
        runtimeOnly(it) {
            artifact { classifier = "natives-desktop" }
        }
    }

    // constraints can be removed with the next LibGDX version which defaults to 3.4.3+
    constraints {
        listOf(
            "org.lwjgl:lwjgl",
            "org.lwjgl:lwjgl-glfw",
            "org.lwjgl:lwjgl-jemalloc",
            "org.lwjgl:lwjgl-openal",
            "org.lwjgl:lwjgl-opengl",
            "org.lwjgl:lwjgl-stb",
        ).forEach {
            implementation(it) {
                version { strictly("3.4.3") }
            }
        }
    }
}

group = "io.github"
version = "1.0"

// Silence JDK 24+ warnings:
//  - libGDX' SharedLibraryLoader calls System.load -> needs --enable-native-access
//  - enable LWJGL's jdk.internal.misc.Unsafe backend instead of the deprecated sun.misc.Unsafe
// Used by the Gradle start scripts / run task (applicationDefaultJvmArgs) and by the Construo packages (roast.vmArgs).
// The JAR manifest below only covers `java -jar`.
val gameJvmArgs = listOf(
    "--enable-native-access=ALL-UNNAMED",
    "--add-exports=java.base/jdk.internal.misc=ALL-UNNAMED",
)

application {
    applicationName = "GdxGame"
    mainClass = "io.github.Lwjgl3LauncherKt"
    applicationDefaultJvmArgs = gameJvmArgs
}

tasks {
    jar {
        archiveBaseName.set(application.applicationName)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(files(sourceSets.main.get().output.classesDirs))
        // resolve the classpath lazily at execution time instead of during configuration
        from(configurations.runtimeClasspath.map { classpath ->
            classpath.map { if (it.isDirectory) it else zipTree(it) }
        })
        // signature files of merged dependency jars would be invalid in the fat jar
        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")

        manifest {
            attributes(
                // Enabling native access is good for JARs run by Java 24 or later.
                // Setting Multi-Release to true allows LWJGL3 to use different classes on recent Java versions.
                "Main-Class" to application.mainClass,
                "Enable-Native-Access" to "ALL-UNNAMED",
                "Multi-Release" to "true",
                "Add-Exports" to "java.base/jdk.internal.misc",
            )
        }
    }
}

// Keep the version in sync with jvmToolchainVersion in libs.versions.toml.
// See the README on how to switch to another JDK, e.g. Azul Zulu.
val jbrVersion = "25.0.4.1-%s-b635.79"
fun jbrUrl(platform: String, extension: String) =
    "https://cache-redirector.jetbrains.com/intellij-jbr/jbrsdk-${jbrVersion.format(platform)}.$extension"

// Alternative: Azul Zulu JDK
// val azulJdk = "zulu25.36.205-ca-jdk25.0.4.1"

construo {
    name.set(application.applicationName)
    // human-readable name, used for example in the `.app` name for macOS
    humanName.set(application.applicationName)

    jlink {
        // jdeps reports LWJGL's Unsafe backend usage as "java.base/jdk.internal.misc",
        // which is a package, not a module, and makes jlink fail with
        // "Module java.base/jdk.internal.misc not found". List the real modules instead
        // (java.base is always included in an image).
        guessModulesFromJar.set(false)
        modules.set(listOf("java.desktop", "jdk.unsupported"))
    }

    roast {
        vmArgs.set(gameJvmArgs)
    }

    targets {
        create<Target.Linux>("linuxX64") {
            architecture.set(Target.Architecture.X86_64)
            jdkUrl.set(jbrUrl("linux-x64", "tar.gz"))
            // Azul: jdkUrl.set("https://cdn.azul.com/zulu/bin/${azulJdk}-linux_x64.tar.gz")
        }
        create<Target.Windows>("winX64") {
            architecture.set(Target.Architecture.X86_64)
            jdkUrl.set(jbrUrl("windows-x64", "zip"))
            // Azul: jdkUrl.set("https://cdn.azul.com/zulu/bin/${azulJdk}-win_x64.zip")
        }
    }
}
