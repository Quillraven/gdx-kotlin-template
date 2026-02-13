plugins {
    buildsrc.convention.`kotlin-jvm`
}

dependencies {
    implementation(libs.gdxTeaVmBackend)
    implementation(libs.gdxBox2dGwt)
    implementation(libs.gdxTeaVmFreetype)
    implementation(project(":core"))
}

val buildAndRun by tasks.registering(JavaExec::class) {
    group = "teavm"
    dependsOn(tasks.classes)
    description = "Transpile bytecode to JavaScript via TeaVM"
    mainClass = "io.github.TeaVMBuilderKt"
    classpath = sourceSets.main.get().runtimeClasspath
}

tasks {
    build {
        dependsOn(buildAndRun)
    }
}