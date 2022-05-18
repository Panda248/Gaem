package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import world.GameMap;

import java.util.ArrayList;

public class Player extends Entity{

    private static final float xMaxSpeed = 220;
    private static final float friction = 6; // must be even
    private static final float airResistance = 2; // must be even
    private static final double jumpPower = 15;


    private static final int frameCol = 24, frameRow = 1;

    public enum State {FALLING, JUMPING, STANDING, RUNNING}
    public State curState;
    public State prevState;
    private Animation<TextureRegion> run;
    private Animation<TextureRegion> jump;
    private Portal[] portals;

    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    Texture image;
    TextureRegion curFrame;
    float elapsedTime;

    public Player(float x, float y, GameMap map) {
        super(x, y, EntityType.PLAYER, map);
        createAnimation();
        image = new Texture("player.png");
        curState = State.STANDING;
        prevState = State.STANDING;
        elapsedTime = 0;
        portals = new Portal[2];
        Array<TextureRegion> frames = new Array<TextureRegion>();
    }

    private void createAnimation() {
        walkSheet = new Texture(Gdx.files.internal("spritesheet.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / frameCol,
                walkSheet.getHeight() / frameRow);

        TextureRegion[] walkFrames = new TextureRegion[frameCol * frameRow];
        int index = 0;
        if (xVel == 0) {
            for (int i = 0; i < frameRow; i++) {
                for (int j = 0; j < frameCol; j++) {
                    walkFrames[index++] = tmp[0][0];
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
            if(portals[0] != null)  {
                portals[0].render(batch);
            }
            if(portals[1] != null)  {
                portals[1].render(batch);
            }
    }

    @Override
    public void update(float deltaTime, float gravity) {
        xAccel = 14;
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
        {
            shoot('l');
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT))
        {
            shoot('r');
        }
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
        teleport();
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

    public void shoot(char type)
    {
        if(type == 'l') {
            portals[0] = new Portal(Gdx.input.getX() - getWidth()/2, Gdx.input.getY() + getHeight()/2, this.map, new Texture((Gdx.files.internal("portal1.png"))));
            System.out.println(portals[0].getX() + ", " + portals[0].getY());
        }
        else if(type == 'r')
        {
            portals[1] = new Portal(Gdx.input.getX() - getWidth()/2, Gdx.input.getY() + getHeight()/2, this.map, new Texture((Gdx.files.internal("portal2.png"))));
        }

    }
    private void teleport()
    {
        if(portals[0] != null && portals[1] != null)
        {
            if((this.getX() + this.getWidth() > portals[0].getPos().x && this.getX() < portals[0].getWidth() + portals[0].getPos().x) &&
                    (this.getY() + this.getHeight() > portals[0].getPos().y && this.getY() < portals[0].getHeight() + portals[0].getPos().y)) {
                this.pos.x = portals[1].getX();
                this.pos.y = portals[1].getY();
            }
        }
    }
}
