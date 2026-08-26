plugins {
    id("kotlin-jvm")
    alias(libs.plugins.gdxTeaVmPlugin)
}

dependencies {
    implementation(libs.gdxBox2dGwt)
    implementation(libs.gdxTeaVmFreetype)
    implementation(project(":core"))
}

gdxTeaVM {
    assets(rootProject.file("assets"))
    reflection("com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator")

    webDefaults {
        mainClass.set("io.github.TeaVMLauncherKt")
        htmlTitle.set("GdxGame TeaVM")
        htmlWidth.set(1280)
        htmlHeight.set(720)
        serverPort.set(8080)
    }

    // use JS during development since it is easier to test and debug.
    // Also, the devServer is enabled for js below. You can also enable it for wasm, if you like.
    js {
        obfuscated.set(false)

        devServer {
            enabled.set(true)
            autoBuild.set(true) // detect changed sources/assets and automatically rebuild teavm stuff
            autoReload.set(true) // automatically refresh the browser after a successful build
        }
    }

    // use wasm for your final release as it has better performance usually
    wasm {
        obfuscated.set(true)

        // wasm generation sometimes needs more memory than Gradle's default 512 MiB daemon heap
        outOfProcess = true
        processMemory = 1024

        devServer {
            enabled.set(false)
        }
    }
}
