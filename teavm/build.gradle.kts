plugins {
    buildsrc.convention.`kotlin-jvm`
}

dependencies {
    implementation(libs.gdxTeaVmBackend)
    implementation(libs.gdxBox2dGwt)
    implementation(libs.gdxTeaVmFreetype)
    implementation(project(":core"))
}

fun registerTeavmTask(name: String, description:String, javaMainArgs: List<String>) =
 tasks.register<JavaExec>(name) {
     group = "teavm"
     this.description = description
     dependsOn(tasks.classes)

     mainClass = "io.github.TeaVMBuilderKt"
     classpath = sourceSets.main.get().runtimeClasspath
     args = javaMainArgs
 }

registerTeavmTask(
    "teavmDebugRun",
    "Build unobfuscated, simple optimized TeaVM artifact and run it at localhost",
    listOf("debug", "startJetty"),
)

registerTeavmTask(
    "teavmReleaseRun",
    "Build obfuscated, advanced optimized TeaVM artifact and run it at localhost",
    listOf("startJetty"),
)

registerTeavmTask(
    "teavmDebugBuild",
    "Build unobfuscated, simple optimized TeaVM artifact",
    listOf("debug"),
)

val teavmReleaseBuild = registerTeavmTask(
    "teavmReleaseBuild",
    "Build obfuscated, advanced optimized TeaVM artifact",
    emptyList(),
)

tasks {
    build {
        dependsOn(teavmReleaseBuild)
    }
}
