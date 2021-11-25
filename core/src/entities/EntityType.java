package entities;

import java.util.HashMap;

public enum EntityType {
    PLAYER("player", 14, 32, 40);

    private String id;
    private int width, height;
    private float weight;

    EntityType(String id, int width, int height, float weight) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }


    private static HashMap<String, EntityType> entityTypes;

    static {
        entityTypes = new HashMap<String, EntityType>();
        for (EntityType type : EntityType.values())
            entityTypes.put(type.id, type);
    }
}
