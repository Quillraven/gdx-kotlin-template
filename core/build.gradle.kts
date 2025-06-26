plugins {
    buildsrc.convention.`kotlin-jvm`
}

dependencies {
    api(libs.gdx)
    api(libs.bundles.ktxBaseBundle)

    testImplementation(kotlin("test"))
}
