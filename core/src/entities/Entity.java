package entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import world.GameMap;

public abstract class Entity {
    protected Vector2 pos;
    protected EntityType type;
    protected float yVel = 0;
    protected float xVel;
    private final double terminalVel = 0.1;
    private final double gravAccel = 0.01;
    protected float gravSpeed;
    protected GameMap map;
    protected boolean grounded = false;

    public Entity(float x, float y, EntityType type, GameMap map) {
        this.pos = new Vector2(x, y);
        this.type = type;
        this.map = map;
    }

    public void update(float deltaTime, float gravity){
        float newY = pos.y;

        if (yVel > 0 || yVel < terminalVel) {
            gravSpeed += gravity * 0.3 + gravAccel;
        }

        this.yVel += gravSpeed;
        newY += this.yVel * deltaTime * 0.3;

        if (map.collideWithMap(pos.x, newY, getWidth(), getHeight())){
            if (yVel < 0){
                this.pos.y = (float) Math.floor(pos.y);
                grounded = true;
            }
            this.yVel = 0;
            this.gravSpeed = 0;
        }
        else {
            this.pos.y = newY;
            grounded = false;
        }
    }

    protected void moveX(float shift){
        float newX = pos.x + shift;

        if (!map.collideWithMap(newX, pos.y, getWidth(), getHeight())){
            this.pos.x = newX;
        }
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
