# Gdx Kotlin Template

> **Gdx-Quilly-Utils:** this template ships with everything included. If you want to
> strip it down to only what your own project needs, configure it via
> [Gdx-Quilly-Utils](https://quillraven.github.io/gdx-quilly-utils/gradle-kotlin-template).

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

The packaged games bundle a JDK that is downloaded automatically. By default, this is the
[JetBrains Runtime SDK (JBRSDK)](https://github.com/JetBrains/JetBrainsRuntime/releases) because it has better Linux support
(e.g. on Wayland). See [Changing the bundled JDK](#changing-the-bundled-jdk) on how to use a different one.

For `teavm` there are four different tasks to build and optionally run it on `http://localhost:8080/`:

- `./gradlew teavm:gdx_teavm_web_js_build`
- `./gradlew teavm:gdx_teavm_web_js_run`
- `./gradlew teavm:gdx_teavm_web_wasm_build`
- `./gradlew teavm:gdx_teavm_web_wasm_run`

The `tool` module is meant for project related tools. It comes with a texture packer as
an example Gradle task and implementation:

- `./gradlew tool:packTextures`

The atlas settings are optimized for pixelart (e.g. nearest neighbor filtering). For other
types of games you should adjust them accordingly.

----

### Info about using JDK 24+

When running `Lwjgl3Launcher` main method from your IDE it most likely ignores the `applicationDefaultJvmArgs`
specified in the `build.gradle.kts` file of the `lwjgl3` module. 
To silence some warnings, add those VM options manually to your run configuration:
```
--enable-native-access=ALL-UNNAMED
--add-exports=java.base/jdk.internal.misc=ALL-UNNAMED
```

----

### Changing the bundled JDK

The JDK that is bundled into the packaged game is configured in `lwjgl3/build.gradle.kts`:

- `jbrVersion` and `jbrUrl(...)` define the JBRSDK download that is used by default.
  To update it, pick a new release from the [JetBrainsRuntime releases](https://github.com/JetBrains/JetBrainsRuntime/releases)
  and adjust the version (e.g. `25.0.4.1-%s-b635.79`).
- To use another JDK, e.g. [Azul Zulu](https://www.azul.com/downloads/?package=jdk#zulu), set `jdkUrl` of each target
  in the `construo { targets { ... } }` block to the download URL of that JDK for the target platform.
  The commented Azul lines next to each target show an example.

