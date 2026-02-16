# Gdx Kotlin Template

This project uses a modern [Gradle](https://gradle.org/) setup approach with Kotlin DSL,
version catalog (see `gradle/libs.versions.toml`) and extracted build logic
to convention plugins located in `buildSrc`.
It also uses both a build cache and a configuration cache (see `gradle.properties`).

It is a starting point for any [LibGDX](https://github.com/libgdx/libgdx) Kotlin application
with [LibKTX](https://github.com/libktx/ktx) extensions and provides two launchers:

- `Desktop` (=lwjgl3): `Lwjgl3Launcher.kt`
- `TeaVM` (=browser): `TeaVMLauncher.kt`

To run `lwjgl3` just execute the `main` method of the launcher class or run:

- `./gradlew lwjgl3:run`

[Construo](https://github.com/fourlastor-alexandria/construo) is used to package your game for distribution.
To package for Linux or Windows run:

- `./gradlew lwjgl3:packageLinuxX64`
- `./gradlew lwjgl3:packageWinX64`

For `teavm` there are four different tasks to build and optionally run it on `http://localhost:8080/`:

- `./gradlew teavm:teavmDebugBuild`: build an unobfuscated version with source-mapping for browser debugging
- `./gradlew teavm:teavmDebugRun`: run the debug build locally
- `./gradlew teavm:teavmReleaseBuild`: build an obfuscated and optimized version
- `./gradlew teavm:teavmReleaseRun`: run the release build locally

This template can be configured
using [Gdx-Quilly-Utils](https://quillraven.github.io/gdx-quilly-utils/gradle-kotlin-template).
