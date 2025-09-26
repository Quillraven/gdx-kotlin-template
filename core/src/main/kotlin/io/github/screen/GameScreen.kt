package io.github.screen

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.Viewport
import io.github.GdxGame
import ktx.app.KtxScreen
import ktx.graphics.use
import ktx.log.logger
import ktx.scene2d.actors
import ktx.scene2d.defaultStyle
import ktx.scene2d.label
import ktx.scene2d.table

class GameScreen(
    game: GdxGame,
    private val gameViewport: Viewport = game.gameViewport,
    private val batch: Batch = game.batch,
    private val stage: Stage = game.stage,
) : KtxScreen {
    private val texture = Texture("logo.png")

    override fun show() {
        val skin = Skin().apply {
            add("default", Label.LabelStyle(BitmapFont(), Color.WHITE))
        }
        stage.actors {
            table(skin) {
                setFillParent(true)
                bottom()
                label("Have fun!", defaultStyle, skin) {
                    this.setAlignment(Align.center)
                    it.growX()
                }
            }
        }
    }

    override fun render(delta: Float) {
        gameViewport.apply()
        batch.use(gameViewport.camera) {
            it.draw(texture, 0f, 20f)
        }

        stage.viewport.apply()
        stage.act(delta)
        stage.draw()
    }

    override fun dispose() {
        texture.dispose()
        log.info { "GameScreen resources disposed" }
    }

    companion object {
        private val log = logger<GameScreen>()
    }
}