package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameMap {
    public void render(OrthographicCamera camera, SpriteBatch batch){

    }
    public void update(float delta){

    }
    public abstract void dispose();

    public TileType tileByLocation(int layer, float x, float y){
        return this.tileByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
    }

    public abstract TileType tileByCoordinate(int layer, int col, int row);

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();

    public int getPixelWidth(){
        return this.getWidth()*TileType.TILE_SIZE;
    }

    public int getPixelHeight(){
        return this.getHeight()*TileType.TILE_SIZE;
    }

}
