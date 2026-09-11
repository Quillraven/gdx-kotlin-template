# Gdx Kotlin Template

This project uses a modern [Gradle](https://gradle.org/) setup approach with Kotlin DSL,
version catalog (see `gradle/libs.versions.toml`) and extracted build logic
to convention plugins located in `build-logic`.
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

- `./gradlew teavm:gdx_teavm_web_js_build`
- `./gradlew teavm:gdx_teavm_web_js_run`
- `./gradlew teavm:gdx_teavm_web_wasm_build`
- `./gradlew teavm:gdx_teavm_web_wasm_run`

This template can be configured
using [Gdx-Quilly-Utils](https://quillraven.github.io/gdx-quilly-utils/gradle-kotlin-template).

----

### Info about using JDK 24+

When running `Lwjgl3Launcher` main method from your IDE it most likely ignores the `applicationDefaultJvmArgs`
specified in the `build.gradle.kts` file of the `lwjgl3` module. 
To silence some warnings, add those VM options manually to your run configuration:
```
--enable-native-access=ALL-UNNAMED
--add-exports=java.base/jdk.internal.misc=ALL-UNNAMED
```
