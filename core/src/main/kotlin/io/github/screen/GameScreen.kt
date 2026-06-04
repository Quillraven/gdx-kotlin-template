package io.github.screen

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.Viewport
import io.github.GdxGame
import ktx.actors.plusAssign
import ktx.actors.repeatForever
import ktx.actors.then
import ktx.app.KtxScreen
import ktx.graphics.use
import ktx.log.logger

class GameScreen(
    game: GdxGame,
    private val gameViewport: Viewport = game.gameViewport,
    private val batch: Batch = game.batch,
    private val shapeRenderer: ShapeRenderer = game.shapeRenderer,
    private val stage: Stage = game.stage,
) : KtxScreen {
    private val texture = Texture("logo.png")
    private val skin = Skin().apply {
        add("defaultFont", BitmapFont())
        add("default", Label.LabelStyle(getFont("defaultFont"), Color.WHITE))
    }

    override fun show() {
        stage.addActor(
            Table(skin).apply {
                setFillParent(true)
                bottom()
                add(Label("Have fun!", skin).apply {
                    setAlignment(Align.center)

                    val action = (moveBy(-100f, 0f, 2f) then moveBy(100f, 0f, 2f)).repeatForever()
                    this += action
                }).growX().padBottom(20f)
            }
        )
    }

    override fun render(delta: Float) {
        gameViewport.apply()
        batch.use(gameViewport.camera) {
            it.draw(texture, 2f, 1f, 12f, 2f)
        }

        val x = 5.5f
        val y = 4f
        shapeRenderer.use(ShapeType.Filled, gameViewport.camera) {
            it.color = Color.PURPLE
            it.rect(x + 0.25f, y - 0.25f, 4f, 4f)
            it.color = Color.PINK
            it.rect(x, y, 4f, 4f)
            it.color = Color.CYAN
            it.rect(x + 1f, y + 1f, 2f, 2f)
            it.color = Color.WHITE
            it.rect(x + 2f, y + 2f, 1f, 1f)
        }

        stage.viewport.apply()
        stage.act(delta)
        stage.draw()
    }

    override fun dispose() {
        texture.dispose()
        skin.dispose()
        log.info { "GameScreen resources disposed" }
    }

    companion object {
        private val log = logger<GameScreen>()
    }
}