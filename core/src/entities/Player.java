package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Player extends Entity{

    private static final float xMaxSpeed = 220;
    private static final float friction = 6; // must be even
    private static final float airResistance = 2; // must be even
    private static final double jumpPower = 15;

    Texture image;

    public Player(float x, float y, GameMap map) {
        super(x, y, EntityType.PLAYER, map);
        image = new Texture("player.png");
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }

    @Override
    public void update(float deltaTime, float gravity) {
        xAccel = 14;
        if (Gdx.input.isKeyPressed(Keys.W) && grounded){
            this.yVel += jumpPower * getWeight();
        }
        else if (Gdx.input.isKeyPressed(Keys.W) && !grounded && this.yVel > 0){
            this.yVel += jumpPower * getWeight() * deltaTime;
        }

        if (Gdx.input.isKeyPressed(Keys.A)){
            if(Math.abs(xVel) < xMaxSpeed) {
                xVel -= xAccel;
            }
        }
        else if (Gdx.input.isKeyPressed(Keys.D)){
            if(Math.abs(xVel) < xMaxSpeed) {
                xVel += xAccel;
            }
        }
        else {
            xVel *= 0.9;
        }

        super.update(deltaTime, gravity);
        moveX(xVel * deltaTime);

        if(xVel > 0) {
            if(grounded) {
                xVel -= friction;
            }
            xVel -= airResistance;
        }
        else if(xVel < -0) {
            if(grounded) {
                xVel += friction;
            }
            xVel += airResistance;
        }
    }
}
