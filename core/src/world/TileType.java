package world;

import java.util.HashMap;

public enum TileType {

    STONE(1, true, "Stone"),
    SKY(2, false, "Sky"),
    Water(3, false, "Water"),
    QUARTZ(4, true, "Quartz"),
    LAVA(5, false, "Lava"),
    Grass(6, true, "Grass");

    int id;
    boolean collidable;
    String name;
    float damage;

    public static final int TILE_SIZE = 16;

    TileType(int id, boolean collidable, String name){
        this(id, collidable, name, 0);
    }

    TileType(int id, boolean collidable, String name, float damage){
        this.id = id;
        this.collidable = collidable;
        this.name = name;
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    private static HashMap<Integer, TileType> tileMap;

    static{
        tileMap = new HashMap<Integer, TileType>();
        for(TileType tileType : TileType.values()){
            tileMap.put(tileType.getId(), tileType);
        }
    }

    public static TileType getTileTypeById (int id){
        return tileMap.get(id);
    }
}
