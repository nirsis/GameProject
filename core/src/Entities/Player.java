package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

    private Vector2 movement = new Vector2();
    private float speed = 60*2;
    private TiledMapTileLayer staticLayer;



    public Player(Sprite sprite, TiledMapTileLayer staticLayer) {

        super(sprite);
        this.staticLayer = staticLayer;

    }

    @Override
    public void draw(Batch batch) {

        super.draw(batch);
    }

    public void draw(SpriteBatch spriteBatch) {

        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);

    }

    public void update(float delta) {

        setX(getX()+movement.x);
        setY(getY()+movement.y);

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

            //check left tile above the object
            colX = staticLayer.getCell((int)(getX()/tileW), (int)((getY()+getHeight()) / tileH)).getTile().getProperties().containsKey("Blocked");

            //Only check for middle and bottom if the colX is still false
            if(!colX) {
                //check left tile at the object position
                colX = staticLayer.getCell((int) (getX() / tileW), (int) ((getY() + getHeight() / 2) / tileH)).getTile().getProperties().containsKey("Blocked");
            }

            if(!colX) {
                //check left tile under the object
                colX = staticLayer.getCell((int) (getX() / tileW), (int) (getY() / tileH)).getTile().getProperties().containsKey("Blocked");
            }
        }
        else if(movement.x > 0) {

            //check right tile above the object
            colX = staticLayer.getCell((int)((getX()+getWidth())/tileW), (int)((getY()+getHeight())/tileH)).getTile().getProperties().containsKey("Blocked");

            //Only check for middle and bottom if the colX is still false
            if(!colX) {

                //check right tile at the object position
                colX = staticLayer.getCell((int)((getX()+getWidth())/tileW), (int) ((getY() + getHeight() / 2) / tileH)).getTile().getProperties().containsKey("Blocked");
            }

            if(!colX) {

                //check right tile under the object
                colX = staticLayer.getCell((int)((getX()+getWidth())/tileW), (int) (getY() / tileH)).getTile().getProperties().containsKey("Blocked");
            }
        }
        if(colX == true) {
            setX(oldX);
            movement.x = 0;
        }


        if(movement.y < 0) {

            //start checking bottom left tile
            colY = staticLayer.getCell((int) (getX()/ tileW), (int) (getY()/tileH)).getTile().getProperties().containsKey("Blocked");

            //if still false, check straight under and then bottom right
            if(!colY) {
            colY = staticLayer.getCell((int) ((getX()+getWidth()/2)/tileW), (int) (getY()/tileH)).getTile().getProperties().containsKey("Blocked");
            }
            if(!colY) {
                colY = staticLayer.getCell((int) ((getX()+getWidth())/tileW), (int) (getY()/tileH)).getTile().getProperties().containsKey("Blocked");
            }
        }
        else if(movement.y > 0) {
        //start checking bottom left tile
        colY = staticLayer.getCell((int) (getX()/ tileW), (int) ((getY()+getHeight())/tileH)).getTile().getProperties().containsKey("Blocked");

        //if still false, check straight above and then top right
        if(!colY) {

        colY = staticLayer.getCell((int) ((getX()+getWidth()/2)/tileW), (int) ((getY()+getHeight())/tileH)).getTile().getProperties().containsKey("Blocked");

        }
        if(!colY) {

        colY = staticLayer.getCell((int) ((getX()+getWidth())/tileW), (int) ((getY()+getHeight())/tileH)).getTile().getProperties().containsKey("Blocked");

        }
        }

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
}
