package Entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;

/* Class to create map objects, made Simpleton to avoid getting various instances of the same object by mistake.
* Not implemented yet*/
public class Objects {

    private TiledMap map;
    private static Objects INSTANCE = null;


    private Array<MapObject> objectArray = new Array<MapObject>();

    private Objects(TiledMap map) {
        this.map = map;
        }

    public static Objects getInstance(TiledMap map)
    {
        if (INSTANCE == null)
            INSTANCE = new Objects(map);

        return INSTANCE;
    }


    public void fillObjects() {
        for (MapObject object : map.getLayers().get("Objects").getObjects()) {
            objectArray.add(object);}
    }

    public Array<MapObject> getObjects() {
        fillObjects();
        return objectArray;
    }

}

