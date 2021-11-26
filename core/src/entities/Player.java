package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Player extends Entity{

    private static float xSpeed, ySpeed;
    private static float xAccel = 5;
    private static float xMaxSpeed = 100;
    private static float friction = 3;
    private static float airResistance = 2;
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
        if (Gdx.input.isKeyPressed(Keys.W) && grounded){
            this.yVel += jumpPower * getWeight();
        }
        else if (Gdx.input.isKeyPressed(Keys.W) && !grounded && this.yVel > 0){
            this.yVel += jumpPower * getWeight() * deltaTime;
        }
        moveX(xSpeed*deltaTime);
        if(xSpeed > 0) {
            if(grounded) {
                xSpeed -= friction;
            }
            else    {
                xSpeed -= airResistance;
            }
        }
        if(xSpeed < 0) {
            if(grounded) {
                xSpeed += friction;
            }
            else    {
                xSpeed += airResistance;
            }
        }

        super.update(deltaTime, gravity);

        if (Gdx.input.isKeyPressed(Keys.A)){
            if(Math.abs(xSpeed) < xMaxSpeed) {
                xSpeed -= xAccel;
            }
            moveX(xSpeed * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Keys.D)){
            if(Math.abs(xSpeed) < xMaxSpeed) {
                xSpeed += xAccel;
            }
            moveX(xSpeed * deltaTime);
        }

    }
}
