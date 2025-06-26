plugins {
    buildsrc.convention.`kotlin-jvm`
}

dependencies {
    api(libs.gdx)
    api(libs.bundles.ktxBaseBundle)
    api(libs.bundles.box2d)
    api(libs.bundles.freetype)
    api(libs.bundles.ai)
    api(libs.ktxTiled)
    api(libs.ktxPreferences)
    api(libs.ktxI18n)
    api(libs.fleks)

    testImplementation(kotlin("test"))
}
