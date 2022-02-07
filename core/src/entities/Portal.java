package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import world.GameMap;

public class Portal extends Entity{
    private float width = 14;
    private float height = 34;
    private Texture sprite;

    public Portal(float x, float y, GameMap map, Texture img) {
        super(x,y,EntityType.PORTAL,map);
        this.sprite = img;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(this.sprite, this.getPos().x, this.getPos().y);
    }
}
