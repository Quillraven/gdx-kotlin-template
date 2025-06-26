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
    api(libs.gdx)
    api(libs.bundles.ktxBaseBundle)
    api(libs.bundles.box2dBundle)
    api(libs.bundles.freetypeBundle)
    api(libs.bundles.aiBundle)
    api(libs.ktxTiled)
    api(libs.ktxPreferences)
    api(libs.ktxI18n)
    api(libs.fleks)

    testImplementation(kotlin("test"))
}
