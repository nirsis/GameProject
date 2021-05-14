package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.CeltsvsVikings;

public class MainMenu implements Screen {

    private CeltsvsVikings game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch menuBackground;
    private Texture menuTexture;


    public MainMenu(CeltsvsVikings game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.position.set(400f, 600f, 0);
        menuBackground = new SpriteBatch(1);
        menuTexture = new Texture("backgrounds/");
        //viewport = new StretchViewport(400f, 600f, camera);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        menuBackground.begin();
        menuBackground.draw(menuTexture, 0,0);
        menuBackground.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        menuBackground.dispose();
    }
}
