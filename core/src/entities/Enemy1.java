package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import world.GameMap;

public class Enemy1 extends Entity{
    private static final int frameCol = 24, frameRow = 1;

    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    Texture image;
    TextureRegion curFrame;
    float elapsedTime;

    public Enemy1(float x, float y, GameMap map) {
        super(x, y, EntityType.ENEMYONE, map);
        createAnimation();
        image = new Texture("enemy1.png");
        elapsedTime = 0;
    }

    private void createAnimation() {
        walkSheet = new Texture(Gdx.files.internal("enemysprite.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / frameCol,
                walkSheet.getHeight() / frameRow);

        TextureRegion[] walkFrames = new TextureRegion[frameCol * frameRow];
        int index = 0;
        for (int i = 0; i < frameRow; i++) {
            for (int j = 0; j < frameCol; j++) {
                walkFrames[index++] = tmp[0][0];
            }
        }
        walkAnimation = new Animation<>(1f / (float) frameCol, walkFrames);
        curFrame=walkAnimation.getKeyFrame(0);
        elapsedTime = 0f;
    }

    @Override
    public void render(SpriteBatch batch) {
            batch.draw(curFrame, pos.x, pos.y, getWidth(), getHeight());
    }

    @Override
    public void update(float deltaTime, float gravity) {


        super.update(deltaTime, gravity);
        moveX(xVel * deltaTime);


        elapsedTime += deltaTime;
            curFrame = walkAnimation.getKeyFrame(elapsedTime, true);
    }
}
