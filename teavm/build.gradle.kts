plugins {
    buildsrc.convention.`kotlin-jvm`
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

    js {
        mainClass.set("io.github.TeaVMLauncherKt")
        htmlTitle.set("GdxGame JS")
        htmlWidth.set(1280)
        htmlHeight.set(720)
        serverPort.set(8080)
        obfuscated.set(false)
    }

    wasm {
        mainClass.set("io.github.TeaVMLauncherKt")
        htmlTitle.set("GdxGame Wasm")
        htmlWidth.set(1280)
        htmlHeight.set(720)
        serverPort.set(8080)
        obfuscated.set(false)

        // wasm generation sometimes needs more memory than Gradle's default 512 MiB daemon heap
        outOfProcess = true
        processMemory = 1024
    }
}
