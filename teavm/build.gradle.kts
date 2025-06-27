plugins {
    buildsrc.convention.`kotlin-jvm`
    alias(libs.plugins.grettyGradlePlugin)
}

gretty {
    contextPath = "/"
    extraResourceBase("build/dist/webapp")
}

dependencies {
    implementation(libs.bundles.teaVmBundle)
    implementation(libs.gdxBox2dGwt)
    implementation(libs.gdxTeaVmFreetype)
    implementation(project(":core"))
}

val buildJavaScript by tasks.registering(JavaExec::class) {
    group = "teavm"
    dependsOn(tasks.classes)
    description = "Transpile bytecode to JavaScript via TeaVM"
    mainClass = "io.github.TeaVMBuilderKt"
    classpath = sourceSets.main.get().runtimeClasspath
}

tasks {
    build {
        dependsOn(buildJavaScript)
    }
}

tasks.register("run") {
    group = "teavm"
    dependsOn(buildJavaScript, tasks.named("jettyRun"))
    description = "Run the JavaScript application hosted via a local Jetty server at http://localhost:8080/"
}
