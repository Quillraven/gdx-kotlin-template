plugins {
    id("kotlin-jvm")
    alias(libs.plugins.kotlinSerialization)
}

sourceSets {
    main {
        // assets folder contains the resources of the game
        resources.srcDir(rootProject.files("assets"))
    }
}

dependencies {
    api(libs.gdx) // requires API to correctly expose Disposable for launcher classes (TeaVM)
    api(libs.bundles.ktxBaseBundle) // requires API to correctly expose ApplicationListener for launcher classes
    api(libs.kotlinxSerializationJson)
    implementation(libs.bundles.box2dBundle)
    implementation(libs.bundles.freetypeBundle)
    implementation(libs.bundles.aiBundle)
    implementation(libs.ktxTiled)
    implementation(libs.ktxPreferences)
    implementation(libs.ktxI18n)
    implementation(libs.fleks)
    implementation(libs.textraTypist)
    implementation(libs.freeTypist)

    testImplementation(kotlin("test"))
}
