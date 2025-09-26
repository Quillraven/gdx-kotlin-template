plugins {
    buildsrc.convention.`kotlin-jvm`
}

sourceSets {
    main {
        // assets folder contains the resources of the game
        resources.srcDir(rootProject.files("assets"))
    }
}

dependencies {
    implementation(libs.gdx)
    api(libs.bundles.ktxBaseBundle) // requires API to correctly expose ApplicationListener for launcher classes
    implementation(libs.bundles.box2dBundle)
    implementation(libs.bundles.freetypeBundle)
    implementation(libs.bundles.aiBundle)
    implementation(libs.ktxTiled)
    implementation(libs.ktxPreferences)
    implementation(libs.ktxI18n)
    implementation(libs.fleks)

    testImplementation(kotlin("test"))
}
