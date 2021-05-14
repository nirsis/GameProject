package Entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import Scenes.Gameplay;

public class NewPlayer implements InputProcessor {



    private Vector2 movement = new Vector2();
    private float speed = 500;
    private Sprite sprite;
    private Gameplay gp = new Gameplay();
    private TiledMap map;
    private float x;
    private float y;
    private float width;
    private float height;
    private boolean col;



    public NewPlayer(Sprite sprite, TiledMap map) {
        this.sprite = sprite;
        this.map = map;

    }

    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        sprite.draw(batch);
    }

    public boolean outOfBounds() {
        boolean toReturn = false;
        if((sprite.getX()) < 0 || (sprite.getX()) > 1920 || (sprite.getY()) < 0 || (sprite.getY()) > 1920) {
            toReturn = true;

        }
        return toReturn;
    }
    public boolean isBlocked () {

        boolean toReturn = false;

        for (MapObject objects : map.getLayers().get("Objects").getObjects()) {
            if (objects instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) objects).getRectangle();
                if (Intersector.overlaps(rect, sprite.getBoundingRectangle())) {
                    toReturn = true;

                }}}


        return toReturn;
    }

    public void checkCollision(float oldX, float oldY) {
        col = false;

        if(outOfBounds() || isBlocked()) {
            col = true;

        }

        // If there is a collision, set the position back to the old position and stop movement
        if(col == true) {

            if(movement.x < 0) {
                sprite.setX(oldX+5);
            }else if(movement.x > 0) {
                sprite.setX(oldX-5);
            }else if(movement.y < 0) {
                sprite.setY(oldY+5);
            }else if(movement.y > 0) {
                sprite.setY(oldY-5);
            }

            movement.x = 0;
            movement.y = 0;

        }
    }




    public void update(float delta) {
        boolean col = false;
        //character movement
        sprite.setX(sprite.getX()+movement.x * delta);
        sprite.setY(sprite.getY()+movement.y * delta);

        //return the character to its previous position in case of collision
        float oldX = sprite.getX();
        float oldY = sprite.getY();
        //collision.checkCollision(oldX, oldY, col);
        checkCollision(oldX, oldY);
    }


    public float getX() {
        this.x = sprite.getX();
        return this.x;
    }

    public float getY() {
        this.y = sprite.getY();
        return this.y;
    }

    public float getWidth() {
        this.width = sprite.getWidth();
        return this.width;
    }

    public float getHeight() {
        this.height = sprite.getHeight();
        return this.height;
    }
    public void setPositionS (float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Texture getTexture() {
        return sprite.getTexture();
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2 getMovement() {
        return movement;
    }

    public Rectangle getBoundingRectangle() {
        Rectangle rect = sprite.getBoundingRectangle();
        return rect;
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

        return true;
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

        return true;
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
