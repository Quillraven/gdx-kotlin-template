package io.github

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.Texture.TextureWrap
import com.badlogic.gdx.tools.texturepacker.TexturePacker
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Resampling

fun main() {
    val inputFolder = "../assets/graphics"
    val outputFolder = "../assets/graphics"
    val packageFileName = "sprites"
    val settings = defaultSettings()

    TexturePacker.process(settings, inputFolder, outputFolder, packageFileName)
}

private fun defaultSettings(): TexturePacker.Settings = TexturePacker.Settings().apply {
    pot = true
    multipleOfFour = false
    paddingX = 2
    paddingY = 2
    edgePadding = true
    duplicatePadding = true
    bleed = true
    bleedIterations = 2
    rotation = false
    minWidth = 16
    minHeight = 16
    maxWidth = 2048
    maxHeight = 2048
    square = false
    stripWhitespaceX = false
    stripWhitespaceY = false
    alphaThreshold = 0
    filterMin = TextureFilter.Nearest
    filterMag = TextureFilter.Nearest
    wrapX = TextureWrap.ClampToEdge
    wrapY = TextureWrap.ClampToEdge
    format = Pixmap.Format.RGBA8888
    alias = false
    outputFormat = "png"
    jpegQuality = 0.9f
    ignoreBlankImages = true
    fast = false
    debug = false
    silent = false
    combineSubdirectories = true
    ignore = false
    flattenPaths = false
    premultiplyAlpha = false
    useIndexes = true
    limitMemory = true
    grid = false
    scale = floatArrayOf(1f)
    scaleSuffix = arrayOf("")
    scaleResampling = arrayOf(Resampling.nearest)
    atlasExtension = ".atlas"
    prettyPrint = true
    legacyOutput = true
}
