package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
<<<<<<< Updated upstream
=======

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
>>>>>>> Stashed changes
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import Entities.NewPlayer;



<<<<<<< Updated upstream
    private String hello = "Hello";
=======
public class Gameplay implements Screen {


>>>>>>> Stashed changes
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private ShapeRenderer sr;
    private OrthographicCamera camera;
<<<<<<< Updated upstream
    private Player player;
=======
    private NewPlayer newPlayer;

>>>>>>> Stashed changes

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
<<<<<<< Updated upstream
        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
=======

        //Make the camera follow the position of the player, keeps the player in the middle of the screen
        camera.position.set(newPlayer.getX()+newPlayer.getWidth()/2, newPlayer.getY()+newPlayer.getHeight()/2, 0);
        camera.update();
        renderer.setView(camera);



           // Render the object layer that has the rectangles for collision
        for(MapObject object : map.getLayers().get("Objects").getObjects()) {
            if(object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.rect(rect.x, rect.y, rect.width, rect.height);
            sr.end();}}

        sr.setProjectionMatrix(camera.combined);

        renderer.getBatch().begin();
        //Render First layer of the map
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Map"));
        //Render player
        newPlayer.draw(renderer.getBatch());
        //Render Second layer of the map
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Static"));
        renderer.getBatch().end();

>>>>>>> Stashed changes

    }

    @Override
    public void resize(int width, int height) {

        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();

    }

    public TiledMap getMap() {
        return map;
    }

    @Override
    public void show() {

        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/themap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
<<<<<<< Updated upstream
        camera = new OrthographicCamera();
        player = new Player(new Sprite(new Texture("players/Swordsman.png")),(TiledMapTileLayer)map.getLayers().get(0));
        player.setPosition(15*player.getStaticLayer().getTileWidth(), 15 * player.getStaticLayer().getTileHeight());
=======
        sr = new ShapeRenderer();
        camera = new OrthographicCamera();
        newPlayer = new NewPlayer(new Sprite(new Texture("players/Swordsman.png")),map);
        Gdx.input.setInputProcessor(newPlayer);
        newPlayer.setPositionS(1500, 15);
>>>>>>> Stashed changes
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
        map.dispose();
        renderer.dispose();
        newPlayer.getTexture().dispose();
    }
}
