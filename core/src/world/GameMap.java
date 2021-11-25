package world;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class GameMap {
    public abstract void render(OrthographicCamera camera);
    public abstract void update(float delta);
    public abstract void dispose();

    public TileType tileByLocation(int layer, float x, float y){
        return this.tileByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
    }

    public abstract TileType tileByCoordinate(int layer, int col, int row);

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();

}
