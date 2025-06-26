package io.github

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import io.github.screen.GameScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.log.logger

class GdxGame : KtxGame<KtxScreen>() {
    val batch: Batch by lazy { SpriteBatch() }
    val gameViewport: Viewport = FitViewport(800f, 600f)
    val uiViewport: Viewport = ScreenViewport()
    val stage: Stage by lazy { Stage(uiViewport, batch) }

    override fun create() {
        log.info { "Starting game" }
        addScreen(GameScreen(this))
        setScreen<GameScreen>()
    }

    override fun resize(width: Int, height: Int) {
        gameViewport.update(width, height, true)
        uiViewport.update(width, height, true)
        super.resize(width, height)
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()
        stage.dispose()
        log.info { "GdxGame resources disposed" }
    }

    companion object {
        private val log = logger<GdxGame>()
    }

}