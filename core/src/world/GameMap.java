package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.Enemy1;
import entities.Entity;
import entities.Player;

import java.util.ArrayList;

public abstract class GameMap {

    public ArrayList<Entity> entities;

    public GameMap(){
        entities = new ArrayList<Entity>(   );
        entities.add(new Player(40f, 100f, this));
        entities.add(new Enemy1(100f, 100f, this));
    }

    public void render(OrthographicCamera camera, SpriteBatch batch){
        for(Entity entity : entities){
            entity.render(batch);
        }
    }

    public void update(float delta){
        for(Entity entity : entities){
            entity.update(delta, -9.8f);
        }
    }

    public abstract void dispose();

    public TileType tileByLocation(int layer, float x, float y){
        return this.tileByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
    }

    public abstract TileType tileByCoordinate(int layer, int col, int row);

    public boolean collideWithMap(float x, float y, int width, int height){
        if (x < 0 || y < 0 || x + width > getPixelWidth() || y + height > getPixelHeight()){
            return true;
        }

        for (int row = (int) (y/TileType.TILE_SIZE); row < Math.ceil((y+height)/TileType.TILE_SIZE); row++){
            for (int col = (int) (x/TileType.TILE_SIZE); col < Math.ceil((x+width)/TileType.TILE_SIZE); col++){
                for (int layer = 0; layer < getLayers(); layer++){
                    TileType type = tileByCoordinate(layer, col, row);
                    if (type != null){
                        if (type.isCollidable()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

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
