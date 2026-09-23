plugins {
    id("kotlin-jvm")
}

dependencies {
    implementation(libs.gdxTools)
}

tasks.register<JavaExec>("packTextures") {
    group = rootProject.name
    description = "Packs textures of the assets folder into texture atlases"

    mainClass.set("io.github.TexturePackerKt")
    classpath = sourceSets.main.get().runtimeClasspath
}
