package entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import world.GameMap;

public abstract class Entity {
    protected Vector2 pos;
    protected EntityType type;
    protected float yVel;
    protected float xVel;
    protected GameMap map;
    protected boolean grounded = false;

    public Entity(float x, float y, EntityType type, GameMap map, boolean grounded) {
        this.pos = new Vector2(x, y);
        this.type = type;
        this.map = map;
        this.grounded = grounded;
    }

    public void update(float deltaTime, float gravity){

    }

    protected void moveX(float shift){

    }

    public abstract void render(SpriteBatch batch);

    public Vector2 getPos() {
        return pos;
    }

    public EntityType getType() {
        return type;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public int getWidth(){
        return type.getWidth();
    }

    public int getHeight(){
        return type.getHeight();
    }

    public float getWeight(){
        return type.getWeight();
    }
}
