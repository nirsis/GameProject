package Mechanics;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import Entities.NewPlayer;

public class Collision {
    /*We are trying to move the collision detection away from the NewPlayer class in order to modularize the program
     * This class is not implemented anywhere yet*/

    private NewPlayer player;
    private TiledMap map;

    public Collision(NewPlayer player, TiledMap map) {
        this.player = player;
        this.map = map;

    }
    public boolean outOfBounds() {
        boolean toReturn = false;
        if((player.getX()-player.getWidth()/2) < 0 || (player.getX()+player.getWidth()) > 1920 || (player.getY()-player.getHeight()/2) < 0 || (player.getY()+player.getHeight()) > 1920) {
            toReturn = true;
        }
        return toReturn;
    }

    public boolean isBlocked () {

        boolean toReturn = false;

        for (MapObject objects : map.getLayers().get("Objects").getObjects()) {
            if (objects instanceof RectangleMapObject) {

                Rectangle rect = ((RectangleMapObject) objects).getRectangle();
                if (Intersector.overlaps(rect, player.getBoundingRectangle())) {
                    toReturn = true;
                }}}


        return toReturn;
    }

    public void checkCollision(float oldX, float oldY, boolean collision) {
        collision = false;

        if(outOfBounds() || isBlocked()) {
            collision = true;
        }

        // If there is a collision, set the position back to the old position and stop movement
        if(collision == true) {
            player.setX(oldX);
            player.setY(oldY);
            player.getMovement().x = 0;
            player.getMovement().y = 0;
        }
    }


}
