package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import Scenes.Gameplay;

public class Player extends Sprite implements InputProcessor {

    private Vector2 movement = new Vector2();
    private float speed = 500;
    private TiledMapTileLayer staticLayer;
    private Gameplay gp = new Gameplay();
    private TiledMap map;



    public Player(Sprite sprite, TiledMapTileLayer staticLayer) {

    super(sprite);
    this.staticLayer = staticLayer;

  }


    public void draw(SpriteBatch spriteBatch) {

    update(Gdx.graphics.getDeltaTime());
    super.draw(spriteBatch);

    }


   @Override
   public void draw(Batch batch) {

        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
}
/*
     Code from  Java Tiled Map Game (LibGDX) | Episode 4 update - better collision detection implementation
     Source: https://www.youtube.com/watch?v=TLZbC9brH1c&list=PLXY8okVWvwZ0qmqSBhOtqYRjzWtUCWylb&index=5&ab_channel=dermetfan
 */

public boolean isBlocked (float x, float y) {

    boolean toReturn = false;
    TiledMapTileLayer collisionObjectLayer = (TiledMapTileLayer)map.getLayers().get("ObjectLayer");
    MapObjects objects = collisionObjectLayer.getObjects();
    for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {

        Rectangle rectangle = rectangleObject.getRectangle();
        if (Intersector.overlaps(rectangle, getBoundingRectangle())) {
            toReturn = true;

        }
    }
//    TiledMapTileLayer.Cell cell = staticLayer.getCell((int)(x / staticLayer.getTileWidth()), (int) (y / staticLayer.getTileHeight()));
//    System.out.println();
//  return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("Blocked");
    return toReturn;
}

public boolean collidesRight() {
    boolean collision = false;

    for(float i = 0; i< getHeight(); i += staticLayer.getTileHeight()/2) {

        if(collision = isBlocked(getX()+getWidth(), getY()+ i)) {
            break;
        }

    }

    return collision;

}
public boolean collidesLeft() {
    boolean collision = false;

    for(float i = 0; i< getHeight(); i += staticLayer.getTileHeight()/2) {

        if(collision = isBlocked(getX(), getY()+ i)) {
            break;
        }

    }

    return collision;

}
public boolean collidesTop() {
    boolean collision = false;

    for(float i = 0; i< getWidth(); i += staticLayer.getTileWidth()/2) {

        if(collision = isBlocked(getX()+ i, getY()+getHeight())) {
            break;
        }

    }

    return collision;

}
public boolean collidesBottom() {
    boolean collision = false;

    for(float i = 0; i< getWidth(); i += staticLayer.getTileWidth()/2) {

        if(collision = isBlocked(getX()+ i, getY())) {
            break;
        }

    }

    return collision;

}
    public void update(float delta) {

        setX(getX()+movement.x * delta);
        setY(getY()+movement.y * delta);

        //collision detection
        float oldX = getX();
        float oldY = getY();

        //Booleans to check if an object is colliding in either axis
        boolean colX = false;
        boolean colY = false;

        // We need each map tile width and height to check collisions
        float tileW = staticLayer.getTileWidth();
        float tileH = staticLayer.getTileHeight();

        /*Check areas of the map towards where the character is moving, if movement.x < 0 the character is moving left
        if movement.x is < 0 the character is moving right, same for movement.y points towards top and bottom depending on the value.
        This avoids checking the whole map at the same time.
        The map tile was created using 2 different layers of tiles, one layer called Map that contains the floor tiles, and a second one called Static
        that contains all the objects that can not be walked through, such as walls, rocks, trees, gates, etc.
        The static layer contains a property (String called Blocked), and in the collision detection method is checked if the property exists in the tile.
        If colX/Y is true, further movement will be denied in that direction*/

        if(movement.x < 0) {
            //Check left side
            colX = collidesLeft();
        }else if(movement.x > 0) {
            //Check right side
            colX = collidesRight();
        }

        // If there is a collision, set the position back to the old position and stop movement
        if(colX) {
            setX(oldX);
            movement.x = 0;
        }


        if(movement.y < 0) {
            //Check bottom side
            colY = collidesBottom();
        }
        else if(movement.y > 0) {
            //Check top side
            colY = collidesTop();
        }

        // If there is a collision, set the position back to the old position and stop movement
        if(colY == true) {
            setY(oldY);
            movement.y = 0;
        }
    }
    public Vector2 getMovement() {
        return movement;
    }

    public void setMovement(Vector2 movement) {
        this.movement = movement;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public TiledMapTileLayer getStaticLayer() {
        return staticLayer;
    }

    public void setStaticLayer(TiledMapTileLayer staticLayer) {
        this.staticLayer = staticLayer;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            movement.y = speed;

        }else if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            movement.x = -speed;

        }else if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT){
            movement.x = speed;

        }else if(keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            movement.y = -speed;

        }else if(keycode == Input.Keys.SPACE) {

        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            movement.y = 0;

        }else if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            movement.x = 0;

        }else if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT){
            movement.x = 0;

        }else if(keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            movement.y = 0;

        }else if(keycode == Input.Keys.SPACE) {

        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
