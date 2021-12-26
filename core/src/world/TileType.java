package world;

import java.util.HashMap;

public enum TileType {

    STONE(1, true, "Stone", false),
    SKY(2, false, "Sky", false),
    Water(3, false, "Water", true),
    Grass(4, true, "Grass", false);

    int id;
    boolean collidable;
    String name;
    float damage;
    boolean gravity;

    public static final int TILE_SIZE = 16;

    TileType(int id, boolean collidable, String name, boolean gravity){
        this(id, collidable, name, 0, gravity);
    }

    TileType(int id, boolean collidable, String name, float damage, boolean gravity){
        this.id = id;
        this.collidable = collidable;
        this.name = name;
        this.damage = damage;
        this.gravity = gravity;
    }

    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }
    
    public boolean isGravity(){return gravity;}

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
