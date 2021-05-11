package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

    private Vector2 movement = new Vector2();
    private float speed = 60*2;

    public Player(Sprite sprite) {
        super(sprite);
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
        boolean collisionX = false;
        boolean collisionY = false;

        /*Check areas of the map towards where the character is moving, if movement.x < 0 the character is moving left
        if movement.x is < 0 the character is moving right, same for movemnt.y points towards top and bottom depending on the value.
        This avoids checking the whole map at the same time */

        if(movement.x < 0) {

            //check top left

          //  collisionX =.getCell(getX(), getY()+getHeight());




        }
        else if(movement.x > 0) {

        }

        if(movement.y < 0) {

        }
        else if(movement.y > 0) {

        }
    }

}
