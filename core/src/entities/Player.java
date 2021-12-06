package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import world.GameMap;

public class Player extends Entity{

    private static final float xMaxSpeed = 220;
    private static final float friction = 6; // must be even
    private static final float airResistance = 2; // must be even
    private static final double jumpPower = 15;


    private static final int frameCol = 24, frameRow = 1;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    Texture image;
    TextureRegion curFrame;
    float elapsedTime;

    public Player(float x, float y, GameMap map) {
        super(x, y, EntityType.PLAYER, map);
        createAnimation();
        image = new Texture("player.png");
    }

    private void createAnimation() {
        walkSheet = new Texture(Gdx.files.internal("spritesheet.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / frameCol,
                walkSheet.getHeight() / frameRow);

        TextureRegion[] walkFrames = new TextureRegion[frameCol * frameRow];
        int index = 0;
        if (Gdx.input.isKeyPressed(Keys.W)) {
            for (int i = 0; i < frameRow; i++) {
                for (int j = 0; j < frameCol; j++) {
                    walkFrames[index++] = tmp[i][j];
                }
            }
        }
        else{
            for (int i = 0; i < frameRow; i++) {
                for (int j = 0; j < frameCol; j++) {
                    walkFrames[index++] = tmp[i][j];
                }
            }
        }

        walkAnimation = new Animation<TextureRegion>(1f/(float)frameCol, walkFrames);
        curFrame=walkAnimation.getKeyFrame(0);
        elapsedTime = 0f;
    }

    @Override
    public void render(SpriteBatch batch) {
            batch.draw(curFrame, pos.x, pos.y, getWidth(), getHeight());
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
        elapsedTime += deltaTime;
            curFrame = walkAnimation.getKeyFrame(elapsedTime, true);
    }
}
