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
    }

}
