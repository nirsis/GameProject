package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;


import Entities.Player;


public class Gameplay implements Screen {


    public TiledMap getMap() {
        return map;
    }

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Player player;

    public Gameplay() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(player.getX()+player.getWidth()/2, player.getY()+player.getHeight()/2, 0);
        camera.update();



        renderer.setView(camera);

        //Render First layer of the map
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Map"));
        //Render player
        renderer.getBatch().begin();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
        //Render Second layer of the map
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Static"));



        for(MapObject object : map.getLayers().get("ObjectLayer").getObjects()) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
            shapeRenderer.end();
        }




    }

    @Override
    public void resize(int width, int height) {

        camera.viewportWidth = width;
        camera.viewportHeight = height;


    }

    @Override
    public void show() {

        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/themap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        player = new Player(new Sprite(new Texture("players/Swordsman.png")),(TiledMapTileLayer)map.getLayers().get(0));
        player.setPosition(1500 , 15 );

        Gdx.input.setInputProcessor(player);
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
        player.getTexture().dispose();
    }
}
